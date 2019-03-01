package lib.java.UI.CComponent;

import javax.swing.*;

public class CTextField extends CComponent {
    public static JTextField create(int[] bounds, Boolean enabled) {
        JTextField component = (JTextField) new CComponent().create(JTextField.class, bounds, enabled);
        component.setText("");
        return component;
    }

    public static JTextField create(int[] bounds) {
        return create(bounds, true);
    }

}
