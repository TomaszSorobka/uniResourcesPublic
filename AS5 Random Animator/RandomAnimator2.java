import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/** 
 * Main class for the homework assignment Random Animator.
 * 
 *  
 * TODO:
 *
 * @author NAME
 * @id ID
 * @author NAME
 * @id ID
 */
public class RandomAnimator {
    JFrame frame;
    Painting painting; // panel that provides the random painting
    JButton regenerateButton;
    JButton shotButton;

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

                // painting provides reaction to buttonclick
                regenerateButton.addActionListener(painting); 

                frame.add(regenerateButton, BorderLayout.SOUTH);
                shotButton = new JButton("Screenshot");
                shotButton.addActionListener(painting);
                frame.add(shotButton, BorderLayout.NORTH);
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