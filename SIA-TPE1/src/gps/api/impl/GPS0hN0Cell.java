package gps.api.impl;

public class GPS0hN0Cell {
	
	private int value=0;
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

}
