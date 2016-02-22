package Basic;

import java.util.Date;

public class TimeSlot {
	int id;
	String description;
	
	public TimeSlot(int i, String s) {
		id = i;
		description = s;
	}

	public int getID() {
		return id;
	}
	public String toString() {
		return description;
	}
}
