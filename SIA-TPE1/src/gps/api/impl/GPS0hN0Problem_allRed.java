package gps.api.impl;

import java.util.List;

import gps.api.GPSRule;
import gps.api.GPSState;

public class GPS0hN0Problem_allRed extends GPS0hN0Problem{

	@Override
	public List<GPSRule> getRulesImpl() {
		return getRulesByColor(Color.blue);
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


}
