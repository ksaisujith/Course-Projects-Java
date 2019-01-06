/**
 * ConsumerProducer.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

/**
 * This class reads the number of producer & consumer threads should exist and the units they produce/consume. 
 * It also reads the storage size to wait and proceed the production/consumption by multi-thread communication
 */

public class ConsumerProducer extends Thread{	
	// Variable Declaration
    int threadId;
    int units;
    boolean isProducer;
    static int index = 0;
    static int available_space = 0;
    static Integer[] storage = null;

    // Constructor which creates producers and consumers. This also initializes the variables
    public ConsumerProducer(boolean toProduce,  int availableSpace, int unit, int id ) {
        this.isProducer = toProduce;
        this.units = unit;
        this.threadId = id;
        // Creating the storage for the first time 
        if(storage == null) {
            available_space = availableSpace;
	        storage = new Integer[availableSpace];
	        for(int i = 0; i < availableSpace ; i++) {
	            storage[i] = 0;
	        }
        }
    }

    /**
     * This function is executed by the threads when they are run after they are started
     * 
     * @return 
     */
    @Override
    public void run() {
        while(true) {
        	// Producing and consuming until there are units
            synchronized ( storage ) {
                if(this.isProducer) {
                    // PRODUCER thread running
                    if(available_space >= this.units) {
                    	// Space available to produce the units                        
                    	for(int i=0;i<this.units;i++) {
                    		// Producing the units
                            storage[index] = 1;
                            index++;
                            available_space--;
                        }
                        System.out.println(" "+ this.threadId+ " : Produce " + this.units + " units"
                        //		+ "\t"+ this  // **Uncomment this line for debugging**
                        		);
                        // notifying all the objects in wait state, so that they can consume
                        storage.notifyAll();
                    }
                    else{
                        try {
                        	// Waiting since there is no space to produce
                            storage.wait();
                        } catch (InterruptedException e) {
                            // Handling the interrupted exception
                            e.printStackTrace();
                        }
                        catch(Exception e) {
                        	// Unknown exception
                        	System.err.println("Unknown error");
                        	e.printStackTrace();
                        }
                    }
                }
                
                
                else
                {
                    // CONSUMER thread running
                    if(available_space < storage.length - this.units ) {
                    	// Checking if there are enough units available to consume
                        for(int i=0;i< this.units;i++) {
                        	// Consuming the units
                            index--;
                            storage[index] = 0;
                            available_space++;
                        }
                        System.out.println(" "+ this.threadId+ " : Consume " + this.units + " units"
                        //		+ "\t"+ this  // **Uncomment this line for debugging**
                        		);
                        // Notifying the all the objects that space is consumed and they can produce
                        storage.notifyAll();
                    }
                    else {
                        try {
                        	// Waiting until enough units are generated
                            storage.wait();
                        } catch (InterruptedException e) {
                            // Handling the interrupted exception
                            e.printStackTrace();
                        }
                        catch(Exception e) {
                        	// Unknown exception
                        	System.err.println("Unknown error");
                        	e.printStackTrace();
                        }
                    }
                }
            }
        }        
    }
    

    /**
     * This function prints the elements in the storage
     * 
     * @return		result		String		all the elements in the storage
     */
    @Override
    public String toString() {
        String result ="[ ";
        for(int n:storage ) {
            result += n + " ";
        }
        result += "]";
        return result;
    }

    /**
     * Main function
     * 
     * @param args		String		Command line parameters
     */
    public static void main(String args[])
    {
    	// Variable declaration
        int consumersCount = 0;
        int producersCount = 0;
        int unitsConsumed = 0;
        int unitsProduced = 0;
        int storageSpace = 0;

        if(args.length == 0){
        	// Default values if no values are passed through command line
            consumersCount = 20;
            producersCount = 20;
            storageSpace = 20;
            unitsConsumed = 5;
            unitsProduced = 10;
        }
        else {
            try {
            	// Reading values from the input
                consumersCount = Integer.parseInt(args[0]);
                unitsConsumed =  Integer.parseInt(args[1]);
                producersCount = Integer.parseInt(args[2]);
                unitsProduced =  Integer.parseInt(args[3]);
                storageSpace = Integer.parseInt(args[4]);
                
                if (consumersCount < 0 || unitsConsumed < 0 || producersCount < 0 || unitsProduced < 0 || storageSpace < 0)
                	// Negative count is not allowed
                	throw new NegativeNumberException();
                
                if (consumersCount == 0 || unitsConsumed == 0 || producersCount == 0 || unitsProduced == 0 || storageSpace == 0)
                	// Zero is not allowed
                	throw new UnknownNumberException();
                if ( unitsConsumed > storageSpace || unitsProduced > storageSpace)
                	// Zero is not allowed
                	throw new InvalidParametersException();
                
            }catch(UnknownNumberException ue) {
            	// Handling zeros
            	System.out.println("Parameter as zero leads to a deadlock. Hence it is not allowed");
                System.exit(1);
            }
            catch(NegativeNumberException ne) {
            	// Handling negative numbers
            	System.out.println("No negative numbers are allowed.");
                System.exit(1);
            }
            catch (InvalidParametersException ie) {
            	// Handling invalid parameters
            	System.out.println("Storage should be greater than consuption and production rate");
            	System.exit(1);
            }
            catch(Exception e) {
            	// Handling number format exceptions and all other exceptions
                System.out.println("Please provide correct arguments. \n"
                        + "ConsumerProducer.java <NumofConsumers> <UnitsConsumes> <NumofProducers> <UnitsProduces> <StorageSpace>");
                System.exit(1);
            }
        }

        //Creating producers & Consumers
        ConsumerProducer[] consumers = new ConsumerProducer[consumersCount];
        ConsumerProducer[] producers = new ConsumerProducer[producersCount];

        for(int threadiId=0;threadiId < producers.length;threadiId++)
            producers[threadiId] = new ConsumerProducer(true, storageSpace, unitsProduced , threadiId);

        for(int threadiId=0; threadiId < consumers.length; threadiId++)
            consumers[threadiId] = new ConsumerProducer(false,storageSpace,unitsConsumed,threadiId);

        // Starting Producers and Consumers
        for(ConsumerProducer producer : producers)
            producer.start();

        for(ConsumerProducer consumer : consumers)
            consumer.start();
    }
}