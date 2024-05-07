
package Characters;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Caveman {
	int x;
	int dyDown = -9;
	public int dyUp;
	int y;
	public BufferedImage img;
	public Caveman() {
		x = 20;
		y = 200;
	}
	
	public void move() {
		y += dyUp-dyDown;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}
}
