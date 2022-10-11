import java.awt.Graphics;

/**
 * CircleDingus is an example of a very simple Dingus.
 * 
 * @author NAME
 * @id ID
 * @author NAME
 * @id ID
 */
class CircleDingus extends Dingus {
    protected int radius;
    protected boolean filled; // true: filled, false: outline

    /**
     * Create and initialize a new CircleDingus.
     * 
     * @param maxX upper bound for the x coordinate of the position
     * @param maxY upper bound for the y coordinate of the position
     */
    public CircleDingus(int maxX, int maxY) {
        // intialize randomly the Dingus properties, i.e., position and color
        super(maxX, maxY);

        // initialize randomly the CircleDingus properties, i.e., radius and filledness
        radius = random.nextInt(maxX / 4);
        filled = random.nextBoolean();
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        if (filled) {
            g.fillArc(x, y, radius, radius, 0, 360);
        } else {
            g.drawArc(x, y, radius, radius, 0, 360);
        }
    }
}
