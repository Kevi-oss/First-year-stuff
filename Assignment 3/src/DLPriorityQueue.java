public class DLPriorityQueue<T> implements PriorityQueueADT<T>
{
	private DLinkedNode<T> front;
	private DLinkedNode<T> rear;
	private int count;
	public DLPriorityQueue()
	{	
		count =0;
	}
	public void add (T dataItem, double priority)
	{
		//new node
		DLinkedNode<T> next = new DLinkedNode<T>(dataItem, priority);
		/* If count == 0, then we set up the front and rear with the Node and increase count*/
		if(count == 0)
		{
			front = next;
			rear = front;
			count++;
		}
		/*
		 * But if count is greater than 0, then we place it inside the queue
		 */
		else
		{
			//If prority number is greater or equal than all in the Queue
			if(priority >= rear.getPriority())
			{
				//Place it at the back of the Queue
				
				rear.setNext(next);
				rear = rear.getNext();
				count++;
			}
			//Check if priority highest
			else if(priority<front.getPriority())
			{
				next.setNext(front);
				front = next;
				count++;
				
			}
			//otherwise
			else
			{
				/*
				 * Go through the queue and look for the place where the priority fits,
				 * in this while loop, I used value.getNext().getPriority because I knew
				 * that the priority of the new node is less than the last one,
				 * which means the loop would end by the last node, making it so that 
				 * I won't get a empty node from doing this loop
				 */
				DLinkedNode<T> value = front;
				//Get the last node that has lower (greater) priority than the node
				while(priority>value.getNext().getPriority())
				{
					value = value.getNext();
				}
				/*
				 * exchange, set the next of node as whatever is next for value.
				 * And set its previous to value, afterwards set next for value to be the new node
				 */
				next.setNext(value.getNext());
				next.setPrev(value);
				value.setNext(next);
				//Always need to increase the count 
				count++;
			}
		}
		//This method also puts items with the same priority in the order they are entered
			
	}
	//upgradePriority method
	public void updatePriority (T dataItem, double newPriority) throws InvalidElementException
	{
			//@var spotted would be the node which contains the item from parameter
			DLinkedNode<T> spotted = null;
			//@var Next, the thing used to test each node
			DLinkedNode<T> next = front;
			//Look through each node
			for(int x = 0; x<count; x++)
			{
				if(!next.getDataItem().equals(dataItem))
				{
					next = next.getNext();
				}
				else
				{
					spotted = next;
				}
				
			}
			//if can't find item, throw exception
			if(spotted == null)
				throw new InvalidElementException("Cannot find dataItem in node");
			/*
			 * otherwise, take the item away from the queue, and just add it back with new priority
			 * to avoid sorting the queue again (Although this does waste a little more memory)
			 */
			else
			{
				DLinkedNode<T> storage = spotted.getNext();
				DLinkedNode<T> storage2 = spotted.getPrev();
				// if count == 1, just switch priority
				if(count ==1)
				{
					front.setPriority(newPriority);
				}
				//if the node is at the beginning, do this to delete:
				else if(storage == null)
				{
					storage2.setNext(null);
					rear = rear.getPrev();
				}
				//if the node is at the end, delete it like this:
				else if (storage2 == null)
				{
					storage.setPrev(null);
					front = front.getNext();
				}
				//if it's between beginning and end, do this to delete it from existence
				else
				{
					storage.setPrev(storage2);
					storage2.setNext(storage);
					
				}
				/*
				 * After we delete the node, add the item back with the
				 * new priority so there is no need to resort the queue.
				 * This uses a little more memory but it'll still work fast for
				 * numbers not too large. We also -1 count as there is a decrease in nodes
				 */
				count--;
				add(dataItem, newPriority);
			}
	}
	//removemin method
	public T removeMin() throws EmptyPriorityQueueException
	{
		if(isEmpty())
		{
			throw new EmptyPriorityQueueException("Empty Queue");
		}
		//save front
		DLinkedNode<T> value = front;
		//make front the one after and disconnect the nodes
		front = front.getNext();
		front.setPrev(null);
		//decrease count
		count--;
		//return dataItem of the original front
		return value.getDataItem();
	}
	//isEmpty method
	public boolean isEmpty()
	//is count == 0?
	{return count == 0;}
	//@return size of queue
	public int size()
	{
		return count;
	}
	//tostring method
	public String toString()
	{
		String collection = "";
		DLinkedNode<T> test = front;
		for(int x = 1; x<count; x++)
		{
			collection += test.getDataItem()+ ", ";
			test = test.getNext();
		}
		collection += test.getDataItem();
		return collection;
	}
	public DLinkedNode<T> getRear()
	{
		return rear;
	}
}