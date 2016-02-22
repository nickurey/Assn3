import java.io.*;
import java.util.*;

import Basic.User;

public class Admin extends User{
    
    public Admin()
    {
        super(-1, "admin","");
        
        try{
             
           Scanner fileScanner = new Scanner(new File("admin.dat"));
           String data = fileScanner.nextLine();
           setHashedPassword(data);
           
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("admin.dat not found.");
            System.out.println("admin set to default password.");
            //set to "password" as the default password
            String newHashedPassword = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
            setHashedPassword(newHashedPassword);
        }
           
    }
    
    //overide the super class method
    public void setHashedPassword(String newHashedPassword)
    {
        try
        {  
          PrintWriter fileOut = new PrintWriter("admin.dat");
          fileOut.print(newHashedPassword);
          fileOut.close();
          super.setHashedPassword(newHashedPassword);
          
        }
        catch(IOException e)
        {
            System.out.println("Failed to open admin.dat, password not updated");
        }
    }
}
