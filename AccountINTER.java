
//Interface definition for Accounts (extends Remote to allow for remote account access)
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface AccountINTER extends Remote {
	public void Deposit(float depositAmount) throws RemoteException;
	public void Withdrawl(float withdrawlAmount) throws RemoteException;
	public float GetBalance() throws RemoteException;
	
}



