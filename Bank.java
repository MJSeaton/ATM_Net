
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;
public interface Bank extends Remote {
	/*
	 *Interface that the bank  implements. Interface that extends remote is necessary to get access to it remotely   
	 */
	ArrayList<AccountINTER> ProcessRequest(TransactionNotification transaction) throws AccountException, RemoteException;
}
