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
    
    private int points;
    
    public Member(int id, String loginName,String hashedPassword,int points)
    {
        super(id,loginName,hashedPassword);
        this.points=points;
    }
    
    public int getPoints()
    {
        return points;
    }
    
    public void addPoints(int points)
    {
        this.points+=points;
    }
    
    public void deductPoints(int points)
    {
        this.points-=points;
    }
     
}
