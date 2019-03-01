package lib.java.UI.Teacher;

import lib.java.DAO.PersistException;
import lib.java.Services.TeacherService;
import lib.java.Entities.TeacherEntity;
import lib.java.UI.CComponent.CButton;
import lib.java.UI.CComponent.CLabel;
import lib.java.UI.CComponent.CTextField;
import lib.java.Utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;

public class AddTeacherView extends JFrame {

    private TeacherService scientificWorkService;
    private JButton apply;
    private JButton cancel;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;

    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;
    private JTextField field5;
    private JTextField field6;
    private JTextField field7;
    private JTextField field8;

    private DefaultTableModel model;

    public AddTeacherView(DefaultTableModel model, TeacherService scientificWorkService) {
        this.model = model;
        this.scientificWorkService = scientificWorkService;
        initUi();
    }

    private void initUi() {
        this.setTitle("Додати Викладача");
        this.setSize(500, 600);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 600));
        contentPane.setBackground(new Color(192, 192, 192));

        int start = 50;
        int step = 50;

        label1 = CLabel.create("Код: ", new int[]{80, start, 121, 32});
        field1 = CTextField.create(new int[]{240, start, 150, 35});

        label2 = CLabel.create("Кафедра", new int[]{80, start += step, 122, 25});
        field2 = CTextField.create(new int[]{240, start, 150, 35});

        label3 = CLabel.create("Посада", new int[]{80, start += step, 122, 25});
        field3 = CTextField.create(new int[]{240, start, 150, 35});

        label4 = CLabel.create("Прізвище", new int[]{80, start += step, 122, 25});
        field4 = CTextField.create(new int[]{240, start, 150, 35});

        label5 = CLabel.create("Звання", new int[]{80, start += step, 122, 25});
        field5 = CTextField.create(new int[]{240, start, 150, 35});

        label6 = CLabel.create("Початок роботи", new int[]{80, start += step, 122, 25});
        field6 = CTextField.create(new int[]{240, start, 150, 35});

        label7 = CLabel.create("Телефон", new int[]{80, start += step, 105, 33});
        field7 = CTextField.create(new int[]{240, start, 150, 35});

        label8 = CLabel.create("Стать", new int[]{80, start += step, 105, 33});
        field8 = CTextField.create(new int[]{240, start, 150, 35});

        cancel = CButton.create("Відмінити", new int[]{61, 500, 150, 35});
        cancel.addActionListener(e -> dispose());

        apply = CButton.create("ОК", new int[]{277, 500, 150, 35});
        apply.addActionListener(e -> {
            try {
                TeacherEntity teacherEntity = TeacherRowParser.createObject(field1, field2, field3, field4, field5, field6, field7, field8);
                scientificWorkService.getDao().persist(teacherEntity);
                model.addRow(teacherEntity.getRow());
                dispose();
            } catch (ParseException | PersistException ex) {
                Utils.showError(ex);
            }
        });

        contentPane.add(apply);
        contentPane.add(cancel);

        contentPane.add(field1);
        contentPane.add(field2);
        contentPane.add(field3);
        contentPane.add(field4);
        contentPane.add(field5);
        contentPane.add(field6);
        contentPane.add(field7);
        contentPane.add(field8);

        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(label3);
        contentPane.add(label4);
        contentPane.add(label5);
        contentPane.add(label6);
        contentPane.add(label7);
        contentPane.add(label8);

        this.add(contentPane);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
}




















