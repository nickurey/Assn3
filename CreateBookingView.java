

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
	private JLabel note;
	JComboBox facilityList;
	JComboBox slotList;
	
	public CreateBookingView(AdminView controller, int idToBook){
		
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
		
		 int selectedIndex = 0;
		 
		for(Facility f: listF){
			facilityList.addItem(f);
			
				}
		for(Facility f: listF){
			if (idToBook != f.getId()) {
				selectedIndex++;
				break;
			}
			
				}
		
		if (idToBook != 0){
			facilityList.setSelectedIndex(selectedIndex);
		}
		facilityList.setBounds(193, 52, 151, 27);
		facilityList.addActionListener (new RefreshPriceOnDemand ());
		
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
		slotList.addActionListener (new RefreshPriceOnDemand ());
		
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
		
		note = new JLabel("Price will be different during peak hours");
		note.setBounds(10, 210, 208, 14);
		panel.add(note);
		
		
		
		
	}
	
	private class ButtonListener implements ActionListener{
	
		public void actionPerformed(ActionEvent e){
			controller.mainMenu();
		}
	}
	public class RefreshPriceOnDemand implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Facility selectedF = (Facility) facilityList.getSelectedItem();
			TimeSlot selectedT = (TimeSlot) slotList.getSelectedItem();
			String date = chosenDate.getText();
			
			String isPeak= "";
			if (getRemainingCapacityAtSlot(date, selectedT.getID(), selectedF.getId()) >= selectedF.getCapacity()) isPeak = "- (Peak Hours)";
		note.setText("Price per table/room is:"+ calculatePriceBasedOnDemand(selectedF, selectedT, date)+ isPeak);	
		}
	}
	
	private class SubmitButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
//			try {
				//runAddTransactionSQL(int member, int facility, int timeSlot, int paxCount) 
				Facility selectedF = (Facility) facilityList.getSelectedItem();
				TimeSlot selectedT = (TimeSlot) slotList.getSelectedItem();
				String date = chosenDate.getText();
				int unitprice = calculatePriceBasedOnDemand(selectedF, selectedT, date);
				//Check if booking pax count is greater than capacity left.
				if (Integer.parseInt( noOfPax.getText()) > (selectedF.getCapacity() - getRemainingCapacityAtSlot(date, selectedT.getID(), selectedF.getId()))){
					JOptionPane.showMessageDialog(panel, "Please enter pax count from 1 to "+(selectedF.getCapacity() - getRemainingCapacityAtSlot(date, selectedT.getID(), selectedF.getId())),"Information", JOptionPane.PLAIN_MESSAGE);
				} else{
					int price = Integer.parseInt( noOfPax.getText()) * unitprice ;
					controller.createBooking(chosenDate.getText(), selectedF.getId(), selectedT.getID(), Integer.parseInt(noOfPax.getText()), price );
					JOptionPane.showMessageDialog(panel, "Transaction recorded","Information", JOptionPane.PLAIN_MESSAGE);
				}
				
//			} catch (CreateMemberException e1) {
//				JOptionPane.showMessageDialog(panel, e1.getMessage(),"Information", JOptionPane.PLAIN_MESSAGE);
//			}
			
		}
	}

	public void setFacilityID(int idToBook) {
		// TODO Auto-generated method stub
		
	}
	public int getRemainingCapacityAtSlot(String date, int timeSlot, int facilityID){
		int i = dp.getTotalBookingCountAt(date, timeSlot, facilityID);
		return i;
	}
	public int calculatePriceBasedOnDemand(Facility selectedF, TimeSlot selectedT, String date){

		//Peak: Number of total pax count >= half of Facility capacity
		
		if (getRemainingCapacityAtSlot(date, selectedT.getID(), selectedF.getId()) >= selectedF.getCapacity()) return selectedF.getPeakPrice();
		else 
		return selectedF.getNonPeakPrice();
	}
}
