

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Basic.Facility;
import Basic.Member;
import Basic.TimeSlot;



public class UpdateFacilityView extends JFrame{
	private Admin admin;
	//private AdminController controller;
	private JPanel panel;
	private JButton submitButton, cancelButton;
	private JLabel label0, label1;
	private JTextField input0, input1;
	private AdminView controller;
	private DataPool dp;
	private JTextField facilityName;
	private JTextField priceAtNonPeak;
	JComboBox facilityType;
	private JTextField priceAtPeak;
	private JTextField capacity;
	private int idToUpdate;
	private Facility fToUpdate;
	
	public UpdateFacilityView(AdminView controller, int idToUpdate){
		this.idToUpdate = idToUpdate;
		//
		dp = new DataPool();
		
		ArrayList<Facility> listOfAllFacilities = dp.getFacilityList();
		for(Facility f: listOfAllFacilities){
			if (f.getId() == this.idToUpdate) {
				fToUpdate = f;
				break;
			}
		}
		
		ArrayList<String> listOfType = new ArrayList<String>();
		listOfType.add("Restaurant");
		listOfType.add("Ktv");
		listOfType.add("Billiard");
		listOfType.add("Bbq pit");
		
		setBounds(100, 100, 450, 300);
		
		this.controller=controller;
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		//panel.setLayout(null);
		
		JLabel lblFacilityName = new JLabel("Facility Name");
		lblFacilityName.setBounds(6, 31, 142, 16);
		panel.add(lblFacilityName);
		
		facilityName = new JTextField();
		facilityName.setText(fToUpdate.getName());
		facilityName.setBounds(193, 26, 151, 26);
		panel.add(facilityName);
		facilityName.setColumns(10);
		
		JLabel lblFacility = new JLabel("Facility Type");
		lblFacility.setBounds(6, 56, 117, 16);
		panel.add(lblFacility);
		
		 facilityType = new JComboBox();

		for(String f: listOfType){
			facilityType.addItem(f);		
				}
		facilityType.setSelectedIndex(typeToIndex(fToUpdate.getType()));
		facilityType.setBounds(193, 52, 151, 27);
		panel.add(facilityType);
		
		JLabel lblSlot = new JLabel("Price at Peak");
		lblSlot.setBounds(6, 84, 130, 16);
		panel.add(lblSlot);
		
		JLabel lblNumberOfPax = new JLabel("Price At Non Peak");
		lblNumberOfPax.setBounds(6, 124, 142, 16);
		panel.add(lblNumberOfPax);
		
		priceAtNonPeak = new JTextField();
		priceAtNonPeak.setText(""+(fToUpdate.getNonPeakPrice()));
		priceAtNonPeak.setBounds(193, 119, 151, 26);
		panel.add(priceAtNonPeak);
		priceAtNonPeak.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(193, 191, 117, 29);
		btnSubmit.addActionListener(new SubmitButtonListener());
		panel.add(btnSubmit);
	
		//panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Update Facility"));
		//panel.setLayout(new GridLayout(3,2));
		panel.setBackground(Color.white);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(81, 192, 100, 26);
		btnCancel.addActionListener(new ButtonListener());
		panel.add(btnCancel);
		
		
		getContentPane().add(panel);
		
		priceAtPeak = new JTextField();
		priceAtPeak.setText(""+fToUpdate.getPeakPrice());
		priceAtPeak.setBounds(193, 79, 151, 26);
		panel.add(priceAtPeak);
		priceAtPeak.setColumns(10);
		
		JLabel lblCapacity = new JLabel("Capacity");
		lblCapacity.setBounds(6, 161, 117, 16);
		panel.add(lblCapacity);
		
		capacity = new JTextField();
		capacity.setText(""+fToUpdate.getCapacity());
		capacity.setBounds(193, 153, 151, 26);
		panel.add(capacity);
		capacity.setColumns(10);
		
		
		
		
	}
	
	private class ButtonListener implements ActionListener{
	
		public void actionPerformed(ActionEvent e){
			controller.frameSearchFacility();
		}
	}
	
	private class SubmitButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
//			try {
				//runAddTransactionSQL(int member, int facility, int timeSlot, int paxCount) 
			String name = facilityName.getText();
				String facility = (String) facilityType.getSelectedItem();
				int pricePeak = Integer.parseInt(priceAtPeak.getText());
				int priceNonPeak = Integer.parseInt(priceAtNonPeak.getText());
				int cap = Integer.parseInt(capacity.getText());
				
				controller.updateFacility(idToUpdate, name, facility, pricePeak, priceNonPeak, cap);
				JOptionPane.showMessageDialog(panel, "Facility Updated","Information", JOptionPane.PLAIN_MESSAGE);
//			} catch (CreateMemberException e1) {
//				JOptionPane.showMessageDialog(panel, e1.getMessage(),"Information", JOptionPane.PLAIN_MESSAGE);
//			}
			
		}
	}
	public int typeToIndex(String type){
		int index = 0;
		switch (type){
		case "Restaurant":
			index = 0;
			break;
			
		case "Ktv":
			index = 1;
			break;
			
		case "Billiard":
			index = 2;
			break;
			
		case "Bbq pit":
			index = 3;
			break;
			
		}
		return index;
	}

	
}
