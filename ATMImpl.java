
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.util.*;
import java.rmi.*;

public class ATMImpl implements ATM {
	/* ATMImpl is the implementation class of the ATM Interface. It allows a user to deposit, withdraw, transfer funds between accounts, and check account balances. 
	 * it has a limited amount of cash on hand which is set during the initialization, a list of listeners, and handles for remote BankImpl and Security objects
	 */
	Float cashOnHand;
	ArrayList<ATMListener> listeners;
	Security security;
	Bank bank;
	public void RegisterListener(ATMListener atmListener) throws RemoteException {
		//registers a listener of the ATM
		listeners.add(atmListener);
	}
	public ATMImpl() throws RemoteException{
		//the default constructor gets handles to bank and security objects via Naming.Lookup, then initialized the ArrayList of listeners, and sets cash on hand to 500f 
		try{
		bank= (Bank) Naming.lookup("//localhost/Bank");
		security= (Security)Naming.lookup("//localhost/Security");
		listeners= new ArrayList<ATMListener>();
		cashOnHand=500f;
		} catch (MalformedURLException mue) {
	           mue.printStackTrace();
	        } catch (NotBoundException nbe) {
	           nbe.printStackTrace();
	        } catch (UnknownHostException uhe) {
	           uhe.printStackTrace();
	        } catch (RemoteException re) {
	           re.printStackTrace();
	        }
	}
	
	public void Deposit(AccountInfo accountInfo, float amount) throws ATMException, RemoteException {
		// Deposit takes in an accountInfo object and an amount, creates a transaction notification based on the account info and type of use. It then notifies all listeners, checks with the security 
		// object for authentication and authorization, gets access to the account through the bank, and deposits that amount in the ATM
		TransactionNotification transactionNotification = new TransactionNotification(accountInfo.GetAccountNumber(), "Deposit", amount);
		for (int i=0; i< listeners.size(); i++){
			listeners.get(i).RecieveRequest(transactionNotification);
		}
		try{
		security.CheckAuthentication(accountInfo);
		security.CheckAuthorization(transactionNotification);
		} catch(SecurityException s){ 
			throw new ATMException(s.getMessage());
		}
		try{
		ArrayList<AccountINTER> accounts=bank.ProcessRequest(transactionNotification);
		accounts.get(0).Deposit(amount);
		}catch (AccountException e){
			throw new ATMException(e.getMessage());
		}
	}

	

	public void Withdraw(AccountInfo accountInfo, float amount) throws ATMException, RemoteException {
		// Withdraw takes in an accountInfo object and an amount(f), creates a transaction notification based on the account info and type of use. It then notifies all listeners, checks with the security 
		// object for authentication and authorization, checks to see if there is enough cash in the ATM, gets access to the account through the bank, and withdraws that amount from the ATM and the account
				
		TransactionNotification transactionNotification = new TransactionNotification(accountInfo.GetAccountNumber(), "Withdrawl", amount);
		for (int i=0; i< listeners.size(); i++){
			listeners.get(i).RecieveRequest(transactionNotification);
		}
		
		try{
			security.CheckAuthentication(accountInfo);
			security.CheckAuthorization(transactionNotification);
			} catch(SecurityException s){ 
				throw new ATMException(s.getMessage());
			}
		if( amount> cashOnHand){
			//throw new InsufficientFundsException("Not enough cash in the ATM to complete this request!");
			throw new ATMException("Not enough cash in the ATM to complete this request!");
			
		}
		try{
		ArrayList<AccountINTER> accounts=bank.ProcessRequest(transactionNotification);
		if (accounts.get(0).GetBalance() - amount >=0){
			
			accounts.get(0).Withdrawl(amount);
		}
		else{
			throw new ATMException("Not enough funds in account!");
		
		}
		}catch(AccountException e){
			throw new ATMException(e.getMessage());
		}
	}

	@Override
	public Float GetBalance(AccountInfo account) throws ATMException, RemoteException, SecurityException {
		// GetBalance takes in an accountInfo object, creates a transaction notification based on the account info and type of use. It then notifies all listeners, checks with the security 
		// object for authentication and authorization, gets access to the account through the bank, and returns a float with the current balance
				
		TransactionNotification transactionNotification = new TransactionNotification(account.GetAccountNumber(), "Balance", 0f);
		for (int i=0; i< listeners.size(); i++){
			listeners.get(i).RecieveRequest(transactionNotification);
		}
		
		try{
			security.CheckAuthentication(account);
			security.CheckAuthorization(transactionNotification);
			} catch(SecurityException s){ 
				throw new SecurityException(s.getMessage());
			}
		try{
			ArrayList<AccountINTER> accounts=bank.ProcessRequest(transactionNotification);
			return(accounts.get(0).GetBalance());
			} catch(AccountException e){
				e.printStackTrace();
				throw new ATMException(e.getMessage());
			}
	}

	@Override
	public boolean Transfer(AccountInfo account, AccountInfo destination, Float amount) throws ATMException, RemoteException {
		// Deposit takes in two accountInfo object and an amount, creates a transaction notification based on the account info and type of use. It then notifies all listeners, checks with the security 
		// object for authentication and authorization in the account initiating the transfer, gets access to both accounts through the bank, and deposits that amount in the destination account
				
		TransactionNotification T = new TransactionNotification(account.GetAccountNumber(), destination.GetAccountNumber(), amount);
		for (int i=0; i< listeners.size(); i++){
			listeners.get(i).RecieveRequest(T);
		}
		try{
			security.CheckAuthentication(account);
			security.CheckAuthorization(T);
			} catch(SecurityException s){ 
				s.printStackTrace();
				throw new ATMException(s.getMessage());
			}
		try{
		ArrayList<AccountINTER> A=bank.ProcessRequest(T);
		 
		if (A.get(0).GetBalance() - amount >=0){
			A.get(0).Withdrawl(amount);
			A.get(1).Deposit(amount);
		}
		else{
			throw new ATMException("Insufficient funds!") ;
		}
		}
		catch( AccountException e){
			System.out.println(e.getMessage());
		}
		return true;
	}

}
