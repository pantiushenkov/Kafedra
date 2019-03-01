package lib.java.UI.CComponent;

import javax.swing.*;

public class CLabel extends CComponent {
    public static JLabel create(String text, int[] bounds, Boolean enabled) {
        JLabel component = (JLabel) new CComponent().create(JLabel.class, bounds, enabled);
        component.setText(text);
        return component;
    }

    public static JLabel create(String text, int[] bounds) {
        return create(text, bounds, true);
    }
}
