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
	
	private GamePanel panel;
	// private BackgroundPanel backgroundPanel;
	private static volatile boolean done = false;
	private static int delay = 20;
	
	public static void startGUI() throws InterruptedException {
		Enviornment theGUI = new Enviornment();
	    SwingUtilities.invokeLater( () -> theGUI.createFrame(theGUI) );
	    synchronized (theGUI ) {
	      theGUI.wait();
	    }
	  }
	
	public void createFrame(Object semaphore) {
		
		this.setSize(1920, 1080);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new GamePanel();
	   
	        panel.setBounds(0, 0, 500,500);
	        add(panel);
	        panel.setVisible(false);
	      
	      // Set the current panel and make it visible 
	     
	      panel.setVisible(true);

	      // Set this JFrame to be visible
	      this.setVisible(true);

	          System.out.println("All done creating our frame");
	      // tell the main thread that we are done creating our dialogs.
	          // This allows the main thread to stop wait()'ing.
	      synchronized (semaphore) {
	        semaphore.notify();
	      }
		
		
	}
	
	public void startAnimation() {
        // We set done to false and allow the UI thread to change the value
        // to true when menu options are selected.
    Enviornment.done = false;
    try {			
      while (!Enviornment.done) {
        panel.updateAnimation();
                // This informs the UI Thread to repaint this component
        repaint();
                // This causes our main thread to wait... to sleep... for a bit.
        Thread.sleep(Enviornment.delay);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
