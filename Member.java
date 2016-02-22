import Basic.User;

public class Member extends User {
    
    private int points;
    
    public Member(int id, String loginName,String hashedPassword, int points)
    {
        super(id, loginName,hashedPassword);
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
    
    
    public String toString(){
    	
    	return getLoginName() + " " + points;
    }
    
     
}
