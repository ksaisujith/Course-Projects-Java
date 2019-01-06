/**
 * qClientTcp.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

import java.io.*;
import java.net.*;

/**
 * Connects to server and reads a quote from it
 */
public class qClientTcp {
  
	/**
	 * main function
	 * @param args	String[] command line arguments
	 */
  public static void main(String[] args) {
	  // The client socket
	  Socket clientSocket = null;
	  // The output and input stream
	  DataOutputStream out = null;
	  BufferedReader in = null;
	  
	  int portNumber = 0;
	  String host = null;

    if (args.length != 2) {
    	// Wrong parameters are passed
      System.out.println("Wrong parameters passed \n"
    		  + "qClintTCP <hostname> <port_num>");
      System.exit(1);
    }
    else {
    	// parsing the command line arguments
      host = args[0];
      try {
      portNumber = Integer.parseInt(args[1]);
      } catch(Exception e) {
    	  System.out.println("Only integer value accepted for port number");
    	  System.exit(1);
      }
    }

    /*
     * Open a socket on a given host and port. Open input and output streams.
     */
    try {
    	// Creating the client socket to communicate with the server
      clientSocket = new Socket(InetAddress.getByName(host), portNumber);

      // getting the streams to read and write from the server
      out = new DataOutputStream(clientSocket.getOutputStream());
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      
      // Connecting to the server
      out.writeBytes("Hello");
      
      // reading the input
	  System.out.println(in.readLine());
	  String temp = new BufferedReader(new InputStreamReader(System.in)).readLine();
      out.writeBytes(temp + "\n");
 
      // Printing the quote
	  System.out.println(in.readLine());
    }catch (UnknownHostException e) {
      System.err.println("Don't know about host " + host);
    }
    catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to the host " + host);
    }
  }
}