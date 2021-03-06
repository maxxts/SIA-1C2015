package gps.api.impl;

import gps.api.GPSState;

import java.util.ArrayList;
import java.util.List;

public class GPS0hN0State implements GPSState {
	
	private GPS0hN0Cell[][] board;
	private List<CellWrapper> cellsToCheck;
	private int completeCells = 0;
		
	public static int BOARD_SIZE = 7;
		
	public GPS0hN0State(GPS0hN0Cell[][] board, List<CellWrapper> initCells, int completeCells) {
		this.board = board;
		this.cellsToCheck = initCells;
		this.completeCells = completeCells;
	}
	
	// For debug proposes
	public static void changeSize(int size){
		BOARD_SIZE = size;
	}
	
	//Returns a state initialized
	public GPS0hN0State(){
		
		board = new GPS0hN0Cell[BOARD_SIZE][BOARD_SIZE];
		cellsToCheck = new ArrayList<CellWrapper>();
		
		switch(BOARD_SIZE){
			case 5:
				board[0][0] = new GPS0hN0Cell(2, Color.blue, true);
				cellsToCheck.add(new CellWrapper(0,0,board[0][0]));
				
				board[0][2] = new GPS0hN0Cell(0, Color.red, true);
				
				board[1][1] = new GPS0hN0Cell(1, Color.blue, true);
				cellsToCheck.add(new CellWrapper(1,1,board[1][1]));
				
				board[1][3] = new GPS0hN0Cell(0, Color.red, true);
				
				board[2][2] = new GPS0hN0Cell(3, Color.blue, true);
				cellsToCheck.add(new CellWrapper(2,2,board[2][2]));
				
				board[3][2] = new GPS0hN0Cell(4, Color.blue, true);
				cellsToCheck.add(new CellWrapper(3,2,board[3][2]));
				
				board[3][4] = new GPS0hN0Cell(5, Color.blue, true);
				cellsToCheck.add(new CellWrapper(3,4,board[3][4]));
				
				board[4][0] = new GPS0hN0Cell(1, Color.blue, true);
				cellsToCheck.add(new CellWrapper(4,0,board[4][0]));
				
				board[4][3] = new GPS0hN0Cell(0, Color.red, true);

				board[4][4] = new GPS0hN0Cell(0, Color.red, true);
				break;
			
			case 6:
				board[0][1] = new GPS0hN0Cell(1,Color.blue,true);
				cellsToCheck.add(new CellWrapper(0,1,board[0][1]));
				
				board[0][3] = new GPS0hN0Cell(1,Color.blue,true);
				cellsToCheck.add(new CellWrapper(0,3,board[0][3]));
				
				board[0][5] = new GPS0hN0Cell(1,Color.blue,true);
				cellsToCheck.add(new CellWrapper(0,5,board[0][5]));
				
				board[1][0] = new GPS0hN0Cell(1, Color.blue, true);
				cellsToCheck.add(new CellWrapper(1,0,board[1][0]));
				
				board[1][5] = new GPS0hN0Cell(3,Color.blue,true);
				cellsToCheck.add(new CellWrapper(1,5,board[1][5]));
				
				board[3][0] = new GPS0hN0Cell(3, Color.blue, true);
				cellsToCheck.add(new CellWrapper(3,0,board[3][0]));
				
				board[3][1] = new GPS0hN0Cell(2, Color.blue, true);
				cellsToCheck.add(new CellWrapper(3,1,board[3][1]));
				
				board[3][0] = new GPS0hN0Cell(5, Color.blue, true);
				cellsToCheck.add(new CellWrapper(3,0,board[3][0]));
				
				board[3][3] = new GPS0hN0Cell(4, Color.blue, true);
				cellsToCheck.add(new CellWrapper(3,3,board[3][3]));
				
				board[4][2] = new GPS0hN0Cell(5, Color.blue, true);
				cellsToCheck.add(new CellWrapper(4,2,board[4][2]));
				
				board[4][3] = new GPS0hN0Cell(6, Color.blue, true);
				cellsToCheck.add(new CellWrapper(4,3,board[4][3]));
							
				board[0][0] = new GPS0hN0Cell(0, Color.red, true);
				
				board[2][4] = new GPS0hN0Cell(0, Color.red, true);
				
				board[5][4] = new GPS0hN0Cell(0, Color.red, true);
				break;
			
			case 7:
				board[0][0] = new GPS0hN0Cell(1,Color.blue,true);
				cellsToCheck.add(new CellWrapper(0,0,board[0][0]));
				
				board[0][3] = new GPS0hN0Cell(3,Color.blue,true);
				cellsToCheck.add(new CellWrapper(0,3,board[0][3]));
				
				board[0][6] = new GPS0hN0Cell(4,Color.blue,true);
				cellsToCheck.add(new CellWrapper(0,6,board[0][6]));
				
				board[1][1] = new GPS0hN0Cell(6, Color.blue, true);
				cellsToCheck.add(new CellWrapper(1,1,board[1][1]));
				
				board[2][1] = new GPS0hN0Cell(5,Color.blue,true);
				cellsToCheck.add(new CellWrapper(2,1,board[2][1]));
				
				board[2][4] = new GPS0hN0Cell(2, Color.blue, true);
				cellsToCheck.add(new CellWrapper(2,4,board[2][4]));
				
				board[2][5] = new GPS0hN0Cell(6, Color.blue, true);
				cellsToCheck.add(new CellWrapper(2,5,board[2][5]));
				
				board[3][0] = new GPS0hN0Cell(5, Color.blue, true);
				cellsToCheck.add(new CellWrapper(3,0,board[3][0]));
				
				board[4][5] = new GPS0hN0Cell(6, Color.blue, true);
				cellsToCheck.add(new CellWrapper(4,5,board[4][5]));
				
				board[5][1] = new GPS0hN0Cell(7, Color.blue, true);
				cellsToCheck.add(new CellWrapper(5,1,board[5][1]));
				
				board[5][6] = new GPS0hN0Cell(2, Color.blue, true);
				cellsToCheck.add(new CellWrapper(5,6,board[5][6]));
				
				board[6][2] = new GPS0hN0Cell(3, Color.blue, true);
				cellsToCheck.add(new CellWrapper(6,2,board[6][2]));
				
				board[6][4] = new GPS0hN0Cell(3, Color.blue, true);
				cellsToCheck.add(new CellWrapper(6,0,board[6][4]));
				
				board[6][5] = new GPS0hN0Cell(1, Color.blue, true);
				cellsToCheck.add(new CellWrapper(6,5,board[6][5]));
								
				board[4][2] = new GPS0hN0Cell(0, Color.red, true);
				
				board[5][3] = new GPS0hN0Cell(0, Color.red, true);
				break;
		
		}		
	}
	
	public GPS0hN0State cloneState(){
		
		GPS0hN0Cell[][] board_clone = new GPS0hN0Cell[BOARD_SIZE][BOARD_SIZE];
		List<CellWrapper> cellsToCheck_clone = new ArrayList<CellWrapper>();
		
		for(int i=0 ; i < BOARD_SIZE ; i++){
			for(int j=0 ; j < BOARD_SIZE ; j++){
				board_clone[i][j] = (GPS0hN0Cell) board[i][j].cloneCell();
			}
		}
		
		for(CellWrapper each: this.cellsToCheck){
			cellsToCheck_clone.add(each.clone());
		}
		
		return new GPS0hN0State(board_clone, cellsToCheck_clone,completeCells);
		
	}
	
	@Override
	public String toString(){
		
		String newLine = "\r\n ";
		String stateStr = "B : Blue not fixed | R : Red not fixed | Number: Blue fixed | X: Red fixed ";
		stateStr += newLine;
		
		
		for(int i = 0 ; i < BOARD_SIZE ; i++)
		{	
			for(int j = 0 ; j < BOARD_SIZE ; j++)
			{
				
				GPS0hN0Cell cell = this.board[i][j];
				if (cell.isFixed()){
					
					if (cell.getColor() == Color.red)
					{
						stateStr += " X ";
					}
					else
					{
						stateStr += " " + cell.getValue() + " ";
					}
				}
				else
				{
					if (cell.getColor() == Color.blue){
						stateStr += " B ";	
					}
					else if (cell.getColor() == Color.red)
					{
						stateStr += " R ";
					}			
				}
			}
			
			stateStr += newLine;

		}
		
		return stateStr;
	}
	
	public void printStateForDebug(){
			
			System.out.println("____________________");
			for(int i = 0 ; i < BOARD_SIZE ; i++)
			{	
				System.out.println();
				for(int j = 0 ; j < BOARD_SIZE ; j++)
				{
					GPS0hN0Cell cell = this.board[i][j];
					if (cell.isFixed()){
						if (cell.getColor() == Color.red)
						{
							System.out.print(" R ");
						}
						else
						{
							System.out.print(" " + cell.getValue() + " ");
						}
					}
					else
					{
						if (cell.getColor() == Color.blue){
							System.out.print(" O ");	
						}
						else if (cell.getColor() == Color.red)
						{
							System.out.print(" X ");
						}			
					}
				}
				System.out.println();
			}
			System.out.println("____________________");
			System.out.println();
		}
	
	public GPS0hN0Cell[][] getBoard() {
		return board;
	}
	
	public List<CellWrapper> getCellsToCheck(){
		return cellsToCheck;
	}

	@Override
	public boolean compare(GPSState state) {
		
		if(state == null){
			return false;
		}
		
		GPS0hN0State ohno_state = (GPS0hN0State) state;
		
		if(ohno_state.getBoard() == null){
			return false;
		}
		
		GPS0hN0Cell[][] state_board = ohno_state.getBoard();
		
		for(int i=0 ; i < GPS0hN0State.BOARD_SIZE ; i++ ){
			
			for(int j = 0 ; j < GPS0hN0State.BOARD_SIZE ; j++){
				
				if( !(board[i][j].equals(state_board[i][j])) ){
					return false;
				}
				
			}
		}
		
		return true;
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
	
	public int getCompleteCells(){
		//return completeCells;
		
		int complete = 0;
		
		for(CellWrapper cell: cellsToCheck){
			if(cell.getCell().isCompleted()){
				complete++;
			}
		}
		
		return complete;
				
	}
	
	public void setComplete(int complete){
		completeCells = complete;
	}


	
	
}
