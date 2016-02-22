package Basic;


public class Facility {
	private int id;
	private String name;
	private String type;
	private int peakPrice;
	private int nonPeakPrice;
	private int capacity;
	
	public Facility(int i, String s, String h, int a, int b, int c) {
		id = i;
		name = s;
		type = h;
		peakPrice = a;
		nonPeakPrice = b;
		capacity = c;
	}
	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}
	public String toString() {
		return name + " - (" + type +")";
	}
	public String getType() {
		return type;
	}
	public int getPeakPrice() {
		return peakPrice;
	}
	public int getNonPeakPrice() {
		return nonPeakPrice;
	}
	public int getCapacity() {
		return capacity;
	}
}
