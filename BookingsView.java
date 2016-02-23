

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Basic.Facility;
import Basic.Member;
import Basic.TimeSlot;
import Basic.Transaction;




public class BookingsView extends JFrame{
	private Admin admin;
	//private AdminController controller;
	private JPanel panel;
	private JButton submitButton, cancelButton;
	private JLabel label0, label1;
	private JTextField input0, input1;
	private AdminView controller;
	private DataPool dp;
	private JComboBox facilityType;
	private JTable table;
	private ButtonGroup selectedBooking;
	private static final Object[][] rowData = {};
	private static final Object[] columnNames = {"ID","Date","Member","Facility","TimeSlot","PaxCount","Price"};
	private JPanel searchResult;
	private JTable table_1;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel label;
	
	public BookingsView(AdminView controller){

		//
		dp = new DataPool();

		ArrayList<String> listOfType = new ArrayList<String>();
		listOfType.add("All");
		listOfType.add("Restaurant");
		listOfType.add("Ktv");
		listOfType.add("Billiard");
		listOfType.add("Bbq pit");

		setBounds(100, 100, 450, 300);

		this.controller=controller;
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);

		facilityType = new JComboBox();

		for(String f: listOfType){
			facilityType.addItem(f);		
		}
		facilityType.setBounds(173, 11, 151, 27);
		panel.add(facilityType);

		JLabel filterLabel = new JLabel("Filter");
		filterLabel.setBounds(108, 23, 54, 16);
		panel.add(filterLabel);

		//panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Booking"));
		//panel.setLayout(new GridLayout(3,2));
		panel.setBackground(Color.white);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(324, 11, 100, 26);
		btnCancel.addActionListener(new ButtonListener());
		panel.add(btnCancel);


		getContentPane().add(panel);
		
		searchResult = new JPanel();
		searchResult.setBounds(6, 49, 418, 181);
		panel.add(searchResult);
		searchResult.setLayout(null);

			btnDelete = new JButton("Book");
			btnDelete.setBounds(212, 235, 117, 29);
			btnDelete.addActionListener(new ButtonDeactivateListener());
			panel.add(btnDelete);

		
		
		label = new JLabel("");
		label.setBounds(6, 248, 205, 16);
		panel.add(label);
		

	}

	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			controller.mainMenu();
		}
	}
	private class ButtonUpdateListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
//			label.setText(selectedFacility.getSelection().getActionCommand());
			int idToUpdate = Integer.parseInt(selectedBooking.getSelection().getActionCommand());
			controller.frameUpdateFacility(idToUpdate);
		}
	}
	private class ButtonDeactivateListener_ implements ActionListener{

		public void actionPerformed(ActionEvent e){

			String facility = (String) facilityType.getSelectedItem();
			
			ArrayList<Transaction> fList = dp.getTransactionList();
			DefaultTableModel listTableModel;
			listTableModel = new DefaultTableModel(rowData, columnNames);
			int resultCount = 0;
			JRadioButton bt;
			for (Transaction f: fList) {
				
					if (!(facility.equalsIgnoreCase("all"))){
						if (f.getFacility().getType().toLowerCase().contains(facility.toLowerCase())){
							bt = new JRadioButton();
							bt.setActionCommand(String.valueOf(f.getID()));
							resultCount++;
							// {"ID","Date","Member","Facility","TimeSlot","PaxCount","Price"};
							listTableModel.addRow(new Object[]{f.getID(), f.getDate(), f.getUser().getfName() + f.getUser().getlName(),
									f.getTimeSlot().toString(), f.getPaxCount(),f.getPrice(), f.getStatus()});
							
						}
					} else {
						bt = new JRadioButton();
						bt.setActionCommand(String.valueOf(f.getID()));
						resultCount++;
						listTableModel.addRow(new Object[]{f.getID(), f.getDate(), f.getUser().getfName() + f.getUser().getlName(),
								f.getTimeSlot().toString(), f.getPaxCount(),f.getPrice(), f.getStatus()});
					}
					
				
				
				}
			label.setText("Found "+resultCount+(resultCount==1?" booking.":" bookings."));
			table = new JTable(listTableModel){
				public void tableChanged(TableModelEvent e) {
					super.tableChanged(e);
					repaint();
				}
			};
			selectedBooking = new ButtonGroup();
			for (int i = 0; i < resultCount; i++){
				selectedBooking.add((JRadioButton) listTableModel.getValueAt(i, 5));	
			}

			table.getColumn("Select").setCellRenderer(
					new RadioButtonRenderer());
			table.getColumn("Select").setCellEditor(
					new RadioButtonEditor(new JCheckBox()));
			//table = new JTable();

			table.setBounds(6, 6, 426, 177);
			System.out.println(table.toString());
			//JScrollPane scroll = new JScrollPane(table);
		    
			searchResult.removeAll();

			searchResult.add(table);
			searchResult.revalidate();

			searchResult.repaint();
			//JScrollPane scroll = new JScrollPane(table);

			//getContentPane().add(scroll);
			//controller.createFacility(name, facility, pricePeak, priceNonPeak, cap);
			//JOptionPane.showMessageDialog(panel, "Player Created","Information", JOptionPane.PLAIN_MESSAGE);
			//			} catch (CreateMemberException e1) {
			//				JOptionPane.showMessageDialog(panel, e1.getMessage(),"Information", JOptionPane.PLAIN_MESSAGE);
			//			}

		}
	}
	class RadioButtonRenderer implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if (value == null)
				return null;
			return (Component) value;
		}
	}

	private class ButtonDeactivateListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			int idToDeactivate = Integer.parseInt(selectedBooking.getSelection().getActionCommand());
			int n = JOptionPane.showConfirmDialog(panel, "The selected Booking will be deactivated. Confirm?","Warning", JOptionPane.OK_CANCEL_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				dp.deactivateBooking(idToDeactivate);
				searchResult.removeAll();
				searchResult.revalidate();
				searchResult.repaint();
			} else if (n == JOptionPane.NO_OPTION) {

			} else {

			}
		}
	}

	class RadioButtonEditor extends DefaultCellEditor implements ItemListener {
		private JRadioButton button;

		public RadioButtonEditor(JCheckBox checkBox) {
			super(checkBox);
		}

		public Component getTableCellEditorComponent(JTable table, Object value,
				boolean isSelected, int row, int column) {
			if (value == null)
				return null;
			button = (JRadioButton) value;
			button.addItemListener(this);
			return (Component) value;
		}

		public Object getCellEditorValue() {
			button.removeItemListener(this);
			return button;
		}

		public void itemStateChanged(ItemEvent e) {
			super.fireEditingStopped();
		}
	}

}
