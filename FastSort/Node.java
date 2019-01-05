/**
 * Node.java
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 * Version: $Id$
 * 
 * Revision: $log$   
 */

/*
 * This class creates generic node which holds the data and the next node
 */
public class Node<E> {
	E data;
	Node<E> nextNode = null;
	// Constructor creates a node with the value provided
	public Node(E value) {
		this.data = value; 
	}
}
