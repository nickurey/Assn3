

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

import Basic.Utility;


public class LoginView extends JFrame{
	private Admin admin;
	//private AdminController controller;
	private JPanel panel;
	private JButton submitButton;
	private JLabel label0;
	public AdminModule ad;
	private JTextField input0;
	private boolean auth;
	
	public LoginView(AdminModule adminModule){
		ad= adminModule;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		auth = false;
		panel = new JPanel();
		label0 = new JLabel("Password");
		input0 = new JTextField();
		submitButton = new JButton("Log in");
		submitButton.addActionListener(new SubmitButtonListener());
		
		panel.add(label0);
		panel.add(input0);

		
		panel.add(submitButton);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Login"));
		panel.setLayout(new GridLayout(3,2));
		panel.setBackground(Color.white);
		
		add(panel);
		
		
	}
	public boolean getAuth(){
		return auth;
	}
	private class SubmitButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			String password = input0.getText();
			String hashedPassword = null;
			try {
				Scanner fileScanner = new Scanner(new File("admin.dat"));
				
				while (fileScanner.hasNextLine()) {
					hashedPassword = fileScanner.nextLine();
				}
				if(Utility.getHash(password).equals(hashedPassword)) {
					auth = true;
					ad.authStatus = true;
					System.out.println(auth);
					//dispose();	
				}
				else JOptionPane.showMessageDialog(panel, "Log in failed!","Information", JOptionPane.PLAIN_MESSAGE);
				fileScanner.close();

			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(panel, "Admin data not found","Information", JOptionPane.PLAIN_MESSAGE);

			}
			
			
		}
		

	}

	
}
