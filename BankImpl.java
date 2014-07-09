
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.rmi.RemoteException;
public class BankImpl implements Bank {
	/*
	 * The BankImpl is the the server-side implementation of the bank interface
	 * it is used to initialize and provide remote access to the accounts.
	 * It contains an ArrayList of accounts, and a function that provides access to accounts based on a TransactionNotification object.
	 * 	 */
	private ArrayList<Account>theAccounts;
	
	public BankImpl() throws RemoteException{
		//The default constructor initializes the ArrayList, creates the accounts, and adds them to the ArrayList
		theAccounts=new ArrayList<Account>();
		theAccounts.add(new Account(0));
		theAccounts.add(new Account(100));
		theAccounts.add(new Account(500));
		
		
	}
	
	public ArrayList<AccountINTER> ProcessRequest(TransactionNotification transaction) throws AccountException, RemoteException {
		/*ProcessRequest takes in a notification of a transaction in the form of an object, shifts the indexes down by one (since according to the specification, accounts are sequentially numbered starting at 1
		* then it adds the accounts involved in the transaction to an ArrayList (since some transactions will involve multiple accounts), and returns the arrayList. 
		*
		*/
		ArrayList<AccountINTER> ToReturn= new ArrayList<AccountINTER>();
		try{
			int OffShift= transaction.accountNumber-1;
			AccountINTER A= theAccounts.get(OffShift);
			
		ToReturn.add(A);
		if (transaction.accountNumber2!= -1){
			int OffShift2= transaction.accountNumber2-1;
			AccountINTER B= theAccounts.get(OffShift2);
			ToReturn.add(B);
		}
		}catch (Exception e){
			throw new AccountException(e.getMessage());
		}
		return ToReturn;
		
	}

}
