package lib.java.UI.Department;

import lib.java.Entities.DepartmentEntity;
import lib.java.Services.DepartmentService;

import lib.java.UI.CComponent.CButton;
import lib.java.UI.CComponent.CLabel;
import lib.java.UI.CComponent.CTextField;
import lib.java.Utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AddDepartmentView extends JFrame {

    private DepartmentService departmentService;
    private JButton apply;
    private JButton cancel;private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    private JTextField field1;
    private JTextField field2;
    private JTextField field3;

    private DefaultTableModel model;

    public AddDepartmentView(DefaultTableModel model, DepartmentService departmentService) {
        this.model = model;
        this.departmentService = departmentService;
        initUi();
    }

    private void initUi() {
        this.setTitle("Додати Кафедру");
        this.setSize(500, 400);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.setBackground(new Color(192, 192, 192));

        label1 = CLabel.create("Код: ", new int[]{80, 100, 121, 32});
        label2 = CLabel.create("Назва: ", new int[]{80, 150, 122, 25});
        label3 = CLabel.create("Телефон: ", new int[]{80, 200, 105, 33});

        field1 = CTextField.create(new int[]{240, 100, 150, 35});
        field2 = CTextField.create(new int[]{240, 150, 150, 35});
        field3 = CTextField.create(new int[]{240, 200, 150, 35});

        cancel = CButton.create("Відмінити", new int[]{61, 299, 150, 35});
        cancel.addActionListener(e -> dispose());

        apply = CButton.create("ОК", new int[]{277, 299, 150, 35});
        apply.addActionListener(e -> {
            try {
                DepartmentEntity departmentEntity = DepartmentRowParser.createObject(field2, field3);
                departmentService.getDao().persist(departmentEntity);
                model.addRow(departmentEntity.getRow());
                dispose();
            } catch (Exception ex) {
                Utils.showError(ex);
            }
        });

        contentPane.add(apply);
        contentPane.add(cancel);

        contentPane.add(field2);
        contentPane.add(field3);

        contentPane.add(label2);
        contentPane.add(label3);

        this.add(contentPane);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
}




















