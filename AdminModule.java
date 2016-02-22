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

import Basic.Utility;


public class AdminModule {
	private LoginView login;
	public boolean authStatus = false;
	private JPanel panel;
	private JButton submitButton;
	private JLabel label0;
	private JTextField input0;
	private JFrame loginFrame;
	public static void main(String args[]) {
		new AdminModule().run();
	}

	public void run() {
		loginFrame = new JFrame();
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		loginFrame.add(panel);
		//loginFrame.setLocationRelativeTo(c);
		//System.out.println(loginFrame.getLocationOnScreen());
		//loginFrame.pack();
		//loginFrame.setPreferredSize(new Dimension(400, 300));
		loginFrame.pack();
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setVisible(true);
		

	}
	
	private class SubmitButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			String password = input0.getText();
			String hashedPassword = null;
			try {
				Scanner fileScanner = new Scanner(new File("admin.dat"));
				
//				while (fileScanner.hasNextLine()) {
//					hashedPassword = fileScanner.nextLine();
//				}
//				if(Utility.getHash(password).equals(hashedPassword)) {
					loginFrame.setVisible(false);
					Admin admin = new Admin();
					DataPool playerPool = new DataPool();
					AdminController controller = new AdminController(admin, playerPool);
					AdminView view = new AdminView(admin, controller);
					controller.attachView(view);
					controller.run();
//				}
//				else JOptionPane.showMessageDialog(panel, "Log in failed!","Information", JOptionPane.PLAIN_MESSAGE);
//				fileScanner.close();

			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(panel, "Admin data not found","Information", JOptionPane.PLAIN_MESSAGE);

			}
			
			
		}
		

	}

	

}
