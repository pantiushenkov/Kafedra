package lib.java.UI.ScientificWork;

import lib.java.DAO.PersistException;
import lib.java.Entities.ScientificWorkEntity;
import lib.java.Services.ScientificWorkService;
import lib.java.Utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.*;
import java.util.List;

public class ScientificWorkView {

    private String[] columns = {"Код Кафедри", "Назва", "Керівник", "Замовник", "Початок", "Кінець"};
    private ScientificWorkService scientificWorkService = new ScientificWorkService();
    List<ScientificWorkEntity> scientificWorkEntities;

    private JTextField search2;
    private JTextField search3;
    private JTextField search4;
    private JTextField search5;
    private JTextField search6;
    private JTextField search7;

    private int tableHeight = 700;
    private int tableWidth = tableHeight + 300;
    private int searchHeight = 50;
    private int searchStartY = 50;

    public ScientificWorkView() throws PersistException {
        JFrame frame = new JFrame();
        frame.setTitle("Наукова тема");
        final JTable table = new JTable();

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

        scientificWorkEntities = scientificWorkService.getDao().getAll();
        fillTable(scientificWorkEntities, model);

        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", 1, 14);
        table.setFont(font);
        table.setRowHeight(19);

        JTextField[] searches = new JTextField[]{
                search2 = new JTextField(),
                search3 = new JTextField(),
                search4 = new JTextField(),
                search5 = new JTextField(),
                search6 = new JTextField(),
                search7 = new JTextField()
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

        frame.add(search2);
        frame.add(search3);
        frame.add(search4);
        frame.add(search5);
        frame.add(search6);
        frame.add(search7);

        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);
        frame.add(btnSearch);
        frame.add(btnClearSearch);

        frame.setSize(tableWidth + 100, tableHeight + searchHeight + 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        btnAdd.addActionListener(e -> new AddScientificWorkView(model, scientificWorkService));

        btnDelete.addActionListener(e -> {
            try {
                int row = table.getSelectedRow();
                scientificWorkService.getDao().delete(getEntity(row, table));
                model.removeRow(row);
            } catch (Exception ex) {
                ex.printStackTrace();
                Utils.showError(ex);
            }
        });

        btnClearSearch.addActionListener(e -> {
            try {
                model.setRowCount(0);
                setSearchFields();
                fillTable(scientificWorkService.getDao().getAll(), model);
            } catch (PersistException e1) {
                Utils.showError(e1);
            }
        });

        btnSearch.addActionListener(e -> {
            try {
                model.setRowCount(0);
                fillTable(scientificWorkService.getDao().search(getSearchFields()), model);
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
                    scientificWorkService.getDao().update(getEntity(row, table));
                } catch (Exception ek) {
                    table.setEditingRow(row);
                    Utils.showError(ek);
                }
            }
            changedRows.clear();
        });
    }

    private void fillTable(List<ScientificWorkEntity> departments, DefaultTableModel model) {
        for (ScientificWorkEntity department : departments) {
            model.addRow(department.getRow());
        }
    }

    private void setSearchFields() {
        search2.setText("");
        search3.setText("");
        search4.setText("");
        search5.setText("");
        search6.setText("");
        search7.setText("");
    }

    private ScientificWorkEntity getSearchFields() throws ParseException {
        return ScientificWorkRowParser.createObject(search2, search3, search5, search6, search7);
    }

    private ScientificWorkEntity getEntity(int row, JTable table) throws ParseException {
        Date start = table.getValueAt(row, 3) == null ? null : ScientificWorkRowParser.getDate(table.getValueAt(row, 3).toString());
        Date end = table.getValueAt(row, 4) == null ? null : ScientificWorkRowParser.getDate(table.getValueAt(row, 4).toString());

        return new ScientificWorkEntity(
                scientificWorkEntities.get(row).getKey(),
                table.getValueAt(row, 0).toString(),
                table.getValueAt(row, 1).toString(),
                table.getValueAt(row, 2).toString(),
                start,
                end
        );
    }
}
