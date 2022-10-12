/* to do 
 * 1.bouncing physics (random angles, direction, etc. initiated once every press of the start Animation)
 * 3.screenshotting not saving
 * 4. komentarze
*/

import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Painting.
 * 
 * Paint with Dinguses, i.e., generate a new painting by making a random 
 * composition of Dingus shapes.
 *
 * TODO:
 *
 * @author Tomasz Soróbka
 * @id 1808982
 */
public class Painting extends JPanel implements ActionListener {

    /*---- Randomness ----*/
    Random random = Painting.RANDOM;
    /**
     * Seed for the random number generator.
     * 
     * You can change the variable SEED if you like. The same program with the same
     * SEED will generate exactly the same sequence of pictures.
     */
    static final long SEED = 1337;

    // DON'T CHANGE the following three lines:
    // RANDOM is the random number generator used and shared by all classes in your
    // program.
    static final Random RANDOM = new Random(SEED);
    int numberOfRegenerates = 0;
    int numberOfRecoloration = 0;

    // ---- Screenshots ----
    // DON'T CHANGE the following two lines:
    char current = '0';
    String filename = "randomshot_"; // prefix

    /*---- Dinguses ----*/
    ArrayList<Dingus> shapes;
    // ...
    int numberOfDinguses;
    int MAX_NUMBER_OF_DINGUSES = 30;
    int[] xvelocity = new int[MAX_NUMBER_OF_DINGUSES];
    int[] yvelocity = new int[MAX_NUMBER_OF_DINGUSES];

    int[] lastBounceArray = new int[MAX_NUMBER_OF_DINGUSES];

    int NUMBER_OF_DINGUSES_ANIMATION = 7;
    HashSet indicesToAnimate = new HashSet(NUMBER_OF_DINGUSES_ANIMATION);

    int numberOfCircles;
    int numberOfCrowns;
    int numberOfHourglasses;
    int numberOfRectangles;
    int numberOfTrees;
    

    static boolean animationOn = false;
    /**
     * Create a new painting.
     */
    public Painting() {
        setPreferredSize(new Dimension(800, 450)); // make panel 800 by 450 pixels.
        // ...
    }

    @Override
    protected void paintComponent(Graphics g) { // draw all your shapes
        super.paintComponent(g); // clears the panel
        // draw all shapes
        

        for (Dingus shape: shapes){ 
            shape.draw(g);
        }

        if (animationOn) {
            int k = 0;
            Boolean bounce = false;
            int lastBounce = 0;
            for (Dingus shape: shapes){ 

                if (indicesToAnimate.contains(k)) {
                    if (shape instanceof HourglassDingus || shape instanceof CrownDingus) {
                        for (int i = 0; i < shape.npoints; i++) {
                            if (shape.xp[i] > 800) {
                                bounce = true;
                                lastBounce = 1;
                            } else if (shape.yp[i] > 450) {
                                bounce = true;
                                lastBounce = 2;
                            } else if (shape.xp[i] < 0) {
                                bounce = true;
                                lastBounce = 3;
                            } else if (shape.yp[i] < 0) {
                                bounce = true;
                                lastBounce = 4;
                            }
                            shape.xp[i] += xvelocity[k];
                            shape.yp[i] += yvelocity[k];
                        }
                        
                        
                    } else {
                        if (shape.x + shape.colistionWidth > 800) {
                            bounce = true;
                            lastBounce = 1;
                        } else if (shape.y + shape.colistionWidth > 450) {
                            bounce = true;
                            lastBounce = 2;
                        } else if (shape.x - shape.colistionWidth < 0) {
                            bounce = true;
                            lastBounce = 3;
                        } else if (shape.y - shape.colistionWidth < 0) {
                            bounce = true;
                            lastBounce = 4;
                        }
                        shape.x += xvelocity[k];
                        shape.y += yvelocity[k];
                    }
                    if (bounce && lastBounceArray[k] != lastBounce) {
                       // bounceDingus(k, lastBounce);
                        bounce = false;
                        lastBounceArray[k] = lastBounce;
                    }
                }
                k++;
            }

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }

    }

    /**
     * Reaction to button press.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Regenerate".equals(e.getActionCommand())) {
            regenerate();
            repaint();
        } else if ("Recolor".equals(e.getActionCommand())) {
            recolor();
            repaint();
        } else if ("Start Animation".equals(e.getActionCommand())) {
            startAnimation();
        } else if ("Stop Animation".equals(e.getActionCommand())) {
            stopAnimation();
        } else { // screenshot
            saveScreenshot(this, filename + current++); // ++ to show of compact code :-)
        }
    }

    /**
     * Regenerate this painting.
     */
    void regenerate() {
        numberOfRegenerates++; // do not change

        // clear the shapes list
        // TODO
        shapes = new ArrayList<Dingus>();
        // create random shapes
        numberOfCircles = random.nextInt(4) + 2;
        numberOfCrowns = random.nextInt(4) + 2;
        numberOfTrees = random.nextInt(4) + 2;
        numberOfRectangles = random.nextInt(4) + 2;
        numberOfHourglasses = random.nextInt(4) + 2;
        for (int i = 0; i<numberOfCircles; i++) {
            
            shapes.add(new CircleDingus((int) getSize().getWidth(), (int) getSize().getHeight()));
            
        }
        for (int i = 0; i<numberOfCrowns; i++) {
            shapes.add(new CrownDingus((int) getSize().getWidth(), (int) getSize().getHeight()));
            
        }
        for (int i = 0; i<numberOfTrees; i++) {
            shapes.add(new TreeDingus((int) getSize().getWidth(), (int) getSize().getHeight()));
            
        }
        for (int i = 0; i<numberOfRectangles; i++) {
            shapes.add(new RectangleDingus((int) getSize().getWidth(), (int) getSize().getHeight()));
            
        }
        for (int i = 0; i<numberOfHourglasses; i++) {
            shapes.add(new HourglassDingus((int) getSize().getWidth(), (int) getSize().getHeight()));
            
        }   
        
        numberOfDinguses = numberOfCircles + numberOfCrowns + numberOfHourglasses + numberOfRectangles + numberOfTrees;
        
    }

    void recolor() {
        numberOfRecoloration++; 

        for (Dingus shape: shapes){ 
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();
            Color newColor = new Color(r, g, b);
            shape.color = newColor;
        }
    }

    void generateVelocities() {
        int yrand;
        int xrand;
        int ysign;
        int xsign;
        for (int i = 0; i < numberOfDinguses; i++) {
            
                ysign = random.nextInt(2);
                xsign = random.nextInt(2);
                if (ysign == 0) {
                    ysign = -1;
                }
                if (xsign == 0) {
                    xsign = -1;
                }
            
            
            yrand = (random.nextInt(6) + 1) * ysign;
            xrand = (random.nextInt(6) + 1) * xsign;
            xvelocity[i] = xrand;
            yvelocity[i] = yrand;
        }
    }

    void bounceDingus(int indexOfDingus, int lastBounce) {
        int sign = random.nextInt(2);
        if (sign == 0) {
            sign = -1;
        }
        int rand = (random.nextInt(3) + 1) * sign;
        
        if (lastBounce == 1 || lastBounce == 3) {
            xvelocity[indexOfDingus] *= -1;
            yvelocity[indexOfDingus] *= -1;
        } else {
            yvelocity[indexOfDingus] *= -1;
            xvelocity[indexOfDingus] *= -1;
        }

        
    }

    void startAnimation() {
        if (!animationOn) {
            int rand;
            indicesToAnimate.clear();
            for (int i = 0; i < NUMBER_OF_DINGUSES_ANIMATION; i++) {
                do {
                    rand = random.nextInt(numberOfDinguses);
                } while (indicesToAnimate.contains(rand));
                
                indicesToAnimate.add(rand);
            }
            if (!animationOn) {
                generateVelocities();
            }
            animationOn = true;
            
            repaint();
        }

    }

    void stopAnimation() {
        animationOn = false;
    }

    /**
     * Saves a screenshot of this painting to disk.
     * 
     * Note. This action will override existing files!
     *
     * @param component Component to be saved
     * @param name      filename of the screenshot, followed by a sequence number
     * 
     */
    void saveScreenshot(Component component, String name) {
        int numberOfScreenshots = 1;
        if (animationOn) {
            numberOfScreenshots = 5;
        }
        String nameCopy = new String(name);

        for (int i = 0; i < numberOfScreenshots; i++) {
            if (animationOn) {
                name = nameCopy + "anim" + i;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // minus 1 because the initial picture should not count
            String randomInfo = "" + SEED + "+" + (numberOfRegenerates - 1);
            System.out.println(SwingUtilities.isEventDispatchThread());
            BufferedImage image = new BufferedImage(
                    component.getWidth(),
                    component.getHeight(),
                    BufferedImage.TYPE_INT_RGB);

            // call the Component's paint method, using
            // the Graphics object of the image.
            Graphics graphics = image.getGraphics();
            component.paint(graphics); // alternately use .printAll(..)
            graphics.drawString(randomInfo, 0, component.getHeight());

            try {
                ImageIO.write(image, "PNG", new File(name + ".png"));
                System.out.println("Saved screenshot as " + name);
            } catch (IOException e) {
                System.out.println("Saving screenshot failed: " + e);
            }
        }
    }
    // void saveScreenshot(Component component, String name) {
    //     // minus 1 because the initial picture should not count
    //     String randomInfo = "" + SEED + "+" + (numberOfRegenerates - 1);
    //     System.out.println(SwingUtilities.isEventDispatchThread());
    //     BufferedImage image = new BufferedImage(
    //             component.getWidth(),
    //             component.getHeight(),
    //             BufferedImage.TYPE_INT_RGB);

    //     // call the Component's paint method, using
    //     // the Graphics object of the image.
    //     Graphics graphics = image.getGraphics();
    //     component.paint(graphics); // alternately use .printAll(..)
    //     graphics.drawString(randomInfo, 0, component.getHeight());

    //     try {
    //         ImageIO.write(image, "PNG", new File(name + ".png"));
    //         System.out.println("Saved screenshot as " + name);
    //     } catch (IOException e) {
    //         System.out.println("Saving screenshot failed: " + e);
    //     }
    // }

}