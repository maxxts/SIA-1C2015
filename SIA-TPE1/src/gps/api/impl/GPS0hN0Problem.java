package gps.api.impl;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GPS0hN0Problem implements GPSProblem {

	@Override
	public GPSState getInitState() {
		

		GPS0hN0Cell[][] init_board = new GPS0hN0Cell[7][7];
		List<CellWrapper> cellsToCheck = new ArrayList<CellWrapper>();
		
		init_board[0][1] = new GPS0hN0Cell(3,Color.blue,true);
		cellsToCheck.add(new CellWrapper(0,1,init_board[0][1]));
		
		init_board[0][6] = new GPS0hN0Cell(4,Color.blue,true);
		cellsToCheck.add(new CellWrapper(0,6,init_board[0][6]));
		
		init_board[1][0] = new GPS0hN0Cell(5,Color.blue,true);
		cellsToCheck.add(new CellWrapper(1,0,init_board[1][0]));
		
		init_board[1][2] = new GPS0hN0Cell(6, Color.blue, true);
		cellsToCheck.add(new CellWrapper(1,2,init_board[1][2]));
		
		init_board[1][3] = new GPS0hN0Cell(5,Color.blue,true);
		cellsToCheck.add(new CellWrapper(1,3,init_board[1][3]));
		
		init_board[2][3] = new GPS0hN0Cell(2, Color.blue, true);
		cellsToCheck.add(new CellWrapper(2,3,init_board[2][3]));
		
		init_board[4][0] = new GPS0hN0Cell(7, Color.blue, true);
		cellsToCheck.add(new CellWrapper(4,0,init_board[4][0]));
		
		init_board[4][1] = new GPS0hN0Cell(7, Color.blue, true);
		cellsToCheck.add(new CellWrapper(4,1,init_board[4][1]));
		
		init_board[4][3] = new GPS0hN0Cell(5, Color.blue, true);
		cellsToCheck.add(new CellWrapper(4,3,init_board[4][3]));
		
		init_board[4][4] = new GPS0hN0Cell(7, Color.blue, true);
		cellsToCheck.add(new CellWrapper(4,4,init_board[4][4]));
		
		init_board[4][5] = new GPS0hN0Cell(6, Color.blue, true);
		cellsToCheck.add(new CellWrapper(4,5,init_board[4][5]));
		
		init_board[5][0] = new GPS0hN0Cell(3, Color.blue, true);
		cellsToCheck.add(new CellWrapper(5,0,init_board[5][0]));
		
		init_board[6][0] = new GPS0hN0Cell(2, Color.blue, true);
		cellsToCheck.add(new CellWrapper(6,0,init_board[6][0]));
		
		init_board[6][5] = new GPS0hN0Cell(4, Color.blue, true);
		cellsToCheck.add(new CellWrapper(6,5,init_board[6][5]));
		
		init_board[6][6] = new GPS0hN0Cell(5, Color.blue, true);
		cellsToCheck.add(new CellWrapper(6,6,init_board[6][6]));
		
		init_board[3][6] = new GPS0hN0Cell(0, Color.red, true);
		
		return new GPS0hN0State(init_board,cellsToCheck);
	}


	@Override
	public List<GPSRule> getRules() {
		
		List<GPSRule> rules = new LinkedList<GPSRule>(); 
		int i,j;
		for (i = 0; i<7; i++){
			for (j = 0; j < 7; j++){
				rules.add( ((GPSRule) new GPS0hN0Rule(i,j,Color.red)));
			}
		}
		
		return rules;
	}

	@Override
	public Integer getHValue(GPSState state) {
		// TODO Max: "Creo que esto lo podriamos dejar... o lo queres hacer?"
		return 23;
	}


	@Override
	public boolean isGoal(GPSState state) {
		
		// TODO Auto-generated method stub
		
		GPS0hN0State ohno_state = ((GPS0hN0State)state);
		GPS0hN0Cell[][] ohno_board = ohno_state.getBoard();
		
		int rows = ohno_board.length;
		int cols = ohno_board[0].length;
		
		List<CellWrapper> cellsToCheck = ohno_state.getCellsToCheck();
		
		for(CellWrapper cell : cellsToCheck){
			
			int sees = 0;
			int row = cell.getI();
			
			//Check how many cells it sees in the same column
			for(int j = 0 ; j < cols ; j++ ){
				//if(board[row][j])
			}
			
		}
		
		
		return false;
	}

}
