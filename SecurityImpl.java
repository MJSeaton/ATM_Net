//
import java.util.*;
//SecurityImpl is the server-side implementation of the security interface. It contains the account information, and the functions that allow for this data to be checked.
import java.rmi.RemoteException;
public class SecurityImpl implements Security {
	private ArrayList<AccountInfo> accountInfos;
	SecurityImpl() throws RemoteException{
		accountInfos=new ArrayList<AccountInfo>();
		accountInfos.add(new AccountInfo(0000001, 1234));
		accountInfos.add(new AccountInfo(0000002, 2345));
		accountInfos.add(new AccountInfo(0000003, 3456));
		//default constructor just sets up the permissions and password tables
	}
	@Override
	public boolean CheckAuthentication(AccountInfo accountInfo)
	//checks to make sure the correct PIN is being used to access the account contained in the account info object.
			throws SecurityException, RemoteException {
			for (int l=0; l< accountInfos.size(); l++){
				if(accountInfo.GetAccountNumber() == accountInfos.get(l).GetAccountNumber()){
					if (accountInfo.GetPIN() != accountInfos.get(l).GetPIN()){
						throw new SecurityException("The incorrect pin is being used!");
					}
					return(true);
				}
			}
			throw new SecurityException("Failed Authentication");
	}

	@Override
	public boolean CheckAuthorization(TransactionNotification transaction)
	//checks to make sure all accounts involved in a transaction are permitted to make the necessary modifications
			throws SecurityException, RemoteException {
			if (transaction.accountNumber == 0000001){	
				}
			if (transaction.accountNumber == 0000002){
				if (transaction.transactionType == "Withdrawl" || transaction.transactionType == "Transfer"){
					throw new SecurityException("Account is not permitted to make withdrawls!");
				}
			}
			if (transaction.accountNumber == 0000003){
				if (transaction.transactionType == "Deposit"){
					throw new SecurityException("Account is not permitted to make deposits!");
				}
			if (transaction.accountNumber2 == -1){
				return true;
				}
			else{
				if (transaction.accountNumber2 == 0000003){
					throw new SecurityException("The destination account has a freeze on it!");
					}
				}
			}
	
			
		return true;
	}

}
