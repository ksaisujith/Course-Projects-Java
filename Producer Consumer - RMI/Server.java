/**
 * Server.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Server class which registers the remote object in the register and waits for the client to connect 
 * and use it
 */
public class Server extends CProducerConsumer{

	/**
	 * Default constructor to handle the RemoteException
	 * @throws RemoteException
	 */
	protected Server() throws RemoteException {}
	
	/**
	 * Main method 
	 * 
	 * @param args command line arguments
	 */
	public static void main(String args[]) {
		int storage_size = 0;
		if (args.length == 1) {
			// Command line arguments are provided
			try {
				storage_size = Integer.parseInt(args[0]);
			} catch(Exception e) {
				System.out.println("Only integer values are allowed as arguments.");
				System.exit(1);
			}
		}
		
		else {
			// Wrong or no command line parameters are passed
			storage_size = 100;
			System.out.println("No(or)wrong parameters passed.Running with default storage size of 100 \n");
		}
		
		try {
			// Getting the exported object
			IProducerConsumer export_stub = (IProducerConsumer) 
					UnicastRemoteObject.exportObject( new CProducerConsumer(storage_size),0);
			
			// Registering it in the server
			LocateRegistry.createRegistry(2053).bind("sk7634_kp5955_prodCons", export_stub);
			
			System.out.println("Server Ready");
		} catch (AlreadyBoundException e) {
			// Handling the exception if there exists an object in the registry with the same name
			System.out.println("Object with the same name exits");
			System.exit(1);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}