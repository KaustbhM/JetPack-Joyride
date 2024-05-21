
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;

import Characters.Caveman;
import Characters.Character;
import Characters.Obstacle;
import Characters.Block;

public class GamePanel extends JPanel implements ActionListener {

  private JButton startBtn = new JButton();
  Caveman caveman = new Caveman();
  private Timer time;

  private Image backgroundImage;
  private int bgX = 0;
  private final int SPEED = 1;


  // Obstacle Related Variables
  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  public final int maxWidth = (int) screenSize.getWidth(); // 1980
  public final int maxHeight = (int) screenSize.getHeight() - 210; // 1000
  private ArrayList<Obstacle> obstacles;
  private int obstacleCheckpoint = (int) screenSize.getWidth() / 2;
  private BufferedImage block;
  private int difficulty = Constants.INIT_DIFFICULTY;
  private int[] obstacleDimensions = { (int) (maxHeight * .1), (int) (maxHeight * .125), (int) (maxHeight * .2),
      (int) (maxHeight * .25), (int) (maxHeight * .325) };

  private static final int BTN_SIZE = 50;
  private Random random = new Random();

  public GamePanel() {
    addEventHandlers();
    backgroundImage = new ImageIcon(getClass().getResource("cave.jpg")).getImage();

    if (backgroundImage.getWidth(null) == -1) {
      System.out.println("Image not loaded properly");
    }

    // Load all images
    loadImages();

    System.out.println("Screen width " + maxWidth + " Screen height " + maxHeight);

    obstacles = new ArrayList<Obstacle>();
    obstacles.add(new Block(1, 200, 50, 50, 800));

    time = new Timer(5, this);
    time.start();
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    setPreferredSize(new Dimension(800, 600));

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // String s = e.getActionCommand();
    moveBackground();
    caveman.move();
    updateObstacles();
    moveObstacles();
    repaint();

  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D twoD = (Graphics2D) g;
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
    try {
      man = ImageIO.read(getClass().getResourceAsStream("caveman.png"));
      fire = ImageIO.read(getClass().getResourceAsStream("fire.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    twoD.drawImage(man, caveman.getX(), caveman.getY(), 175, 100, null);
    // only draws fire image when caveman is moving up
    if (caveman.dyUp < caveman.dyDown) {
      twoD.drawImage(fire, caveman.getX() + 37, caveman.getY() + 62, 40, 65, null);
    }

    // Draw obstacles
    for (Obstacle obstacle : obstacles) {
      System.out.println("Drawing obstacle at: " + obstacle.getPlayerCoordY());
      g.setColor(Color.BLUE);
      int[] definitions = obstacle.getDefinitions();
      g.drawImage(block, definitions[0], definitions[1], definitions[2], definitions[3], null);
      // g.drawImage(block, 400, 400, 50, 50, null);
    }

    System.out.println("Outside obstacles");
    // createComponents();
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

  private void loadImages() {
    // block = new ImageIcon(getClass().getResource("block.png")).getImage();
    try {
      block = ImageIO.read(getClass().getResourceAsStream("block.png"));
    } catch (Exception ex) {
      ex.printStackTrace();

    }

  }

  private void moveObstacles() {
    for (Obstacle obstacle : obstacles) {
      obstacle.iterate();
    }
  }

  /**
   * private void updateObstacles() { int[] lastObstacleDefinitions =
   * obstacles.get(obstacles.size() - 1).getDefinitions();
   * 
   * if (2 * lastObstacleDefinitions[0] < obstacleCheckpoint + (difficulty * 0.2 *
   * obstacleCheckpoint)) {
   * 
   * int numOfObstacles =
   * Constants.OBSTACLE_NUMBERS[random.nextInt(Constants.OBSTACLE_NUMBERS.length)];
   * 
   * int obstacleDimension =
   * obstacleDimensions[random.nextInt(obstacleDimensions.length)];
   * 
   * if (numOfObstacles == 1) { obstacles.add(new Block(1, maxHeight/2,
   * obstacleDimension, obstacleDimension, maxWidth));
   * 
   * }
   * 
   * if (numOfObstacles == 2) {
   * 
   * obstacles.add(new Block(1, 0, obstacleDimension, obstacleDimension,
   * maxWidth)); obstacles.add(new Block(1, maxHeight - obstacleDimension,
   * obstacleDimension, obstacleDimension, maxWidth)); }
   * 
   * if (numOfObstacles == 3) {
   * 
   * }
   * 
   * if (numOfObstacles == 4) {
   * 
   * }
   * 
   * } }
   */
  private void updateObstacles_bkp() {
    // Increase the speed of obstacles as time progresses
    if (time.getDelay() > 1) {
      time.setDelay(time.getDelay() - 1); // Decrease delay to increase speed
    }

    // Check if we need to add new obstacles
    int[] lastObstacleDefinitions = obstacles.get(obstacles.size() - 1).getDefinitions();
    if (lastObstacleDefinitions[0] < maxWidth - obstacleCheckpoint) {
      int numOfObstacles = random.nextInt(4) + 1; // Random number between 1 and 4

      for (int i = 0; i < numOfObstacles; i++) {
        int width = obstacleDimensions[random.nextInt(obstacleDimensions.length)];
        int height = obstacleDimensions[random.nextInt(obstacleDimensions.length)];
        int x = maxWidth;
        int y = random.nextInt(maxHeight - height);

        obstacles.add(new Block(1, y, width, height, maxWidth));
      }

      obstacleCheckpoint = maxWidth + random.nextInt(maxWidth / 2); // Set a new checkpoint for adding obstacles
    }
  }

  private void updateObstacles() {
    // Increase the speed and frequency of obstacles as time progresses
      if (time.getDelay() > 1) {
      time.setDelay(time.getDelay() - 1); // Decrease delay to increase speed
    }

    // Check if we need to add new obstacles
    int[] lastObstacleDefinitions = obstacles.get(obstacles.size() - 1).getDefinitions();
    if (lastObstacleDefinitions[0] < maxWidth - obstacleCheckpoint) {
      int numGroups = random.nextInt(2) + 1; // Random number between 1 and 2 for groups
      int totalObstacles = random.nextInt(4) + 1; // Random number between 1 and 4 for obstacles per group

      if (numGroups == 1) {
        // One group centered vertically
        int width = obstacleDimensions[random.nextInt(obstacleDimensions.length)];
        int height = obstacleDimensions[random.nextInt(obstacleDimensions.length)];
        int x = maxWidth;
        int y = (maxHeight - height) / 2; // Centered vertically

        obstacles.add(new Block(1, y, width, height, maxWidth));
      } else {
        // Two groups, one at the top and one at the bottom
        int gapSize = 200; // Space for the sprite to pass through
        int obstacleHeight = (maxHeight - gapSize) / 2;

        for (int i = 0; i < totalObstacles; i++) {
          int width = obstacleDimensions[random.nextInt(obstacleDimensions.length)];
          int height = obstacleHeight;
          int x = maxWidth;
          int topBound = Math.max(1, obstacleHeight - height); // Ensure the bound is positive
                  int yTop = random.nextInt(topBound); // Top group

                  int bottomBound = Math.max(1, obstacleHeight - height); // Ensure the bound is positive
                    int yBottom = maxHeight - obstacleHeight + random.nextInt(bottomBound); // Bottom group

          obstacles.add(new Block(1, yTop, width, height, maxWidth));
          obstacles.add(new Block(1, yBottom, width, height, maxWidth));
        }
      }

      obstacleCheckpoint = maxWidth + random.nextInt(maxWidth / 2); // Set a new checkpoint for adding obstacles
    }
  }


}
