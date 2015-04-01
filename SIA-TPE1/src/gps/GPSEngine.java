package gps;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;
import gps.api.impl.GPS0hN0Problem;
import gps.api.impl.GPS0hN0State;
import gps.exception.NotAppliableException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class GPSEngine {

	private List<GPSNode> open = new LinkedList<GPSNode>();
	
	private List<GPSNode> closed = new ArrayList<GPSNode>();

	private GPSProblem problem;

	// Use this variable in the addNode implementation
	private SearchStrategy strategy;
	
	private int generatedCOunter = 0;

	public void engine(GPSProblem myProblem, SearchStrategy myStrategy) {

		problem = myProblem;
		setStrategy(myStrategy);

		GPSNode rootNode = new GPSNode(problem.getInitState(), 0);
		generatedCOunter++;
		
		long startTime = System.currentTimeMillis();
		boolean finished = false;
		boolean failed = false;
		long explosionCounter = 0;
		
		((GPS0hN0Problem) problem).prepareBoardForSearch((GPS0hN0State) rootNode.getState());
		
		open.add(rootNode);
		while (!failed && !finished) {
			if (open.size() <= 0) {
				failed = true;
			} else {
				GPSNode currentNode = open.get(0);
				closed.add(currentNode);
				open.remove(0);
				if (problem.isGoal(currentNode.getState())) {
					long endTime = System.currentTimeMillis();
					finished = true;
					System.out.println("Solution board");
					System.out.println(currentNode.getSolution());
					System.out.println("Depth: " + currentNode.getCost());
					System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
					System.out.println("Generated nodes: " + generatedCOunter);
					System.out.println("Expanded nodes: " + explosionCounter);
					System.out.println("Border nodes: " + open.size());
				} else {
					explosionCounter++;
					explode(currentNode);
				}
			}
		}

		if (finished) {
			System.out.println("OK! solution found!");
		} else if (failed) {
			System.err.println("FAILED! solution not found!");
		}
	}

	private  boolean explode(GPSNode node) {
		if(problem.getRules() == null){
			System.err.println("No rules!");
			return false;
		}
		
		for (GPSRule rule : problem.getRules()) {
			GPSState newState = null;
			try {
				newState = rule.evalRule(node.getState());
			} catch (NotAppliableException e) {
				// Do nothing
			}
			if (newState != null
					&& !checkBranch(node, newState)
					&& !checkOpenAndClosed(node.getCost() + rule.getCost(),
							newState)) {
				GPSNode newNode = new GPSNode(newState, node.getCost()
						+ rule.getCost());
				newNode.setParent(node);
				addNode(newNode);
				generatedCOunter++;
			}
		}
		return true;
	}

	private  boolean checkOpenAndClosed(Integer cost, GPSState state) {
		for (GPSNode openNode : open) {
			if (openNode.getState().compare(state) && openNode.getCost() <= cost) {
				return true;
			}
		}
		for (GPSNode closedNode : closed) {
			if (closedNode.getState().compare(state)
					&& closedNode.getCost() <= cost) {
				return true;
			}
		}
		return false;
	}

	private  boolean checkBranch(GPSNode parent, GPSState state) {
		if (parent == null) {
			return false;
		}
		return checkBranch(parent.getParent(), state)
				|| state.compare(parent.getState());
	}

	public SearchStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(SearchStrategy strategy) {
		this.strategy = strategy;
	}
	
	public List<GPSNode> getOpen() {
		return open;
	}
	
	public GPSProblem getProblem(){
		return problem;
	}


	public abstract  void addNode(GPSNode node);
	
}
