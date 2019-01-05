/**
 * MyHashSet.java
 *
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 *
 * Version: $Id$
 * Revision: $log$
 */

/*
 * This class implements a generic storage system with Hashset.
 */

public class MyHashSet<E> implements SetI<E> {
    private int hashBucketSize;
    private Storage<E>[] buckets;
    private int size;

    /**
     * This constructor creates the Hashmap with default 10 buckets
     *
     * @param:
     * @return:
     */
    public MyHashSet(){
        // Initializing the values of the hashmap
        this.size = 0;
        this.hashBucketSize = 10;
        // Creates the hashmap
        this.buckets = new Storage[hashBucketSize];
        for(int i = 0;i<hashBucketSize;i++)
            buckets[i] = new Storage();
    }

    /**
     * This constructor creates the Hashmap with custom hasBuckets buckets
     *
     * @param hashBuckets       int         number of buckets required in the Hashmap
     * @return
     */
    public MyHashSet(int hashBuckets){
        // Initializing the values of the hashmap
        size = 0;
        hashBucketSize = hashBuckets;
        // Creates the hashmap with hashBucketSize
        this.buckets = new Storage[hashBucketSize];
        for(int i = 0;i<hashBucketSize;i++)
            buckets[i] = new Storage();
    }

    @SuppressWarnings("unchecked")
	@Override
    /**
     * This method checks if the object is present in the hashmap
     *
     * @param   o                 Object        Object that has to be checked
     * @return  false/true        boolean       true if object exists else false
     */
    public boolean contains(Object o){
        // Calculating the hash function to know the bucket of the object
        int bucket_number = hashFunction(o);
        // checking for the object in the bucket
        return buckets[bucket_number].contains((E)o);
    }

    @Override
    /**
     * This method checks if the hashmap is empty or not
     *
     * @param
     * @return    false/true        boolean       true if hashmap is empty else false
     */
    public boolean isEmpty() {
        for(int bucket=0;bucket<hashBucketSize;bucket++){
            try {
                // Checking the size of each bucket
                if (buckets[bucket].size() != 0)
                    return false;
            } catch (NullPointerException e) {
                continue;
            }
        }
        return true;
    }

    @Override
    /**
     * This method removes the object from the hashmap
     *
     * @param       o           Object      the element that has to be deleted from the hasmap
     * @return      false/true  boolean     true if object if deleted successfully else false
     */
    public boolean remove(Object o) {
        try {
            if (this.contains(o)) {
                // Finding the bucket
                int bucket_index = this.hashFunction(o);
                // deleting the element from the bucket
                if (buckets[bucket_index].remove(o))
                    size--;
                return true;
            }
        } catch (NullPointerException e){
            return false;
        }
        return false;
    }

    @Override
    /**
     * Returns number of elements in the hashmap
     *
     * @param
     * @return      size        int     Size of the hashmap
     */
    public int size() {
        return this.size;
    }

    @Override
    /**
     * Returns all the elements in the hashmap
     *
     * @param
     * @return      allElements     Object[]        all the elements in the hashmap
     */
    public Object[] toArray() {
    	//Creating the array to store the values
        Object[] allElements = new Object[this.size()];
        int elementCount = 0 ;
        // Looping through each bucket
        for(int index=0; index<this.hashBucketSize;index++){
            try{
            	// Adding all the elements in the bucket to the array only if it is not null
                for(Object o : buckets[index].toArray()){
                    if(o != null){
                        allElements[elementCount] = o;
                        elementCount++;
                    }
                }
            }catch (NullPointerException e){
            	// Moving to the next bucket if this bucket is null
                continue;
            }
        }
        return allElements;
    }

    @Override
    /**
     * Adds the element to the hasmap if that is not present in the list
     *
     * @param       e               E           element that has to be added to the hashmap
     * @return      false/true      boolean     true if object is added successfully else false
     */
    public boolean add(E e){
    	// Calculating the hash function to decide the bucket 
        int bucket_index = hashFunction(e);
        // Inserting the element in the correct bucket 
        if(buckets[bucket_index].add(e)){
            this.size++;
            return true;
        }
        return false;
    }

    @Override
    /**
     * Adds all the elements that are not present in the current hasmap
     *
     * @param       c                   SetI        Another set of elements that has to be added
     * @result      insertAtleastone    Boolean     true if atleast one element is inserted else false
     */
    public boolean addAll(SetI<? extends E> c) {
        boolean insertAtleastone = false;
        // Looping through each element in the set and adding it to the current hashmap
        for(Object e:c.toArray()){
            if(this.add((E)e))
            {
                insertAtleastone = true;
            }
        }
        return insertAtleastone;
    }

    @Override
    /**
     * Checks if all the elements are present in the current hashmap
     *
     * @param       c               SetI        list of elements that has to be tested in the hasmap
     * @result      false/true      boolean     true if all objects are present in hashmap else false
     */
    public boolean containsAll(SetI<?> c) {
    	// Looping through each element in the list and checking if all elements exist in the current map
        for(Object obj:c.toArray()){
            if(!this.contains(obj))
                return false;
        }
        return true;
    }

    @Override
    /**
     * Removes all elements from the hasmap
     *
     * @param       c                   SetI        list of elements that are to be deleted from the hashmap
     * @return      oneElementRemoved   boolean     returns true if at least one element is deleted else false
     */
    public boolean removeAll(SetI<?> c) {
        boolean oneElementRemoved = false;
        // Looping through each element and removing the element in the list
        for(Object obj:c.toArray()){
            if(this.remove(obj)){
                oneElementRemoved = true;
            }
        }
        return oneElementRemoved;
    }

    @Override
    /**
     * resets the hashmap
     *
     * @param
     * @return
     */
    public void clear() {
    	// Clearing all the buckets
        for(int bucket=0;bucket<hashBucketSize;bucket++){
            buckets[bucket]=new Storage();
        }
        size = 0;
    }


    /**
     * Checks if 2 variables are pointing to the same objects
     * https://docs.oracle.com/javase/7/docs/api/java/util/AbstractSet.html#hashCode()
     * 
     *
     * @param       o               Object      Object that has to be tested with the current hashset
     * @return      true/false      boolean     returns true if both the object are true else false
     */
    @Override
    public boolean	equals(Object o){
    	boolean isEqual = false;
    	// Checking if both are pointing to same objects
       if(this == o)
        isEqual = true;
 
       return  isEqual;
    }


    @Override
    /**
     * https://docs.oracle.com/javase/7/docs/api/java/util/AbstractMap.html#hashCode()
     * Based on the above documentation the hashcode function returns the hashcode of the hashmap by adding the hashvalues of all the
     * objects in the hashmap
     *
     * @param
     * @return      hashCode        int     hashCode value of the object
     */
    public int	hashCode(){
        int hashCode = 0;
        for(Object hashelement : this.toArray())
            hashCode += this.hashFunction(hashelement);
        return hashCode;
    }


    /**
     * Calculates the hash function and returns the bucket index to which the element is inserted
     *
     * @param   element      Object      element to which hashFunction has to be calculated
     * @return  hascode      int         calculated bucket number
     */
    private int hashFunction(Object element){
        if(element == null)
            return 0;
        // Initializing the variables
        int hashCode = 0;
        int intValue = 0;
        // Converting the object to a string
        String stringValue = element.toString();
        for(int c_index=0; c_index < stringValue.length();c_index++){
        	// Adding the ascii value of each character
            intValue += (int)stringValue.charAt(c_index);
        }
        // Calculating the hashcode by mod division with the size
        hashCode = intValue%hashBucketSize;
        return hashCode;
    }

    @Override
    /**
     * Returns the elements in the hashmap as a string
     * 
     * @param
     * @return		str		String		all elements in the hashmap
     */
    public String toString()
    {
        String str = "";
        for(int index=0; index<this.hashBucketSize;index++) {
            str += "Bucket "+ index + ":";
            try{
                for(Object element : buckets[index].toArray()) {
                        str += element + "-> ";
                }
            }catch(NullPointerException e) {
                str += "null\n";
                continue;
            }
            str += "null \n";
        }
            return  str;
    }
}