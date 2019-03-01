package lib.java.UI.Teacher;

import lib.java.Entities.TeacherEntity;

import javax.swing.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TeacherRowParser {
    public static TeacherEntity createObject(JTextField field1, JTextField field2, JTextField field3, JTextField field4, JTextField field5, JTextField field6, JTextField field7, JTextField field8) throws ParseException {
        Integer key, department;
        try {
            key = Integer.parseInt(field1.getText());
        } catch (Exception e) {
            key = null;
        }

        try {
            department = Integer.parseInt(field2.getText());
        } catch (Exception e) {
            department = null;
        }

        return new TeacherEntity(
                key,
                department,
                field3.getText(),
                field4.getText(),
                field5.getText(),
                getDate(field6.getText()),
                field7.getText(),
                field8.getText()
        );
    }

    public static Date getDate(String field) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (field.equals("")) return null;
        return new Date(dateFormat.parse(field).getTime());
    }
}
