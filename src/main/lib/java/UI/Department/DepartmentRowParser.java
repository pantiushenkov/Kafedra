package lib.java.UI.Department;

import lib.java.Entities.DepartmentEntity;

import javax.swing.*;

public class DepartmentRowParser {
    public static DepartmentEntity createObject(JTextField field2, JTextField field3) {
        return new DepartmentEntity(field2.getText(), field3.getText());
    }
}
