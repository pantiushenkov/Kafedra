package lib.java.Utils;

import javax.swing.*;

public class Utils {
    public static void showError(Object error) {
        String mainMessage = null;
        String title = null;
        if (error instanceof String) {
            mainMessage = (String) error;
            title = "Помилка!";
        } else if (error instanceof Exception) {
            Exception exceptionError = (Exception) error;
            mainMessage = "Message: " + exceptionError.getMessage();
            title = exceptionError.getClass().getName();
            System.out.println(title);
        }

        JOptionPane.showMessageDialog(null, mainMessage, title, JOptionPane.WARNING_MESSAGE);
    }

}
