package gps.api.impl;

public class GPS0hN0Rule_RED extends GPS0hN0Rule{

	public GPS0hN0Rule_RED(int i, int j) {
		super(i, j, Color.red);
	}

	@Override
	public boolean isAppliable(int visibleCells, int cellValue) {
		return visibleCells < cellValue ? false : true; 
	}

}
