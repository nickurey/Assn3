

public class MembershipTier {
	//private Member member;
	private String rank;
	private int discountPercentage;
	
	public MembershipTier (String s, int d) {
		rank = s;
		discountPercentage = d;
	}

	public String getRank() {
		return rank;
	}
	public int getDiscountPercentage() {
		return discountPercentage;
	}

}
