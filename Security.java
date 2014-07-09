
////the interface definition  of the security system to allow for remote communication
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Security extends Remote {
	public boolean CheckAuthentication(AccountInfo accountInfo) throws SecurityException, RemoteException;
	public boolean CheckAuthorization(TransactionNotification transactionNotification) throws SecurityException, RemoteException;
}
