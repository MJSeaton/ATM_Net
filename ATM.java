
/*
 * ATM is the interface (named according to spec sheet) that allows remote access to an ATM Object. ATM contains functions for all of the major types of banking functions
 * deposits, withdrawals, balance checking, and transfer. It also allows listeners to be registered so that they will be notified of any changes a user wants to use the ATM to make
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface ATM extends Remote {

    public void Deposit(AccountInfo AccountInfo, float Amount) throws ATMException, RemoteException;
    public void Withdraw(AccountInfo AccountInfo, float Amount) throws ATMException, RemoteException;
    public Float GetBalance(AccountInfo AccountInfo) throws ATMException, RemoteException, SecurityException;
    public void RegisterListener(ATMListener Listener) throws RemoteException;
    public boolean Transfer(AccountInfo AccountInfo, AccountInfo Dest, Float Amount) throws ATMException, RemoteException;
}
