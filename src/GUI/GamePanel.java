package GUI;

import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener{

	private static final int BTN_SIZE = 50;
	private JButton startBtn = new JButton("START");
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public GamePanel() {
		this.setBackground(Color.black);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// System.out.println("Hello");
		super.paintComponent(g);
		BufferedImage img = null;
	       try{
	    	   img = ImageIO.read(getClass().getResourceAsStream("cave.jpg"));
	       }catch(IOException e){
	         e.printStackTrace();
	       }
	      g.drawImage(img, 0, 0, 1920, 1080, null);
	      createComponents();
	      
	}
	
	public void updateAnimation() {
		
	}
	
	
	 public void createComponents() {
	        // Set the background of our pane to be Gray
	        this.setBackground(Color.GRAY);
	        // This is critically important to allow components to be
	        // placed by pixel location instead of using a LayoutManager.
	        // Set the LayoutManager to be nothing.
	        setLayout(null);
	        startBtn.setBounds(750, 450, 400, 100);
	        this.add(startBtn);
	    }

	
}
