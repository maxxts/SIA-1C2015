package gps.api.impl;

import gps.api.GPSState;

import java.util.List;

public class GPS0hN0State implements GPSState {
	
	private GPS0hN0Cell[][] board;
	private List<CellWrapper> cellsToCheck;
	
	
	public GPS0hN0State(GPS0hN0Cell[][] board, List<CellWrapper> initCells) {
		this.board = board;
		this.cellsToCheck = initCells;
	}
	
	public boolean addTile (Color color, int x, int y) {
		GPS0hN0Cell cell = board[x][y];
		if (cell == null || cell.getValue() == 0){
			board[x][y] = new GPS0hN0Cell(color);
			return true;
		}
		return false;
	}
	
	public GPS0hN0Cell[][] getBoard() {
		return board;
	}
	
	public List<CellWrapper> getCellsToCheck(){
		return cellsToCheck;
	}

	@Override
	public boolean compare(GPSState state) {
		// TODO Maldita Juli no quiere hacerlo.
		return false;
	}
	
	public void prepareBoardForSearch(){
		
		for(int i=0 ; i < board.length ; i++){
			for(int j = 0 ; j < board[0].length ; j++){
				
				GPS0hN0Cell cell = board[i][j];
				
				if(cell == null){
					board[i][j] = new GPS0hN0Cell(Color.blue);
				}
				
			}
		}
	}

}
