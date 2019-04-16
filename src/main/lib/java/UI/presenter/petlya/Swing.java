package lib.java.UI.presenter.petlya;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JScrollBar;
import java.awt.SystemColor;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ScrollPaneConstants;

public class Swing {
	private JFrame frame;
	private String task;
	private int studentButnNum=0;

	
	public Swing() {
		initialize();
	}
	
	private void initialize() {
		ChoiseMenuContentSetter cmcs=new ChoiseMenuContentSetter();
		FastDBgetter fdbg=new FastDBgetter();
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(200, 200, 750, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		
		ButtonGroup group = new ButtonGroup();
		panel.setLayout(new BorderLayout(0, 0));
		
		
		
		
		JPanel panel_1 = new JPanel();
		
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u0422\u0435\u0441\u0442\u043E\u0432\u0438\u0439 \u0422\u0435\u043A\u0441\u0442");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 24));
		lblNewLabel.setBounds(33, 13, 533, 67);
		panel_1.add(lblNewLabel);
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 77, 669, 277);
		panel_1.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		Box verticalBox = Box.createVerticalBox();
		panel_2.add(verticalBox, BorderLayout.NORTH);
		verticalBox.setForeground(SystemColor.inactiveCaptionBorder);
		verticalBox.setBackground(SystemColor.inactiveCaption);
		
		
		
		panel.add(panel_1);
		JButton btnNewButton = new JButton("\u0410\u0441\u043F\u0456\u0440\u0430\u043D\u0442\u0443\u0440\u0430");
		panel.add(btnNewButton, BorderLayout.NORTH);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.setVisible(true);
				btnNewButton.setVisible(false);
				cmcs.setStartContent(lblNewLabel, group, verticalBox);
				task="CChoise";
			}
		});
		JButton btnNewButton_1 = new JButton("\u041C\u0435\u043D\u044E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				btnNewButton.setVisible(true);
				task="Menu";
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(578, 13, 134, 57);
		panel_1.add(btnNewButton_1);
		
		JButton button = new JButton("\u0412\u0438\u0431\u0440\u0430\u0442\u0438");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				if(task=="Menu") {}
				else if(task=="CChoise") {	int k=group.getButtonCount();
					Enumeration<AbstractButton> buttons = group.getElements();
					for(int i=0;i<k;i++) {
						  AbstractButton button = buttons.nextElement();
					if(button.isSelected()) {
						if(i==0) {task="DChoise";;
						
						cmcs.setContent("getDeps",fdbg.getDeps(),lblNewLabel, group, verticalBox);
						break;
						}
						else if(i==1) {task="PChoise";
						cmcs.setContent("getProfs",fdbg.getProfs(),lblNewLabel, group, verticalBox);
						break;}
						else if(i==2) {task="";
							cmcs.setContent("getProfsW",fdbg.getProfsW(),lblNewLabel, group, verticalBox);
							break;}
						else {break;}
					}
				}
					}
				else if(task=="DChoise") {	int k=group.getButtonCount();
					Enumeration<AbstractButton> buttons = group.getElements();
					for(int i=0;i<k;i++) {
						
						  AbstractButton button = buttons.nextElement();
					if(button.isSelected()) {
						task="GetAsp";
						
						cmcs.setContent("getAspsByDeps",fdbg.getAspsByDeps(i),lblNewLabel, group, verticalBox);
						break;
						

					}
				else {}
				
			}
					}
					
				else if(task=="PChoise") {	int k=group.getButtonCount();
				Enumeration<AbstractButton> buttons = group.getElements();
				for(int i=0;i<k;i++) {
					
					  AbstractButton button = buttons.nextElement();
				if(button.isSelected()) {
					task="GetAsp";
					
					cmcs.setContent("getAspsByProf",fdbg.getAspsByProf(i),lblNewLabel, group, verticalBox);
					break;
					
			
				}
			else {}
			
		}
				
	}	
				
				else if(task=="GetAsp") {	int k=group.getButtonCount();
				Enumeration<AbstractButton> buttons = group.getElements();
				for(int i=0;i<k;i++) {
					
					  AbstractButton button = buttons.nextElement();
				if(button.isSelected()) {
					task="GetWorks";
					studentButnNum=i;
					cmcs.showAsp(fdbg.getAsp(i),lblNewLabel, group, verticalBox);
					break;
					
			
				}
			else {}
			
		}
				
	}
				else if(task=="GetWorks") {	
					cmcs.showWorks(fdbg.getWorks(studentButnNum),lblNewLabel, verticalBox);
					
				
	}
			}});
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBounds(53, 367, 134, 57);
		panel_1.add(button);
		panel_1.setVisible(false);
	}
	
		
	}

