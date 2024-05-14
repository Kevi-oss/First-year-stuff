import java.util.*;
public class DLinkedNode<T> {
	private T dataItem;
	private double priority;
	private DLinkedNode<T> next;
	private DLinkedNode<T> prev;
	// set dataItem and priority
	public DLinkedNode (T data, double prio)
	{
		dataItem = data;
		priority = prio;
	}
	//empty parameter
	public DLinkedNode()
	{
		dataItem = null;
		priority = 0;
	}
	//getter method
	public double getPriority()
	{
		return priority;
	}
	//@return dataItem
	public T getDataItem()
	{
		return dataItem;
	}
	//get next item
	public DLinkedNode<T> getNext()
	{
		return next;
	}
	//return previous item
	public DLinkedNode<T> getPrev()
	{
		return prev;
	}
	//change data Item
	public void setDataItem(T data)
	{
		dataItem = data;
	}
	//change next
	public void setNext(DLinkedNode<T> next)
	{
		this.next = next;
	}
	//change previouis
	public void setPrev(DLinkedNode<T>past)
	{
		this.prev = past; 
	}
	//change priority of item
	public void setPriority(double num)
	{
		priority = num;
	}
	
	
	
}
