
/*
 * 
 * An exception for when an account has insufficient funds
 */
public class InsufficientFundsException extends Exception {
	  public InsufficientFundsException(String msg) {
			super(msg);
		    }
}
