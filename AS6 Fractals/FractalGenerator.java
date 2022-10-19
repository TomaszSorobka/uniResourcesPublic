import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FractalGenerator {
    JFrame frame;
    Painting fractalPainting;
    JMenuBar menuBar;
 
    FractalGenerator() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                menuBar = new JMenuBar();
                fractalPainting = new Painting();
                frame.add(fractalPainting, BorderLayout.CENTER);

                frame = new JFrame("Computer Assisted Fractal Generator");
                frame.setJMenuBar(menuBar);
                frame.pack();
                frame.setVisible(true);
            }
        }); 
    }
    public static void main(String[] arg) {
        new FractalGenerator();
    }
}
