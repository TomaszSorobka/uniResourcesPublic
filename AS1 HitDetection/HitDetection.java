import java.util.Scanner;

/**
 * Detects if a point hits any of two circles.
 * 
 * Usage:
 * After the start of the program, input 8 floating point numbers in the following order:
 * 1. x-coordinate of the first circle, 2. y-coordinate of the first circle,
 * 3. radius of the first circle, 4. x-coordinate of the second circle,
 * 5. y-coordinate of the second circle, 6. radius of the second circle,
 * 7. x-coordinate of the point on the graph, 8. y-coordinate of the point on the graph.
 * After inputting the values, press enter. The program will output the message whether the point
 * hits the first circle, the second circle, both, neither or will inform of an input error
 * depending on the inputted coordinates. After the message is outputted, the program stops running.
 * 
 * @author Tomasz Soróbka
 * @ID 1808982
 */

class HitDetection {
    Scanner sc = new Scanner(System.in);

    /*
    * Calculate the distance between the center of a circle and a point on the graph
    *
    * @param xCircle x-coordinate of the center of a circle 
    * @param yCircle y-coordinate of the center of a circle 
    * @param xPoint x-coordinate of the point on the graph
    * @param yPoint y-coordinate of the point on the graph 
    */
    public double calculateDistance(double xCircle, double yCircle, double xPoint, double yPoint) {
        double distance = Math.sqrt(Math.pow(xPoint - xCircle, 2.0)
            + Math.pow(yPoint - yCircle, 2.0));
        return distance;
    }

    public void run() {

        boolean inCircle1 = false;
        boolean inCircle2 = false;

        // 8 floating point numbers (coordinates and radiuses) input
        double xCircle1 = sc.nextDouble();
        double yCircle1 = sc.nextDouble();
        double radius1 = sc.nextDouble();
        double xCircle2 = sc.nextDouble();
        double yCircle2 = sc.nextDouble();
        double radius2 = sc.nextDouble();
        double xPoint = sc.nextDouble();
        double yPoint = sc.nextDouble();

        // Calculate whether the point hits the first circle 
        // and, if yes, set the boolean inCircle1 variable to true
        if (calculateDistance(xCircle1, yCircle1, xPoint, yPoint) <= radius1) {
            inCircle1 = true;
        }

        // Calculate whether the point hits the second circle 
        // and, if yes, set the boolean inCircle2 variable to true
        if (calculateDistance(xCircle2, yCircle2, xPoint, yPoint) <= radius2) {
            inCircle2 = true;
        }

        /* Depending on the coordinates and values of inCircle1 and inCircle2
        * the program outputs a message whether the point on the graph
        * hits the first circle, the second circle, both, neither,
        * or if there is an input error.
        */
        if (radius1 < 0.0 || radius2 < 0.0) {
            System.out.println("input error");
        } else if (inCircle1 && !inCircle2) {
            System.out.println("The point hits the first circle");
        } else if (!inCircle1 && inCircle2) {
            System.out.println("The point hits the second circle");
        } else if (inCircle1 && inCircle2) {
            System.out.println("The point hits both circles");
        } else {
            System.out.println("The point does not hit either circle");
        }
    }

    public static void main(String[] args) {
        (new HitDetection()).run();
    }
}