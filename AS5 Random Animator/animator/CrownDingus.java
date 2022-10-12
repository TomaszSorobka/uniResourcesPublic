/**
 * CircleDingus is an example of a very simple Dingus.
 * 
 * @author Tomasz Soróbka
 * @id 1808982
 */

import java.awt.Graphics;




class CrownDingus extends Dingus {

    private boolean filled; // true: filled; false: outline
    // public int[] xp = new int[8];
    // public int[] yp = new int[8];
    // public int npoints = 8;
    private int rate;
    private double angle = random.nextDouble()*360;

    public CrownDingus(int maxX, int maxY) {
        
        // initialize Dingus properties
        super(maxX, maxY);
        xp = new int[8];
        yp = new int[8];
        npoints = 8;
        rate = random.nextInt(maxX/100);
         for (int i = 0; i<8; i++) {
             xp[i] = x;
             yp[i] = y;
         }
        xp[1] += 5*rate; yp[1] += 10*rate;
        xp[2] += 10*rate; 
        xp[3] += 15*rate; yp[3] += 10*rate;
        xp[4] += 20*rate;
        xp[5] += 18*rate; yp[5] += 18*rate;
        xp[6] += 2*rate; yp[6] += 18*rate;
        
        filled = random.nextBoolean();  

        for (int i = 1; i<7; i++) {
            double angleRad = ((angle/180)*Math.PI);
            double cosAngle = Math.cos(angleRad);
            double sinAngle = Math.sin(angleRad);
            double dx = (xp[i]-x);
            double dy = (yp[i]-y);

            xp[i] = x + (int) (dx*cosAngle-dy*sinAngle);
            yp[i] = y + (int) (dx*sinAngle+dy*cosAngle);

        }
    }


    @Override
    void draw(Graphics g) {
        g.setColor(color);
        if (filled) {
            g.fillPolygon(xp, yp, npoints);
        } else {
            g.drawPolygon(xp, yp, npoints);
        }

    }
}
