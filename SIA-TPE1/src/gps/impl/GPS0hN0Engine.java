package gps.impl;

import gps.GPSEngine;
import gps.GPSNode;
import gps.SearchStrategy;

public class GPS0hN0Engine extends GPSEngine {

	@Override
	public void addNode(GPSNode node) {
		
		System.out.println("Executing " + this.getStrategy().toString() + " addNode step...");
		
		// TODO: Ver de sacar el caso base
		
		if (this.getStrategy().equals(SearchStrategy.BFS))
		{
			this.getOpen().add(node);
		}
		
		if (this.getStrategy().equals(SearchStrategy.DFS))
		{
			
			if(this.getOpen().size() == 0){
				
				this.getOpen().add(node);
			
			}else{
				
				int i = 0;
				while (this.getOpen().get(i).getParent().equals(node.getParent()))
				{
					i++;
				}
				
				this.getOpen().add(i,node);
			}
			
			
		}
		
		if(this.getStrategy().equals(SearchStrategy.Iterative))
		{
			if (node.getParent().equals(null) && this.getOpen().size() == 0)
			{
				this.getOpen().add(node);
				return;
			}
			
			// Search for parent, self or parent's brother in open list.
			
			boolean parentIsOpen = false;
			boolean nodeIsOpen = false;
			boolean parentBrotherIsOpen = false;
			int i = 0;
			int lastParentBrotherIndex = 0;
			
			for (GPSNode aNode : this.getOpen())
			{
				if (node.getParent().equals(aNode))
				{
					parentIsOpen = true;
				}
				if(node.equals(aNode))
				{
					nodeIsOpen = true;
				}
				if(node.getParent().getParent().equals(aNode.getParent()))
				{
					parentBrotherIsOpen = true;
					lastParentBrotherIndex = i;
					
				}
				i++;
			}
			
			// If my parent is not open, I have to re-add it to the list for next level.
			if (!parentIsOpen)
			{
				// If my parent's brother is open, I add my parent after it
				if (parentBrotherIsOpen)
				{
					this.getOpen().add(lastParentBrotherIndex + 1, node.getParent());
				}
				else
				{
					this.getOpen().add(node.getParent());
				}
				
			}
			
			// If self node is not open, I add at the end of the list
			if (!nodeIsOpen) 
			{
				this.getOpen().add(node);
			}			
			
		}
				
	}

}
