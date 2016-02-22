import java.util.*;

import Basic.Facility;
import Basic.Member;
import Basic.TimeSlot;
import Basic.Utility;

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
	
	public void createMember(String name, String password) throws CreateMemberException{

		try{
			Member p = DataPool.getMember(name);
			
			throw new CreateMemberException("Member name exists");
			
		}catch(InvalidMemberException ex){
			
			//The password's first character must be a letter, 
			//it must contain at least 4 characters and no more than 15 characters and 
			//no characters other than letters, numbers and the underscore may be used
			if(!password.matches("^[a-zA-Z]\\w{3,14}$")) throw new CreateMemberException("Strong password required");
			
			password = Utility.getHash(password);
			Member p = new Member(1, name, password, 0);
			DataPool.addMember(p);
		}
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

	public ArrayList<Member> getTopScorer(){
		
		ArrayList<Member> topList = new ArrayList<Member>();
		ArrayList<Member> MemberList =  DataPool.getMemberList();
		
		//get the highest score
		int highestScore = MemberList.get(0).getPoints();
		for(Member Member: MemberList){
			
			if(Member.getPoints()>highestScore){
				highestScore = Member.getPoints();
			}
		}
		
		for(Member Member: MemberList){
			
			if(Member.getPoints()==highestScore){
				topList.add(Member);				
			}
		}
		
		return topList;
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
