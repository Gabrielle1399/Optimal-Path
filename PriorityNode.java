/**
 * This class represents the nodes of the doubly linked list used to implement the priority queue.
 * @author gaoge
 * 
 * @param <T>
 */
public class PriorityNode<T> {
	
	/**
	 * Instance variables include dataItem - reference to the data item stored in this node.
	 * next -  reference to the next node in the liked list.
	 * previous - reference to the previous node in the linked list. 
	 * priority - the priority of the data item stored in this node. 
	 */
	private T dataItem;
	private PriorityNode<T> next;
	private PriorityNode<T> previous;
	private double priority;
	
	/**
	 * Constructor. Creates a node storing the specific data and priority.
	 * @param data
	 * @param prio
	 */
	public PriorityNode(T data, double prio)
	{
		this.dataItem = data;
		this.priority = prio;
		this.next = null;
		this.previous = null;
	}
	
	/**
	 * Constructor. Creates an empty node with null data and zero priority.
	 */
	public PriorityNode()
	{
		this.dataItem = null;
		this.priority = 0;
		this.next = null;
		this.previous = null;
	}
	
	/**
	 * Get the priority of this node in the queue.
	 * @return priority of the node.
	 */
	public double getPriority()
	{
		return this.priority;
	}
	
	/**
	 * Get the data stored in this node.
	 * @return this node's data item.
	 */
	public T getDataItem()
	{
		return this.dataItem;
	}
	
	/**
	 * Get the reference to the next node in the linked list.
	 * @return the reference to the next node.
	 */
	public PriorityNode<T> getNext()
	{
		return this.next;
	}
	
	/**
	 * Get the reference to the previous node in the linked list.
	 * @return the reference to the previous node.
	 */
	public PriorityNode<T> getPrevious()
	{
		return this.previous;
	}
	
	/**
	 * Set the data item in this node.
	 * @param data
	 */
	public void setDataItem(T data)
	{
		this.dataItem = data;
	}
	
	/**
	 * Set the reference to the next node in the linked list.
	 * @param nextnode
	 */
	public void setNext(PriorityNode<T> nextnode)
	{
		this.next = nextnode;
	}
	
	/**
	 * Set the reference to the previous node in the linked list.
	 * @param prenode
	 */
	public void setPrevious(PriorityNode<T> prenode)
	{
		this.previous = prenode;
	}
	
	/**
	 * Set the priority of this node with the new priority.
	 * @param newpriority
	 */
	public void setPriority(double newpriority)
	{
		this.priority = newpriority;
	}
	
}
