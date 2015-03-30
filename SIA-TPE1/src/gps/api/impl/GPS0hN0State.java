package gps.api.impl;

import gps.api.GPSState;

import java.util.ArrayList;
import java.util.List;

public class GPS0hN0State implements GPSState {
	
	private GPS0hN0Cell[][] board;
	private List<CellWrapper> cellsToCheck;
	
	public static int BOARD_SIZE = 7;
	
	
	public GPS0hN0State(GPS0hN0Cell[][] board, List<CellWrapper> initCells) {
		this.board = board;
		this.cellsToCheck = initCells;
	}
	
	//Returns a state initialized
	public GPS0hN0State(){
		
		board = new GPS0hN0Cell[BOARD_SIZE][BOARD_SIZE];
		List<CellWrapper> cellsToCheck = new ArrayList<CellWrapper>();
		
		board[0][1] = new GPS0hN0Cell(3,Color.blue,true);
		cellsToCheck.add(new CellWrapper(0,1,board[0][1]));
		
		board[0][6] = new GPS0hN0Cell(4,Color.blue,true);
		cellsToCheck.add(new CellWrapper(0,6,board[0][6]));
		
		board[1][0] = new GPS0hN0Cell(5,Color.blue,true);
		cellsToCheck.add(new CellWrapper(1,0,board[1][0]));
		
		board[1][2] = new GPS0hN0Cell(6, Color.blue, true);
		cellsToCheck.add(new CellWrapper(1,2,board[1][2]));
		
		board[1][3] = new GPS0hN0Cell(5,Color.blue,true);
		cellsToCheck.add(new CellWrapper(1,3,board[1][3]));
		
		board[2][3] = new GPS0hN0Cell(2, Color.blue, true);
		cellsToCheck.add(new CellWrapper(2,3,board[2][3]));
		
		board[4][0] = new GPS0hN0Cell(7, Color.blue, true);
		cellsToCheck.add(new CellWrapper(4,0,board[4][0]));
		
		board[4][1] = new GPS0hN0Cell(7, Color.blue, true);
		cellsToCheck.add(new CellWrapper(4,1,board[4][1]));
		
		board[4][3] = new GPS0hN0Cell(5, Color.blue, true);
		cellsToCheck.add(new CellWrapper(4,3,board[4][3]));
		
		board[4][4] = new GPS0hN0Cell(7, Color.blue, true);
		cellsToCheck.add(new CellWrapper(4,4,board[4][4]));
		
		board[4][5] = new GPS0hN0Cell(6, Color.blue, true);
		cellsToCheck.add(new CellWrapper(4,5,board[4][5]));
		
		board[5][0] = new GPS0hN0Cell(3, Color.blue, true);
		cellsToCheck.add(new CellWrapper(5,0,board[5][0]));
		
		board[6][0] = new GPS0hN0Cell(2, Color.blue, true);
		cellsToCheck.add(new CellWrapper(6,0,board[6][0]));
		
		board[6][5] = new GPS0hN0Cell(4, Color.blue, true);
		cellsToCheck.add(new CellWrapper(6,5,board[6][5]));
		
		board[6][6] = new GPS0hN0Cell(5, Color.blue, true);
		cellsToCheck.add(new CellWrapper(6,6,board[6][6]));
		
		board[3][6] = new GPS0hN0Cell(0, Color.red, true);
		
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
	
	public void prepareBoardForSearch(Color fillColor){
		
		for(int i=0 ; i < BOARD_SIZE ; i++){
			for(int j = 0 ; j < BOARD_SIZE ; j++){
				
				GPS0hN0Cell cell = board[i][j];
				
				if(cell == null){
					board[i][j] = new GPS0hN0Cell(fillColor);
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
