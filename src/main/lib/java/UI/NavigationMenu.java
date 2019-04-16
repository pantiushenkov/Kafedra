package lib.java.UI;

import lib.java.DAO.PersistException;
import lib.java.UI.Department.DepartmentView;
import lib.java.UI.Management.ManagementView;
import lib.java.UI.ScientificWork.ScientificWorkView;
import lib.java.UI.Teacher.TeacherView;
import lib.java.UI.presenter.chuhalova.ScienceTopic_Form;
import lib.java.UI.presenter.chuhalova.Scientists_Form;
import lib.java.UI.presenter.petlya.Swing;
import lib.java.UI.presenter.starostiuk.MainForm;
import lib.java.Utils.ServiceFactory;

import javax.jnlp.ServiceManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class NavigationMenu extends JFrame {

    private JButton btnOpenDepartment;
    private JButton btnOpenTeacher;
    private JButton btnOpenScientificWOrk;
    private JButton btnOpenManagement;
    private JButton btnMasterManagement;
    private JButton btnScienceTopics;
    private JButton btnScientistsForm;
    private JButton btnAspirants;


    public NavigationMenu() {

        this.setTitle("Університет");

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 450));
        contentPane.setBackground(new Color(192, 192, 192));


        btnOpenDepartment = new JButton();
        btnOpenDepartment.setBounds(50, 46, 170, 70);
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

        btnMasterManagement = new JButton();
        btnMasterManagement.setBounds(280, 46, 170, 70);
        btnMasterManagement.setBackground(new Color(214, 217, 223));
        btnMasterManagement.setForeground(new Color(0, 0, 0));
        btnMasterManagement.setEnabled(true);
        btnMasterManagement.setFont(new Font("sansserif", 0, 12));
        btnMasterManagement.setText("Магістратура");
        btnMasterManagement.setVisible(true);

        btnMasterManagement.addActionListener(e -> {
            try {
                new MainForm(ServiceFactory.getMasterService());
            } catch (Exception el) {
                el.printStackTrace();
            }

        });

        btnScienceTopics = new JButton();
        btnScienceTopics.setBounds(280, 140, 170, 70);
        btnScienceTopics.setBackground(new Color(214, 217, 223));
        btnScienceTopics.setForeground(new Color(0, 0, 0));
        btnScienceTopics.setEnabled(true);
        btnScienceTopics.setFont(new Font("sansserif", 0, 12));
        btnScienceTopics.setText("Наукові теми");
        btnScienceTopics.setVisible(true);

        btnScienceTopics.addActionListener(e -> {
            try {
                new ScienceTopic_Form(ServiceFactory.getMasterService(), ServiceFactory.getChuhalovaService());
            } catch (Exception el) {
                el.printStackTrace();
            }

        });

        btnScientistsForm = new JButton();
        btnScientistsForm.setBounds(280, 236, 170, 70);
        btnScientistsForm.setBackground(new Color(214, 217, 223));
        btnScientistsForm.setForeground(new Color(0, 0, 0));
        btnScientistsForm.setEnabled(true);
        btnScientistsForm.setFont(new Font("sansserif", 0, 12));
        btnScientistsForm.setText("Науковці");
        btnScientistsForm.setVisible(true);

        btnScientistsForm.addActionListener(e -> {
            try {
                new Scientists_Form(ServiceFactory.getMasterService(), ServiceFactory.getChuhalovaService());
            } catch (Exception el) {
                el.printStackTrace();
            }

        });

        btnAspirants = new JButton();
        btnAspirants.setBounds(280, 330, 170, 70);
        btnAspirants.setBackground(new Color(214, 217, 223));
        btnAspirants.setForeground(new Color(0, 0, 0));
        btnAspirants.setEnabled(true);
        btnAspirants.setFont(new Font("sansserif", 0, 12));
        btnAspirants.setText("Аспірантура");
        btnAspirants.setVisible(true);

        btnAspirants.addActionListener(e -> {
            try {
                new Swing();
            } catch (Exception el) {
                el.printStackTrace();
            }

        });

        btnMasterManagement = new JButton();
        btnMasterManagement.setBounds(280, 46, 170, 70);
        btnMasterManagement.setBackground(new Color(214, 217, 223));
        btnMasterManagement.setForeground(new Color(0, 0, 0));
        btnMasterManagement.setEnabled(true);
        btnMasterManagement.setFont(new Font("sansserif", 0, 12));
        btnMasterManagement.setText("Магістратура");
        btnMasterManagement.setVisible(true);

        btnMasterManagement.addActionListener(e -> {
            try {
                new MainForm(ServiceFactory.getMasterService());
            } catch (Exception el) {
                el.printStackTrace();
            }

        });


        btnOpenTeacher = new JButton();
        btnOpenTeacher.setBounds(50, 140, 170, 70);
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
        btnOpenScientificWOrk.setBounds(50, 236, 170, 70);
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
        btnOpenManagement.setBounds(50, 330, 170, 70);
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
        label3.setBounds(350, 390, 200, 30);
        label3.setBackground(new Color(214, 217, 223));
        label3.setForeground(new Color(0, 0, 0));
        label3.setFont(new Font("sansserif", 0, 12));
        label3.setText("Пантюшенко Владислав");
        label3.setVisible(true);

        JLabel label4 = new JLabel();
        label4.setBounds(350, 420, 200, 30);
        label4.setBackground(new Color(214, 217, 223));
        label4.setForeground(new Color(0, 0, 0));
        label4.setFont(new Font("sansserif", 0, 12));
        label4.setText("Старостюк Денис");
        label4.setVisible(true);

        JLabel label5 = new JLabel();
        label5.setBounds(350, 410, 200, 30);
        label5.setBackground(new Color(214, 217, 223));
        label5.setForeground(new Color(0, 0, 0));
        label5.setFont(new Font("sansserif", 0, 12));
        label5.setText("Чухалова Вероніка");
        label5.setVisible(true);

        JLabel label6 = new JLabel();
        label6.setBounds(350, 400, 200, 30);
        label6.setBackground(new Color(214, 217, 223));
        label6.setForeground(new Color(0, 0, 0));
        label6.setFont(new Font("sansserif", 0, 12));
        label6.setText("Петля Володимир");
        label6.setVisible(true);

        contentPane.add(btnOpenDepartment);
        contentPane.add(btnOpenTeacher);
        contentPane.add(btnOpenScientificWOrk);
        contentPane.add(btnOpenManagement);
        contentPane.add(btnMasterManagement);
        contentPane.add(btnScienceTopics);
        contentPane.add(btnScientistsForm);
        contentPane.add(btnAspirants);
        contentPane.add(label3);
        contentPane.add(label4);
        contentPane.add(label5);
        contentPane.add(label6);


        this.add(contentPane);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }


}
