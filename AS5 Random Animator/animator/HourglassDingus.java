/**
 * HourglassDingus represents the shape of the hourglass.
 * 
 * @author Tomasz Soróbka
 * @id 1808982
 */

import java.awt.Graphics;

class HourglassDingus extends Dingus {

    private boolean filled; // true: filled; false: outline
    
    private int rate;
    private double angle = random.nextDouble() * 360;

    public HourglassDingus(int maxX, int maxY) {
        // initialize Dingus properties
        super(maxX, maxY);

        // initialize the number of vertices of the hourglass shape
        nPoints = 5;
        // initialize the arrays storing the x and y coordinates
        xp = new int[nPoints];
        yp = new int[nPoints];
        
        // rate is a variable that stores the relative scale of the shape
        rate = random.nextInt(maxX / 60);

        //for each vertex adjust the coordinate of the xp and yp arrays by the rate
        // so the relative distance between the vertices is maintained
        for (int i = 0; i < nPoints; i++) {
            xp[i] = x;
            yp[i] = y;

            if (i == 1 || i == 3) {
                xp[i] += 10 * rate;
            }

            if (i == 2 || i == 3) {
                yp[i] += 10 * rate;
            }
        }
        // adjust the values for the x and y coordinates for the rotation of the shape
        for (int i = 1; i < nPoints - 1; i++) {
            double angleRad = ((angle/180) * Math.PI);
            double cosAngle = Math.cos(angleRad);
            double sinAngle = Math.sin(angleRad);
            double dx = (xp[i] - x);
            double dy = (yp[i] - y);

            // rotate the shape
            xp[i] = x + (int) (dx * cosAngle - dy * sinAngle);
            yp[i] = y + (int) (dx * sinAngle + dy * cosAngle);

        }
        // randomly assign whether the shape should be filled or not
        filled = random.nextBoolean();  
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
