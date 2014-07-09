
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/*
 * client is one of the three processes necessary for the distributed system to function.  
 */
public class client implements ATMListener {
	public client() throws RemoteException{
	}
	
	@Override
	public void RecieveRequest(TransactionNotification transactionNotification) throws RemoteException{
		//upon receiving notification of a transaction from an ATM it is listening to, the client displays that notification on the screen. 
		System.out.println(transactionNotification);
	}
	public static AccountInfo GetAccountInfo(int accountNumber, int PIN) throws RemoteException{
		//regular getter for account info of a given account. Since accounts can only be accessed by account number and pin, an accountInfo object is created and passed both of these parameters
		return (new AccountInfo(accountNumber, PIN));
	}
	
	  public static void main(String[] args) {
		 /*creates a client object, an ATMListener, and an ATM factory, registers the factory with the naming server, then registers itself as a listener of the ATM
		  * it then performs the provided tests for the ATM and accounts 
		  */	
			try {
				
				   ATMListener client= new client();
		           ATMFactory factory = (ATMFactory)Naming.lookup("//localhost/atmfactory");
		           ATM theATM= factory.getATM();
		           
		           theATM.RegisterListener((ATMListener) UnicastRemoteObject.exportObject(client,0));
		           TestATM(theATM);
			}catch (MalformedURLException mue) {
		           mue.printStackTrace();
		        } catch (NotBoundException nbe) {
		           nbe.printStackTrace();
		        } catch (UnknownHostException uhe) {
		           uhe.printStackTrace();
		        } catch (RemoteException re) {
		           re.printStackTrace();
		        }
			
	
	  }
	  public static void TestATM(ATM atm) {
	      if (atm!=null) {
	         PrintBalances(atm);
	         PerformTestOne(atm);
	         PerformTestTwo(atm);
	         PerformTestThree(atm);
	         PerformTestFour(atm);
	         PerformTestFive(atm);
	         PerformTestSix(atm);
	         PerformTestSeven(atm);
	         PerformTestEight(atm);
	         PerformTestNine(atm);
	         PrintBalances(atm);
	      }
	   }        
	   public static void PrintBalances(ATM atm) {        
	      try {
	         System.out.println("Balance(0000001): "+atm.GetBalance(GetAccountInfo(0000001, 1234)));
	         System.out.println("Balance(0000002): "+atm.GetBalance(GetAccountInfo(0000002, 2345)));
	         System.out.println("Balance(0000003): "+atm.GetBalance(GetAccountInfo(0000003, 3456)));
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	   public static void PerformTestOne(ATM atm) {       
	      try {
	         atm.GetBalance(GetAccountInfo(0000001, 5555));
	      } catch (Exception e) {
	         System.out.println("Failed as expected: "+e);
	      }
	   }
	   public static void PerformTestTwo(ATM atm) {       
	      try {
	         atm.Withdraw(GetAccountInfo(0000002, 2345), 500);
	      } catch (Exception e) {
	         System.out.println("Failed as expected: "+e);
	      }
	   }
	   public static void PerformTestThree(ATM atm) {        
	      try {
	         atm.Withdraw(GetAccountInfo(0000001, 1234), 50);
	      } catch (Exception e) {
	         System.out.println("Failed as expected: "+e);
	      }
	   }
	   public static void PerformTestFour(ATM atm) {         
	      try {
	         atm.Deposit(GetAccountInfo(0000001, 1234), 500);
	      } catch (Exception e) {
	         System.out.println("Unexpected error: "+e);
	      }
	   }
	   public static void PerformTestFive(ATM atm) {         
	      try {
	         atm.Deposit(GetAccountInfo(0000002, 2345), 100);
	      } catch (Exception e) {
	         System.out.println("Unexpected error: "+e);
	      }
	   }
	   public static void PerformTestSix(ATM atm) {       
	      try {
	         atm.Withdraw(GetAccountInfo(0000001, 1234), 100);
	      } catch (Exception e) {
	         System.out.println("Unexpected error: "+e);
	      }
	   }
	   public static void PerformTestSeven(ATM atm) {        
	      try {
	         atm.Withdraw(GetAccountInfo(0000003, 3456), 300);
	      } catch (Exception e) {
	         System.out.println("Unexpected error: "+e);
	      }
	   }
	   public static void PerformTestEight(ATM atm) {        
	      try {
	         atm.Withdraw(GetAccountInfo(0000001, 1234), 200);
	      } catch (Exception e) {
	         System.out.println("Failed as expected: "+e);
	      }
	   }
	   public static void PerformTestNine(ATM atm) {        
	      try {
	         atm.Transfer(GetAccountInfo(0000001, 1234),GetAccountInfo(0000002, 2345), 100f);
	      } catch (Exception e) {
	         System.out.println("Unexpected error: "+e);
	      }
	   }
	
}

