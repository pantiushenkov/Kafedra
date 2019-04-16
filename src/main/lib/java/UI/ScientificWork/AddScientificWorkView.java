package lib.java.UI.ScientificWork;

import lib.java.DAO.PersistException;
import lib.java.Entities.ScientificWorkEntity;
import lib.java.Services.ScientificWorkService;
import lib.java.UI.CComponent.CButton;
import lib.java.UI.CComponent.CLabel;
import lib.java.UI.CComponent.CTextField;
import lib.java.Utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;

public class AddScientificWorkView extends JFrame {

    private ScientificWorkService scientificWorkService;
    private JButton apply;
    private JButton cancel;

    private JLabel label2;
    private JLabel label3;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;

    private JTextField field2;
    private JTextField field3;
    private JTextField field5;
    private JTextField field6;
    private JTextField field7;

    private DefaultTableModel model;

    public AddScientificWorkView(DefaultTableModel model, ScientificWorkService scientificWorkService) {
        this.model = model;
        this.scientificWorkService = scientificWorkService;
        initUi();
    }

    private void initUi() {
        this.setTitle("Додати Наукову тему");
        this.setSize(500, 600);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 500));
        contentPane.setBackground(new Color(192, 192, 192));

        int start = 50;
        int step = 50;

        label2 = CLabel.create("Код кафедри: ", new int[]{80, start += step, 122, 25});
        field2 = CTextField.create(new int[]{240, start, 150, 35});

        label3 = CLabel.create("Назва: ", new int[]{80, start += step, 122, 25});
        field3 = CTextField.create(new int[]{240, start, 150, 35});

        label5 = CLabel.create("Замовник: ", new int[]{80, start += step, 122, 25});
        field5 = CTextField.create(new int[]{240, start, 150, 35});

        label6 = CLabel.create("Початок (Дата): ", new int[]{80, start += step, 122, 25});
        field6 = CTextField.create(new int[]{240, start, 150, 35});

        label7 = CLabel.create("Кінець (Дата): ", new int[]{80, start += step, 105, 33});
        field7 = CTextField.create(new int[]{240, start, 150, 35});

        cancel = CButton.create("Відмінити", new int[]{61, 400, 150, 35});
        cancel.addActionListener(e -> dispose());

        apply = CButton.create("ОК", new int[]{277, 400, 150, 35});
        apply.addActionListener(e -> {
            try {
                ScientificWorkEntity scientificWorkEntity = ScientificWorkRowParser.createObject(field2, field3, field5, field6, field7);
                scientificWorkService.getDao().persist(scientificWorkEntity);
                model.addRow(scientificWorkEntity.getRow());
                dispose();
            } catch (ParseException | PersistException ex) {
                Utils.showError(ex);
            }
        });

        contentPane.add(apply);
        contentPane.add(cancel);

        contentPane.add(field2);
        contentPane.add(field3);
        contentPane.add(field5);
        contentPane.add(field6);
        contentPane.add(field7);

        contentPane.add(label2);
        contentPane.add(label3);
        contentPane.add(label5);
        contentPane.add(label6);
        contentPane.add(label7);

        this.add(contentPane);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
}




















