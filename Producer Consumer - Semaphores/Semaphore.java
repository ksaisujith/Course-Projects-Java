/**
 * Semaphore.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

/**
 * This class is a counting semaphore. It maintains a set of permits. 
 * Each acquire() blocks if necessary until a permit is available, and then takes it.
 * Each release() adds a permit, potentially releasing a blocking acquirer.
 */
public class Semaphore{

	static int permits;
	static Object sema_lock = new Object();
	
	/**
	 * Constructor to create a semaphore with permits
	 * 
	 * @param permit		int		maximum number of threads that can access the block at a time
	 */
	public Semaphore(int permit) {
		permits= permit;
	}
	
	
	/**
	 * Acquires a permit from this semaphore, blocking until one is available, or the thread is interrupted.
	 */
	public void acquire() {
		synchronized(sema_lock) {
			// Waits until the permit is available
			while(permits <= 0) {
				try {
					sema_lock.wait();
				} catch (InterruptedException e) {
					// Handling the exception
					e.printStackTrace();
				}
			}
			//Got the permit
			permits--;		
		}
	}
	
	/**
	 * Releases a permit, returning it to the semaphore.
	 */
	public void release() {		
			synchronized(sema_lock) {
				// Releasing the lock
				permits++;
				sema_lock.notifyAll();
			}
	}
}
