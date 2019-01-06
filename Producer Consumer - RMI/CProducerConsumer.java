/**
 * CProducerConsumer.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */
import java.rmi.RemoteException;

/**
 * This class is the implementation of the remote object
 */
public class CProducerConsumer extends Thread implements IProducerConsumer {

	// Variables required to keep track of storage
	private static boolean[] storage = null;
	private static int free_space = 0;
	private static int fill_index = 0;
	private static int size = 0;
	private boolean isProducer = true;
	private int items = 0;

	/**
	 * Default constructor to handle the remote Exception
	 * 
	 * @throws RemoteException
	 */
	protected CProducerConsumer() throws RemoteException  {}
	
	/**
	 * Constructor used to create a thread with its it
	 * 
	 * @param is_producer  Boolean	True if producer, flase for a consumer
	 * @param item_count	int		number of items to produce/consume
	 */
	protected CProducerConsumer(boolean is_producer, int item_count) {
		this.isProducer = is_producer;
		this.items = item_count;
	}

	/**
	 * Constructor to initialize the storage, free space required for the producing and consuming 
	 *  
	 * @param storage_size
	 */
	public CProducerConsumer(int storage_size) {
		size = storage_size;		
		storage = new boolean[size];
		free_space = size;
		
		for(int index = 0; index<size; index++)
			storage[index] = false;
	}

	@Override
	/**
	 * Actual Implementation of the interface method to produce 
	 * 
	 * @param item_count	int 	number of items to produce
	 */
	public String produce(int item_count) throws RemoteException {
		CProducerConsumer producer = new CProducerConsumer(true, item_count);
		producer.start();
		try{
			// waiting to return until it produces
			producer.join();
		} catch( InterruptedException  e ) { }
		return "Produced "+ item_count +" items";
	}

	@Override
	/**
	 * Actual Implementation of the interface method to consume 
	 * 
	 * @param item_count	int 	number of items to consume
	 */
	public String consume(int item_count) throws RemoteException{
		CProducerConsumer consumer= new CProducerConsumer( false, item_count);
		consumer.start();
		try{
			// waiting to return until it consumes
			consumer.join();
		} catch( InterruptedException  e ) { }
		return  "Consumed " + item_count+ " items";
	}
	
	
	@Override
	/**
	 * producing or consuming the items from the storage
	 */
	public void run() {
		synchronized(storage) {
			if(isProducer) {
				// Producer thread entered
				if(free_space >= items) {
					// produce items and notify consumers
					for(int item = 0; item < items; item++) {
						storage[fill_index] = true;
						fill_index++;
						free_space--;
					}
					storage.notifyAll();
				}
				else {
					try {
						// waiting till the items are consumed and free space is created
						storage.wait();
					} catch (InterruptedException e) {
						// Handling the interrupted exception
						e.printStackTrace();
					}
				}
			}
			
			else {
				// Consumer thread entered
				if(fill_index > items) {
					// Consume and notify producers
					for(int item = 0; item < items; item++) {
						fill_index--;
						storage[fill_index] = false;
						free_space++;
					}
					storage.notifyAll();
				}
				else {
					// wait till items are produced
					try {
						storage.wait();
					} catch (InterruptedException e) {
						// Handling the interrupted exception
						e.printStackTrace();
					}
				}
			}
		}
	}
}