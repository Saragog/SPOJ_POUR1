import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class PouringWater {

	private static Scanner in;
	private LinkedList<Integer> contents1;
	private LinkedList<Integer> contents2;
	private LinkedList<Integer> newPossibilities1;
	private LinkedList<Integer> newPossibilities2;
	private LinkedList<Integer> lastIterationPossibilities1;
	private LinkedList<Integer> lastIterationPossibilities2;
	private int targetContent;
	private int vesselMaxCapacity1;
	private int vesselMaxCapacity2;
	
	private boolean findNewContentPossibilities()
	{
		newPossibilities1.clear();
		newPossibilities2.clear();
		ListIterator<Integer> it1 = lastIterationPossibilities1.listIterator();
		ListIterator<Integer> it2 = lastIterationPossibilities2.listIterator();
		
		while (it1.hasNext())
		{
			if (it1.next() > 0) // wylanie 1
			{
				newPossibilities1.add(0);
				newPossibilities2.add(it2.next());
			}
			if (it2.next() > 0) // wylanie 2
			{
				newPossibilities1.add(it1.next());
				newPossibilities2.add(0);
			}
			if (it1.next() < vesselMaxCapacity1) // zalanie 1 do konca
			{
				newPossibilities1.add(vesselMaxCapacity1);
				newPossibilities2.add(it2.next());
			}
			if (it2.next() < vesselMaxCapacity2) // zalanie 2 do konca
			{
				newPossibilities1.add(it1.next());
				newPossibilities2.add(vesselMaxCapacity2);
			}
		}
		
		return true;
	}
	
	private boolean analyzeNewContentPossibilities()
	{
		
		
		return true;
	}
	
	private boolean checkIfTargetContentReached ()
	{
		if (contents1.contains(targetContent) || contents2.contains(targetContent)) return true;
		else return false;
	}
	
	private void performOneTest()
	{
		int	turnCounter = 0;
		targetContent = in.nextInt();
		vesselMaxCapacity1 = in.nextInt();
		vesselMaxCapacity2 = in.nextInt();
		contents1 = new LinkedList<Integer>();
		contents1.add(0);
		contents2 = new LinkedList<Integer>();
		contents2.add(0);
		newPossibilities1 = new LinkedList<Integer>();
		newPossibilities2 = new LinkedList<Integer>();
		lastIterationPossibilities1 = new LinkedList<Integer>();
		lastIterationPossibilities1.add(0);
		lastIterationPossibilities2 = new LinkedList<Integer>();
		lastIterationPossibilities2.add(0);
		
		while (true)
		{	
			if (checkIfTargetContentReached())
			{
				System.out.println("Szukanie zakonczone po: " + turnCounter + " turach :)");
				break;
			}
			else if ((!findNewContentPossibilities()) || (!analyzeNewContentPossibilities()))
			{
				System.out.println("Nie udalo sie znalezc rozwiazania :(");
				break;
			}
			
			turnCounter++;
		}
		return;
	}
	
	public static void main(String[] args) {
		// TODO BFS Breadth first search algorithm for searching minimal number of steps to acquire exact amount of water
		// using two vessels
		in = new Scanner(System.in);
		int numberOfTests;
		PouringWater pouringWater = new PouringWater();
		numberOfTests = in.nextInt();
		while (numberOfTests-- > 0)
			pouringWater.performOneTest();
	}
}