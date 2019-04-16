package lib.java.UI.Teacher;

import lib.java.Entities.TeacherEntity;

import javax.swing.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TeacherRowParser {
    public static TeacherEntity createObject(JTextField field1, JTextField field2, JTextField field3, JTextField field4, JTextField field5, JTextField field6, JTextField field7) throws ParseException {
        return new TeacherEntity(
                field1.getText(),
                field2.getText(),
                field3.getText(),
                getDate(field4.getText()),
                field5.getText(),
                field6.getText(),
                field7.getText()
        );
    }

    public static Date getDate(String field) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (field.equals("")) return null;
        return new Date(dateFormat.parse(field).getTime());
    }
}
