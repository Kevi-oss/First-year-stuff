
import java.util.Arrays;

public class Counter {
	PowerSet<Card> cardps;
	Card starter;
	public Counter(Card[] hand, Card starter)
	{
		this.starter = starter;
		cardps = new PowerSet<Card>(hand);
	}
	public int countPoints()
	{
		int count =0;
		
		boolean checkLenFive = false;
		boolean checkLenFour = false;
		
		//The loop starts at index 5 as the number of cards are fixed, and index 0-5 are either one cards or no cards at all
		for(int x=cardps.getLength()-1;x >= 0;x--)
		{
			//System.out.println(cardps.getSet(x));
			Set<Card> card = cardps.getSet(x);
			//test for pairs
			if(card.getLength() == 2 && 
			   card.getElement(0).getRunRank() == card.getElement(1).getRunRank()) {
				count+=2;
			}

			// fifteen check
			int rankSum = 0;
			for(int i=0;i<card.getLength();i++) {
				rankSum += card.getElement(i).getFifteenRank();
			}
			
			if(rankSum == 15) {
				count += 2;
			}

			// calculate the maxLength for run check
			if(isRun(card)) {
				//Create a array to contain all the ranks
				int[] cards = new int[card.getLength()];
				for(int y= 0; y<card.getLength();y++)
				{
					cards[y]= card.getElement(y).getRunRank();
					
				}
				//Sort the array

				Arrays.sort(cards);
				
				boolean runCheck = true;
				//check for runs, if it isn't a full run then don't do anything
				for(int y = 0; y<cards.length - 1;y++)
				{
					if(cards[y]+1 != cards[y+1])
					{
						runCheck = false;
					}
				}
				//if this set is fully a run, then add it to count. Record also the longest run to prevent smaller ones
				if(runCheck) {
					if(card.getLength() == 5) {
						count += 5;
						checkLenFive = true;
					}
					else if(card.getLength() == 4 && !checkLenFive) {
						count += 4;
						checkLenFour = true;
					}
					else if(card.getLength() == 3 && !checkLenFive && !checkLenFour) {
						count += 3;
					}
				}
					
			}

			
			// flush check
			boolean check = true;
			//Check whether all the suits are the same
			for (int i = 1; i < card.getLength(); i++) {	
				if (!card.getElement(0).getSuit().equals(card.getElement(i).getSuit())) {
					check = false;
				}
			}
			//if everything checks out, determine whether it is a 5 or 4 flush using starter
			if(check) {
				if(card.getLength() == 4 && !card.contains(starter)) {
					count += 4;
				}
				else if(card.getLength() == 5) {
					count += 1;
				}
			}

			
			// knobs check
			if (card.getLength() == 1 && 
				!card.contains(starter) && 
				card.getElement(0).getLabel().equals("J") && 
				card.getElement(0).getSuit().equals(starter.getSuit())) {
				count += 1;
			}

		}
				

		return count;
	}

	private boolean isRun (Set<Card> set) {
		// In this method, we are going through the given set to check if it constitutes a run of 3 or more
		// consecutive cards. To do this, we are going to create an array of 13 cells to represent the
		// range of card ranks from 1 to 13. We go through each card and increment the cell corresponding to
		// each card's rank. For example, an Ace (rank 1) would cause the first (index 0) cell to increment.
		// An 8 would cause the 8th (index 7) cell to increment. When this loop is done, the array will
		// contain 5 or less cells with values of 1 or more to represent the number of cards with each rank.
		// Then we can use this array to search for 3 or more consecutive non-zero values to represent a run.
		
		int n = set.getLength();
		
		if (n <= 2) return false; // Run must be at least 3 in length.
		
		int[] rankArr = new int[13];
		for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.
		
		for (int i = 0; i < n; i++) {
			rankArr[set.getElement(i).getRunRank()-1] += 1;
		}

		// Now search in the array for a sequence of n consecutive 1's.
		int streak = 0;
		int maxStreak = 0;
		for (int i = 0; i < 13; i++) {
			if (rankArr[i] == 1) {
				streak++;
				if (streak > maxStreak) maxStreak = streak;
			} else {
				streak = 0;
			}
		}
		if (maxStreak == n) { // Check if this is the maximum streak.
			return true;
		} else {
			return false;
		}

	}
}


