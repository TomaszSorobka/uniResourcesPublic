import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Dingus represents an arbitraty shape.
 * 
 * @author NAME
 * @id ID
 * @author NAME
 * @id ID
 */
abstract class Dingus {
    /**
     * Random generator to be used by all subclasses of Dingus.
     * DON'T CHANGE
     */
    Random random = Painting.RANDOM;

    /** 
     * Postion of the shape (upper left corner).
     *
     */
    protected int x;
    protected int y;

    /** 
     * Color used for drawing this shape.
     */
    protected Color color;

    /** 
     * Maximal coordinates; drawing area is (0,0)- (maxX,maxY).
     */
    int maxX;
    int maxY;

    /**
     * Initialize color and position to random values.
     *
     * @param maxX upper bound for the x coordinate of the position
     * @param maxY upper bound for the y coordinate of the position
     */
    public Dingus(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;

        // Initialize to a random position
        x = random.nextInt(maxX);
        y = random.nextInt(maxY);

        // Initialize to a random color
        // TODO
    }

    abstract void draw(Graphics g);
}