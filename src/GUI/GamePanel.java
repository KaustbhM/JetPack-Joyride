package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;

import Characters.Caveman;
import Characters.Character;

public class GamePanel extends JPanel implements ActionListener{
	private JButton startBtn = new JButton();
	Caveman caveman = new Caveman();
	Timer time;
	private int dx = 1;
	private int bx = 1900;

	
	private void move() {
		bx += dx;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
		
	}
	
	public GamePanel() {
		addKeyListener(new AL());
		this.setBackground(Color.black);
		time = new Timer(5, this);
		time.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D twoD = (Graphics2D)g;
		super.paintComponent(twoD);
		BufferedImage img = null;
	       try{
	    	   img = ImageIO.read(getClass().getResourceAsStream("cave.jpg"));
	       }catch(IOException e){
	         e.printStackTrace();
	       }
	       //draws the background image
	      twoD.drawImage(img, 1900-bx, 0, null);
	      //trys to draw second background image after first has passed
	      
	      createComponents();
	}

	public void createComponents() {
		startBtn.setBackground(Color.black);
		startBtn.setBorderPainted(false);
		setLayout(null);
		startBtn.setBounds(750, 450, 250, 100);
		this.add(startBtn);
		 try {
			    Image img = ImageIO.read(getClass().getResourceAsStream("download-removebg-preview.png"));
			    startBtn.setIcon(new ImageIcon(img));
			  } catch (Exception ex) {
			    System.out.println(ex);
			  }
	}
	
	
	public void updateAnimation() {
		
	}
	
	private class AL extends KeyAdapter{
		public void KeyPressed(KeyEvent e) {
			caveman.keyPressed(e);
		}
	}
	

}
