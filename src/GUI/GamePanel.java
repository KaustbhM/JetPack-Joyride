package GUI;

import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public GamePanel() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// System.out.println("Hello");
		super.paintComponent(g);
		BufferedImage img = null;
	       try{
	         img = ImageIO.read(new File("/Caveman-Joyride/src/Images/pixel-art-game-background-underground-cave-vector-49391631.jpg"));
	       }catch(IOException e){
	         e.printStackTrace();
	       }

	      g.drawImage(img, 0, 0, 500, 500, null);
	      
	}

}
