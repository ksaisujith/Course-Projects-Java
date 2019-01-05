/**
 * FastSort.java
 * Version: $Id$
 * Revision: $log$  
 *    
 */

/**
 * This class takes generic data and stores in linked list. 
 * This also provides all the methods required to add access to the Storage
 * Sorting on these items has the complexity of O(1)
 * 
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 */

public class FastSort<E extends Comparable<E>> implements StorageI<E> {
	
	private Node<E> head = null;
	private Node<E> tail = null;
	private Node<E> iterator = head;
	private int size = 0;

    @Override
    /**
	 * This adds the element e to the Storage by checking the position
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
			size++;
		}
		else {
			//Initialising the values
			int insertPosition = 0;
			Node<E> currentNode = this.head;
			while(currentNode!=null) {
				//Finding the position to insert
				if(currentNode.data.compareTo(e)<0) {
					insertPosition++;
				}
				if(currentNode.data.compareTo(e)>0) {
					//Found the position
					break;
				}
				//Moving to next node
				currentNode = currentNode.nextNode;
			}
			//Inserting the element at found index position
			add(insertPosition,e);
		}
		return true;
	}
	
    @Override
	/**
	 * Retrieves and returns the value that is being pointed by iterator of this Storage
     * 
     * @return	E	value		the value that is being pointed by the iterator this list.
     */
    public E get() {
    	if(iterator != null) {
			// returning the value iterator
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
     * @param	o	Object	
     */
    public boolean contains(E e) {
	   if (head == null) {
           //The linked list is null
           return false;
       }
       else{
           Node<E> currentNode = head;
           while (currentNode != null){
        	   //Checking if the value already present in the Storage
               if (e == currentNode.data)
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
    	//Pointing the iterator to the head so that it starts from first
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
    
    /**
	 * This adds the element e to the Storage at the index position
     * 
     * @param	int			index		Index in the list where the element has to be inserted in the Storage
     * @param 	E			e			Generic element that has to be added to the Storage
     * @return	boolean		true		Returns true once the element is added to the Storage
     */
	private void add(int index, E element) {
		if(index > size || index < 0) {
			//Displaying error if the index greater than the list
			System.err.println("IndexOutOfBound of the storage. Index provided:"
					+ index + " Max index:"+ (size-1));
			
		}
		else {
			if (head == null && index == 0) {
				//List is empty and adding the new node at the starting position
				head = new Node<E>(element);
				tail = head;
				iterator = head;
			}
			else if(index == 0)
			{
				// if the index is 0, it is added as head
				addFirst(element);
			}
			else if (index == size) {
				addLast(element);
			}
			else {//Creating a new node for the element 
				Node<E> newNode = new Node<E>(element);
				//Traversing for the node in index 
				Node<E> nodeAtIndex = head;
				for(int listIndex = 1 ; listIndex <= index-1 ; listIndex++) {
					nodeAtIndex = nodeAtIndex.nextNode;
				}
					//Add the index-th node's links to the new node
					newNode.nextNode = nodeAtIndex.nextNode;
					//breaking the index-th node link and adding to the new node
					nodeAtIndex.nextNode = newNode;
					size++;
			}
			
		}
	}
	
	/**
	 * This adds the element e to the Storage at the beginning
     * 
     * @param 	E			e			Generic element that has to be added to the Storage
     * @return	boolean		true		Returns true once the element is added to the Storage
     */
	private void    addFirst(E e){
		if (head == null) {
			//creating and adding the first node
			head = new Node<E>(e);
			tail = head;
		}
		else {
			//creating the node with the new data
			Node<E> newNode = new Node<E>(e);
			//adding it to the first 
			newNode.nextNode = head;
			//Making it as the head
			head = newNode;
		}
		iterator = head;
		size++;
	}
	
	/**
	 * This adds the element e to the Storage at the beginning
     * 
     * @param 	E			e			Generic element that has to be added to the Storage
     * @return	boolean		true		Returns true once the element is added to the Storage
     */
	private boolean addLast(E e) {
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
}
