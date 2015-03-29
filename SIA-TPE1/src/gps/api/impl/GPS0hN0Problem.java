package gps.api.impl;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GPS0hN0Problem implements GPSProblem {

	public static int BOARD_SIZE = 7;
	
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
		for (i = 0; i < BOARD_SIZE ; i++){
			for (j = 0; j < BOARD_SIZE; j++){
				rules.add( ((GPSRule) new GPS0hN0Rule(i,j,Color.red)));
			}
		}
		
		return rules;
	}

	
	//REVISAR!!!!
	
	@Override
	public Integer getHValue(GPSState state) {
		
		GPS0hN0State ohno_state = (GPS0hN0State) state;
		
		//Implementacion de heuristica admisible
		
		int unComplete = 0;
		
		for(CellWrapper cell: ohno_state.getCellsToCheck()){
			if(!cell.getCell().isCompleted()){
				unComplete++;
			}
		}
		
		int intersections = sumIntersections(ohno_state);
		
		if(unComplete == 0){
			return 0;
		}
		
		return unComplete - 1 - intersections;
	}


	@Override
	public boolean isGoal(GPSState state) {
		
		GPS0hN0State ohno_state = ((GPS0hN0State)state);
		GPS0hN0Cell[][] ohno_board = ohno_state.getBoard();
		
		List<CellWrapper> cellsToCheck = ohno_state.getCellsToCheck();
		
		for(CellWrapper cell : cellsToCheck){
			
			/*int sees = visibleCells(cell,ohno_board);
			
			if(sees != cell.getCell().getValue()){
				return false;
			}*/
			
			if(!cell.getCell().isCompleted()){
				return false;
			}
			
		}
		
		
		return true;
	}
	
	//It checks how many cells are visible for the one to analyze
	private int visibleCells(CellWrapper cell, GPS0hN0Cell[][] board){
		
		int visibleCells = 0;
		int row = cell.getI();
		int col = cell.getJ();
		
		//Check how many cells it sees in the same row
		for(int j = 0 ; j < BOARD_SIZE ; j++ ){
			if( j != col && board[row][j].getColor() == Color.blue){
				visibleCells++;
			}
		}
		
		//Check how many cells it sees in the same column
		for(int i = 0; i < BOARD_SIZE ; i++){
			if( i != row && board[i][col].getColor() == Color.blue){
				visibleCells++;
			}
		}
		
		return visibleCells;
	}
	
	private int sumIntersections(GPS0hN0State state){
		
		int intersections = 0;
		
		boolean flag = false;
		
		int col,row;
		
		//First step: check for interceptions in same columns
		for(col = 0 ; col < BOARD_SIZE ; col ++){
			for(row = 0 ; row < BOARD_SIZE ; row++){
				GPS0hN0Cell cell = state.getBoard()[row][col];
				
				if(cell.getValue() != 0 && !cell.isCompleted()){
					
					if(flag){
						intersections++;
						if(row + 1 < BOARD_SIZE && state.getBoard()[row+1][col].getValue() != 0){
							flag = false;							
						} 
					} else if(row + 1 < BOARD_SIZE && state.getBoard()[row+1][col].getValue() == 0){						
						flag = true;
					}
				} else if(cell.getColor() == Color.red) {
					flag = false;
				}
			}
			
			flag = false;
			
		}
		
		//Second: Check for interceptions in same row
		for(row = 0 ; row < BOARD_SIZE ; row ++){
			for(col = 0 ; col < BOARD_SIZE ; col++){
				GPS0hN0Cell cell = state.getBoard()[row][col];
				
				if(cell.getValue() != 0 && !cell.isCompleted()){
					
					if(flag){
						intersections++;
						if(row + 1 < BOARD_SIZE && state.getBoard()[row][col+1].getValue() != 0){
							flag = false;							
						} 
					} else if(row + 1 < BOARD_SIZE && state.getBoard()[row][col+1].getValue() == 0){						
						flag = true;
					}
				} else if(cell.getColor() == Color.red) {
					flag = false;
				}
			}
			
			flag = false;
			
		}
		
		
		return intersections;
	}

}
