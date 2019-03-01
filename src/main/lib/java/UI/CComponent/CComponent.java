package lib.java.UI.CComponent;

import javax.swing.*;
import java.awt.*;


public class CComponent<T extends JComponent> {
    public JComponent create(Class<T>  cclass, int[] bounds, Boolean enabled) {
        JComponent component = null;
        try {
            component = cclass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        component.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        component.setBackground(new Color(255, 255, 255));
        component.setForeground(new Color(0, 0, 0));
        component.setFont(new Font("sansserif", 0, 12));
        component.setEnabled(enabled);
        return component;
    }
}
