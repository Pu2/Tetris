
public class Cell {
	private int x, y;
	private String colour;

	Cell(int x, int y, String colour){
		this.x = x;
		this.y = y;
		this.colour = colour;
	}

	int getX(){
		return x;
	}

	int getY(){
		return y;
	}

	String getColor(){
		return colour;
	}

	void setColour(String colour){
		this.colour = colour;
	}
	
	
}
