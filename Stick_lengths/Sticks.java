/**
 * Sticks.java
 * Version: $Id$
 * Revision: $log$  
 *    
 */

import java.util.Arrays;

/**
 * This program takes the length of a stick and checks if it can be formed with the 
 * existing sticks by combining them. 
 * If yes, then prints the sticks that are used to make the new stick else prints No 
 * 
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 */

public class Sticks {

		
	static int[] stickLengths = { 1, 2, 3,3, 5, 8  };
    static int[] unknowStickLengths = { 1, 5, 6, 7, 8, 9,55};
    static int[] usedSticks = new int[stickLengths.length];
    static int[] slicedList = new int[stickLengths.length];
    static boolean found=false;
    
    
    /**
     * 
     * @param 	number 	Length of the stick that has to be formed
     * @return			True if it can be formed else false
     */
    public static boolean doTestLength(int number){
    	int count = 0;
    	int usedStickCount = 0;
    	
    	//Cleaning the previous result
    	Arrays.fill(usedSticks, 0);
    	
    	//Subtracting the sticks lengths from the new stick lengths until it is zero or 
    	//we run out of the sticks that we have
    	while(number >0 && slicedList.length > count) {
    		count++;
    		//Ignoring the sticks that are longer than the new stick 
    		if(number>=slicedList[slicedList.length-count]) {
    			number -= slicedList[slicedList.length-count];
    			usedSticks[usedStickCount] = slicedList[slicedList.length-count];
    			usedStickCount++;
    		}
    		//Stick can be formed
    		if(number==0) {
    			found = true;
    			return true;
    		}
    	}
    	if(found!=true)
    	{
    		found = false;
    	}
    	return false;
    	
    }
    
	public static void main(String[] args) {
		/**
		 * The main program.
		 * @param    args    command line arguments (ignored)
		 */
		
		
		int requiredStickCount = 0;
		int currentStickCount = 0;
		for ( int index = 0; index < unknowStickLengths.length; index ++ ) {
			//Cleaning the previous slicedList
	    	Arrays.fill(slicedList, 0);
	    	requiredStickCount=0;
	    	found=false;
	    	System.out.println("");

	    	//Assuming the list is always in non-descending order &
	    	//Slicing the known stick lengths list to have only the lengths 
	    	//less than unknownStickLength
	    	for(int i =0; i<=stickLengths.length; i++) {
    			if(i==stickLengths.length) {
    				
    				//Taking complete list if the unknownStickLength is greater than maximum length
    				//in the known list
    				slicedList = Arrays.copyOfRange(stickLengths, 0, i);
    				break;
    			}

    			if(stickLengths[i]>unknowStickLengths[index]) {
    				//Slicing the list only till the lower ranges
    				slicedList = Arrays.copyOfRange(stickLengths, 0, i);
    				break;
    			}
    		}
	    		   
	    	//checking with the combinations from the sliced list
	    	for (int slicedListIndex=0;slicedListIndex<slicedList.length;slicedListIndex++) {
				if(doTestLength(unknowStickLengths[index])) {
					currentStickCount=0;
					for(int stick:usedSticks)
					{
						if(stick!=0)
							currentStickCount++;
					}
					if(requiredStickCount==0)
					{
						requiredStickCount = currentStickCount;
						//Stick can be formed with different combinations
						System.out.print("Yes, The sticks to create "+ unknowStickLengths[index] + " inches are:(");
						for(int item=0;item<usedSticks.length;item++) {
							if(usedSticks[item]==0) {
								break;
							}
							System.out.print(usedSticks[item]+"  ");	
						}
						System.out.print(");");
					}
					else if(requiredStickCount==currentStickCount) {
						requiredStickCount = currentStickCount;
						System.out.print("(");
						//Stick can be formed with different combinations						
						for(int item=0;item<usedSticks.length;item++) {
							if(usedSticks[item]==0) {
								break;
							}
							System.out.print(usedSticks[item]+"  ");	
						}
						System.out.print(");");
					}
				}
				slicedList = Arrays.copyOfRange(slicedList, 0, slicedList.length-1);
			}
			
	    	if(!found) {
					System.out.print("No, The stick of "+ unknowStickLengths[index] + " inches can't be made");
			}
	    	
		}
	}
}