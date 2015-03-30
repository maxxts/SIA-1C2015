package gps.impl;

import gps.GPSEngine;
import gps.GPSNode;
import gps.SearchStrategy;
import gps.api.GPSState;

public class GPS0hN0Engine extends GPSEngine {

	@Override
	public void addNode(GPSNode node) {
		
		//System.out.println("Executing " + this.getStrategy().toString() + " addNode step...");
		
		// TODO: Ver de sacar el caso base
		
		if (this.getStrategy().equals(SearchStrategy.BFS))
		{
			this.getOpen().add(node);
		}
		
		if (this.getStrategy().equals(SearchStrategy.DFS))
		{
			int openSize = this.getOpen().size();
			if(openSize == 0){
				
				this.getOpen().add(node);
			
			}else{
				
				int i = 0;
				
				while ( i < openSize && this.getOpen().get(i).getParent().equals(node.getParent()))
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
			
			GPSNode grandParent = node.getParent().getParent();
			boolean parentIsOpen = false;
			boolean nodeIsOpen = false;
			boolean parentBrotherIsOpen = false;
			int lastParentBrotherIndex = 0;
			int i = 0;
						
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
				if(grandParent != null && grandParent.equals(aNode.getParent()))
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
		
		if(this.getStrategy().equals(SearchStrategy.Greedy))
		{
			
			if(this.getOpen().size() == 0){
				this.getOpen().add(node);
				return;
			}
			
			int i = 0;
			int hValue = this.getProblem().getHValue(node.getState());
			
			while ( i < this.getOpen().size() && this.getOpen().get(i).getParent().equals(node.getParent()))
			{
				
				int hValueBrother = this.getProblem().getHValue(this.getOpen().get(i).getState());
				
				if(hValue < hValueBrother){
					this.getOpen().add(i, node);
					return;
				}
				
				i++;
			}
			
			if(i == this.getOpen().size()){
				this.getOpen().add(node);
				return;
			}
			
			this.getOpen().add(i, node);
			
		}
		
		if(this.getStrategy().equals(SearchStrategy.AStar))
		{
			if(this.getOpen().size() == 0){
				this.getOpen().add(node);
				return;
			}
			
			int i = 0;
			int hValue = this.getProblem().getHValue(node.getState());
			int fValue = hValue + node.getCost();
			
			while ( i < this.getOpen().size() )
			{
				GPSNode	other = this.getOpen().get(i);
				
				int fValueOther = this.getProblem().getHValue(other.getState()) + other.getCost();
				
				if(fValue == fValueOther){
					if(hValue < this.getProblem().getHValue(other.getState())){
						this.getOpen().add(i, node);
						return;
					}
				} else {
					if(fValue < fValueOther){
						this.getOpen().add(i, node);
						return;
				}
				
				i++;
			}
			
			this.getOpen().add(node);
		}
		
		
				
	}
	
	
	}

}
