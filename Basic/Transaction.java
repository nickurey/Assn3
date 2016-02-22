package Basic;


import java.util.Date;

public class Transaction {
	private Member member;
	private Facility facility;
	private int timeSlot;
	private Date date;
	private boolean status;
	
	public Transaction(Member m, Facility f, int t, Date d, boolean b) {
		member = m;
		facility = f;
		timeSlot = t;
		date = d;
		status = b;
	}

	public Member getMember() {
		return member;
	}
	public Facility getFacility() {
		return facility;
	}
	public int getTimeSlot() {
		return timeSlot;
	}
	public Date getDate() {
		return date;
	}
	public boolean getStatus() {
		return status;
	}
}

