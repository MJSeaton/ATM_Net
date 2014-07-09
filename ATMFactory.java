
////Interface definition for remote access to an ATMFactory via java.rmi.Remote
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface ATMFactory extends Remote {
	public ATM getATM() throws RemoteException;
}
