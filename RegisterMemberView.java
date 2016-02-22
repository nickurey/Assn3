

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Basic.Facility;
import Basic.Member;
import Basic.TimeSlot;
import Common.CreateMemberException;



public class RegisterMemberView extends JFrame{
	private Admin admin;
	//private AdminController controller;
	private JPanel panel;
	private JButton submitButton, cancelButton;
	private JLabel label0, label1;
	private JTextField input0, input1;
	private AdminModule controller;
	private DataPool dp;
	private JTextField userNameField;
	private JTextField passwordField;
	private JTextField lNameField;
	private JTextField fNameField;
	
	public RegisterMemberView(AdminModule am){
		
		//
		dp = new DataPool();
		
		ArrayList<String> listOfType = new ArrayList<String>();
		listOfType.add("restaurant");
		listOfType.add("ktv");
		
		setBounds(100, 100, 450, 300);
		
		this.controller=am;
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		//panel.setLayout(null);
		
		JLabel username = new JLabel("Username");
		username.setBounds(6, 43, 142, 16);
		panel.add(username);
		
		userNameField = new JTextField();
		userNameField.setBounds(193, 38, 151, 26);
		panel.add(userNameField);
		userNameField.setColumns(10);
		
		JLabel fName = new JLabel("First Name");
		fName.setBounds(6, 68, 117, 16);
		panel.add(fName);


		JLabel lName = new JLabel("Last Name");
		lName.setBounds(6, 96, 130, 16);
		panel.add(lName);
		
		JLabel pword = new JLabel("Password");
		pword.setBounds(6, 124, 142, 16);
		panel.add(pword);
		
		passwordField = new JTextField();
		passwordField.setBounds(193, 119, 151, 26);
		panel.add(passwordField);
		passwordField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(193, 191, 117, 29);
		btnSubmit.addActionListener(new SubmitButtonListener());
		panel.add(btnSubmit);
	
		//panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Member Registration"));
		//panel.setLayout(new GridLayout(3,2));
		panel.setBackground(Color.white);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(81, 192, 100, 26);
		btnCancel.addActionListener(new ButtonListener());
		panel.add(btnCancel);
		
		
		getContentPane().add(panel);
		
		lNameField = new JTextField();
		lNameField.setBounds(193, 91, 151, 26);
		panel.add(lNameField);
		lNameField.setColumns(10);
		
		fNameField = new JTextField();
		fNameField.setBounds(193, 63, 151, 26);
		panel.add(fNameField);
		fNameField.setColumns(10);
		
		
		
		
	}
	
	private class ButtonListener implements ActionListener{
	
		public void actionPerformed(ActionEvent e){
			controller.viewLoginFrame();
		}
	}
	
	private class SubmitButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			try {
				//runAddTransactionSQL(int member, int facility, int timeSlot, int paxCount) 
				String name = userNameField.getText();
				String fN = fNameField.getText();
				String lN = lNameField.getText();
				String pw = Utility.getHash(passwordField.getText());
				Member n = new Member(name, fN, lN, pw);
				n.setMembership(4);
				n.setRole("member");
				dp.addMember(n);
				
				//controller.createMember(name, fN, lN, pw);
				JOptionPane.showMessageDialog(panel, "Member Registered. Please log in.","Information", JOptionPane.PLAIN_MESSAGE);
			} catch (CreateMemberException | SQLException e1) {
				JOptionPane.showMessageDialog(panel, e1.getMessage(),"Information", JOptionPane.PLAIN_MESSAGE);
			}
			
		}
	}

	
}
