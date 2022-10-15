import java.awt.Color;
import java.awt.Component;
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

    // ---- Screenshots ----
    // DON'T CHANGE the following two lines:
    char current = '0';
    String filename = "randomshot_"; // prefix

    /*---- Dinguses ----*/
    ArrayList<Dingus> shapes;
    // ...

    int numberOfDinguses;
    static final int MAX_NUMBER_OF_DINGUSES = 40;

    // initialize two arrays that will store the random x and y velocities of the dinguses
    // that are animated
    int[] xVelocity = new int[MAX_NUMBER_OF_DINGUSES];
    int[] yVelocity = new int[MAX_NUMBER_OF_DINGUSES];

    // stores what was the last bounce of each dingus, 
    //so it does not bounce indifinitely off of the same side 
    int[] lastBounceArray = new int[MAX_NUMBER_OF_DINGUSES];

    int numberOfAnimationDinguses;
    // a hashset to store the indices of the dinguses to be animated
    HashSet<Integer> indicesToAnimate;

    // variables used to store the numbers of each kind of shape
    int numberOfCircles;
    int numberOfCrowns;
    int numberOfHourglasses;
    int numberOfRectangles;
    int numberOfTrees;
    
    // a boolean variable that stores the information whether the animation is live or not
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
        

        for (Dingus shape: shapes) { 
            shape.draw(g);
        }

        // the code below is responsible for the animation of the dinguses
        // it is executed only when the Start Animation button was pressed
        if (animationOn) {
            // initialize and declare the help variables
            int k = 0;
            Boolean bounce = false;
            int lastBounce = 0;

            // a loop that goes through every shape in the shapes ArrayList
            for (Dingus shape: shapes) { 

                // if the dingus with index k is in the list of dinguses that are to be animated,
                // proceed
                if (indicesToAnimate.contains(k)) {

                    // if the shape is an hourglass or a crown, use different code than the rest
                    // as the coordinate variables are stored differently
                    if (shape instanceof HourglassDingus || shape instanceof CrownDingus) {

                        // check for each vertex of the shape if it is out of bounds, 
                        //if yes, set the value of the bounce variable to true
                        // and indicate which side the dingus bounced off of 
                        for (int i = 0; i < shape.nPoints; i++) {
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
                            shape.xp[i] += xVelocity[k];
                            shape.yp[i] += yVelocity[k];
                        }
                        
                        // repeat the same steps for the rest of the shapes
                    } else {
                        if (shape.x + shape.colisionWidth > 800) {
                            bounce = true;
                            lastBounce = 1;
                        } else if (shape.y + shape.colisionHeightBot > 450) {
                            bounce = true;
                            lastBounce = 2;
                        } else if (shape.x - shape.colisionWidth < 0) {
                            bounce = true;
                            lastBounce = 3;
                        } else if (shape.y - shape.colisionHeightTop < 0) {
                            bounce = true;
                            lastBounce = 4;
                        }
                        shape.x += xVelocity[k];
                        shape.y += yVelocity[k];
                    }

                    // if the shape has bounced off of the wall, execute this code
                    if (bounce && lastBounceArray[k] != lastBounce) {
                        //call the bounceDingus method
                        bounceDingus(k, lastBounce);
                        bounce = false;
                        lastBounceArray[k] = lastBounce;
                    }
                }
                bounce = false;
                k++;
            }

            // wait 40 milliseconds between each frame of the animation
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // call the repaint method to generate the next frame of the animation
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
            // added the recolor button
        } else if ("Recolor".equals(e.getActionCommand())) {
            recolor();
            repaint();
            // added the start animation button
        } else if ("Start Animation".equals(e.getActionCommand())) {
            startAnimation();
            // added the stop animation button
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

        // generate the random number of shapes of each kind; 
        //the minimum is 2 for each of the 5 kinds of shapes
        // which results in the least amount of shapes of 10;
        // the maximum is 6 for each of the 5 kinds of shapes,
        // which results in the maximum amount of shapes of 30;
        numberOfCircles = random.nextInt(7) + 2;
        numberOfCrowns = random.nextInt(7) + 2;
        numberOfTrees = random.nextInt(7) + 2;
        numberOfRectangles = random.nextInt(7) + 2;
        numberOfHourglasses = random.nextInt(7) + 2;

        // Add the shapes to the ArrayList containing all the shapes
        for (int i = 0; i < numberOfCircles; i++) {
            shapes.add(new CircleDingus((int) getSize().getWidth(), (int) getSize().getHeight()));
        }

        for (int i = 0; i < numberOfCrowns; i++) {
            shapes.add(new CrownDingus((int) getSize().getWidth(), (int) getSize().getHeight()));
        }

        for (int i = 0; i < numberOfTrees; i++) {
            shapes.add(new TreeDingus((int) getSize().getWidth(), (int) getSize().getHeight()));
        }

        for (int i = 0; i < numberOfRectangles; i++) {
            shapes.add(new RectangleDingus((int) getSize().getWidth(),
                (int) getSize().getHeight()));
        }

        for (int i = 0; i < numberOfHourglasses; i++) {
            shapes.add(new HourglassDingus((int) getSize().getWidth(),
                (int) getSize().getHeight())); 
        }   
        
        // calculate the total number of dinguses
        numberOfDinguses = numberOfCircles + numberOfCrowns + numberOfHourglasses
            + numberOfRectangles + numberOfTrees;
        
    }

    /*
     * A method which when called creates a new random color for each dingus and assigns
     *  those colors to dinguses
     */
    void recolor() {

        for (Dingus shape: shapes) { 
            // generate random color
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();
            Color newColor = new Color(r, g, b);
            //set the color of the shape to the newColor
            shape.color = newColor;
        }
    }

    // a method that generates random x and y velocities for every dingus 
    void generateVelocities() {
        //variables that will store random values
        int yRand;
        int xRand;
        int ySign;
        int xSign;

        for (int i = 0; i < numberOfDinguses; i++) {
            // for every dingus generate a random number with restrictions and
            // assign it as x velocity of the dingus
            // repeat for the y velocity
            ySign = random.nextInt(2);
            xSign = random.nextInt(2);
            if (ySign == 0) {
                ySign = -1;
            }
            if (xSign == 0) {
                xSign = -1;
            }
            
            yRand = (random.nextInt(6) + 1) * ySign;
            xRand = (random.nextInt(6) + 1) * xSign;
            xVelocity[i] = xRand;
            yVelocity[i] = yRand;
        }
    }

    /* a method that is called when the dingus bounces off of the wall
    * @param indexOfDingus - the index of the dingus that bounced off of the wall
    * @param lastBounce - the value that indicates which wall the dingus bounced off of 
    */
    void bounceDingus(int indexOfDingus, int lastBounce) {
        int sign = random.nextInt(2);
        if (sign == 0) {
            sign = -1;
        }
        int rand = (random.nextInt(6) + 1) * sign;
        
        // check which wall the dingus bounced off of and check the velocity so it will go
        // in the oposite direction
        if (lastBounce == 1 || lastBounce == 3) {
            xVelocity[indexOfDingus] *= -1;
            yVelocity[indexOfDingus] = rand;
        } else {
            yVelocity[indexOfDingus] *= -1;
            xVelocity[indexOfDingus] = rand;
        }

        
    }

    // a method that starts the animation
    void startAnimation() {
        // check if the animation is already on, if yes, do nothing
        if (!animationOn) {
            int rand;
            // generate a random number of dinguses to animate (between 5 and 10)
            numberOfAnimationDinguses = random.nextInt(6) + 5;
            // clear the hashset of previous shapes so new random shapes can be picked 
            indicesToAnimate = new HashSet<Integer>(numberOfAnimationDinguses);
            for (int i = 0; i < numberOfAnimationDinguses; i++) {
                // generate a random index of a dingus up until you generate a unique one
                do {
                    rand = random.nextInt(numberOfDinguses);
                } while (indicesToAnimate.contains(rand));
                
                //add it to the hash set containing the indices of shapes to animate
                indicesToAnimate.add(rand);
                //generate new velocities of the shapes
                generateVelocities();
            }
            animationOn = true;
            repaint();
        }

    }

    // turn off the animation
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

        // when the animation is live, take 5 screenshots instead of one to capture the movement 
        // of the shapes
        if (animationOn) {
            numberOfScreenshots = 5;
        }
        String nameCopy = new String(name);

        for (int i = 0; i < numberOfScreenshots; i++) {

            if (animationOn) {
                // add a "anim" string to the name of the screenshot taken
                // when the animation was live
                name = nameCopy + "anim" + i;
                try {
                    // wait 100 milliseconds between each screenshot so the movement
                    // of the shapes can be captures
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

}