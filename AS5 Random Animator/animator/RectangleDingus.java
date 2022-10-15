/**
 * RectangleDingus is an example of a very simple Dingus.
 * 
 * @author Tomasz Soróbka
 * @id 1808982
 */

import java.awt.Graphics;

class RectangleDingus extends Dingus {
    protected int width;
    protected int height;
    protected boolean filled; //true: filled, false: outline

    // a subclass constructor
    public RectangleDingus(int maxX, int maxY) {
        // intialize randomly the Dingus properties, i.e., position and color
        super(maxX, maxY);
        
        // initialize randomly the CircleDingus properties, i.e., radius and filledness
        width = random.nextInt(maxX / 4) + 3;
        height = random.nextInt(maxY / 4) + 3;
        filled = random.nextBoolean();

        colisionWidth = width;
        colisionHeightTop = height / 2;
        colisionHeightBot = height / 2;
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        if (filled) {
            g.fillRect(x, y, width, height);
        } else {
            g.drawRect(x, y, width, height);
        }
    }
}
