package lib.java.UI.Management;

import lib.java.Entities.ManagementEntity;

import javax.swing.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ManagementRowParser {
    public static ManagementEntity createObject(JTextField field1, JTextField field2, JTextField field3, JTextField field4) throws ParseException {
        Integer key, key2;
        try {
            key = Integer.parseInt(field1.getText());
        } catch (Exception e) {
            key = null;
        }

        try {
            key2 = Integer.parseInt(field2.getText());
        } catch (Exception e) {
            key2 = null;
        }

        return new ManagementEntity(
                key,
                key2,
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
