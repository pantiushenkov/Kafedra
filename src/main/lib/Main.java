package lib;

import lib.java.UI.NavigationMenu;
import lib.java.UI.presenter.chuhalova.ScienceTopic_Form;
import lib.java.UI.presenter.starostiuk.MainForm;
import lib.java.Utils.ServiceFactory;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new NavigationMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
