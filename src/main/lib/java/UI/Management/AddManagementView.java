package lib.java.UI.Management;

import lib.java.DAO.PersistException;
import lib.java.Entities.ManagementEntity;
import lib.java.Services.ManagementService;
import lib.java.UI.CComponent.CButton;
import lib.java.UI.CComponent.CLabel;
import lib.java.UI.CComponent.CTextField;
import lib.java.Utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;

public class AddManagementView extends JFrame {

    private ManagementService scientificWorkService;
    private JButton apply;
    private JButton cancel;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;

    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;

    private DefaultTableModel model;

    public AddManagementView(DefaultTableModel model, ManagementService scientificWorkService) {
        this.model = model;
        this.scientificWorkService = scientificWorkService;
        initUi();
    }

    private void initUi() {
        this.setTitle("Додати Керівництво");
        this.setSize(500, 500);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 600));
        contentPane.setBackground(new Color(192, 192, 192));

        int start = 50;
        int step = 50;

        label1 = CLabel.create("Код викладача: ", new int[]{80, start, 121, 32});
        field1 = CTextField.create(new int[]{240, start, 150, 35});

        label2 = CLabel.create("Код КР", new int[]{80, start += step, 122, 25});
        field2 = CTextField.create(new int[]{240, start, 150, 35});

        label3 = CLabel.create("Початок КР", new int[]{80, start += step, 122, 25});
        field3 = CTextField.create(new int[]{240, start, 150, 35});

        label4 = CLabel.create("Кінець КР", new int[]{80, start += step, 122, 25});
        field4 = CTextField.create(new int[]{240, start, 150, 35});

        cancel = CButton.create("Відмінити", new int[]{61, 500, 150, 35});
        cancel.addActionListener(e -> dispose());

        apply = CButton.create("ОК", new int[]{277, 500, 150, 35});
        apply.addActionListener(e -> {
            try {
                ManagementEntity managementEntity = ManagementRowParser.createObject(field1, field2, field3, field4);
                scientificWorkService.getDao().persist(managementEntity);
                model.addRow(managementEntity.getRow());
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

        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(label3);
        contentPane.add(label4);

        this.add(contentPane);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
}




















