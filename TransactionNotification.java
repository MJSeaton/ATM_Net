
import java.io.Serializable;
//
public class TransactionNotification implements Serializable {
	/*
	 * TransactionNotifications are used to pass data about a particular type of transaction across the wire. they contain relevant account numbers, the amount involved in the transfer
	 * and the transaction type
	 */
	int accountNumber;
	int accountNumber2;
	
	String transactionType; //
	Float amount;
	TransactionNotification(int AccountNumber, String t_Type, Float Amount){
		accountNumber=AccountNumber;
		accountNumber2= -1;
		transactionType=t_Type;
		amount=Amount;
	}
	TransactionNotification(int AccountNumber, int AccountNumber2, Float Amount){
		///The only time a transaction is spawned with two integers is in the event that a transfer is occurring between two accounts, so the type is must be a transfer 
		///In this case both must be stored, as well as the amount to be transfered.
		accountNumber=AccountNumber;
		accountNumber2= AccountNumber2;
		transactionType="Transfer";
		amount=Amount;
	}	
	
	public String toString(){
		//The transaction notification should be able to be printed directly and display the either the balance or the correct statistics for the transaction.
		if (transactionType.equalsIgnoreCase("Balance")){
			return("Transaction Type:" + transactionType+ "| Account_Number: " + accountNumber);
		}
		else if (accountNumber2== -1){
			return("Transaction Type: "+ transactionType+" | Account_Number: "+ accountNumber + " | Amount: " + amount);
		}
		else{
			return("Transaction Type: "+ transactionType+" | Account_Number: "+ accountNumber + " | Account_Number 2: " + accountNumber2 + "  | Amount: " + amount);
		}
	}
}
	

