import java.util.*;

import Basic.Facility;
import Basic.Member;
import Basic.TimeSlot;
import Basic.Utility;
import Common.CreateMemberException;

public class AdminController {

	private DataPool DataPool;
	private Admin admin;
	private AdminView view2;
	public AdminController(Admin admin, DataPool DataPool) {
		this.DataPool = DataPool;
		this.admin = admin;
	}


	public void attachView(AdminView view){
		
		this.view2 = view;
	}
	
	public void run(){
		view2.show();
	}
	
	

	public void deleteMember(String name) throws InvalidMemberException{
		try{
			Member p = DataPool.getMember(name);
			DataPool.removeMember(name);
		}
		catch(InvalidMemberException ex){
			throw new InvalidMemberException();
		}
	}

	public void resetMemberPassword(String name, String password) throws CreateMemberException{
		try{
			Member p = DataPool.getMember(name);
			if(!password.matches("^[a-zA-Z]\\w{3,14}$")) throw new CreateMemberException("Strong password required");
			password = Utility.getHash(password);
			p.setHashedPassword(password);
		}
		catch(InvalidMemberException ex){
			throw new CreateMemberException("Member not found");
		}
		
	}

	public ArrayList<Facility> getFacilityList(){
		return DataPool.getFacilityList();
	}
	public ArrayList<TimeSlot> getSlotList(){
		return DataPool.getSlotList();
	}
	
	public void changeAdminPassword(String password1, String password2) throws InvalidPasswordException {
		
		if (password1.equals(password2)) {
			password2 = Utility.getHash(password2);
			admin.setHashedPassword(password2);
		} else { 
			throw new InvalidPasswordException();
			//System.out.println("Different passwords, update failed");
		}
	}
	
	public void saveMemberData(){
		DataPool.saveMembersData();
	}


	public void createBooking(int facility, int timeSlot, int paxCount) {
		// TODO Auto-generated method stub
		int a = admin.getId();
		DataPool.createBooking(a, facility,  timeSlot,  paxCount);
	}


	public void createFacility(String name, String facility, int pricePeak, int priceNonPeak, int cap) {
		// TODO Auto-generated method stub
		DataPool.createFacility(name, facility, pricePeak, priceNonPeak, cap);
	}


	public void updateFacility(int idToUpdate, String name, String facility, int pricePeak, int priceNonPeak, int cap) {
		// TODO Auto-generated method stub
		DataPool.updateFacility(idToUpdate,  name,  facility,  pricePeak,  priceNonPeak, cap);
	}

}
