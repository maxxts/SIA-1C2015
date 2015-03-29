package gps.api.impl;

import gps.api.GPSRule;
import gps.api.GPSState;
import java.util.LinkedList;
import java.util.List;

public class GPS0hN0Problem_allBlue extends GPS0hN0Problem {

	@Override
	public List<GPSRule> getRulesImpl() {
		return getRulesByColor(Color.red);
	}

	@Override
	public Integer getHValueImpl(GPSState state) {

		GPS0hN0State ohno_state = (GPS0hN0State) state;

		int unComplete = 0;

		for (CellWrapper cell : ohno_state.getCellsToCheck()) {
			if (!cell.getCell().isCompleted()) {
				unComplete++;
			}
		}

		if (unComplete == 1) {
			return 1;
		}

		if (unComplete == 0) {
			return 0;
		}

		int intersections = sumIntersections(ohno_state);
		return unComplete - 1 - intersections;
	}

	// It checks how many cells are visible for the one being analyzed
	private int visibleCells(CellWrapper cell, GPS0hN0Cell[][] board) {

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

	private int sumIntersections(GPS0hN0State state) {

		int intersections = 0;

		boolean flag = false;

		int col, row;

		// First step: check for intersections in same columns
		for (col = 0; col < GPS0hN0State.BOARD_SIZE; col++) {
			for (row = 0; row < GPS0hN0State.BOARD_SIZE; row++) {
				GPS0hN0Cell cell = state.getBoard()[row][col];

				if (cell.getValue() != 0 && !cell.isCompleted()) {

					if (flag) {
						intersections++;
						if (row + 1 < GPS0hN0State.BOARD_SIZE
								&& state.getBoard()[row + 1][col].getValue() != 0) {
							flag = false;
						}
					} else if (row + 1 < GPS0hN0State.BOARD_SIZE
							&& state.getBoard()[row + 1][col].getValue() == 0) {
						flag = true;
					}
				} else if (cell.getColor() == Color.red) {
					flag = false;
				}
			}

			flag = false;

		}

		// Second: Check for intersections in same row
		for (row = 0; row < GPS0hN0State.BOARD_SIZE; row++) {
			for (col = 0; col < GPS0hN0State.BOARD_SIZE; col++) {
				GPS0hN0Cell cell = state.getBoard()[row][col];

				if (cell.getValue() != 0 && !cell.isCompleted()) {

					if (flag) {
						intersections++;
						if (row + 1 < GPS0hN0State.BOARD_SIZE
								&& state.getBoard()[row][col + 1].getValue() != 0) {
							flag = false;
						}
					} else if (row + 1 < GPS0hN0State.BOARD_SIZE
							&& state.getBoard()[row][col + 1].getValue() == 0) {
						flag = true;
					}
				} else if (cell.getColor() == Color.red) {
					flag = false;
				}
			}

			flag = false;

		}

		return intersections;
	}
}
