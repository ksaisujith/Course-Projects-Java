/**
 * ConsumerProducer.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

import java.util.Scanner;

/**
 * This class reads the number of producer & consumer threads should exist and the units they produce/consume. 
 * It also reads the storage size to wait and proceed the production/consumption by multi-thread communication
 */

public class ConsumerProducer extends Thread{	
	// Variable Declaration
    int threadId;
    int units;
    boolean isProducer;
    int producer_type = 99;
    static int available_space = 0;
    static Integer[] storage = null;
    final static int[] max_vals = {3,5,2};
    static int[] type_count = {0,0,0};
    static int[] partition = null;

    /**
     * Constructor which creates producers and Consumers. This also initializes the variables
     * 
     * @param is_Producer boolean	flag for producer or consumer
     * @param availableSpace int	total space of storage
     * @param unit int	number of units it produces/consumes
     * @param id int	Thread ID
     */ 
    public ConsumerProducer(boolean is_Producer,int availableSpace, int unit, int id ) {
        this.isProducer = is_Producer;
        if(isProducer) {
        	// Assigning the producer type for each producer
        	this.producer_type = id % 3;
        	this.units = unit;
        }
        else {
        	// units it consumes
        	this.units = 10;
        }
        this.threadId = id;
        
        // Creating the storage for the first time 
        if(storage == null) {
            available_space = availableSpace;
	        storage = new Integer[availableSpace];
	        for(int i = 0; i < availableSpace ; i++)
	            storage[i] = -1;
        }
        
        // Creating the storage partition for the first time 
        if (partition == null) {
        	int parition_size = (int)availableSpace/3;
        	partition = new int[4];
        	partition[0] = 0;
        	partition[1] = parition_size * 1;
        	partition[2] = parition_size * 2;
        	partition[3] = parition_size * 3 <= availableSpace ? availableSpace :parition_size * 3 ;
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
                    if(type_count[this.producer_type] < partition[this.producer_type + 1] 
                    		&&  max_vals[this.producer_type] > type_count[this.producer_type]
                    				&& partition[this.producer_type + 1] - partition[this.producer_type] - type_count[this.producer_type] >= this.units ) {
                    	// Space available to produce the units                     
                    	for(int i=0; i<this.units; i++) {
                    		// Producing the units                 		
                                for(int index= partition[this.producer_type]; 
                                		index<partition[this.producer_type + 1]; index ++) {
                                	if(storage[index] == -1) {
                                        storage[index] = this.producer_type;
                                        type_count[this.producer_type]++;
                                        available_space--;
                                		break;
                                	}
                                }
                        }
                        System.out.println(" "+ this.threadId+ " : Produce " + this.units + " units"
                       // 		+ "\t"+ this  // **Uncomment this line for debugging**
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
                    //if(available_space < storage.length - this.units ) {
                	
                	if(type_count[0] >= 3 && type_count[1] >= 5 && type_count[2] >= 2) {
                    	// Checking if there are enough units available to consume
                        for(int i=0;i< 3;i++) {
                        	// Consuming the units of type 1
                            for(int index = 0; index < storage.length ; index ++) {
                            	if(storage[index] == 0) {
                            		storage[index] = -1;
                            		type_count[0]--;
                                    available_space++;
                            		break;
                            	}
                            }

                        }
                        
                        for(int i=0; i < 5; i++) {
                        	// Consuming the units of type 2
                            for(int index = 0; index < storage.length ; index ++) {
                            	if(storage[index] == 1) {
                            		storage[index] = -1;
                            		type_count[1]--;
                                    available_space++;
                            		break;
                            	}
                            }

                        }
                        
                        for(int i=0; i < 2; i++) {
                        	// Consuming the units of type 3
                            for(int index = 0; index < storage.length ; index ++) {
                            	if(storage[index] == 2) {
                            		storage[index] = -1;
                            		type_count[2]--;
                                    available_space++;
                            		break;
                            	}
                            }
                        }
                        
                        System.out.println(" "+ this.threadId+ " : Consume " + this.units + " units"
                       // 		 + "\t"+ this  // **Uncomment this line for debugging**
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
        int unitsProduced = 0;
        int storageSpace = 0;

        if(args.length == 0){
        	   System.out.println("Please provide arguments. \n"
                       + "ConsumerProducer.java <Num of units produced> <Consumers count> <Producers count> <StorageSpace>");
               System.exit(1);
        }
        else {
            try {
            	// Reading values from the input
                unitsProduced =  Integer.parseInt(args[0]);
                consumersCount = Integer.parseInt(args[1]);
                producersCount = Integer.parseInt(args[2]);
                storageSpace = Integer.parseInt(args[3]);
                
                if (consumersCount < 0  || producersCount < 0 || unitsProduced < 0 || storageSpace < 0)
                	// Negative count is not allowed
                	throw new NegativeNumberException();
                
                if (consumersCount == 0  || producersCount == 0 || unitsProduced == 0 || storageSpace == 0)
                	// Zero is not allowed
                	throw new UnknownNumberException();
                if ( unitsProduced > storageSpace)
                	// Zero is not allowed
                	throw new InvalidParametersException();
                
                if ( unitsProduced > storageSpace/3 + 1 )
                	// Zero is not allowed
                	throw new InvalidParametersException();
                
                if(unitsProduced  <= (storageSpace/3) && unitsProduced >= (int)(storageSpace/3) - 3 )
                	throw new DeadLockException();
                
                if(storageSpace < 15){
                	throw new SpaceNotSufficientException();
                }
                
            }catch(UnknownNumberException ue) {
            	// Handling zeros
            	System.out.println("Parameter as zero leads to a deadlock. Hence it is not allowed");
                System.exit(1);
            }
            catch(DeadLockException e) {
            	System.out.println("There is high probability of occuring a deadlock with these parameters"
            			+ "\nPlease enter to continue");
            	Scanner scan = new Scanner(System.in);
            	scan.nextLine();
            	scan.close();
            }
            catch(NegativeNumberException ne) {
            	// Handling negative numbers
            	System.out.println("No negative numbers are allowed.");
                System.exit(1);
            }
            catch(SpaceNotSufficientException e) {
            	// Handling invalid parameters
            	System.out.println("Storage size should be greater than 15 to avoid deadlock");
            	System.exit(1);
            }
            catch (InvalidParametersException ie) {
            	// Handling invalid parameters
            	System.out.println("Storage should be greater than production rate and production rate should be less than  1/3 of storage space ");
            	System.exit(1);
            }
            catch(Exception e) {
            	// Handling number format exceptions and all other exceptions
                System.out.println("Please provide correct arguments. \n"
                        + "ConsumerProducer.java <Num of units produced> <Consumers count> <Producers count> <StorageSpace>");
                System.exit(1);
            }
        }

        //Creating producers & Consumers
        ConsumerProducer[] consumers = new ConsumerProducer[consumersCount];
        ConsumerProducer[] producers = new ConsumerProducer[producersCount];


        for(int threadId=0;threadId < producers.length;threadId++)
            producers[threadId] = new ConsumerProducer(true,storageSpace,unitsProduced,threadId);
        

        for(int threadId=0; threadId < consumers.length; threadId++) {       	
            consumers[threadId] = new ConsumerProducer(false,storageSpace, 10 , threadId);
        }

        // Starting Producers and Consumers
        for(ConsumerProducer producer : producers)
            producer.start();

        for(ConsumerProducer consumer : consumers)
            consumer.start();
    }
}