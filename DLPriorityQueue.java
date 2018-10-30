/**
 * This class implements a queue that stores a collection of data with specific priority. The data items of the
 * priority queue are stored in a doubly linked list.
 * @author gaoge
 *
 * @param <T>
 */
public class DLPriorityQueue<T> implements PriorityQueueADT<T> {

	/**
	 * Instance variables include front - reference to the first node of the doubly linked list.
	 * rear - reference to the last node of the doubly linked list.
	 * count - the number of data items in the priority queue.
	 */
	private PriorityNode<T> front;
	private PriorityNode<T> rear;
	private int count;
	
	/**
	 * Constructor. Creates an empty priority queue. 
	 */
	public DLPriorityQueue()
	{
		front = null;
		rear = null;
		count = 0;
	}
	
	/**
	 * Adds the given dataItem with priority to the rear of the queue.
	 * @param dataItem
	 * @param priority
	 */
	public void enqueue(T dataItem, double priority)
	{
		PriorityNode<T> newnode = new PriorityNode<T>(dataItem, priority);
		if (isEmpty())
		{
			front = newnode;
		} else {
			newnode.setPrevious(rear);  //set rear as the previous node of new node.
			rear.setNext(newnode);  //set new node as the next node of the rear.
		}
		rear = newnode;
		count++;
	}
	
	/**
	 * Removes and returns the data item at the front of the priority queue.
	 * @return the data item that is removed.
	 * @throws EmptyPriorityQueueException is the queue is empty.
	 */
	public T dequeue() throws EmptyPriorityQueueException
	{
		if (isEmpty())
		{
			throw new EmptyPriorityQueueException("The queue is empty!");
		}
		T result = front.getDataItem();
		front = front.getNext();
		if (front == null)  //if there is only one node in the list.
		{
			rear = null;  //set the reference of rear to null.
		}
		front.setPrevious(null);
		count--;
		return result;
	}
	
	/**
	 * Get the priority of the specified data item.
	 * @param dataItem
	 * @return the priority of the data.
	 * @throws InvalidDataItemException if the given data is not found in the queue.
	 */
	public double getPriority(T dataItem) throws InvalidDataItemException
	{
		PriorityNode<T> current = front;
		while (current != null)
		{
			if (current.getDataItem().equals(dataItem))  //to search if dataItem is in the list.
			{
				return current.getPriority();
			}
			current = current.getNext();  //update current reference.
		}
		throw new InvalidDataItemException("Given data item is not found!");
	}
	
	/**
	 * Changes the priority of the given element to the new value.
	 * @param dataItem
	 * @param newPriority
	 * @throws InvalidDataItemException if the given data is not found in the queue.
	 */
	public void changePriority(T dataItem, double newPriority) throws InvalidDataItemException
	{
		PriorityNode<T> current = front;
		while (current != null)
		{
			if (current.getDataItem().equals(dataItem))
			{
				current.setPriority(newPriority);  //set new priority.
				return;
			}
			current = current.getNext();
		}
		throw new InvalidDataItemException("Given data item is not found!");
	}
	
	/**
	 * Removes and returns the data item in the priority queue with smallest priority.
	 * @return the data item with smallest priority.
	 * @throws EmptyPriorityQueueException if the queue is empty.
	 */
	public T getSmallest() throws EmptyPriorityQueueException
	{
		PriorityNode<T> current = front;
		PriorityNode<T> smallest = current;  //create a variable to track the smallest priority.
		T result = null;
		if (isEmpty())
		{
			throw new EmptyPriorityQueueException("The queue is empty!");
		}
		while (current != null)
		{
			if (current.getNext() != null && current.getNext().getPriority() < smallest.getPriority())
			{
				smallest = current.getNext();
			}
			current = current.getNext();
		}
		result = smallest.getDataItem();
		if (smallest == front)  //if the front node has smallest priority.
		{
			this.removeFirst();
			
		} else if (smallest == rear) {  //if the rear has smallest priority.
			this.removeLast();
		} else {
			smallest.getPrevious().setNext(smallest.getNext());  //set the next as the next node of previous node.
			smallest.getNext().setPrevious(smallest.getPrevious());  //set previous as the previous node of the next.
			count--;
		}
		return result;
	}
	
	/**
	 * Returns true if the priority queue is empty and it returns false otherwise.
	 * @return true if it's empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		if (count == 0)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Get the number of data items in the priority queue.
	 * @return count
	 */
	public int numItems()
	{
		return count;
	}
	
	/**
	 * A string representation of the priority queue.
	 * @return string representation
	 */
	public String toString()
	{
		String result = "";
		PriorityNode<T> current = front;
		while (current != null)
		{
			result = result + current.getDataItem().toString();
			current = current.getNext();
		}
		return result;
	}
	
	/**
	 * Remove the first item in the queue.
	 */
	private void removeFirst()  //helping method.
	{
		front = front.getNext();
		if (front == null)
		{
			rear = null;
		} else {
			front.setPrevious(null);
		}
		count--;
	}
	
	/**
	 * Remove the last item in the queue.
	 */
	private void removeLast()  //helping method.
	{
		rear = rear.getPrevious();
		if (rear == null)
		{
			front = null;
		} else {
			rear.setNext(null);
		}
		count--;
	}
	
}
