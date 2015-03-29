package gps.impl;

import gps.GPSEngine;
import gps.GPSNode;
import gps.SearchStrategy;

public class GPS0hN0Engine extends GPSEngine {

	@Override
	public void addNode(GPSNode node) {
		
		System.out.println("Executing " + this.getStrategy().toString() + " addNode step...");
		
		if (this.getStrategy().equals(SearchStrategy.BFS))
		{
			this.getOpen().add(node);
		}
		
		if (this.getStrategy().equals(SearchStrategy.DFS))
		{
			
			if(this.getOpen().size() == 0){
				this.getOpen().add(node);
			}
			
			int i = 0;
			while (this.getOpen().get(i).getParent().equals(node.getParent()))
			{
				i++;
			}
			
			this.getOpen().add(i,node);
		}
		
		if(this.getStrategy().equals(SearchStrategy.Iterative))
		{
			
		}
				
	}

}
