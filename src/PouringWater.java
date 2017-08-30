import java.util.Scanner;

public class PouringWater {

	private static Scanner in;
	
	private static void performOneTest()
	{
		int vesselMaxCapacity1, vesselMaxCapacity2, actualVesselContent1, actualVesselContent2;
		
		
		System.out.println("TODO A LOT");
	}
	
	public static void main(String[] args) {
		// TODO BFS Breadth first search algorithm for searching minimal number of steps to acquire exact amount of water
		// using two vessels
		in = new Scanner(System.in);
		int numberOfTests;
		numberOfTests = in.nextInt();
		while (numberOfTests-- > 0)
			performOneTest();
	}

}
