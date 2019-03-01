package lib;

import lib.java.UI.NavigationMenu;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        SwingUtilities.invokeLater(() -> {
            try {
                new NavigationMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
