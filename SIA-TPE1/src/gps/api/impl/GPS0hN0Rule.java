package gps.api.impl;

import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

public abstract class GPS0hN0Rule implements GPSRule {

	private int i;
	private int j;
	private Color color;

	public GPS0hN0Rule(int i, int j, Color color) {
		this.i = i;
		this.j = j;
		this.color = color;
	}

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Se coloca " + this.color.name() + " en posicion (" + this.i
				+ ", " + this.j + ")";
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {

		GPS0hN0State cloned_state = ((GPS0hN0State) state).cloneState();
		
		GPS0hN0Cell cell = cloned_state.getBoard()[i][j];
		
		if(cell.isFixed() || cell.getColor().equals(color)){
			throw new NotAppliableException();
		}
		
		cloned_state.getBoard()[i][j] = new GPS0hN0Cell(color);
		
		int complete = 0;
		
		for(int index = 0 ; index < cloned_state.getCellsToCheck().size() ; index++){
			
			CellWrapper current = cloned_state.getCellsToCheck().get(index);
			
			int canSee = visibleCells(current,cloned_state.getBoard());
			
			if(canSee == current.getCell().getValue()){
				current.getCell().complete();
				complete++;
			} else {
				
				GPS0hN0State parent = (GPS0hN0State) state;
				
				if( !isAppliable(canSee,current.getCell().getValue())){
					throw new NotAppliableException();
				}
				
				if(parent.getCellsToCheck().get(index).getCell().isCompleted()){
					throw new NotAppliableException();
				}
				
				if(complete < parent.getCompleteCells()){
					throw new NotAppliableException();
				}
			}
			
		}
		
		return cloned_state;

	}

	public abstract boolean isAppliable(int visibleCells, int cellValue);

	// It checks how many cells are visible for the one being analyzed
	protected int visibleCells(CellWrapper cell, GPS0hN0Cell[][] board) {

		int visibleCells = 0;
		int row = cell.getI();
		int col = cell.getJ();

		boolean redFound = false;

		// Check from cell position to left (j--)
		if (col != 0) {
			for (int j = col - 1; j >= 0 && !redFound; j--) {

				if (board[row][j].getColor().equals(Color.red)) {
					redFound = true;
				} else if (board[row][j].getColor().equals(Color.blue)) {
					visibleCells++;
				}

			}

			redFound = false;
		}

		// Check from cell position to right (j++)
		if (col + 1 != GPS0hN0State.BOARD_SIZE) {
			for (int j = col + 1; j < GPS0hN0State.BOARD_SIZE && !redFound; j++) {

				if (board[row][j].getColor().equals(Color.red)) {
					redFound = true;
				} else if (board[row][j].getColor().equals(Color.blue)) {
					visibleCells++;
				}

			}

			redFound = false;
		}

		// Check from cell position up (i--)
		if (row != 0) {
			for (int i = row - 1; i >= 0 && !redFound; i--) {
				if (board[i][col].getColor().equals(Color.red)) {
					redFound = true;
				} else if (board[i][col].getColor().equals(Color.blue)) {
					visibleCells++;
				}
			}

			redFound = false;
		}

		// Check from cell position down (i++)
		if (row + 1 != GPS0hN0State.BOARD_SIZE) {
			for (int i = row + 1; i < GPS0hN0State.BOARD_SIZE && !redFound; i++) {
				if (board[i][col].getColor().equals(Color.red)) {
					redFound = true;
				} else if (board[i][col].getColor().equals(Color.blue)) {
					visibleCells++;
				}
			}

			redFound = false;
		}

		return visibleCells;
	}
}
