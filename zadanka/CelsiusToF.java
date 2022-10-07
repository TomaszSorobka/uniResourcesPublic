import java.util.Scanner;

 class CelsiustoF {
    Scanner sc = new Scanner(System.in);

    public void convertDegrees() {
        double fahrenheit, celsius;
        System.out.println("Input fahrenheit degrees ");
        fahrenheit = sc.nextDouble();
        celsius = (fahrenheit - 32) * 5 / 9;

        System.out.println(fahrenheit + " degrees Fahrenheit = " + celsius + " degrees Celsius");
           
    }

    public static void main(String[] args) {
        (new CelsiustoF() ).convertDegrees();
    }
 }