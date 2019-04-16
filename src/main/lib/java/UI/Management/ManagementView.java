package lib.java.UI.Management;

import lib.java.DAO.PersistException;
import lib.java.Entities.ManagementEntity;
import lib.java.Services.ManagementService;
import lib.java.Utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManagementView {

    private String[] columns = {"Код викладача", "Код КР", "Початок КР", "Кінець КР"};
    private ManagementService managementService = new ManagementService();

    private JTextField search1;
    private JTextField search2;
    private JTextField search3;
    private JTextField search4;

    private int tableHeight = 700;
    private int tableWidth = tableHeight + 400;
    private int searchHeight = 50;
    private int searchStartY = 50;

    public ManagementView() throws PersistException {
        JFrame frame = new JFrame();
        frame.setTitle("Керівна робота");
        final JTable table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1;
            }
        };

        Set<Integer> changedRows = new HashSet<>();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    changedRows.add(table.getSelectedRow());
                } catch (Exception ev) {
                    Utils.showError(ev);
                }
            }
        });

        final DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columns);

        List<ManagementEntity> managementEntities = managementService.getDao().getAll();
        fillTable(managementEntities, model);

        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", 1, 14);
        table.setFont(font);
        table.setRowHeight(19);

        JTextField[] searches = new JTextField[]{
                search1 = new JTextField(),
                search2 = new JTextField(),
                search3 = new JTextField(),
                search4 = new JTextField(),
        };

        for (int i = 0; i < searches.length; i++) {
            searches[i].setBounds((i * tableWidth) / columns.length, searchStartY, tableWidth / columns.length, searchHeight / 2);
        }

        setSearchFields();

        JButton btnAdd = new JButton("Додати");
        JButton btnDelete = new JButton("Видалити");
        JButton btnUpdate = new JButton("Змінити");
        JButton btnSearch = new JButton("Пошук");
        JButton btnClearSearch = new JButton("Скинути");

        btnAdd.setBounds(25, searchStartY + tableHeight + 50, 100, 25);
        btnUpdate.setBounds(150, searchStartY + tableHeight + 50, 100, 25);
        btnDelete.setBounds(275, searchStartY + tableHeight + 50, 100, 25);
        btnSearch.setBounds(0, searchStartY - 25, 100, 25);
        btnClearSearch.setBounds(150, searchStartY - 25, 100, 25);

        final JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, searchStartY + searchHeight, tableWidth, tableHeight);

        frame.setLayout(null);

        frame.add(pane);

        frame.add(search1);
        frame.add(search2);
        frame.add(search3);
        frame.add(search4);

        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);
        frame.add(btnSearch);
        frame.add(btnClearSearch);

        frame.setSize(tableWidth + 100, tableHeight + searchHeight + 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        btnAdd.addActionListener(e -> new AddManagementView(model, managementService));

        btnDelete.addActionListener(e -> {
            try {
                int row = table.getSelectedRow();
                managementService.getDao().delete(getEntity(row, table));
                model.removeRow(row);
            } catch (Exception ex) {
                Utils.showError(ex);
            }
        });

        btnClearSearch.addActionListener(e -> {
            try {
                model.setRowCount(0);
                setSearchFields();
                fillTable(managementService.getDao().getAll(), model);
            } catch (PersistException e1) {
                Utils.showError(e1);
            }
        });

        btnSearch.addActionListener(e -> {
            try {
                model.setRowCount(0);
                fillTable(managementService.getDao().search(getSearchFields()), model);
            } catch (PersistException e1) {
                Utils.showError(e1);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        });

        btnUpdate.addActionListener(e -> {
            if (table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }
            for (int row : changedRows) {
                try {
                    managementService.getDao().update(getEntity(row, table));
                } catch (Exception ek) {
                    table.setEditingRow(row);
                    Utils.showError(ek);
                }
            }
            changedRows.clear();
        });
    }

    private void fillTable(List<ManagementEntity> managementEntities, DefaultTableModel model) {
        for (ManagementEntity managementEntity : managementEntities) {
            model.addRow(managementEntity.getRow());
        }
    }

    private void setSearchFields() {
        search1.setText("");
        search2.setText("");
        search3.setText("");
        search4.setText("");
    }

    private ManagementEntity getSearchFields() throws ParseException {
        return ManagementRowParser.createObject(search1, search2, search3, search4);
    }

    private ManagementEntity getEntity(int row, JTable table) throws ParseException {
        Date start = table.getValueAt(row, 2) == null ? null : ManagementRowParser.getDate(table.getValueAt(row, 2).toString());
        Date end = table.getValueAt(row, 3) == null ? null : ManagementRowParser.getDate(table.getValueAt(row, 3).toString());

        return new ManagementEntity(
                table.getValueAt(row, 0).toString(),
                table.getValueAt(row, 1).toString(),
                start,
                end
        );
    }
}
