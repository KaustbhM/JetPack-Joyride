package GUI;

import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.util.ArrayList;


public class Enviornment extends JFrame{
	
	private GamePanel gamePanel;
	// private BackgroundPanel backgroundPanel;
	
	
	public static void startGUI() throws InterruptedException {
		Enviornment theGUI = new Enviornment();
	    SwingUtilities.invokeLater( () -> theGUI.createFrame(theGUI) );
	    synchronized (theGUI ) {
	      theGUI.wait();
	    }
	  }
	
	public void createFrame(Object semaphore) {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		gamePanel = new GamePanel();
		
		gamePanel.setVisible(true);
		
	}
}
