
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
/*
 * The BankServer class is used to create a BankImpl object as well as a SecurityImpl object. It uses UnicastRemoteObject's exportObject function along with
 * Naming.rebind to allow remote access by binding each by name in the registry. It is run as a self-contained program, one of 3 necessary for the distributed network to function 
 */
public class BankServer {
	public static void main(String args[]){
		
		try{
		BankImpl bankImpl= new BankImpl();
		SecurityImpl securityImpl= new SecurityImpl();
		Bank bank = (Bank) UnicastRemoteObject.exportObject(bankImpl,0);
		Naming.rebind("//localhost/Bank", bank);
		System.out.println("bank bound in reg!");

		Security security = (Security) UnicastRemoteObject.exportObject(securityImpl,0);
		Naming.rebind("//localhost/Security", security);
		System.out.println("security bound in reg!");
		
		} catch (Exception e){
			System.out.println("err: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
