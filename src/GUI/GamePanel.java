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
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener{
	private JButton startBtn = new JButton();

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

}
