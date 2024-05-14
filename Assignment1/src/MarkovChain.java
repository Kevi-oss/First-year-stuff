public class MarkovChain {
	/*
	 * private values
	 */
	private Vector stateVector;
	private Matrix transitionMatrix;
	/*
	 * Initialises everything
	 */
	public MarkovChain (Vector sVector, Matrix tMatrix)
	{
		stateVector = sVector;
		transitionMatrix = tMatrix;
	}
	/*
	 * Check if the instance variables are valid for a Markov chain problem and return a
Boolean to indicate its validity
	 */
	public boolean isValid()
	{
		//get the sum of all values in vector
		
		//test the conditions
		if(transitionMatrix.getNumCols()==transitionMatrix.getNumRows()&&
		   transitionMatrix.getNumCols()==stateVector.getNumCols())
		{
			//check for rows in matrix
			for(int x = 0; x<transitionMatrix.getNumRows();x++)
			{
				double sum = 0;
				for(int y = 0; y<transitionMatrix.getNumCols(); y++)
				{
					sum+=transitionMatrix.getElement(x,y);
				}
				if(sum>1.01 || sum<.99)
					return false;
			}
			
			//checks for vector
			double sum = 0;
			for(int x = 0; x<stateVector.getNumCols(); x++)
			{
				sum+=stateVector.getElement(x);
			}
			if(sum>1.01 || sum<.99)
				return false;
			
			//return true since now we know all the condition meets
			return true;
		}
		//if the first condition doesn't apply, it's already false
		return false;
	}
	public Matrix computeProbabilityMatrix (int numSteps)
	{
		//Is the matrix valid?
		if(!isValid())
		{
			return null;
		}
		//If the matrix is valid then we can begin the code
		Matrix duplicate = transitionMatrix;
		//multipies matrix by itself for numsteps-1 times
		for(int x = 1; x<numSteps; x++)
		{
			duplicate = duplicate.multiply(transitionMatrix);
		}
		//final multiplication (vector by matrix)
		return stateVector.multiply(duplicate);
		
	}

}
