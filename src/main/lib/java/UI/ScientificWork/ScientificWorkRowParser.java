package lib.java.UI.ScientificWork;

import lib.java.Entities.ScientificWorkEntity;

import javax.swing.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ScientificWorkRowParser {
    public static ScientificWorkEntity createObject(JTextField field2, JTextField field3, JTextField field5, JTextField field6, JTextField field7) throws ParseException {
        String department = field2.getText();
        String name = field3.getText();
        String customer = field5.getText();
        Date start = getDate(field6.getText());
        Date end = getDate(field7.getText());

        return new ScientificWorkEntity(department, name, customer, start, end);
    }

    public static Date getDate(String field) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(field);
        if (field.equals("")) return null;
        return new java.sql.Date(dateFormat.parse(field).getTime());
    }
}
