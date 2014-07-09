
import java.util.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
/*
 * ATMFactoryImpl is the implementation of the ATMFactory class. It follows the factory design pattern (as per the spec sheet) to provide potential access to multiple ATM objects (though as per the spec,
 * it is hard coded for one. 
 */
public class ATMFactoryImpl implements ATMFactory {

	private ATMImpl theATM;
	
	public ATMFactoryImpl() throws RemoteException {
		
		theATM= new ATMImpl();
	}
	
	public ATM getATM() throws RemoteException {
		
		try{
		ATM atm=(ATM)UnicastRemoteObject.exportObject(theATM,0);
		return atm;
		}
		catch(Exception e){
		 e.printStackTrace();
			System.out.println("Remote ATMError: "+e.getMessage());
		}
		return null;
	}

}
