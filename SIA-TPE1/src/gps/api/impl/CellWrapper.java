package gps.api.impl;

public class CellWrapper {
	
	private int i;
	private int j;
	private GPS0hN0Cell cell;
	
	public CellWrapper(int i, int j, GPS0hN0Cell cell){
		this.i = i;
		this.j = j;
		this.cell = cell;
	}
	
	public int getI(){
		return i;
	}
	
	public int getJ(){
		return j;
	}
	
	public GPS0hN0Cell getCell(){
		return cell;
	}

}
