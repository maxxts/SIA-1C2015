import gps.GPSEngine;
import gps.SearchStrategy;
import gps.api.GPSProblem;
import gps.api.impl.GPS0hN0Problem_allBlue;
import gps.impl.GPS0hN0Engine;


public class Game0hN0Solver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length != 1) {
			System.out.println("Invalid arguments.");
			return;
		}		
		
		GPSProblem problem = new GPS0hN0Problem_allBlue();
		GPSEngine solver = new GPS0hN0Engine();
		String searchStrategy = args[0];
		
		
		if (searchStrategy.equalsIgnoreCase(SearchStrategy.Iterative.toString()))
		{
			System.out.println("Initializing " + SearchStrategy.Iterative.toString() + " search...");
			solver.engine(problem, SearchStrategy.Iterative);
		}
		
		if (searchStrategy.equalsIgnoreCase(SearchStrategy.DFS.toString()))
		{
			System.out.println("Initializing " + SearchStrategy.DFS.toString() + " search...");
			solver.engine(problem, SearchStrategy.DFS);
		}
		
		if (searchStrategy.equalsIgnoreCase(SearchStrategy.BFS.toString()))
		{
			System.out.println("Initializing " + SearchStrategy.BFS.toString() + " search...");
			solver.engine(problem, SearchStrategy.BFS);
		}
	}

}
