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
import java.util.*;

import Basic.Facility;
import Basic.Member;
import Basic.TimeSlot;
import Common.DBQuery;;

public class DataPool {
    
    private DBQuery query;
    
    public DataPool()
    {
        
    	query = new DBQuery();
    }
    
    public void addMember(Member p)
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
			Member Member = getMember(loginName);
		}catch(InvalidMemberException e){
			state = false;
		}
		
		return state;
	}
    
    
    public Member getMember(String loginName) throws InvalidMemberException
    {
    	//sql select statement
       Member Member = query.getMember(loginName);
       
       if (Member == null)
    	   throw new InvalidMemberException();
       
       return Member;
    }
    public ArrayList<Facility> getFacilityList(){
    	String statement = "Select * from Facility";
        ArrayList<Facility> Facilities = query.runGetFacilitys(statement);
        
    	return Facilities;	
    }
    public ArrayList<TimeSlot> getSlotList(){
    	String statement = "Select * from timeslot";
        ArrayList<TimeSlot> TimeSlot_ = query.runGetTimeSlot(statement);
        
    	return TimeSlot_;	
    }
    public ArrayList<Member> getMemberList(){
    	
    	//select all statemenet
    	//sql select statement
    	String statement = "Select * from User";
        ArrayList<Member> Members = query.runGetMemberListQuery(statement);
        
    	return Members;	
    }
    
    public ArrayList<Member> getTopScorerMemberList(){
    	
    	return query.getTopScorer();
    }
    
    public void closeConnection(){
    	
    	query.closeConnection();
    }



	public void saveMembersData() {
		// TODO Auto-generated method stub
		
	}

	public void createBooking(int member, int facility, int timeSlot, int paxCount) {
		// TODO Auto-generated method stub
		query.runAddTransactionSQL(member, facility, timeSlot, paxCount);
	}

	public void createFacility(String name, String facility, int pricePeak, int priceNonPeak, int cap) {
		// TODO Auto-generated method stub
		query.runInsertFacility(name, facility, pricePeak, priceNonPeak, cap);
	}
    
     
    
}
