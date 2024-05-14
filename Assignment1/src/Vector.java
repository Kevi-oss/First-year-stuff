public class Vector extends Matrix{
	/*
	 * @param c the length of the Vector
	 */
	public Vector(int c)
	{
		super(1,c);
	}
	/*
	 * @param c the length of the Vector
	 * @param linArr Array of numbers that would be inside the vector
	 */
	public Vector(int c, double[] linArr)
	{
		super(1,c, linArr);
	}
	/*
	 * @param c, the desired place to receive a value
	 * @return the value at place c
	 */
	public double getElement(int c)
	{
		return super.getElement(0,c);
	}
	
}
