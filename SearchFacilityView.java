

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



public class SearchFacilityView extends JFrame{
	private Admin admin;
	//private AdminController controller;
	private JPanel panel;
	private JButton submitButton, cancelButton;
	private JLabel label0, label1;
	private JTextField input0, input1;
	private AdminView controller;
	private DataPool dp;
	private JTextField searchField;
	private JComboBox facilityType;
	private JTable table;
	private ButtonGroup selectedFacility;
	private static final Object[][] rowData = {};
	private static final Object[] columnNames = {"ID","Name","Type","Peak Price","Off Peak Price","Select"};
	private JPanel searchResult;
	private JTable table_1;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel label;
	
	public SearchFacilityView(AdminView controller){

		//
		dp = new DataPool();

		ArrayList<String> listOfType = new ArrayList<String>();
		listOfType.add("all");
		listOfType.add("restaurant");
		listOfType.add("ktv");

		setBounds(100, 100, 450, 300);

		this.controller=controller;
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);

		searchField = new JTextField();
		searchField.setBounds(6, 26, 338, 26);
		panel.add(searchField);
		searchField.setColumns(10);

		facilityType = new JComboBox();

		for(String f: listOfType){
			facilityType.addItem(f);		
		}
		facilityType.setBounds(193, 52, 151, 27);
		panel.add(facilityType);

		JLabel filterLabel = new JLabel("Filter");
		filterLabel.setBounds(128, 56, 54, 16);
		panel.add(filterLabel);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(344, 26, 100, 29);
		btnSearch.addActionListener(new SubmitButtonListener());
		panel.add(btnSearch);

		//panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Search Facility"));
		//panel.setLayout(new GridLayout(3,2));
		panel.setBackground(Color.white);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(344, 52, 100, 26);
		btnCancel.addActionListener(new ButtonListener());
		panel.add(btnCancel);


		getContentPane().add(panel);
		
		searchResult = new JPanel();
		searchResult.setBounds(6, 83, 438, 155);
		panel.add(searchResult);
		searchResult.setLayout(null);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(327, 243, 117, 29);
		btnUpdate.addActionListener(new ButtonUpdateListener());
		panel.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(212, 243, 117, 29);
		btnDelete.addActionListener(new ButtonDeleteListener());
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
			int idToUpdate = Integer.parseInt(selectedFacility.getSelection().getActionCommand());
			controller.frameUpdateFacility(idToUpdate);
		}
	}
	private class ButtonDeleteListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			int idToDelete = Integer.parseInt(selectedFacility.getSelection().getActionCommand());
			int n = JOptionPane.showConfirmDialog(panel, "The selected Facility will be delete. Confirm?","Warning", JOptionPane.OK_CANCEL_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				dp.removeFacility(idToDelete);
				searchResult.removeAll();
				searchResult.revalidate();
				searchResult.repaint();
			} else if (n == JOptionPane.NO_OPTION) {

			} else {

			}
		}
	}

	private class SubmitButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){

			String searchTerm = searchField.getText();
			String facility = (String) facilityType.getSelectedItem();
			
			ArrayList<Facility> fList = dp.getFacilityList();
			DefaultTableModel listTableModel;
			listTableModel = new DefaultTableModel(rowData, columnNames);
			int resultCount = 0;
			JRadioButton bt;
			for (Facility f: fList) {
				if (f.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
					if (!(facility.equalsIgnoreCase("all"))){
						if (f.getType().toLowerCase().contains(facility.toLowerCase())){
							bt = new JRadioButton();
							bt.setActionCommand(String.valueOf(f.getId()));
							resultCount++;
							listTableModel.addRow(new Object[]{f.getId(), f.getName(), f.getType(), f.getPeakPrice(), f.getNonPeakPrice(), bt});
							
						}
					} else {
						bt = new JRadioButton();
						bt.setActionCommand(String.valueOf(f.getId()));
						resultCount++;
						listTableModel.addRow(new Object[]{f.getId(), f.getName(), f.getType(), f.getPeakPrice(), f.getNonPeakPrice(), bt});
						
					}
					
				}
				
				}
			label.setText("Found "+resultCount+(resultCount==1?" record.":" records."));
			table = new JTable(listTableModel){
				public void tableChanged(TableModelEvent e) {
					super.tableChanged(e);
					repaint();
				}
			};
			selectedFacility = new ButtonGroup();
			for (int i = 0; i < resultCount; i++){
				selectedFacility.add((JRadioButton) listTableModel.getValueAt(i, 5));	
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
