/**
 * CrownDingus represents the shape of the crown.
 * 
 * @author Tomasz Soróbka
 * @id 1808982
 */

import java.awt.Graphics;

class CrownDingus extends Dingus {

    private boolean filled; // true: filled; false: outline
    private int rate;
    private double angle = random.nextDouble() * 360;

    //a subclass constructor
    public CrownDingus(int maxX, int maxY) {
        
        // initialize Dingus properties
        super(maxX, maxY);
        // declare the size of the coordinates array
        nPoints = 8;
        xp = new int[nPoints];
        yp = new int[nPoints];
        
        // rate is a variable that stores the relative scale of the shape
        rate = random.nextInt(maxX / 60);
        for (int i = 0; i < nPoints; i++) {
            xp[i] = x;
            yp[i] = y;
        }

        // initialize the x and y coordinates of the crown shapes and adjust it with rate
        // so the relative distances between the vertices are maintained
        xp[1] += 5 * rate; 
        yp[1] += 10 * rate;
        xp[2] += 10 * rate; 
        xp[3] += 15 * rate; 
        yp[3] += 10 * rate;
        xp[4] += 20 * rate;
        xp[5] += 18 * rate; 
        yp[5] += 18 * rate;
        xp[6] += 2 * rate; 
        yp[6] += 18 * rate;
        
        // randomly assign whether the shape should be filled or not
        filled = random.nextBoolean();  
        // adjust the values for the x and y coordinates for the rotation of the shape
        for (int i = 1; i < nPoints - 1; i++) {
            double angleRad = ((angle / 180) * Math.PI);
            double cosAngle = Math.cos(angleRad);
            double sinAngle = Math.sin(angleRad);
            double dx = (xp[i] - x);
            double dy = (yp[i] - y);

            // rotate the shape
            xp[i] = x + (int) (dx * cosAngle - dy * sinAngle);
            yp[i] = y + (int) (dx * sinAngle + dy * cosAngle);
        }
    }


    @Override
    void draw(Graphics g) {
        g.setColor(color);
        if (filled) {
            g.fillPolygon(xp, yp, nPoints);
        } else {
            g.drawPolygon(xp, yp, nPoints);
        }

    }
}
