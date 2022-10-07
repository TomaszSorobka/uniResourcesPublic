import java.util.Scanner;
import java.lang.Math;

class Circle {
    Scanner sc = new Scanner(System.in);

    public double pythagorean(double x1, double y1, double x2, double y2) {
        double distance = Math.sqrt(Math.pow(x2-x1,2.0)+Math.pow(y2-y1,2.0));
        return distance;
    }

    public void evalCircle() {
        double x1, y1, radius1, x2, y2, radius2, x, y;
        boolean inradius1 = false, inradius2 = false;

        x1 = sc.nextDouble();
        y1 = sc.nextDouble();
        radius1 = sc.nextDouble();
        x2 = sc.nextDouble();
        y2 = sc.nextDouble();
        radius2 = sc.nextDouble();
        x = sc.nextDouble();
        y = sc.nextDouble();

        if (pythagorean(x1, y1, x, y) <= radius1)
            inradius1 = true;
        
        if (pythagorean(x2, y2, x, y) <= radius2)
            inradius2 = true;

        if (radius1 < 0.0 || radius2 < 0.0)
            System.out.println("input error");
        else if (inradius1 && !inradius2)
            System.out.println("The point lies in the first circle");
        else if (!inradius1 && inradius2)
            System.out.println("The point lies in the second circle");
        else if (inradius1 && inradius2)
            System.out.println("The point lies in both circles");
        else 
            System.out.println("The point does not lie in either circle");
    }



    public static void main(String[] args) {
        (new Circle()).evalCircle();
    }
}