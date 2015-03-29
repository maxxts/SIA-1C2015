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
		
		for(int i=0 ; i < GPS0hN0Problem.BOARD_SIZE ; i++){
			for(int j = 0 ; j < GPS0hN0Problem.BOARD_SIZE ; j++){
				
				GPS0hN0Cell cell = board[i][j];
				
				if(cell == null){
					board[i][j] = new GPS0hN0Cell(Color.blue);
				}
				
			}
		}
	}

	public void completeCell(CellWrapper cell){
		for(int i =0 ; i < cellsToCheck.size() ; i++){
			
			CellWrapper each = cellsToCheck.get(i);
			
			if(each.getI() == cell.getI() && each.getJ() == cell.getJ()){
				each.getCell().complete();
				return;
			}
		}
	}
}
