 /**
 * qClientUdp.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

import java.net.*; 
import java.io.*; 

/**
 * This class connects to a server using UDP and reads the input that is sent by the server 
 */

public class qClientUdp 
{ 
	// Declaring and initialising the variables
	DatagramSocket client_connection = null;
	String host = null;
	int port = 0;
	
	/**
	 * Client that connects to the server using UDP
	 * 
	 * @param hostName		String		name of the server where server is up and running
	 * @param port_num		int			port number that is used for communication
	 * @throws SocketException
	 */
	public qClientUdp(String hostName, int port_num) throws SocketException {
		client_connection = new DatagramSocket();
		host = hostName;
		port = port_num;
	}
	
	/**
	 * Runs the client which connects to server and reads the data from the server
	 * 
	 * @throws IOException
	 */
	public void runClient() throws IOException {
		// Datagram packet that has to be sent to the server
		DatagramPacket client_data = new DatagramPacket(
				new byte[10],10,InetAddress.getByName(host), port);
		
		// Sending the packet
		client_connection.send(client_data);
		
		// Buffer to store the input from server
		byte[] quote_from_server = new byte[1024];
		
		// Receiving data from server
		client_connection.receive( 
				new DatagramPacket(quote_from_server, 1024));
		
		// Printing the quote that is received from server
		System.out.println(new String(quote_from_server));
	}
	
	/**
	 * Main function
	 * @param args	String[] 	command line arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// Declaring and Initialising the variables
		int port =0;
		String host_name = "";		
		
		// Throwing errors when parameters are not passed
		if(args.length == 0 || args.length != 2) {
			if(args.length > 0) {
				// Wrong number of parameters are passed
				System.out.println("Wrong command line parameters passed. Please enter correctly\n"
						+ "qClientUp.java <hostname> <portnumber>");
				System.exit(1);
			}
		
			// Read the parameters from standard input
	      BufferedReader inFromUser =
	    	         new BufferedReader(new InputStreamReader(System.in));
	      
	      System.out.println("Please enter the hostname");
	      host_name = inFromUser.readLine();
	      System.out.println("Please enter the port number");
	      port = Integer.parseInt(inFromUser.readLine());
		}
		else {
			// Reading the values from the command lines
			host_name = args[0];
			port = Integer.parseInt(args[1]);
		}
	      
		// Running the client
	      new qClientUdp(host_name,port).runClient();     
	}
} 
