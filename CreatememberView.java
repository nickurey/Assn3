

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class CreatememberView extends JFrame{
	private Admin admin;
	//private AdminController controller;
	private JPanel panel;
	private JButton submitButton, cancelButton;
	private JLabel label0, label1;
	private JTextField input0, input1;
	private AdminView controller;
	
	public CreatememberView(AdminView controller){
		
		this.controller=controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		
		label0 = new JLabel("Username");
		input0 = new JTextField();
		label1 = new JLabel("Password");
		input1 = new JTextField();
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new SubmitButtonListener());
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ButtonListener());
		
		panel.add(label0);
		panel.add(input0);
		panel.add(label1);
		panel.add(input1);
		
		panel.add(submitButton);
		panel.add(cancelButton);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Create new player"));
		panel.setLayout(new GridLayout(3,2));
		panel.setBackground(Color.white);
		
		add(panel);
		
		
	}
	
	private class ButtonListener implements ActionListener{
	
		public void actionPerformed(ActionEvent e){
			controller.mainMenu();
		}
	}
	
	private class SubmitButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			String name = input0.getText();
			String password = input1.getText();
			System.out.println("=="+name +password);
			try {
				controller.createPlayer(name, password);
				JOptionPane.showMessageDialog(panel, "Player Created","Information", JOptionPane.PLAIN_MESSAGE);
			} catch (CreateMemberException e1) {
				JOptionPane.showMessageDialog(panel, e1.getMessage(),"Information", JOptionPane.PLAIN_MESSAGE);
			}
			
		}
	}

	
}
