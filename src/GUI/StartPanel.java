
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;

import Characters.Caveman;
import Characters.Character;


public class StartPanel extends JPanel implements ActionListener{
  private JButton startBtn = new JButton();
  Caveman caveman = new Caveman();
  private Timer time;



  private Image backgroundImage;
  private int bgX = 0;
  private final int SPEED = 1;


  private static final int BTN_SIZE = 50;

  @Override
  public void actionPerformed(ActionEvent e) {
  String s = e.getActionCommand();
    moveBackground();
    // caveman.move();
    repaint();

  }

  public StartPanel() {
  // addEventHandlers();
    backgroundImage = new ImageIcon(getClass().getResource("cave.jpg")).getImage();

    if (backgroundImage.getWidth(null) == -1) {
      System.out.println("Image not loaded properly");
    }

    time = new Timer(5, this);
    time.start();
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    setPreferredSize(new Dimension(800, 600));


  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D twoD = (Graphics2D)g;
    super.paintComponent(twoD);


      // Call the coin drawing method

      // Draw the continuous looping background
      twoD.drawImage(backgroundImage, bgX, 0, null);

      // g.drawImage(backgroundImage, bgX, 0, 1920, 1080, null);
      if (bgX < 0) {
        twoD.drawImage(backgroundImage, bgX + backgroundImage.getWidth(null), 0, null);

      }

      BufferedImage man = null;
      BufferedImage fire = null;
      try{
        man = ImageIO.read(getClass().getResourceAsStream("caveman.png"));
        fire = ImageIO.read(getClass().getResourceAsStream("fire.png"));
      }catch(IOException e){
        e.printStackTrace();
      }

      twoD.drawImage(man,caveman.getX(), caveman.getY(), 175, 100 , null);
      //only draws fire image when caveman is moving up
      if (caveman.dyUp < caveman.dyDown) {
        twoD.drawImage(fire,caveman.getX()+37, caveman.getY()+62, 40, 65 , null);
      }

       //  createComponents();
  }

  public void createComponents() {
    startBtn.setBackground(Color.black);
    startBtn.setBorderPainted(false);
    setLayout(null);
    startBtn.setBounds(370, 450, 250, 100);
    this.add(startBtn);
     try {
          Image img = ImageIO.read(getClass().getResourceAsStream("download-removebg-preview.png"));
          startBtn.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
          System.out.println(ex);
        }
  }

  private void addEventHandlers() {
      // a mouse listener requires a full interface with lots of methods.
      // to get around having implement all, we use the MouseAdapter class
      // and override just the one method we're interested in.

    this.addKeyListener(new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

          if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) {
            caveman.dyUp = -20;
          }
          }
          @Override
          public void keyReleased(KeyEvent e) {
            caveman.dyUp = 5;
          }
      });

      this.addMouseListener(new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
            caveman.dyUp = -10;
            caveman.mousePressed = true;
            caveman.mouseReleased = false;

          }
          @Override
          public void mouseReleased(MouseEvent e) {
            caveman.dyUp = 5;
            caveman.mouseReleased = true;
            caveman.mousePressed = false;
          }
      });
  }


  public void updateAnimation() {

  }



  // New Code
  private void moveBackground() {
    bgX -= SPEED;
    if (bgX == -backgroundImage.getWidth(null)) {
      bgX = 0;
    }
  }

  public void drawCoin(Graphics g) {

  }


}
