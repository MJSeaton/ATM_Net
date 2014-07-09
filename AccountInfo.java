
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
public class AccountInfo implements Serializable {
	////The AccountInfo class is a bundling wrapper for the account number and PIN as they are passed through the system. It simply has private variables and getter methods for each.
	private int accountNumber;
	private int PIN;
	AccountInfo(int AccountNumber, int PN) throws RemoteException{
		accountNumber=AccountNumber;
		PIN=PN;
	}
	public int GetAccountNumber() throws RemoteException{
		return accountNumber;
	}
	public int GetPIN() throws RemoteException{
		return PIN;
	}
}
