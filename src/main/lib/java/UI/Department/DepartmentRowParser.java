package lib.java.UI.Department;

import lib.java.Entities.DepartmentEntity;

import javax.swing.*;

public class DepartmentRowParser {
    public static DepartmentEntity createObject(JTextField field1, JTextField field2, JTextField field3) {
        Integer key, department;
        try {
            key = Integer.parseInt(field1.getText());
        } catch (Exception e) {
            key = null;
        }

        String name = field2.getText();
        String phone = field3.getText();

        return new DepartmentEntity(key, name, phone);
    }
}
