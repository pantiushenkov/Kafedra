package lib.java.UI.Department;

import lib.java.DAO.PersistException;
import lib.java.Entities.DepartmentEntity;
import lib.java.Services.DepartmentService;
import lib.java.Utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class DepartmentView {
    private String[] columns = {"Код", "Назва", "Телефон"};
    private DepartmentService departmentService = new DepartmentService();

    private JTextField search1;
    private JTextField search2;
    private JTextField search3;

    private int tableHeight = 700;
    private int tableWidth = tableHeight;
    private int searchHeight = 50;
    private int searchStartY = 50;

    public DepartmentView() throws PersistException {
        JFrame frame = new JFrame();
        frame.setTitle("Кафедра");
        final JTable table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return column != 0;
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

        List<DepartmentEntity> departments = departmentService.getDao().getAll();
        fillTable(departments, model);

        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", 1, 14);
        table.setFont(font);
        table.setRowHeight(19);

        search1 = new JTextField();
        search2 = new JTextField();
        search3 = new JTextField();

        setSearchFields();

        JButton btnAdd = new JButton("Додати");
        JButton btnDelete = new JButton("Видалити");
        JButton btnUpdate = new JButton("Змінити");
        JButton btnSearch = new JButton("Пошук");
        JButton btnClearSearch = new JButton("Скинути");

        search1.setBounds(0, searchStartY, tableWidth / columns.length, searchHeight / 2);
        search2.setBounds(tableWidth / columns.length, searchStartY, tableWidth / columns.length, searchHeight / 2);
        search3.setBounds(2 * tableWidth / columns.length, searchStartY, tableWidth / columns.length, searchHeight / 2);

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
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);
        frame.add(btnSearch);
        frame.add(btnClearSearch);

        frame.setSize(tableWidth + 100, tableHeight + searchHeight + 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        btnAdd.addActionListener(e -> new AddDepartmentView(model, departmentService));

        btnDelete.addActionListener(e -> {
            try {
                int row = table.getSelectedRow();
                departmentService.getDao().delete(getEntity(row, table));
                model.removeRow(row);
            } catch (Exception ex) {
                Utils.showError(ex);
            }
        });

        btnClearSearch.addActionListener(e -> {
            try {
                model.setRowCount(0);
                setSearchFields();
                fillTable(departmentService.getDao().getAll(), model);
            } catch (PersistException e1) {
                Utils.showError(e1);
            }
        });

        btnSearch.addActionListener(e -> {
            try {
                model.setRowCount(0);
                fillTable(departmentService.getDao().search(getSearchFields()), model);
            } catch (PersistException e1) {
                Utils.showError(e1);
            }
        });

        btnUpdate.addActionListener(e -> {
            if (table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }
            for (int row : changedRows) {
                try {
                    departmentService.getDao().update(getEntity(row, table));
                } catch (Exception ek) {
                    table.setEditingRow(row);
                    Utils.showError(ek);
                }
            }
            changedRows.clear();
        });
    }

    private void fillTable(List<DepartmentEntity> departments, DefaultTableModel model) {
        for (DepartmentEntity department : departments) {
            model.addRow(department.getRow());
        }
    }

    private void setSearchFields() {
        search1.setText("");
        search2.setText("");
        search3.setText("");
    }

    private DepartmentEntity getSearchFields() {
        return DepartmentRowParser.createObject(search1, search2, search3);
    }

    private DepartmentEntity getEntity(int row, JTable table) {
        Integer key;
        try {
            key = Integer.parseInt(table.getValueAt(row, 0).toString());
        } catch (Exception e) {
            key = null;
        }
        String name = table.getValueAt(row, 1).toString();
        String phone = table.getValueAt(row, 2).toString();
        return new DepartmentEntity(key, name, phone);
    }
}
