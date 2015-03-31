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

		int completeCells_original = ((GPS0hN0State) state).getCompleteCells();

		GPS0hN0State auxState = ((GPS0hN0State) state).cloneState();

		GPS0hN0Cell cellAt = auxState.getBoard()[i][j];

		if (cellAt.isFixed() || cellAt.getColor() == color) {
			throw new NotAppliableException();
		}

		auxState.getBoard()[i][j] = new GPS0hN0Cell(color);

		int complete = 0;

		String fixedCells = "";
		/*
		 * //auxState.printStateForDebug(); for (CellWrapper cell :
		 * auxState.getCellsToCheck()) {
		 * 
		 * int visibleCells = visibleCells(cell, auxState.getBoard());
		 * 
		 * 
		 * if (visibleCells == cell.getCell().getValue()) {
		 * 
		 * auxState.completeCell(cell); complete++;
		 * 
		 * } else if (!isAppliable(visibleCells, cell.getCell().getValue())) {
		 * System.out.println("NO APLICA"); throw new NotAppliableException(); }
		 * 
		 * //fixedCells += "FixedCell: (" + cell.getI() + " , " + cell.getJ() +
		 * ") >>\t" + cell.getCell().isCompleted() + "\r\n ";
		 * //System.out.println("FixedCell: (" + cell.getI() + " , " +
		 * cell.getJ() + ") >>\t" + cell.getCell().isCompleted()); }
		 */

		for (int i = 0; i < auxState.getCellsToCheck().size(); i++) {

			CellWrapper newCell = auxState.getCellsToCheck().get(i);

			int visibleCells = visibleCells(newCell, auxState.getBoard());

			if (visibleCells == newCell.getCell().getValue()) {

				auxState.completeCell(newCell);
				complete++;

			} else {
				if (!isAppliable(visibleCells, newCell.getCell().getValue())) {
					throw new NotAppliableException();
				}

				CellWrapper prevCell = ((GPS0hN0State) state).getCellsToCheck()
						.get(i);

				if (newCell.getCell().isCompleted() != prevCell.getCell()
						.isCompleted()) {
					System.out.println("SE RECHAZA POR NUEVA CONDICION");
					throw new NotAppliableException();
				}
			}

			

		}

		// System.out.println("COmplete original: " + completeCells_original);
		// System.out.println("New board complete: " + complete);
		if (completeCells_original > complete) {
			System.out.println("REJECTED");
			throw new NotAppliableException();
		}

		auxState.setComplete(complete);
		System.out.println(fixedCells);
		return auxState;

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
