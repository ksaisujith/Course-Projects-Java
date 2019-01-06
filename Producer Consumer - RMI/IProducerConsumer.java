/**
 * IProducerConsumer.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * Interface that is used by both the server and client which contains all the methods 
 * that are offered to client by the server
 * 
 */

public interface IProducerConsumer extends Remote {
	public String produce(int item_count) throws RemoteException;
	public String consume(int item_count) throws RemoteException;
}