# Set Storage System

A set is collection of objects in which order has no significance. Members of a set are called elements.

The following operations are<br>
![](http://spiegel.cs.rit.edu/~hpb/Lectures/2181/605/605/60539.png)

defined on sets. It is also possible to ask if x is a member of a set A. x ∈ A is true, if x is a member of A otherwise false.

The following methods are implemented in the set:

              boolean       add(E e)   /* add e to the set                               */
              boolean       addAll(    /* add all elements of the set                    */
                                       /* you have to come of with the correct signature */
                                       /* for all elements of type E or subclass shape   */
                                       /* if possible                                    *
                                       /* true if one element could have been added      */
              boolean       removeAll( /* remove all elements of the set                 */
                                       /* you have to come of with the correct signature */
                                       /* for all elements of type E or subclass shape   */
                                       /* if possible                                    *
                                       /* true if all elements could have been removed   */
              Object[]      toArray()  /* return all elements of the set in an array     */
              boolean       contains(Object o)  /* true if o is in the set, false else   */
              boolean       remove(Object o)    /* remove o, true if o could be removed  */
              void          clear()    /* empty the set                                  */
              int           size()     /* # of elements in the set                       */
