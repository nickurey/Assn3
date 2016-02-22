import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Basic.Member;
import Basic.Utility;
import Common.DBQuery;

import java.awt.FlowLayout;
import java.awt.BorderLayout;


public class AdminModule {
	private LoginView login;
	public boolean authStatus = false;
	private JPanel panel;
	private JButton submitButton;
	private JLabel label0;
	private JTextField passwordField;
	private static JFrame loginFrame;
	private JButton btnRegister;
	private JLabel lblNewLabel;
	private JTextField usernameField;
	private static RegisterMemberView fRegister;
	private static DBQuery dp;
	public static void main(String args[]) {
		AdminModule am = new AdminModule();
		dp = new DBQuery();
		fRegister = new RegisterMemberView(am);
		am.run();
		
	}

	public void run() {
		System.out.println(Utility.getHash("password"));
		loginFrame = new JFrame();
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setBounds(100, 100, 450, 300);
		
		panel = new JPanel();
		//panel.setBounds(0, 0, 450, 278);
		label0 = new JLabel("Password");
		label0.setBounds(24, 103, 101, 28);
		passwordField = new JTextField();
		passwordField.setBounds(121, 96, 323, 43);
		submitButton = new JButton("Log in");
		submitButton.setBounds(167, 159, 117, 51);
		submitButton.addActionListener(new SubmitButtonListener());
		loginFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		panel.setLayout(null);
		
		panel.add(label0);
		panel.add(passwordField);

		
		panel.add(submitButton);
		panel.setBorder(BorderFactory.createTitledBorder("Login"));
		//panel.setLayout(new GridLayout(3,2));
		panel.setBackground(Color.white);
		
		loginFrame.getContentPane().add(panel);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ButtonRegisterListener());
		btnRegister.setBounds(167, 212, 117, 51);
		panel.add(btnRegister);
		
		lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(24, 47, 81, 28);
		panel.add(lblNewLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(121, 40, 323, 43);
		panel.add(usernameField);
		usernameField.setColumns(10);
		//loginFrame.setLocationRelativeTo(c);
		//System.out.println(loginFrame.getLocationOnScreen());
		//loginFrame.pack();
		//loginFrame.setPreferredSize(new Dimension(400, 300));
		//loginFrame.pack();
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setVisible(true);
		

	}
	
	private class SubmitButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			String username = usernameField.getText();
			String password = passwordField.getText();
			String hashedPassword = Utility.getHash(password);
			Member logInUser;
			try {
				logInUser = dp.authenticateMember(username, hashedPassword);
				if (logInUser!=null){
					loginFrame.setVisible(false);
					Admin admin = new Admin();
					DataPool playerPool = new DataPool();
					AdminController controller = new AdminController(admin, playerPool);
					AdminView view = new AdminView(admin, controller);
					controller.attachView(view);
					controller.run();
				}
				else JOptionPane.showMessageDialog(panel, "Log in failed!","Information", JOptionPane.PLAIN_MESSAGE);


			} catch (Exception ex) {
				JOptionPane.showMessageDialog(panel, ex.getMessage(),"Information", JOptionPane.PLAIN_MESSAGE);

			}
			
			
		}
		

	}

	private class ButtonRegisterListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			fRegister.setVisible(true);
			loginFrame.setVisible(false);
		}
	}
	public void viewLoginFrame(){
		fRegister.setVisible(false);
		loginFrame.setVisible(true);
	}
	

}
