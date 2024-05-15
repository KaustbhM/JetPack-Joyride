package Characters;




public class Coin{
	private int x;
	private int y;
	private int width;
	private int height;
	//valid keeps track of whether the player hit the obstacle or not
	//it is able to change by sending special values to contains method
	private boolean valid = true;
	
	public Coin(int id, int x, int y, int diameter) {
		this.x = x;
		this.width = diameter;
		this.y = y;
		this.height = diameter;
	}

	
	private float dist(int x, int y, int x1, int y1) {
		float distance = (float) (Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2)));
		return distance;

	}






	

}


