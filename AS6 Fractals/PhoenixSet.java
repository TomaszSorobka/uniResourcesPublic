package Fractals;

import Settings.ColorArrSetting;
import Settings.ColorSetting;
import Settings.DoubleSetting;
import Settings.IntSetting;
import Settings.Setting;

/**
 * Phoenix fractal class 
 * fractal formula z(n+1) = z(n)^2 + c * z(n) + p*z(n-1) 
 */
public class PhoenixSet /* extends */ {

    /*
     * This constant is only used to test the fractal. 
     * Later, it should be replaced by the user or 
     * a parent class
     */
    private final int MAXITERATIONS = 100;

    // initial fractal constants
    private double C = 0.56666;
    private double P = -0.5;

    PhoenixSet() {
        /* TODO */
    }

    int iterate(double x, double y) {

        /*
         * Complex parts of z point gets the pixel (x,y)
         * Stimulate current z point: z(n)
         */
        double zReal = y;
        double zImag = x;

        /*
         * To save the previous z point: z(n-1)
         */
        double zPrevRe = 0;
        double zPrevIm = 0;

        /* 
         * Keep iterating till the boundary condition is reached
         */
        int n = 0;
        while (n < MAXITERATIONS && zReal * zReal + zImag * zImag < 4.0) {
            double tmpReal = zReal;
            double tmpImag = zImag;

            zReal = zReal * zReal - zImag * zImag + C + P * zPrevRe;
            zImag = 2 * tmpReal * tmpImag + P * zPrevIm;

            zPrevRe = tmpReal;
            zPrevIm = tmpImag;
            n++;
        }

        return n;
    }

    /*
     * More methods to add if necessary
     */

}
