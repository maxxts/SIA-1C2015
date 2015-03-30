package gps.api.impl;

import java.util.LinkedList;
import java.util.List;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

public abstract class GPS0hN0Problem implements GPSProblem{

	@Override
	public GPSState getInitState() {
		return new GPS0hN0State();
	}

	@Override
	public boolean isGoal(GPSState state) {
		
		GPS0hN0State ohno_state = ((GPS0hN0State)state);
		
		List<CellWrapper> cellsToCheck = ohno_state.getCellsToCheck();
		
		for(CellWrapper cell : cellsToCheck){
			if(!cell.getCell().isCompleted()){
				return false;
			}
			
		}
		
		
		return true;
	}

	@Override
	public List<GPSRule> getRules() {
		
		List<GPSRule> rules = new LinkedList<GPSRule>();
		int i, j;
		for (i = 0; i < GPS0hN0State.BOARD_SIZE; i++) {
			for (j = 0; j < GPS0hN0State.BOARD_SIZE; j++) {
				rules.add(getRule(i,j));
			}
		}

		return rules;
	}

	@Override
	public Integer getHValue(GPSState state) {
		return getHValueImpl(state);
	}
	
	public abstract GPSRule getRule(int i, int j);
	
	public abstract Integer getHValueImpl(GPSState state);
	

}
