public class Set <T>{
	private LinearNode<T> setStart;
	public Set()
	{
		setStart=null;
	}
	public void add(T element)
	{
		//if setStart is null then make it refer to a new LinearNode
		if(setStart == null)
		{
			setStart = new LinearNode<T>(element);
		}
		//if setStart isn't null, get to the very bottom of the set and add the element there
		else
		{
		LinearNode<T> current = setStart;
		while(current.getNext()!=null)
		{
			current=current.getNext();
		}
		current.setNext(new LinearNode<T>(element));
		}
	}
	
	public int getLength()
	{
		//using a while loop to count the numbers and then return that number
		int count =0;
		LinearNode<T> test = setStart;
		while(test !=null)
		{
			test=test.getNext();
			count++;
		}
		return count;
	}
	//Uses a loop to get to the specific node and return it
	public T getElement(int i) {

		LinearNode<T> curr = setStart;

		//Loops through the linked list until search = i

		//Once search attains 1 before i, element will be set to the correct node

		for(int j=0;j<i;j++){

		curr = curr.getNext();

		}

		T element = curr.getElement();

		return element;
	}
	//return true or false depending on if the nodes contain this element
	public boolean contains(T element)
	{
		LinearNode<T> test = setStart;
		while(test !=null)
		{
			if(test.getElement() == element)
				return true;
			else
				test=test.getNext();
		}
		return false;
	}
	//returns String version of the Set
	public String toString()
	{
		
		LinearNode<T> test=setStart;
		String statement = "";
		//while the node isn't null, add the element of the node into the String
		while(test!=null)
		{
			statement += test.getElement()+" ";
			test=test.getNext();
			
			
		}
		//return string
		return statement;
	}
	
}
