/* to do 
 * 1.bouncing physics (random angles, direction, etc. initiated once every press of the start Animation)
 * 2.random number of shpaes
 * 3.screenshotting (if animationON == true, do 5 screenshots interval 50 miliseconds)
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
            for (Dingus shape: shapes){ 
                shape.x += 1;
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
        for (int i = 0; i<10; i++) {
            shapes.add(new TreeDingus((int) getSize().getWidth(), (int) getSize().getHeight()));
            shapes.add(new CircleDingus((int) getSize().getWidth(), (int) getSize().getHeight()));
            shapes.add(new RectangleDingus((int) getSize().getWidth(), (int) getSize().getHeight()));
            shapes.add(new HourglassDingus((int) getSize().getWidth(), (int) getSize().getHeight()));
            shapes.add(new CrownDingus((int) getSize().getWidth(), (int) getSize().getHeight()));
        }
        // TODO
    }

    void recolor() {
        numberOfRecoloration++; // do not change

        for (Dingus shape: shapes){ 
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();
            Color newColor = new Color(r, g, b);
            shape.color = newColor;
        }
    }

    void startAnimation() {
        animationOn = true;

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