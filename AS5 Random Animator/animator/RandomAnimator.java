import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;

/** 
 * Main class for the homework assignment Random Animator.
 * 
 * After running the program, a window will open. 
 * The window contains the paining - random shapes generated in the middle
 * 5 buttons: regenerate, screenshot, recolor, start animation, stop animation
 * 
 * Use the 5 buttons to make the program perform different tasks and see the results.
 *
 * @author Tomasz Soróbka
 * @id 1808982
 */
public class RandomAnimator {
    JFrame frame;
    Painting painting; // panel that provides the random painting
    JButton regenerateButton;
    JButton shotButton;
    JButton recolorButton;
    JButton startAnimationButton;
    JButton stopAnimationButton;
    //initialize a new JPanel to place more than one button on one side of the main window
    JPanel east = new JPanel(new BorderLayout());

    /**
     * Create a new instance of the RandomAnimator application.
     */
    RandomAnimator() {
        // invokeLater: preferred way to create components
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                painting = new Painting();
                frame = new JFrame("Computer Assisted Random Animator");
                frame.add(painting, BorderLayout.CENTER);
                regenerateButton = new JButton("Regenerate");
                startAnimationButton = new JButton("Start Animation");

                // painting provides reaction to buttonclick
                regenerateButton.addActionListener(painting); 

                frame.add(regenerateButton, BorderLayout.SOUTH);
                shotButton = new JButton("Screenshot");
                shotButton.addActionListener(painting);
                frame.add(shotButton, BorderLayout.NORTH);

                //add the recolor button on the west side of the window
                recolorButton = new JButton("Recolor");
                recolorButton.addActionListener(painting);
                frame.add(recolorButton, BorderLayout.WEST);

                // create and add the startAnimation button to a new JPanel called east
                startAnimationButton = new JButton("Start Animation");
                startAnimationButton.addActionListener(painting);
                east.add(startAnimationButton, BorderLayout.NORTH);
                // create and add the stopAnimation button to a new JPanel called east
                stopAnimationButton = new JButton("Stop Animation");
                stopAnimationButton.addActionListener(painting);
                east.add(stopAnimationButton, BorderLayout.SOUTH);
                // add the east JPanel to the frame on the east side of the window
                frame.add(east, BorderLayout.EAST);
                frame.pack();
                painting.regenerate(); // can be done here since painting has a size!
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] arg) {
        new RandomAnimator();
    }
}