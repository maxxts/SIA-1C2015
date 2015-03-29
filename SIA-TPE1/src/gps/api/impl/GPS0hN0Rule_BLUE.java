package gps.api.impl;

public class GPS0hN0Rule_BLUE extends GPS0hN0Rule{

	public GPS0hN0Rule_BLUE(int i, int j) {
		super(i, j, Color.blue);
	}

	@Override
	public boolean isAppliable(int visibleCells, int cellValue) {
		return visibleCells > cellValue ? false: true;
	}

}
