package Basic;

/*******************************************************************************
 *  CSCI Assignment 
 *  Filename: ${filename}
 *  Author: Nguyen Van Thanh Huy (Nick Nguyen)
 *  UOW Student Number: 5216746
 *  Description:
 *  
 *******************************************************************************/


public class Member extends User {
    
    private int membership;
    
    public Member(int id, String loginName,String hashedPassword,int m)
    {
        super(id,loginName,hashedPassword);
        this.membership=m;
    }

    
    public Member(String name, String fN, String lN, String pw) {
		// TODO Auto-generated constructor stub
    	super(name, fN, lN, pw);
	}


     
}
