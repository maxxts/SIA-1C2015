package gps.api.impl;

public class GPS0hN0Cell implements Cloneable{
	

	private int value = 0;
	private boolean fixed = false;
	private boolean completed = false;
	private Color color = Color.white;
	
	public GPS0hN0Cell (int value, Color color, boolean fixed)
	{
		this.value = value;
		this.color = color;
		this.fixed = fixed;
	}

	public GPS0hN0Cell(Color color) {
		this.color = color;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean isFixed(){
		return fixed;
	}
	
	public void complete(){
		completed = true;
	}
	
	public boolean isCompleted(){
		return completed;
	}
	
	@Override
	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		
		if(other.getClass() != GPS0hN0Cell.class){
			return false;
		}
		
		GPS0hN0Cell other_cell = (GPS0hN0Cell) other;
		
		return fixed == other_cell.isFixed() && color.equals(other_cell.getColor()) && value == other_cell.getValue();
		
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		return new GPS0hN0Cell(value,color,fixed);
		
	}

}
