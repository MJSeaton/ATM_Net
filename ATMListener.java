
import java.rmi.Remote;

import java.rmi.RemoteException;
////the interface definition for an ATMListener (object notified of changes made by an ATM). Receives requests to push them to screen.
public interface ATMListener extends Remote {
	public void RecieveRequest(TransactionNotification transactionNotification) throws RemoteException;
}
