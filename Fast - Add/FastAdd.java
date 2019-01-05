/**
 * FastAdd.java
 * Version: $Id$
 * Revision: $log$  
 *    
 */

/*
 * This class takes generic data and stores in linked list. 
 * This also provides all the methods required to add access to the Storage
 * Adding on these items has the complexity of O(1)
 * 
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 */

public class FastAdd<E extends Comparable<E>> implements StorageI<E> {
	
	private Node<E> head = null;
	private Node<E> tail = null;
	private Node<E> iterator = head;
	private int size = 0;

    @Override
    /**
	 * This adds the element e to the Storage
     * 
     * @param 	E			e			Generic element that has to be added to the Storage
     * @return	boolean		true		Returns true once the element is added to the Storage
     */
	public boolean add(E e) {
		if (head == null) {
			//Creating and making it the head as there are no existing nodes
			head = new Node<E>(e);
			tail = head;
			iterator = head;
		}
		else {
			//Creating a new node with the data provided
			Node<E> newNode = new Node<E>(e);
			//Adding the new node to the last
			tail.nextNode = newNode;
			//Updating the new node as the last
			tail = newNode;
		}
		size++;
		return true;
	}
	
    @Override
	/**
	 * Retrieves and returns the value that is being pointed by iterator of this Storage
     * 
     * @return	E	value		the value that is being pointed by the iterator this list.
     */
    public E get() {
    	if(iterator  != null) {
			// returning the value in the head and deleting it
			E value = (E) iterator.data;
			iterator = iterator.nextNode;
			return value;			
		}
		return null;
    }

    @Override
	/**
	 * Deletes all the values and clears the Storage
     * 
     * @return	void		null		
     */
	public void    clear()	{
		//Deleting all the values
		head = null;
		tail = null;
		size = 0;
	}

    @Override
    /**
     * Checks if the object is present in the list
     * 
     * @param	e	E	
     */
    public boolean contains(E e) {
	   if (head == null) {
           //The linked list is null
           return false;
       }
       else{
           Node<E> currentNode = head;
           while (currentNode != null){
               if (e == currentNode.data)
            	   //Found the value
                   return true;
               else
                   currentNode = currentNode.nextNode;
           }
           return false;
       }
    }

    @Override
    /**
     * Checks if the list have elements or empty
     * 
     * @return constant 	boolean 	Return true if the list is empty else false
     */
    public boolean isEmpty() {
        return head == null?true:false;
    }

    @Override
    /**
     * Sorts the elements in the list
     * 
     * @param 
     * @return
     */
    public void sort() {
    	Node<E> currentNode = this.head;
    	E temp;
    	for(int pass =0; pass<size;pass++) {
    		while(currentNode.nextNode !=null) {
    			if(currentNode.data.compareTo(currentNode.nextNode.data)>0) {
                    // swapping the values 
            		temp =currentNode.data;
            		currentNode.data=currentNode.nextNode.data;
            		currentNode.nextNode.data = temp;     				
    			}
    			currentNode = currentNode.nextNode;
    		}
    		currentNode = head;
    	}
    	iterator = head;
    }

    @Override
	/**
	 * Returns the number of elements in this list
     * 
     * @return	size	int		Number of elements in this list
     */
	public int     size() {
		return size;
	}

    @Override
    /**
     * Returns the name of the class name
     * 
     * @return	constant	String		Name of the class
     */
    public String getClassName() {
        return this.getClass().getName();
    }
    
    @Override
    /**
     * Returns all the elements in the list as a string
     * 
     * @return	str		String		all elements of the list as a String
     */
	public String toString() {
		String str = "";
		Node<E> currentNode = head;
		while(currentNode != null) {
			str += currentNode.data + " ";
			currentNode = currentNode.nextNode;
		}
		return str;
	}
}