public class PowerSet<T> {
	private Set<T>[] set;
	//Constructs the set instance varaible
	@SuppressWarnings("unchecked")
	public PowerSet(T[] elements)
	{
		//Finding the size of the powerset
		int num=1;
		for(int x= 0; x<elements.length;x++)
		{
			num*=2;
		}
		//Create an array for set to be the size of 2^elements
		set = new Set[num];
		//for each number inside the array we create a set, and then add in all the elements as nodes based on their corresponding binary codes
		for(int x =0; x<num;x++)
		{
			set[x]= new Set<T>();
			for(int y =0; y<Integer.toBinaryString(x).length();y++)
			{
				//Checks binary code
				if(Integer.toBinaryString(x).substring(y,y+1).equals("1"))
				{
					//add element into the set based on whether the part of the binary code we are checking equals to 1
					set[x].add(elements[elements.length-Integer.toBinaryString(x).length()+y]);
				}
			}
		}
		
		
		
		
	}
	//returns length of powerset
	public int getLength()
	{
		return set.length;
	}
	//returns the set inside the powerset
	public Set<T> getSet(int i)
	{
		return set[i];
	}
	

}
