 /**
 * qServerUdp.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

import java.net.*;
import java.util.Random;
import java.io.*; 

/**
 * This class acts as a server. reads the data from the input file of the current directory and returns a quote from it to the 
 * connected clients
 */

public class qServerUdp 
{ 
	DataOutputStream out = null;
	DatagramSocket connection = null;
	

	/**
	 * Starting the server
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public qServerUdp() throws UnknownHostException, IOException {
		connection = new DatagramSocket(0);
	}
	

	/**
	 * Reads the file and returns a random quote from it
	 * 
	 * @param file_name 	String		location of the file that has to be read for the file name
	 * @return	quote		String		quote that has to be returned to the client
	 * @throws IOException
	 */
	public String getQuote(String file_name) throws IOException {
		// reading the file
		BufferedReader file = new BufferedReader(
				new FileReader(file_name));
		file.mark(100000);
		
		// Checking the lines in the file
		int line_number = new Random().nextInt((int)file.lines().count());
		file.reset();
		String quote ="";
		// Reading the random line
		for(int index = 0; 
				(quote =file.readLine()) != null && index < line_number ; 
				index++) {
			continue;
		}
		file.close();
		
		// Returning the random line
		return quote;
	}
	
	/**
	 * Starts the server 
	 * 
	 * @throws IOException
	 */
	public void runServer() throws IOException {
		// Displaying the server name and port that the server is listening
		System.out.println("Server listening on port: "+ connection.getLocalPort());
		System.out.println("Server running on " + InetAddress.getByName("localhost") + " address");
		
		// buffer to read the input
		byte[] client_packet = new byte[100];
		DatagramPacket receive_packet =  new DatagramPacket(client_packet , 100);
		while(true) {
			// Waiting for a client to connect to server
			connection.receive( receive_packet );
			System.out.println("Connected");

			// reading the quote from the quotes file
			String out_data = getQuote(System.getProperty("user.dir")+"/quotes.txt");
			
			// Sending back the data to the client
			connection.send(
					new DatagramPacket(
							out_data.getBytes(),out_data.length(), receive_packet.getAddress(), receive_packet.getPort()
							));
		}
	}
	

	/**
	 * Main function
	 * 
	 * @param args Command line arguments
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void main(String args[]) throws UnknownHostException, IOException 
	{ 
		// Starting and running the server
		new qServerUdp().runServer();
		
	} 
} 
