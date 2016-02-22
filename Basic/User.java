package Basic;

abstract public class User {
    private int id;
    private String loginName;
    private String hashedPassword;
    private String role;
    
    public User(int id, String loginName,String hashedPassword)
    {
    	this.id = id;
        this.loginName = loginName;
        this.hashedPassword = hashedPassword;
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
 
}
