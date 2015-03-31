package gps.api.impl;

import gps.api.GPSRule;
import gps.api.GPSState;

public class GPS0hN0Problem_allRed extends GPS0hN0Problem{

	@Override
	public GPSRule getRule(int i, int j) {
		return new GPS0hN0Rule_BLUE(i, j);
	}

	@Override
	public Integer getHValueImpl(GPSState state) {
		
		int unComplete = 0;
		
		for(CellWrapper cell : ((GPS0hN0State)state).getCellsToCheck()){
			if(!cell.getCell().isCompleted())
				unComplete++;
		}
		
		return unComplete;
		
	}

	@Override
	public Color getColorToFill() {
		return Color.red;
	}



}
