/**
 * Storage.java
 * 
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 * 
 * Version: $Id$
 * Revision: $log$   
 */

/**
 * This class implements a storage system which allows to storage and remove items using generics.
 */

public class Storage<E>{	
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	
	
	/**
	 * This adds the element e to the Storage at the beginning
     * 
     * @param 	E			e			Generic element that has to be added to the Storage
     * @return	boolean		true		Returns true once the element is added to the Storage
     */
	public boolean add(E e) {
		if (head == null) {
			//Creating and making it the head as there are no existing nodes
			head = new Node<E>(e);
			tail = head;
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
	
	/**
	 * This adds the element e to the Storage at the beginning
     * 
     * @param 	E			e			Generic element that has to be added to the Storage
     * @return	boolean		true		Returns true once the element is added to the Storage
     */
	public void    addFirst(E e){
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
		size++;
	}
	
	/**
	 * This adds the element e to the Storage at the end
     * 
     * @param 	E			e			Generic element that has to be added to the Storage
     * @return	void				
     */
	public void addLast(E e) {
		add(e);
	}
	
	/**
	 * This adds the element e to the Storage at the index position
     * 
     * @param	int			index		Index in the list where the element has to be inserted in the Storage
     * @param 	E			e			Generic element that has to be added to the Storage
     * @return	boolean		true		Returns true once the element is added to the Storage
     */
	public void add(int index, E element) {
		if(index >= size || index < 0) {
			//Displaying error if the index greater than the list
			System.err.println("IndexOutOfBound of the storage. Index provided:"
					+ index + " Max index:"+ (size-1));
			
		}
		else {
			if (head == null && index == 0) {
				//List is empty and adding the new node at the starting position
				head = new Node<E>(element);
				tail = head;
			}
			else if(index == 0)
			{
				// if the index is 0, it is added as head
				addFirst(element);
			}
			else if (index == size - 1) {
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
	
	/**
	 * Retrieves, but does not remove, the head (first element) of this list.
     * 
     * @return	E				head.data	the head (first element) of this list.
     */
	public E element() {
		// returning the value in the head
		return  (E) head.data;
	}

	/**
	 * Retrieves and removes the head (first element) of this list.
     * 
     * @return	E	value		the head (first element) of this list.
     */
	public E remove() {
		if(head != null) {
			// returning the value in the head and deleting it
			E value = (E) head.data;
			head = head.nextNode;
			size--;
			return value;			
		}
		return null;
	}
	
	/**
	 * Removes the element at the specified position in this list.
     * 
     * @param	int	index			index position of the element to be deleted
     * @return	E	returnValue		the head (first element) of this list.
     */
	public E remove(int index) {
		E returnValue = null;
		if (index >= size || index < 0) {		
			//Displaying error if the index greater than the list
			System.err.println("IndexOutOfBound of the storage. Index provided:"
					+ index + " Max index:"+ (size-1));
		}
		
		else {
			if (head != null && index == 0) {
				//List is empty and adding the new node at the starting position
				returnValue = remove();
			}			
			else {
				//Traversing for the node in index
				Node<E> nodeAtIndex = head;
				for(int listIndex = 1 ; listIndex <= index-1; listIndex++) {
					nodeAtIndex = nodeAtIndex.nextNode;
				}
					//Add the data at the index location to the return value 
					returnValue = (E) nodeAtIndex.nextNode.data;
					//breaking the chain and skipping the node at the index position
					nodeAtIndex.nextNode = nodeAtIndex.nextNode.nextNode;
					size--;
			}
			
		}
		return returnValue;
	}
	
	/**
	 * Returns the number of elements in this list
     * 
     * @return	int	size		Number of elements in this list.
     */
	public int     size() {
		return size;
	}

	
	/**
	 * Prints the elements in the Storage
     * 
     * @return	String	str		String with all the elements in the Storage
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
