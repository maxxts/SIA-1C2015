import gps.GPSEngine;
import gps.SearchStrategy;
import gps.api.GPSProblem;
import gps.api.impl.GPS0hN0Problem_allBlue;
import gps.api.impl.GPS0hN0Problem_allRed;
import gps.api.impl.GPS0hN0State;
import gps.impl.GPS0hN0Engine;


public class Game0hN0Solver {

	public static void main(String[] args) {
		
		int gameSize = 0;
		if (args.length == 3)
		{
			try {
				gameSize = Integer.parseInt(args[2]);
				if (gameSize == 5 || gameSize == 6 || gameSize == 7)
				{
					GPS0hN0State.changeSize(gameSize);
					System.out.println("Size set to: " + gameSize);
				}
				else
				{
					System.out.println("Invalid arguments! Size must be '5', '6' or '7'");
				}
			}catch(NumberFormatException e){
				System.out.println("Invalid arguments! Size must be a number.");
				return;
			}			
		}
		else if (args.length != 2) 
		{
			System.out.println("Invalid arguments!");
			System.out.println("You need (all_blue | all_red) (bfs | dfs | iterative | greedy | astar) [5 | 6 | 7]");
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
			System.out.println("Invalid arguments! Problem approach must be 'all_blue' or 'all_red'");
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
			System.out.println("Initializing " + SearchStrategy.Greedy.toString() + " search...");
			solver.engine(problem, SearchStrategy.Greedy);
		}
	}

}
