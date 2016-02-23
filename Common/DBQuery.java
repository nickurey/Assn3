/*******************************************************************************
 *  CSCI Assignment 
 *  Filename: ${filename}
 *  Author: Nguyen Van Thanh Huy (Nick Nguyen)
 *  UOW Student Number: 5216746
 *  Description:
 *  
 *******************************************************************************/
package Common;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import Basic.Facility;
import Basic.Member;
import Basic.TimeSlot;
import Basic.Transaction;
import Basic.User;

public class DBQuery {

	private static final String userName = "root";
	private static final String userPassword = "";
	private static String dbDriverName = "com.mysql.jdbc.Driver";
	private static String dbURL = "jdbc:mysql://localhost/ccfbs";
	private static Connection dbConnection;

	public DBQuery(){
		connectToDatabase();
	}

	// Connect and get a dbConnection
	private void connectToDatabase() {
		try {
			Class.forName(dbDriverName);
			dbConnection = DriverManager.getConnection(dbURL, userName,
					userPassword);

		} catch (Exception e) {
			System.out.println("Failure to connect to database");
			System.out.println(e.getMessage());
			System.exit(0);
		}

	}
	
	public User getMember(String loginName){
		String query = "Select * from User where username='"+loginName+"'";
		ArrayList<User> Users = runGetMemberListQuery(query); 
		User u = null;
		
		if(Users.size()!=0){
			u = Users.get(0);
		}
		
		return u;
	}
	
	public User authenticateMember(String loginName, String hashedPassword){
		String query = "Select * from User where username='"+loginName+"' and password='"+hashedPassword+"'";
		ArrayList<User> Members = runGetMemberListQuery(query); 
		User u = null;
		
		if(Members.size()!=0){
			u = Members.get(0);
		}
		
		return u;
	}

	public ArrayList<User> runGetMemberListQuery(String query) {
		
    
		ArrayList<User> Users = new ArrayList<User>();
		
		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				int id = Integer.parseInt(rset.getString("id"));
				String login = rset.getString("username");
				String fN = rset.getString("firstName");
				String lN = rset.getString("lastname");
				String hashPW = rset.getString("password");
				String role = rset.getString("role");
				int membership = Integer.parseInt(rset.getString("membership"));
				
				User user = new Member(id, login,hashPW, membership);
				user.setfName(fN);
				user.setlName(lN);
				user.setRole(role);
				Users.add(user);
			}

		} catch (Exception e) {
			System.out.println("Query failed");
			System.out.println(e.getMessage());
		}
		
		return Users;
	}

	public boolean checkIfMemberExists(String username){
		String sql = "SELECT count(*) FROM user WHERE username = '"+username+"'";
System.out.println(sql);
		ResultSet rset;
		int rowCount = 0;
		try {
			
			Statement stmt = dbConnection.createStatement();
			rset = stmt.executeQuery(sql);
			if (rset.next())
			rowCount = rset.getInt(1);
		
		} catch (Exception e) {
			System.out.println("Add SQL failed");
			System.out.println(e.getMessage());
		}
		
		if (rowCount == 1) return true;
		else return false;
	}
	public void runAddMemberSQL(Member Member) throws CreateMemberException, SQLException {
		
//		try {
			if (checkIfMemberExists(Member.getLoginName())){
				throw new CreateMemberException("Member exists");
			}
			String sql = "Insert into User (username,password,firstname, lastname, membership, role)";
			sql+=" value('"+Member.getLoginName()+"','"+Member.getHashedPassword()+"','"+Member.getfName()+"','"+Member.getlName()+"','"+Member.getMembership()+"','"+Member.getRole()+"')";
			System.out.println(sql);
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(sql);
//		} catch (Exception e) {
//			System.out.println("Add SQL failed");
//			System.out.println(e.getMessage());
//		}
	}
	
	public void runDeleteMemberSQL(String name) {
		
		try {
			
			String sql = "Delete from User where username = '"+name+"'";
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Delete SQL failed");
			System.out.println(e.getMessage());
		}
	}
	
	public void runUpdateMemberHassedPWSQL(String name,String hashedPW){

		try {
			
			String sql = "Update User set password='"+hashedPW+"' where username = '"+name+"'";
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Update hashedPassword SQL failed");
			System.out.println(e.getMessage());
		}
		
	}
	public void runUpdateMemberScore(String name, int points){

		try {
			
			String sql = "Update User set points='"+points+"' where username = '"+name+"'";
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Update hashedPassword SQL failed");
			System.out.println(e.getMessage());
		}
		
	}
	public void updateDeck(int size){

		try {
			
			String sql = "Update Facility set count='"+size+"'";
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Update Deck Size failed");
			System.out.println(e.getMessage());
		}
		
	}
	public void closeConnection() {

		try {
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Facility> runGetFacilitys(String query) {
		
		ArrayList<Facility> Facilities = new ArrayList<Facility>();
		
		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				String facilityName = rset.getString("FacilityName");
				String facilityType = rset.getString("FacilityType");
				int id = Integer.parseInt(rset.getString("id"));
				int priceAtPeak = Integer.parseInt(rset.getString("PriceAtPeak"));
				int priceAtNonPeak = Integer.parseInt(rset.getString("PriceAtNonPeak"));
				int capacity = Integer.parseInt(rset.getString("Capacity"));


					Facility Facility = new Facility(id, facilityName, facilityType, priceAtPeak, priceAtNonPeak, capacity);
					Facilities.add(Facility);

			}
		} catch (Exception e) {
			System.out.println("Query failed");
			System.out.println(e.getMessage());
		}
		
		return Facilities;
	}
	public ArrayList<TimeSlot> runGetTimeSlot(String query) {
		
		ArrayList<TimeSlot> timeSlot = new ArrayList<TimeSlot>();
		
		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				String desc = rset.getString("description");
				int id = Integer.parseInt(rset.getString("id"));


				TimeSlot t = new TimeSlot(id, desc);
				timeSlot.add(t);

			}
		} catch (Exception e) {
			System.out.println("Query failed");
			System.out.println(e.getMessage());
		}
		
		return timeSlot;
	}
	
	public void runAddTransactionSQL(String date, int member, int facility, int timeSlot, int paxCount, int price) {
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
			Date aDate = format.parse("15/02/2016");
			String sql = "Insert into Transaction (TransactionDate,MemberUserID,FacilityID,TimeSlot,PaxCount,Price,Status)";
			//sql+=" value(TO_DATE('15/02/2016','dd/mm/yyyy'), "
			sql+=" value('"+date+"', "
					+"'"+member
					+"','"+facility
					+"','"+timeSlot+"',"
					+paxCount+","+price
					+", 1"+
					")";
			System.out.println(sql);
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Add SQL failed");
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args){
		
		//test out this class
		DBQuery app = new DBQuery();
		ArrayList<Facility> Facilities = app.runGetFacilitys("Select * from Facility");
		for(Facility c: Facilities){
			System.out.println(c.toString()+ " "+c.getType() + " "+ c.getPeakPrice() + " "+ c.getNonPeakPrice() );
			
		}
		
		//test add new transaction
		//Member newMember = new Member("Nick","",1);
		//app.runAddTransactionSQL(newMember.getId(), Facilities.get(0), 0, 0);
		
		app.closeConnection();
		
	}

	public void runInsertFacility(String name, String facility, int pricePeak, int priceNonPeak, int cap) {
		// TODO Auto-generated method stub
		try {
			String sql = "Insert into Facility (facilityName,facilityType,priceAtPeak,PriceAtNonPeak,Capacity)";
			sql+=" value('"+name
					+"','"+facility
					+"','"+pricePeak
					+"',"+priceNonPeak
					+", "+cap+
					")";
			System.out.println(sql);
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Add SQL failed");
			System.out.println(e.getMessage());
		}
	}
	
	public void runSearchFacility(String name, String facility) {
		// TODO Auto-generated method stub
		String sql;
		if (facility.equalsIgnoreCase("all"))
			sql = "Select * from Facility where FacilityName like '%"+name+"%'";
		else 
			sql = "Select * from Facility where FacilityName like '%"+name+"%' and FacilityType = '"+facility+"'";
		try {
			System.out.println(sql);
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Add SQL failed");
			System.out.println(e.getMessage());
		}
	}

	public void runRemoveFacility(int idToDelete) {
		// TODO Auto-generated method stub
		String sql = "Delete from Facility where ID = '"+idToDelete+"'";
		System.out.println(sql);
		try {
			System.out.println(sql);
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("SQL failed");
			System.out.println(e.getMessage());
		}
	}

	public void runUpdateFacility(int idToUpdate, String name, String facility, int pricePeak, int priceNonPeak,
			int cap) {
		// TODO Auto-generated method stub
		String sql = "Update Facility SET "
		+"FacilityName = '"+name+"' ,"
		+"FacilityType = '"+facility+"' ,"
		+"PriceAtPeak = '"+pricePeak+"' ,"
		+"PriceAtNonPeak = '"+priceNonPeak+"' ,"
		+"capacity = '"+cap+"' where ID = '"+idToUpdate+"'"
		;
		System.out.println(sql);
		try {
			System.out.println(sql);
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("SQL failed");
			System.out.println(e.getMessage());
		}
	}

	public int runGetTotalBookingCountAt(String date, int timeSlot, int facilityID) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql; 
			sql = "Select * from Transaction where TransactionDate = '"+date+"' and timeSlot = '"+timeSlot+"' and facilityID ='"+facilityID+"' and status = '1'";
		try {
			System.out.println(sql);
			Statement stmt = dbConnection.createStatement();
			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				int pax = Integer.parseInt(rset.getString("PaxCount"));
				count+=pax;
			}
			System.out.println("Pax: "+count);
		} catch (Exception e) {
			System.out.println("Add SQL failed");
			System.out.println(e.getMessage());
		}
		return count;
	}

}
