/**
 * Storage.java
 *
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 *
 * Version: $Id$
 * Revision: $log$
 */

/*
 * This class implements a storage system which allows store only the non-existing generic elements 
 */

public class Storage<E>{
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    /** This checks if an element is present in the set already
     *
     * @param 	o			Object			Generic element that has checked
     * @return	true		boolean		Returns true if the element is found else, false
     */
    public boolean contains(Object o) {
        if (head == null) {
            //The linked list is null
            return false;
        }
        else{
            Node<E> currentNode = head;
            for (int i =0 ; i < size; i++){
                try {
                    if (o.equals(currentNode.data))
                        //Found in the list
                        return true;
                    else
                        currentNode = currentNode.nextNode;
                } catch (NullPointerException e){
                	if(currentNode.data == null)
                		return true;
                    continue;
                }
            }
            return false;
        }
    }

    /** This adds the element e to the Storage
     *
     * @param 	e			E			Generic element that has to be added to the Storage
     * @return	true		boolean		Returns true once the element is added to the Storage
     */
    public boolean add(E e) {
        if (head == null) {
            //Creating and making it the head as there are no existing nodes
            head = new Node<E>(e);
            tail = head;
            //Incrementing the size of the storage
            size++;
        }
        else {
            if (!this.contains(e)) {
                //Element not present in the list
                //Creating a new node with the data provided
                Node<E> newNode = new Node<E>(e);
                //Adding the new node to the last
                tail.nextNode = newNode;
                //Updating the new node as the last
                tail = newNode;
                //Incrementing the size of the storage
                size++;
            }
            else{
                //Element already present in the list
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all the elements to the Storage that are not present in the passed Storage
     * 
     * @param bStorage      Storage<E>      another storage object to insert
     * @return inserSucess  boolean         returns success if inserted at least one element
     */
    public boolean addAll(Storage<E> bStorage){
        Node<E> currentNode = bStorage.head;
        boolean insertSuccess = false;
        while (currentNode != null){
            if (!this.contains(currentNode.data)){
            	//Adding since the data is not present in the current storage
                this.add(currentNode.data);
                insertSuccess = true;
            }
            currentNode = currentNode.nextNode;
        }
        return insertSuccess;
    }

    /**
     *Remove all the elements from this Storage that are present in the passed Storage
     *
     * @param bStorage      Storage<E>      another storage object to insert
     * @return inserSucess  boolean         returns success if deleted at least one element
     */
    public boolean removeAll(Storage<E> bStorage){
        Node<E> currentNode = bStorage.head;
        boolean insertSuccess = false;
        while (currentNode != null){
            if (this.contains(currentNode.data)){
            	//Found the element and deleting it
                this.remove(currentNode.data);
                insertSuccess = true;
            }
            currentNode = currentNode.nextNode;
        }
        return insertSuccess;
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
     * Removes the object from the Storage
     *
     * @param o Object
     * @return
     */
    public boolean  remove(Object o){
        if (head == null) {
            //The Storage is null
            return false;
        }
        else{
            boolean removeSuccess = false;
            Node<E> currentNode = head;
            for (int index =0 ; index < size; index++){
            	//Checking each node to reach the node to delete
            	try {
	                if (o.equals(currentNode.data)){
	                	//Deleting the node
	                    remove(index);
	                    removeSuccess = true;
	                }
                } catch(NullPointerException e) {
                	if(o == null) {
                		removeSuccess = remove(index) == null?true:false;		
                	}
                }
                currentNode = currentNode.nextNode;
            }
            return removeSuccess;
        }
    }

    /*
     * Prints the elements in the Storage
     *
     * @return	str	String		String with all the elements in the Storage
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
     * Returns all the elements in the storage in the form of an object array
     *
     * @return	elements	 Object[]	 Object array with all the elements in Storage
     */
    public Object[] toArray() {
        Object[] elements = new Object[size];
        if(this.head !=null) {
	        Node<E> currentNode = head;
	        for (int index = 0; currentNode != null; index++){
	        	//Adding elements to the object array
	        	elements[index] = (Object) currentNode.data;
	            currentNode = currentNode.nextNode;
	        }
	      //Returning all the objects
	        return elements;
        }
        else {
        	return null;
        }
    }

    /**
     *  Removes the element at the specified position in this list.
     *
     * @param	index       int			index position of the element to be deleted
     * @return	returnValue E		the head (first element) of this list.
     */
    private E remove(int index) {
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
     *  Retrieves and removes the head (first element) of this list.
     *
     * @return	E	value		the head (first element) of this list.
     */
    private E remove() {
        if(head != null) {
            // returning the value in the head and deleting it
            E value = (E) head.data;
            head = head.nextNode;
            size--;
            return value;
        }
        return null;
    }

    /* Returns the number of elements in this list
     *
     * @return	size	int		Number of elements in this list.
     */
    public int	size() {
        return size;
    }
}
