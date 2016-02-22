

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class DeleteMemberView extends JFrame{
	private Admin admin;
	//private AdminController controller;
	private JPanel panel;
	private JButton submitButton, cancelButton;
	private JLabel label0;

	private JTextField input0;
	private AdminView controller;
	
	public DeleteMemberView(AdminView controller){
		
		this.controller=controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		
		label0 = new JLabel("Enter Player name to delete");
		input0 = new JTextField();
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new SubmitButtonListener());
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ButtonListener());
		
		panel.add(label0);
		panel.add(input0);
		
		panel.add(submitButton);
		panel.add(cancelButton);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Delete Player"));
		panel.setLayout(new GridLayout(2,2));
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
			try {
				controller.deletePlayer(name);
				JOptionPane.showMessageDialog(panel, "Player Deleted","Information", JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(panel, e1.getMessage(),"Information", JOptionPane.PLAIN_MESSAGE);
			}
			
		}
	}

	
}
