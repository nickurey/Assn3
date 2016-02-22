
public class InvalidPasswordException extends Exception{
	
	public InvalidPasswordException(){
		super("Password not matched");
	}

}