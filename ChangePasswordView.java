

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ChangePasswordView extends JFrame{
	private Admin admin;
	//private AdminController controller;
	private JPanel panel;
	private JButton submitButton, cancelButton;
	private JLabel label0, label1;

	private JTextField input0, input1;
	private AdminView controller;
	
	public ChangePasswordView(AdminView controller){
		
		this.controller=controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		
		label0 = new JLabel("Password");
		label0.setBounds(6, 70, 207, 37);
		input0 = new JTextField();
		input0.setBounds(137, 70, 307, 37);
		label1 = new JLabel("Retype password");
		label1.setBounds(6, 108, 219, 42);
		input1 = new JTextField();
		input1.setBounds(137, 108, 307, 42);
		submitButton = new JButton("Submit");
		submitButton.setBounds(237, 162, 147, 44);
		submitButton.addActionListener(new SubmitButtonListener());
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(78, 162, 147, 44);
		cancelButton.addActionListener(new ButtonListener());
		panel.setLayout(null);
		
		panel.add(label0);
		panel.add(input0);
		panel.add(label1);
		panel.add(input1);
		
		panel.add(submitButton);
		panel.add(cancelButton);
		panel.setBorder(BorderFactory.createTitledBorder("Change Password"));
		panel.setBackground(Color.white);
		
		getContentPane().add(panel);
		
		
	}
	
	private class ButtonListener implements ActionListener{
	
		public void actionPerformed(ActionEvent e){
			controller.mainMenu();
		}
	}
	
	private class SubmitButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			String password1 = input0.getText();
			String password2 = input1.getText();
			try {
				controller.changeAdminPassword(password1, password2);
				JOptionPane.showMessageDialog(panel, "Admin password reset!","Information", JOptionPane.PLAIN_MESSAGE);
			} catch (InvalidPasswordException e1) {
				JOptionPane.showMessageDialog(panel, "Password not matched","Information", JOptionPane.PLAIN_MESSAGE);
			}
			
			
		}
	}

	
}
