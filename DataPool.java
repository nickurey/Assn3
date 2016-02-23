/*******************************************************************************
 *  CSCI Assignment 
 *  Filename: ${filename}
 *  Author: Nguyen Van Thanh Huy (Nick Nguyen)
 *  UOW Student Number: 5216746
 *  Description:
 *  
 *******************************************************************************/

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

import Basic.Facility;
import Basic.Member;
import Basic.TimeSlot;
import Basic.Transaction;
import Basic.User;
import Common.CreateMemberException;
import Common.DBQuery;;

public class DataPool {
    
    private DBQuery query;
    
    public DataPool()
    {
        
    	query = new DBQuery();
    }
    
    public void addMember(Member p) throws CreateMemberException, SQLException
    {
    	//sql insert statement
    	query.runAddMemberSQL(p);
       
    }
    
    public void removeMember(String name)
    {
    	//sql delete statement
    	query.runDeleteMemberSQL(name);
    	
    }
    
    public void updateMemberPassword(String name,String hashedPW){
    	
    	query.runUpdateMemberHassedPWSQL(name,hashedPW);
    }
    
    public boolean MemberExists(String loginName) {

		boolean state = true;

		try{
			User Member = getMember(loginName);
		}catch(InvalidMemberException e){
			state = false;
		}
		
		return state;
	}
    
    
    public User getMember(String loginName) throws InvalidMemberException
    {
    	//sql select statement
       User Member = query.getMember(loginName);
       
       if (Member == null)
    	   throw new InvalidMemberException();
       
       return Member;
    }
    public ArrayList<Facility> getFacilityList(){
    	String statement = "Select * from Facility";
        ArrayList<Facility> Facilities = query.runGetFacilitys(statement);
        
    	return Facilities;	
    }
    public ArrayList<Transaction> getTransactionList(){
    	String statement = "Select * from Transaction where status ='1'";
        ArrayList<Transaction> Transactions = query.runGetTransactions(statement);
        
    	return Transactions;	
    }
    public ArrayList<TimeSlot> getSlotList(){
    	String statement = "Select * from timeslot";
        ArrayList<TimeSlot> TimeSlot_ = query.runGetTimeSlot(statement);
        
    	return TimeSlot_;	
    }
    public ArrayList<User> getMemberList(){
    	
    	//select all statemenet
    	//sql select statement
    	String statement = "Select * from User";
        ArrayList<User> Members = query.runGetMemberListQuery(statement);
        
    	return Members;	
    }
    

    
    public void closeConnection(){
    	
    	query.closeConnection();
    }



	public void saveMembersData() {
		// TODO Auto-generated method stub
		
	}

	public void createBooking(String date, int member, int facility, int timeSlot, int paxCount, int price) {
		// TODO Auto-generated method stub
		query.runAddTransactionSQL(date, member, facility, timeSlot, paxCount, price);
	}

	public void createFacility(String name, String facility, int pricePeak, int priceNonPeak, int cap) {
		// TODO Auto-generated method stub
		query.runInsertFacility(name, facility, pricePeak, priceNonPeak, cap);
	}

	public void removeFacility(int idToDelete) {
		// TODO Auto-generated method stub
		query.runRemoveFacility(idToDelete);
	}

	public void updateFacility(int idToUpdate, String name, String facility, int pricePeak, int priceNonPeak, int cap) {
		// TODO Auto-generated method stub
		query.runUpdateFacility(idToUpdate,  name,  facility,  pricePeak,  priceNonPeak, cap);
	}

	public int getTotalBookingCountAt(String date, int timeSlot, int facilityID) {
		// TODO Auto-generated method stub
		return query.runGetTotalBookingCountAt(date,  timeSlot,  facilityID);
	}

	public void deactivateBooking(int idToDeactivate) {
		// TODO Auto-generated method stub
		query.runDeactivateBooking(idToDeactivate);
	}
    
     
    
}
