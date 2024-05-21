
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.util.ArrayList;

public class Enviornment extends JFrame {
	BufferedImage backgroundImage;

	private GamePanel panel;

	private StartPanel startPanel;
	// private BackgroundPanel backgroundPanel;
	private static volatile boolean done = false;
	private static int delay = 20;

	public static void startGUI() throws InterruptedException {
		Enviornment theGUI = new Enviornment();
		SwingUtilities.invokeLater(() -> theGUI.createFrame(theGUI));
		synchronized (theGUI) {
			theGUI.wait();
		}
	}

	public void createFrame(Object semaphore) {
		/**
		 * try { backgroundImage =
		 * ImageIO.read(getClass().getResourceAsStream("cave.jpg")); } catch
		 * (IOException e) { e.printStackTrace(); }
		 * 
		 * this.setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
		 * this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * 
		 * panel = new GamePanel();
		 * 
		 * panel.setBounds(0, 0, 1920, 1080); add(panel); panel.setVisible(false);
		 * 
		 * // Set the current panel and make it visible
		 * 
		 * panel.setVisible(true);
		 * 
		 * // Set this JFrame to be visible this.setVisible(true);
		 * 
		 * System.out.println("All done creating our frame"); // tell the main thread
		 * that we are done creating our dialogs. // This allows the main thread to stop
		 * wait()'ing. synchronized (semaphore) { semaphore.notify(); }
		 */

		/*** My changes ***/
		try {
			backgroundImage = ImageIO.read(getClass().getResourceAsStream("cave.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		buildStartScreen();

		// Add startPanel to the frame
		this.setContentPane(startPanel);


		// Set this JFrame to be visible
		this.setVisible(true);

		System.out.println("All done creating our frame");
		// tell the main thread that we are done creating our dialogs.
		// This allows the main thread to stop wait()'ing.
		synchronized (semaphore) {
			semaphore.notify();
		}

		/** My Changes - End ***/

	}

	private void buildStartScreen() {

		startPanel = new StartPanel();

		// Add buttons to start panel
		JButton startButton = new JButton();
		startButton.setBackground(Color.black);
		startButton.setBorderPainted(false);
		setLayout(null);
		startButton.setBounds(370, 450, 250, 100);
		startPanel.add(startButton);
		try {
			Image img = ImageIO.read(getClass().getResourceAsStream("download-removebg-preview.png"));
			startButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
				revalidate();
			}
		});
	}

	private void startGame() {

	 panel = new GamePanel();

	 panel.setBounds(0, 0, 1920, 1080); add(panel); panel.setVisible(false);

	 // Set the current panel and make it visible

	 panel.setVisible(true);

	 this.setContentPane(panel);

	 revalidate();

	}

	/**
	 * 
	 * public void startAnimation() { // We set done to false and allow the UI
	 * thread to change the value // to true when menu options are selected.
	 * Enviornment.done = false; try { while (!Enviornment.done) {
	 * panel.updateAnimation(); // This informs the UI Thread to repaint this
	 * component repaint(); // This causes our main thread to wait... to sleep...
	 * for a bit. Thread.sleep(Enviornment.delay); } } catch (InterruptedException
	 * e) { e.printStackTrace(); } }
	 **/
}
