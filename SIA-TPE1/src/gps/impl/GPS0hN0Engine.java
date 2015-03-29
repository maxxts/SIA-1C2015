package gps.impl;

import gps.GPSEngine;
import gps.GPSNode;
import gps.SearchStrategy;

public class GPS0hN0Engine extends GPSEngine {

	@Override
	public void addNode(GPSNode node) {
		if (this.getStrategy() == SearchStrategy.Iterative){
			System.out.println("Iterative search...");
		}
		
	}

}
