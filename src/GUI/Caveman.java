package GUI;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Caveman {
	int x;
	int dx = 1;
	int dy;
	int y;
	int bx2;
	BufferedImage img;
	
	public Caveman() {
		//BufferedImage img = null;
//	       try{
//	    	   img = ImageIO.read(getClass().getResourceAsStream(""));
//	       }catch(IOException e){
//	         e.printStackTrace();
//	       }
		x = 75;
		y = 100;
		bx2 = 1900;
	}
	
	public void move() {
		x = x + dx;
		bx2 += dx;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_UP) {
			y += 1;
		}
	}

	public void setbx2(int i) {
		bx2 = i;
		
	}
	public int getbx2() {
		return bx2;
	}
}
