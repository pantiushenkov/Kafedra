package lib.java.UI.CComponent;

import javax.swing.*;

public class CButton extends CComponent {
    public static JButton create(String text, int[] bounds, Boolean enabled) {
        JButton component = (JButton) new CComponent().create(JButton.class, bounds, enabled);
        component.setText(text);
        return component;
    }

    public static JButton create(String text, int[] bounds) {
        return create(text, bounds, true);
    }
}
