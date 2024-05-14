
public class Matrix {
	
	private int numRows;
	private int numCols;
	private double[][] data;
	/* @param r the rows of the matrix
	 * @param c the colomns of the matrix
	 * */
	public Matrix (int r, int c)
	{
		numRows = r;
		numCols = c;
		data = new double[numRows][numCols];
	}
	/* @param r the rows of the matrix
	 * @param c the colomns of the matrix
	 * @param linArr, the elements inside the matrix
	 * */
	public Matrix (int r, int c, double[] linArr)
	{
		numRows = r;
		numCols = c;
		data = new double[numRows][numCols];
		/*I don't want to make a for loops as it would be too complicated, making a while
		 * loop would help me arrange all the things from the array to the 2D array and helps prevent
		 * errors that might occur if linArr has too little(or too much) value for the 2D array 
		 * */
		int rows = 0;
		int cols=0;
		int num=0;
		while(num<linArr.length)
		{
			//Check if there is enough colomns
			if(rows<data.length)
			{
				//Check if columes are overbounds
				if(cols<data[0].length)
				{
					data[rows][cols] = linArr[num];
					cols+=1;
					num+=1;
				}
				else
				{
					cols=0;
					rows+=1;
				}
			}
			//if there is too many values for the 2D array, then when it reaches its limits, the loop stops itself, I wanted to use end but I'm not too familiar with it
			else
			{
				num=linArr.length;
			}

		}
	}
	/*
	 * @return number of rows in the matrix
	 * */
	public int getNumRows()
	{
		return numRows;
	}
	/*
	 * @return how many colomns in the matrix
	 */
	public int getNumCols()
	{
		return numCols;
	}
	/*
	 * @returns the 2D matrix 
	 */
	public double[][] getData()
	{
		return data;
	}
	/*
	 * @param r the desired row
	 * @param c the desired colomn
	 * @return the value from the desired row and colomn
	 */
	public double getElement(int r, int c)
	{
		return data[r][c];
	}
	/*
	 * @param r the desired row
	 * @param c the desired colomn
	 * @param value the value you want in 2D array
	 */
	public void setElement(int r, int c, double value)
	{
		data[r][c]=value;
	}
	/*
	 * Transpose the matrix and update the instance variables to store the transposed
matrix as the new instance of 'this' matrix
	 */
	public void transpose()
	{
		//Make a 2D array to record the transposed matrix
		double[][] matrix = new double[numCols][numRows];
		//change numCols and numRows to the transposed version
		numCols = matrix[0].length;
		numRows = matrix.length;
		//Recording the data using loop
		for(int x = 0; x<numRows; x++)
		{
			for(int y =0; y<numCols; y++)
			{
				matrix[x][y]=data[y][x];
			}
		}
		//change data into its transposed version
		data = matrix;
	}
	/*
	 * @param scaler, the scale you want to multiply the Matrix by 
	 * @return a modified Matrix whose values are multiplied by scaler
	 */
	public Matrix multiply(double scaler)
	{
		/*
		 * create Matrix that would contain the multiplied value
		 */
		Matrix upgraded = new Matrix(numRows, numCols);
		//for each element in data, we put it into the upgraded matrix multiplied by the scale using a loop
		for(int x= 0; x<numRows;x++)
		{
			for(int y = 0; y<numCols;y++)
			{
				upgraded.setElement(x,y,data[x][y]*scaler);
			}
		}
		
		return upgraded;
	}
	/*
	 * @param other, the other Matrix that would be compared
	 * @return matrix of this matrix and the other matrix multiplied
	 * multiply method required in the assignment (I don't want to have too much
	 * words for the desription)
	 */
	public Matrix multiply(Matrix other)
	{
		//@return null when numCols does not equal to the rows of other Matrix
		if(this.numCols!=other.getNumRows())
		{
			return null;
		}
		//otherwise
		Matrix combined = new Matrix(numRows,other.getNumCols());
		for(int x= 0; x<numRows;x++)
		{
			
			for(int y = 0; y<other.getNumCols();y++)
			{
				double value=0;
				for(int w = 0; w<other.getNumCols(); w++)
				{
					value += data[x][w]*other.getElement(w,y);
				}
				combined.setElement(x,y, value);
				
				
			}
		}
		return combined;
		
		
		
	}
	/*
	 * @return a String version of the object
	 */
	public String toString()
	{
		
		if(data.length==0)
			return "Empty matrix";
		//if length>1 then proceed with program
		String build = "";
		for(int x = 0; x<numRows; x++)
		{
			for(int y = 0; y<numCols; y++)
			{
					build+= String.format("%8.3f",data[x][y]);
					
			}
			build+="\n";
		}
		return build;
		
		
	}
	
	

}
