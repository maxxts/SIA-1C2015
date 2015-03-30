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
		// TODO Florcha, haceme.
		return 1;
	}

	@Override
	public String getName() {
		return "Se coloca " + this.color.name() + " en posicion (" + this.i
				+ ", " + this.j + ")";
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {

		GPS0hN0State auxState = (GPS0hN0State) state;

		GPS0hN0Cell[][] board = auxState.getBoard();

		GPS0hN0Cell cellAt = board[i][j];

		if (cellAt.isFixed() || cellAt.getColor() == color) {
			throw new NotAppliableException();
		}

		board[i][j] = new GPS0hN0Cell(color);

		for (CellWrapper cell : auxState.getCellsToCheck()) {

			int visibleCells = visibleCells(cell, board);

			if (visibleCells == cell.getCell().getValue()) {

				auxState.completeCell(cell);

			} else if (!isAppliable(visibleCells, cell.getCell().getValue())) {
				throw new NotAppliableException();
			}

		}

		return auxState;

	}

	public abstract boolean isAppliable(int visibleCells, int cellValue);

	// It checks how many cells are visible for the one being analyzed
	protected int visibleCells(CellWrapper cell, GPS0hN0Cell[][] board) {

		int visibleCells = 0;
		int row = cell.getI();
		int col = cell.getJ();

		int rows = board.length;
		int cols = board[0].length;

		// Check how many cells it sees in the same row
		for (int j = 0; j < cols; j++) {
			if (j != col && board[row][j].getColor() == Color.blue) {
				visibleCells++;
			}
		}

		// Check how many cells it sees in the same column
		for (int i = 0; i < rows; i++) {
			if (i != row && board[i][col].getColor() == Color.blue) {
				visibleCells++;
			}
		}

		return visibleCells;
	}

}
