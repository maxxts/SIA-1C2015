import gps.GPSEngine;
import gps.SearchStrategy;
import gps.api.GPSProblem;
import gps.api.impl.GPS0hN0Problem;
import gps.impl.GPS0hN0Engine;


public class Game0hN0Solver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length != 1) {
			System.out.println("Invalid arguments.");
			return;
		}
		
		
		GPSProblem problem = new GPS0hN0Problem();
		GPSEngine solver = new GPS0hN0Engine();
		String searchStrategy = args[0];
		
		
		if (searchStrategy.equalsIgnoreCase(SearchStrategy.Iterative.toString()))
		{
			System.out.println("Initializing " + SearchStrategy.Iterative.toString() + " search...");
			solver.engine(problem, SearchStrategy.Iterative);
		}
	}

}
