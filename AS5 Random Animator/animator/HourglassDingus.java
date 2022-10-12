/**
 * CircleDingus is an example of a very simple Dingus.
 * 
 * @author Tomasz Soróbka
 * @id 1808982
 */

import java.awt.Graphics;
//import java.awt.Graphics2D;



class HourglassDingus extends Dingus {

    private boolean filled; // true: filled; false: outline
    
    private int rate;
    private double angle = random.nextDouble()*360;


    public HourglassDingus(int maxX, int maxY) {
        // initialize Dingus properties
        super(maxX, maxY);
        xp = new int[5];
        yp = new int[5];
        npoints = 5;
        rate = random.nextInt(maxX/100);
         for (int i = 0; i<5; i++) {
             xp[i] = x;
             yp[i] = y;

            if (i == 1 || i == 3)
                xp[i] += 10*rate;
            if (i == 2 || i == 3)
                yp[i] += 10*rate;
        }

        for (int i = 1; i<4; i++) {
            double angleRad = ((angle/180)*Math.PI);
            double cosAngle = Math.cos(angleRad);
            double sinAngle = Math.sin(angleRad);
            double dx = (xp[i]-x);
            double dy = (yp[i]-y);

            xp[i] = x + (int) (dx*cosAngle-dy*sinAngle);
            yp[i] = y + (int) (dx*sinAngle+dy*cosAngle);

        }

        filled = random.nextBoolean();  
    }

    @Override
    void draw(Graphics g) {
        // Graphics2D g2d = (Graphics2D) g;
        // g2d.setColor(color);
        // g2d.rotate(Math.toRadians(angle));
        g.setColor(color);

        if (filled) {
            g.fillPolygon(xp, yp, npoints);
        } else {
            g.drawPolygon(xp, yp, npoints);
        }

    }
}
