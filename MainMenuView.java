

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenuView extends JFrame{
	
	private JPanel panel;
	private JButton button0;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JLabel label;
	private AdminView controller;
	
	public MainMenuView(AdminView controller){
		
		this.controller=controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		label = new JLabel("Frame 1");
		button0 = new JButton("Create a booking");
		button0.addActionListener(new ButtonListener());
		button1 = new JButton("Create a Facility");
		button1.addActionListener(new CreateFacilityButtonListener());
		button2 = new JButton("Reset member's password");
		button2.addActionListener(new ResetMemberButtonListener());
		button3 = new JButton("Search Facility");
		button3.addActionListener(new SearchFacilityButtonListener());
		button4 = new JButton("Change Admin Password");
		button4.addActionListener(new ChangeAdminPasswordButtonListener());
		button5 = new JButton("Log Out");
		button5.addActionListener(new LogoutButtonListener());
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		//panel.add(label);
		panel.add(button0);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("ADMIN MAIN MENU"));
		panel.setLayout(new GridLayout(6,1));
		panel.setBackground(Color.white);
		getContentPane().add(panel);
		
		
	}
	
	private class ButtonListener implements ActionListener{
	
		public void actionPerformed(ActionEvent e){
			controller.frameCreateBooking();
			
		}
	}
	private class CreateFacilityButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			controller.frameCreateFacility();
			
		}
	}
	private class ResetMemberButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			controller.frameResetPlayer();
			
		}
	}
	private class SearchFacilityButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			controller.frameSearchFacility();
			
		}
	}
	private class LogoutButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			controller.logOut();
			
		}
	}
	
	private class ChangeAdminPasswordButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			controller.frameChangeAdminPassword();
			
		}
	}
	
}
