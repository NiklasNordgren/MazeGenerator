/*
 * This is free to use in courses at University of Gävle.
 */


/**
 * An interface for the generic Abstract Data Type <code>Queue</code>.
 * 
 * @author Magnus Hjelmblom
 * @author Anders Jackson
 * @author Peter Jenke
 * @version 2014-01-16
 */

public interface Queue<V>
{
    /** 
     * Removes all elements from this queue and writes 
     * a message on {@link System}.out).
     */
    public void clear ();
    
	/**
	 * Checks if this queue is empty.
	 * 
	 * @return true if queue is empty, false otherwise.
	 */
	public boolean isEmpty ();

	/**
	 * Add an element at the last position in this queue.
	 * 
	 * @param v object added to this queue.
	 */
	public void enqueue (V v);

	/**
	 * Removes the first element from this queue. Will throw
	 * {@link ArrayQueueEmptyException} if this queue is empty.
	 * 
	 * @return the first element.
	 */
	public V dequeue ();

	/**
	 * Returns the first element in this queue, without removing it. Will throw
	 * {@link ArrayQueueEmptyException} if this queue is empty.
	 * 
	 * @return the first element.
	 */
	public V getFront ();
}
