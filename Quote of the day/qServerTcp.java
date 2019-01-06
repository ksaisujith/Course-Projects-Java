/**
 * qServerTcp.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * A server that allows multiple clients to connect to it.
 */
public class qServerTcp {
	/**
	 * main method 
	 * @param args	String[] command line arguments
	 */
  @SuppressWarnings("resource")
public static void main(String args[]) {
	  // Server socket is created.
	  ServerSocket serverSocket = null;
	  // Client socket is created.
	  Socket clientSocket = null;
	 
    /*
     * Opening a server
     */
    try {
      serverSocket = new ServerSocket(0);
      System.out.println("Listening on port number:" + serverSocket.getLocalPort());
    } catch (IOException e) {
      System.out.println(e);
    }

    /*
     * Create a client socket for each connection and creating a new thread corresponding to it.
     */
    while (true) {
      try {
        clientSocket = serverSocket.accept();
        }catch (IOException e) {
        System.out.println(e);
      }
      new clientThread(clientSocket).start();
    }
  }
}

/**
 * Client thread is created for each client to connect to the server
 */
class clientThread extends Thread {

	  private BufferedReader in = null;
	  private DataOutputStream out = null;
	  private Socket clientSocket = null;

	  /**
	   * A constructor to initialize the thread
	   */
	  public clientThread(Socket clientSocket) {
	    this.clientSocket = clientSocket;
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
	  
/*
 * Run() fetches the name of the client being connected
 * */
	  public void run() {
	    try {
	      /*
	       * Create input and output streams for this client. ID(here name) is printed.
	       */
	      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	      out = new DataOutputStream(clientSocket.getOutputStream());
	      
	      // Welcoming the client
	      out.writeBytes("Welcome !! Please press enter \n");
	      in.readLine();
	      System.out.println("Sending data to Client");

	      /* A random quote has been read from the text file and sent to the particular client */
	      try {
	    	  // Writing the quote to client
	    	  out.writeBytes(getQuote(System.getProperty("user.dir")+"/text.txt") + "\n");
	    	  } catch(FileNotFoundException e) {  
	        	  e.printStackTrace();
	    	  }
	    } catch (IOException e) {
      	  e.printStackTrace();
	    }
	  }
}