import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;

/** 
 * Main class for the homework assignment Random Animator.
 * 
 *  
 * TODO:
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
                recolorButton = new JButton("Recolor");
                recolorButton.addActionListener(painting);
                frame.add(recolorButton, BorderLayout.WEST);

                startAnimationButton = new JButton("Start Animation");
                startAnimationButton.addActionListener(painting);
                east.add(startAnimationButton, BorderLayout.NORTH);
                stopAnimationButton = new JButton("Stop Animation");
                stopAnimationButton.addActionListener(painting);
                east.add(stopAnimationButton, BorderLayout.SOUTH);

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