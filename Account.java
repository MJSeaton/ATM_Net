
import java.rmi.RemoteException;


import java.rmi.server.UnicastRemoteObject;
public class Account extends UnicastRemoteObject implements AccountINTER {
	/*
	 * The implementation of the Account interface. contains a balance and the functions necessary to make requests (Deposit, withdraw, and check balance)
	 */
	private float balance;
	public Account() throws RemoteException{
		balance=0f;
	}
	public Account(float seedMoney)throws RemoteException{	
		balance=seedMoney;
	}
	public void Deposit(float depositAmount) throws RemoteException{
			balance=balance+depositAmount;
			}

	public void Withdrawl(float withdrawlAmount) throws RemoteException{
		if (balance-withdrawlAmount<0){
			System.out.println ("This amount cannot be withdrawn; you will overdraft and your credit is terrible");
		}
		else{
			balance=balance-withdrawlAmount;
		}
	}
	public float GetBalance() throws RemoteException{
		return balance;
	}
	
}
