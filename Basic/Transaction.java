package Basic;


import java.util.Date;

public class Transaction {
	private User user;
	private Facility facility;
	private TimeSlot timeSlot;
	private String date;
	private int paxCount;
	private int price;
	private int status;
	private int id;

	public Transaction( int transactionID,String d, User u, Facility f, TimeSlot ts, int pax, int p,
			int status2) {
		id = transactionID;
		date = d;
		user = u;
		facility = f;
		timeSlot = ts;
		paxCount = pax;
		price = p;
		status = status2;
		
	}
	public void setID(int i){
		id = i;
	}
	public int getID(){
		return id;
	}

	public User getUser() {
		return user;
	}
	public Facility getFacility() {
		return facility;
	}
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}
	public String getDate() {
		return date;
	}
	public int getStatus() {
		return status;
	}
	public int getPaxCount(){
		return paxCount;
	}
	public int getPrice(){
		return price;
	}
}

