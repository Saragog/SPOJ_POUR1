import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.HashMap;

// BFS Breadth first search algorithm for searching minimal number of steps to acquire exact amount of water
// using two vessels

class PouringWater {

	private static Scanner in;
	private LinkedList<Pair> newPossibilities;
	private LinkedList<Pair> lastIterationPossibilities;
	private int targetContent;
	private int vesselMaxCapacity1;
	private int vesselMaxCapacity2;
	private HashMap<Pair, Boolean> historyContents = new HashMap<Pair, Boolean>();
	
	
	static class Pair
	{
		private int content1;
		private int content2;
		
		public Pair(int content1, int content2)
		{
			this.content1 = content1;
			this.content2 = content2;
		}
		
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj) return true;
			if (!(obj instanceof Pair)) return false;
			Pair pair2 = (Pair)obj;
			return ((content1 == pair2.getContent1()) && (content2 == pair2.getContent2()));
		}
		
		@Override
		public int hashCode()
		{
			return content1 * 40001 + content2; // hash value
		}
		
		public int getContent1()
		{
			return this.content1;
		}
		
		public int getContent2()
		{
			return this.content2;
		}
	}
	
	private boolean findNewContentPossibilities()
	{
		int content1, content2, difference;
		Pair actualPair;
		boolean newContentPossibilitiesFound = false;
		newPossibilities.clear();
		ListIterator<Pair> it = lastIterationPossibilities.listIterator();
		
		while (it.hasNext())
		{
			actualPair = it.next();
			content1 = actualPair.content1;
			content2 = actualPair.content2;
			
			if (content1 > 0) // spilling water from first vessel
			{
				newPossibilities.add(new Pair(0, content2));
				newContentPossibilitiesFound = true;
			}
			if (content2 > 0) // spilling water from second vessel
			{
				newPossibilities.add(new Pair(content1,0));
				newContentPossibilitiesFound = true;
			}
			if (content1 < vesselMaxCapacity1) // filling first vessel
			{
				newPossibilities.add(new Pair(vesselMaxCapacity1, content2));
				newContentPossibilitiesFound = true;
			}
			if (content2 < vesselMaxCapacity2) // filling second vessel
			{
				newPossibilities.add(new Pair(content1, vesselMaxCapacity2));
				newContentPossibilitiesFound = true;
			}
			if ((content1 > 0) && (content2 < vesselMaxCapacity2)) // pouring from vessel 1 to vessel 2
			{
				if (content1 > vesselMaxCapacity2 - content2) difference = vesselMaxCapacity2 - content2;
				else difference = content1;
				newPossibilities.add(new Pair(content1 - difference, content2 + difference));
				newContentPossibilitiesFound = true;
			}
			if ((content2 > 0) && (content1 < vesselMaxCapacity1)) // pouring from vessel 2 to vessel 1
			{
				if (content2 > vesselMaxCapacity1 - content1) difference = vesselMaxCapacity1 - content1;
				else difference = content2;
				newPossibilities.add(new Pair(content1 + difference, content2 - difference));
				newContentPossibilitiesFound = true;
			}
		}
		return newContentPossibilitiesFound;
	}
	
	private boolean checkNewContentPossibilities()
	{
		ListIterator<Pair> itNewPossibilities = newPossibilities.listIterator();
		Pair actualPossibility;
		int newPossibilityValue1, newPossibilityValue2;
		lastIterationPossibilities.clear();
		
		while (itNewPossibilities.hasNext())
		{
			actualPossibility = itNewPossibilities.next();
			newPossibilityValue1 = actualPossibility.getContent1();
			newPossibilityValue2 = actualPossibility.getContent2();
			if ((newPossibilityValue1 == targetContent) || (newPossibilityValue2 == targetContent)) return true;
			if (historyContents.get(new Pair(newPossibilityValue1, newPossibilityValue2)) == false)
			{
				historyContents.replace(new Pair(newPossibilityValue1, newPossibilityValue2), true);
				lastIterationPossibilities.add(new Pair(newPossibilityValue1, newPossibilityValue2));
			}
		}
		return false;
	}
	
	private void initializeMap(int capacity1, int capacity2)
	{
		historyContents.clear();
		historyContents.put(new Pair(0,0), true);
		for (int values1 = 0; values1 <= capacity1; values1++)
			for (int values2 = 0; values2 <= capacity2; values2++)
				historyContents.put(new Pair(values1, values2), false);
		historyContents.replace(new Pair(0,0), true);
		return;
	}
	
	private void performOneTest()
	{
		int	turnCounter = 0;
		vesselMaxCapacity1 = in.nextInt();
		vesselMaxCapacity2 = in.nextInt();
		targetContent = in.nextInt();
		
		newPossibilities = new LinkedList<Pair>();
		lastIterationPossibilities = new LinkedList<Pair>();
		lastIterationPossibilities.add(new Pair(0,0));
		initializeMap(vesselMaxCapacity1, vesselMaxCapacity2);

		// checking if targetContent is appropriate
		if ((targetContent < 0) || 
		   ((targetContent > vesselMaxCapacity1) && 
		   (targetContent > vesselMaxCapacity2))) turnCounter = -1;
		else if (targetContent == 0) turnCounter = 0;
		else
		{
			while (true)
			{
				turnCounter++;
				if ((!findNewContentPossibilities()))
				{
					turnCounter = -1;
					break;
				}
				else if (checkNewContentPossibilities())
					break;
			}
		}
		System.out.println(turnCounter);		
		return;
	}
	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		int numberOfTests;
		PouringWater pouringWater = new PouringWater();
		numberOfTests = in.nextInt();
		while (numberOfTests-- > 0)
			pouringWater.performOneTest();
	}
}