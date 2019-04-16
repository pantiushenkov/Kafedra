package lib.java.UI.Management;

import lib.java.Entities.ManagementEntity;

import javax.swing.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ManagementRowParser {
    public static ManagementEntity createObject(JTextField field1, JTextField field2, JTextField field3, JTextField field4) throws ParseException {
        return new ManagementEntity(
                field1.getText(),
                field2.getText(),
                getDate(field3.getText()),
                getDate(field4.getText())
        );
    }

    public static Date getDate(String field) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (field.equals("")) return null;
        return new Date(dateFormat.parse(field).getTime());
    }
}
