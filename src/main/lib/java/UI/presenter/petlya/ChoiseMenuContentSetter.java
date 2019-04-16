package lib.java.UI.presenter.petlya;

import javax.swing.*;
import java.util.ArrayList;

public class ChoiseMenuContentSetter {

    String data;

    public ChoiseMenuContentSetter() {
    }

    public void setContent(String task, ArrayList<DBdata> array, JLabel lbl, ButtonGroup group, Box box) {
        clearGroup(group);
        box.setVisible(false);
        box.removeAll();
        if (array.size() == 0 || (array.size() == 1 && (task == "getAspsByProf" || task == "getAspsByDeps"))) {
            lbl.setText("����� ����!");
        } else {
            if (task == "getProfs") {
                lbl.setText("Teachers");
                JRadioButton b = new JRadioButton(array.get(0).name);
                box.add(b);
                group.add(b);
                b.setSelected(true);
                for (int i = 1; i < array.size(); i++) {
                    JRadioButton bt = new JRadioButton(array.get(i).name);
                    box.add(bt);
                    group.add(bt);

                }
            }
            if (task == "getDeps") {
                lbl.setText("Cathedras");
                JRadioButton b = new JRadioButton(array.get(0).name);
                box.add(b);
                group.add(b);
                b.setSelected(true);
                for (int i = 1; i < array.size(); i++) {
                    JRadioButton bt = new JRadioButton(array.get(i).name);
                    box.add(bt);
                    group.add(bt);
                }
            }
            if (task == "getProfsW") {
                lbl.setText("����������");

                for (int i = 0; i < array.size(); i++) {
                    JLabel b = new JLabel("������� ���������: " + array.get(i).name);
                    box.add(b);
                    JLabel b1 = new JLabel("ʳ������ ���� ���� ��������: " + array.get(i).id);
                    box.add(b1);
                    JLabel b2 = new JLabel("***");
                    box.add(b2);
                }
            }
            if (task == "getAspsByProf") {
                lbl.setText("Aspirants " + array.get(0).name + " :��������");
                JRadioButton b = new JRadioButton(array.get(1).name);
                box.add(b);
                group.add(b);
                b.setSelected(true);
                for (int i = 2; i < array.size(); i++) {
                    JRadioButton bt = new JRadioButton(array.get(i).name);
                    box.add(bt);
                    group.add(bt);

                }
            }
            if (task == "getAspsByDeps") {
                lbl.setText("Aspirants of cathedra " + array.get(0).name + " :��������");
                JRadioButton b = new JRadioButton(array.get(1).name);
                box.add(b);
                group.add(b);
                b.setSelected(true);
                for (int i = 2; i < array.size(); i++) {
                    JRadioButton bt = new JRadioButton(array.get(i).name);
                    box.add(bt);
                    group.add(bt);

                }
            }
            if (task == "GetAsp") {
                lbl.setText(task);
                JRadioButton b = new JRadioButton(array.get(0).name);
                box.add(b);
                group.add(b);
                b.setSelected(true);
                for (int i = 1; i < array.size(); i++) {
                    JRadioButton bt = new JRadioButton(array.get(i).name);
                    box.add(bt);
                    group.add(bt);

                }
            }
        }

        box.setVisible(true);
    }


    public void showAsp(Aspirant asp, JLabel lbl, ButtonGroup group, Box box) {
        clearGroup(group);
        box.setVisible(false);
        box.removeAll();
        lbl.setText("�������");
        box.add(new JLabel("�������: " + asp.name));
        box.add(new JLabel("�����: " + asp.sex));
        box.add(new JLabel("�������: " + asp.num));
        box.add(new JLabel("�������: " + asp.dep));
        box.add(new JLabel("�������: " + asp.prof));
        box.add(new JLabel("�������: " + asp.date1));
        box.add(new JLabel("���������: " + asp.date2));
        box.add(new JLabel("������: " + asp.date3));
        box.add(new JLabel("����: " + asp.theme));
        JRadioButton b = new JRadioButton("����������� ������");
        b.setSelected(true);
        box.add(b);
        box.setVisible(true);
    }


    public void setStartContent(JLabel lbl, ButtonGroup group, Box box) {
        box.removeAll();
        clearGroup(group);
        lbl.setText("����������");
        JRadioButton rdbtnR = new JRadioButton("����� �� ��������");
        box.add(rdbtnR);

        JRadioButton rdbtnT = new JRadioButton("����� �� ����������");
        box.add(rdbtnT);
        JRadioButton rdbtnL = new JRadioButton("����������� ���������� ��������");
        box.add(rdbtnL);
        group.add(rdbtnR);
        group.add(rdbtnT);
        group.add(rdbtnL);
        rdbtnR.setSelected(true);

    }

    private void clearGroup(ButtonGroup group) {


        while (group.getElements().hasMoreElements()) {
            group.remove(group.getElements().nextElement());
        }

    }

    public void showWorks(ArrayList<DBdata> array, JLabel lbl, Box box) {

        box.setVisible(false);
        box.removeAll();
        if (array.size() == 1) {
            lbl.setText("����� ����!");
        } else {
            lbl.setText("������� " + array.get(0).name + " :������");

            for (int i = 1; i < array.size(); i++) {
                box.add(new JLabel("�����: " + array.get(i).name));
                box.add(new JLabel("г�: " + array.get(i).id));
                box.add(new JLabel("***"));

            }


        }
        box.setVisible(true);
    }
}
