
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
/*
 * The ATMServer is one of the components of the ATM network. It creates an ATMFactory, registers it with the rmi server for remote name based access, and prints a message.
 */
public class ATMServer {
		
		public static void main(String args[]){
			
			try{
			ATMFactory ATMFactory= new ATMFactoryImpl();
			ATMFactory atmFactory = (ATMFactory) UnicastRemoteObject.exportObject(ATMFactory,0);
			Naming.rebind("//localhost/atmfactory", atmFactory);
			System.out.println("ATMFactory bound in reg!");
			
			} catch (Exception e){
				System.out.println("ATMFactoryImpl err: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}


