

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Basic.Facility;
import Basic.Member;
import Basic.TimeSlot;



public class CreateBookingView extends JFrame{
	private Admin admin;
	//private AdminController controller;
	private JPanel panel;
	private JButton submitButton, cancelButton;
	private JLabel label0, label1;
	private JTextField input0, input1;
	private AdminView controller;
	private DataPool dp;
	private JTextField chosenDate;
	private JTextField noOfPax;
	JComboBox facilityList;
	JComboBox slotList;
	
	public CreateBookingView(AdminView controller){
		
		//
		dp = new DataPool();
		ArrayList<TimeSlot> listT = dp.getSlotList();
		ArrayList<Facility> listF = dp.getFacilityList();
		
		setBounds(100, 100, 450, 300);
		
		this.controller=controller;
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		//panel.setLayout(null);
		
		JLabel lblDateOfAppointment = new JLabel("Date of Appointment");
		lblDateOfAppointment.setBounds(6, 31, 142, 16);
		panel.add(lblDateOfAppointment);
		
		chosenDate = new JTextField();
		chosenDate.setBounds(193, 26, 151, 26);
		panel.add(chosenDate);
		chosenDate.setColumns(10);
		
		JLabel lblFacility = new JLabel("Facility");
		lblFacility.setBounds(6, 56, 61, 16);
		panel.add(lblFacility);
		
		 facilityList = new JComboBox();

		for(Facility f: listF){
			facilityList.addItem(f);		
				}
		facilityList.setBounds(193, 52, 151, 27);
		panel.add(facilityList);
		
		JLabel lblSlot = new JLabel("Slot");
		lblSlot.setBounds(6, 84, 61, 16);
		panel.add(lblSlot);
		
		JLabel lblNumberOfPax = new JLabel("Number of Pax");
		lblNumberOfPax.setBounds(6, 124, 142, 16);
		panel.add(lblNumberOfPax);
		
		noOfPax = new JTextField();
		noOfPax.setBounds(193, 119, 151, 26);
		panel.add(noOfPax);
		noOfPax.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(193, 157, 117, 29);
		btnSubmit.addActionListener(new SubmitButtonListener());
		panel.add(btnSubmit);
		
		
		
		 slotList = new JComboBox();
		for(TimeSlot t: listT){
			slotList.addItem(t);		
				}
		
		slotList.setBounds(193, 80, 151, 27);
		panel.add(slotList);
		//panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Create new booking"));
		//panel.setLayout(new GridLayout(3,2));
		panel.setBackground(Color.white);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(81, 158, 100, 26);
		btnCancel.addActionListener(new ButtonListener());
		panel.add(btnCancel);
		
		
		getContentPane().add(panel);
		
		
		
		
	}
	
	private class ButtonListener implements ActionListener{
	
		public void actionPerformed(ActionEvent e){
			controller.mainMenu();
		}
	}
	
	private class SubmitButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
//			try {
				//runAddTransactionSQL(int member, int facility, int timeSlot, int paxCount) 
				Facility selectedF = (Facility) facilityList.getSelectedItem();
				TimeSlot selectedT = (TimeSlot) slotList.getSelectedItem();
				controller.createBooking(selectedF.getId(), selectedT.getID(), Integer.parseInt(noOfPax.getText()));
				JOptionPane.showMessageDialog(panel, "Player Created","Information", JOptionPane.PLAIN_MESSAGE);
//			} catch (CreateMemberException e1) {
//				JOptionPane.showMessageDialog(panel, e1.getMessage(),"Information", JOptionPane.PLAIN_MESSAGE);
//			}
			
		}
	}

	
}
