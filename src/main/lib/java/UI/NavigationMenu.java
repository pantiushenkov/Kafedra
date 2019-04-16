package lib.java.UI;

import lib.java.DAO.PersistException;
import lib.java.UI.Department.DepartmentView;
import lib.java.UI.Management.ManagementView;
import lib.java.UI.ScientificWork.ScientificWorkView;
import lib.java.UI.Teacher.TeacherView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class NavigationMenu extends JFrame {

    private JButton btnOpenDepartment;
    private JButton btnOpenTeacher;
    private JButton btnOpenScientificWOrk;
    private JButton btnOpenManagement;

    public NavigationMenu() {

        this.setTitle("Університет");

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 450));
        contentPane.setBackground(new Color(192, 192, 192));


        btnOpenDepartment = new JButton();
        btnOpenDepartment.setBounds(147, 46, 202, 70);
        btnOpenDepartment.setBackground(new Color(214, 217, 223));
        btnOpenDepartment.setForeground(new Color(0, 0, 0));
        btnOpenDepartment.setEnabled(true);
        btnOpenDepartment.setFont(new Font("sansserif", 0, 12));
        btnOpenDepartment.setText("Кафедри");
        btnOpenDepartment.setVisible(true);

        btnOpenDepartment.addActionListener(e -> {
            try {
                new DepartmentView();
            } catch (Exception el) {
                el.printStackTrace();
            }

        });

        btnOpenTeacher = new JButton();
        btnOpenTeacher.setBounds(146, 140, 202, 70);
        btnOpenTeacher.setBackground(new Color(214, 217, 223));
        btnOpenTeacher.setForeground(new Color(0, 0, 0));
        btnOpenTeacher.setEnabled(true);
        btnOpenTeacher.setFont(new Font("sansserif", 0, 12));
        btnOpenTeacher.setText("Викладачі");
        btnOpenTeacher.setVisible(true);
        btnOpenTeacher.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    new TeacherView();
                } catch (PersistException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnOpenScientificWOrk = new JButton();
        btnOpenScientificWOrk.setBounds(147, 236, 202, 70);
        btnOpenScientificWOrk.setBackground(new Color(214, 217, 223));
        btnOpenScientificWOrk.setForeground(new Color(0, 0, 0));
        btnOpenScientificWOrk.setEnabled(true);
        btnOpenScientificWOrk.setFont(new Font("sansserif", 0, 12));
        btnOpenScientificWOrk.setText("Наукові теми");
        btnOpenScientificWOrk.setVisible(true);

        btnOpenScientificWOrk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    new ScientificWorkView();
                } catch (PersistException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnOpenManagement = new JButton();
        btnOpenManagement.setBounds(147, 330, 202, 70);
        btnOpenManagement.setBackground(new Color(214, 217, 223));
        btnOpenManagement.setForeground(new Color(0, 0, 0));
        btnOpenManagement.setEnabled(true);
        btnOpenManagement.setFont(new Font("sansserif", 0, 12));
        btnOpenManagement.setText("Керівні роботи");
        btnOpenManagement.setVisible(true);

        btnOpenManagement.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    new ManagementView();
                } catch (PersistException e1) {
                    e1.printStackTrace();
                }
            }
        });


        JLabel label3 = new JLabel();
        label3.setBounds(350, 420, 200, 30);
        label3.setBackground(new Color(214, 217, 223));
        label3.setForeground(new Color(0, 0, 0));
        label3.setFont(new Font("sansserif", 0, 12));
        label3.setText("Пантюшенко Владислав");
        label3.setVisible(true);

        contentPane.add(btnOpenDepartment);
        contentPane.add(btnOpenTeacher);
        contentPane.add(btnOpenScientificWOrk);
        contentPane.add(btnOpenManagement);
        contentPane.add(label3);


        this.add(contentPane);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }


}
