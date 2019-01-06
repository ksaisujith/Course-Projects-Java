/**
 * StorageServer.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * This class acts as a has a storage system that is used to produce or consume by different clients
 */

public class StorageServer extends Thread {
	// Declaring the variables
	static boolean[]  storage = null; 
	ServerSocket storage_socket = null;
	static int fill_index = 0;
	
	/**
	 * Creating a new StorageServer object which generates a new port for communication
	 */
	public StorageServer() {
		try {
			// Opens a new port for communication
			storage_socket = new ServerSocket(0);
		} catch (IOException e) {
			// Handling the exception
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the storage with the specified size. 
	 * This also creates the a port and listens for the  clients 
	 * 
	 * @param size 		int 	Size of the storage that has to be created
	 */
	public StorageServer(int size) {
		// Creating the storage
		storage = new boolean[size];
		// Initializing the values in the storage
		for(int i=0; i<size;i++) {
			storage[i] = false;
		}
		// Initialing the filling index
		fill_index = 0;
		try {
			// Opening the new socket in which it waits for the client
			storage_socket = new ServerSocket(0);
		} catch (IOException e) {
			// Faced an error while creating the socket
			System.out.println("Faced an error while creating the socket");
			e.printStackTrace();
		}
	}
	
	/**
	 * Starts the storage and listens for the clients.
	 * It creates and returns new port for each client for further communication
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void runStorageServer() throws IOException, InterruptedException {
		// Showing the information in which the client has to communicate initially
		System.out.println("Storage running on port:" + storage_socket.getLocalPort());
		System.out.println("Storage running on host:" + storage_socket.getLocalSocketAddress());
		
		while(true) {
			// Waiting for clients to connect to server
			Socket new_connection = storage_socket.accept();
			
			// Creating a new port 
			StorageServer aServer = new StorageServer();
			aServer.start();
			// Sending the new port information to the connected client
			String send_newPort_number = ""+aServer.getLocalPort()+ "\n";
			new DataOutputStream(new_connection.getOutputStream()).writeBytes(send_newPort_number);
			
			// Waiting for Acknowledgement and closing the connection
			if(new BufferedReader(
					new InputStreamReader(new_connection.getInputStream())).readLine().equals("ReceivedNewPort")) 
			{
				new_connection.close(); // Closing connection only after the all the data is reached to client is acknowledged
			}
		}
	}
	
	/**
	 * Returns new prot number in which the client has to communicate further
	 * 
	 * @return  int  new port number 
	 */
	private int getLocalPort() {
		// Returns the new port assigned
		return storage_socket.getLocalPort();
	}

	/**
	 * Main function that handles the storage space for producing or consuming
	 *  
	 */
	public void run()  {
		
		// Declaring the variables required
		Socket connection = null;
		BufferedReader from_client = null;
		DataOutputStream to_client = null;
		String[] client_data = null;
		
		try {
			// Waiting for the message from the client to the new port
			connection = storage_socket.accept();
			
			// creating streams for the communication between server and client
			from_client = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			to_client = new DataOutputStream(
					connection.getOutputStream());
			
			// Splitting the data that is read from the client
			client_data = from_client.readLine().split(" ");	
		} catch (IOException e) {
			// Handling the exception
			e.printStackTrace();
		}
	
		// Synchronized block
		synchronized(storage) {
			if(client_data[0].equals("Producer")){
				// Producer Client
				int production_rate = Integer.parseInt(client_data[1]);
				if((storage.length - fill_index) >= production_rate) {
					// Free space available. Hence producing the units
					for(int produce=0; produce < production_rate; produce++)
						storage[fill_index++] = true;
					
					// Notifying all the waiting clients
					storage.notifyAll();
					System.out.println("Producing " + client_data[1] + " items by " + client_data[2]);
				}
				else {
					try {
						// waiting for free space
						storage.wait();
					} catch (InterruptedException e) {
						// Handling the exception
						e.printStackTrace();
					}
				}
			}
			else{
				// Consumer client
				int consumption_rate = Integer.parseInt(client_data[1]);
				if(fill_index >= consumption_rate ) {
					// Enough units are there to consume
					for(int consume=0; consume < consumption_rate; consume++) {
						storage[--fill_index] = false;
					}
					// Notifying all the other waiting clients
					storage.notifyAll();
					System.out.println("Consuming " + client_data[1] + " items by " + client_data[2]);
				}
				else {
					try {
						// waiting for enough units
						storage.wait();
					} catch (InterruptedException e) {
						// Handling the interrupted Exception
						e.printStackTrace();
					}
				}
			}
			// *** Uncomment this part to debug  ***
			/* Displays the storage
			System.out.print("[");
			for(boolean b: storage) {
				System.out.print(b +" ");
			}
			System.out.print("]");
			System.out.println("\n"+fill_index);
			*/
			// *********
			
			// Sending out the information to consumer
			String out_message = "done by "+ client_data[2] +"\n";
			
			try {
				// Sending out the information to consumer 
				to_client.write(out_message.getBytes());

				// Waiting for the acknowledgement before closing
				if(from_client.readLine().equals("DONE")) {
					sleep(100);
					connection.close(); // Closing connection only after the all the data is reached to client
				}
			} catch(IOException | InterruptedException e) {
				// Handling exceptions
				e.printStackTrace();
			}
		}
	}

	/**
	 * Displays the usage of the program
	 */
	public static void usage() {
		System.out.println("Usage of this class:"
				+ "StorageServer.java [<storage size>]");
	}
	/**
	 * Main function
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		int storage_size = 100;
		try {
			if(args.length == 1) {
				// Taking the size from the command line
				storage_size = Integer.parseInt(args[0]);
			}
			
			if (args.length > 1) {
				// Wrong number of parameters passed
				throw new InvalidInputException();
			}
		}catch(InvalidInputException e) {
			// Handling wrong number of parameters
			usage();
			System.exit(1);
		} catch(Exception e) {
			// Handling all other exceptions
			usage();
			System.exit(1);
		}
		
		try {
			// Starting the server
			new StorageServer(storage_size).runStorageServer();
			
		} catch (IOException | InterruptedException e) {
			// Handling the exceptions
			e.printStackTrace();
		}
	}
}