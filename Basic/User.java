package Basic;

abstract public class User {
    private int id;
    private String loginName;
    private String firstName;
    private String lastName;
    private String hashedPassword;
    private String role;
    private int membership;
    
    public User(int id, String loginName,String hashedPassword)
    {
    	this.id = id;
        this.loginName = loginName;
        this.hashedPassword = hashedPassword;
    }
    public User(String name, String fN, String lN, String pw){
    	loginName = name;
    	firstName = fN;
    	lastName = lN;
    	hashedPassword = pw;
    }
    public int getId(){
    	return id;
    }
    public String getLoginName()
    {
        return loginName;
    }
    
    public String getHashedPassword()
    {
        return hashedPassword;
    }
    
    public void setHashedPassword(String newHashedPassword)
    {
        this.hashedPassword = newHashedPassword;
    }
    
    public boolean checkPassword(String password)
    {
        boolean status = false;
        String hPassword = Utility.getHash(password);
        
        if(hashedPassword.equals(hPassword))
        {
            status = true;
        }
        
        return status;
    }
    public void setRole(String s){
    	role = s;
    }
    public String getRole(){
    	return role;
    }
    public void setfName(String s){
    	firstName = s;
    }
    public String getfName(){
    	return firstName;
    }
    public void setlName(String s){
    	lastName = s;
    }
    public String getlName(){
    	return lastName;
    }
    public void setMembership(int s){
    	membership = s;
    }
    public int getMembership(){
    	return membership;
    }
 
}
