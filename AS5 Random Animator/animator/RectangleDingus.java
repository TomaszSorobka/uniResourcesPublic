/**
 * CircleDingus is an example of a very simple Dingus.
 * 
 * @author Tomasz Soróbka
 * @id 1808982
 */

import java.awt.Graphics;

class RectangleDingus extends Dingus {
    protected int width, height;
    protected boolean filled; //true: filled, false: outline

    public RectangleDingus(int maxX, int maxY) {
        // intialize randomly the Dingus properties, i.e., position and color
        super(maxX, maxY);
        // initialize randomly the CircleDingus properties, i.e., radius and filledness
        width = random.nextInt(maxX/4);
        height = random.nextInt(maxY/4);
        filled = random.nextBoolean();
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        if (filled)
            g.fillRect(x, y, width, height);
        else
            g.drawRect(x, y, width, height);
    }
}
