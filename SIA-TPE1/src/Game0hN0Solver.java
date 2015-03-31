import gps.GPSEngine;
import gps.SearchStrategy;
import gps.api.GPSProblem;
import gps.api.impl.GPS0hN0Problem_allBlue;
import gps.api.impl.GPS0hN0Problem_allRed;
import gps.impl.GPS0hN0Engine;


public class Game0hN0Solver {

	public static void main(String[] args) {
		
		if (args.length != 2) {
			System.out.println("Invalid arguments.");
			return;
		}		
		
		GPSProblem problem = null;
		GPSEngine solver = new GPS0hN0Engine();
		String problemApproach = args[0];
		String searchStrategy = args[1];
		
		if(problemApproach.equalsIgnoreCase("all_blue")){
			problem = new GPS0hN0Problem_allBlue();
		} else if(problemApproach.equalsIgnoreCase("all_red")){
			problem = new GPS0hN0Problem_allRed();
		} else {
			System.out.println("Invalid problem approach. Options: All_Blue or All_Red");
			return;
		}
		
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
		
		if (searchStrategy.equalsIgnoreCase(SearchStrategy.AStar.toString()))
		{
			System.out.println("Initializing " + SearchStrategy.AStar.toString() + " search...");
			solver.engine(problem, SearchStrategy.AStar);
		}
		
		if (searchStrategy.equalsIgnoreCase(SearchStrategy.Greedy.toString()))
		{
			System.out.println("Initializing " + SearchStrategy.AStar.toString() + " search...");
			solver.engine(problem, SearchStrategy.Greedy);
		}
	}

}
