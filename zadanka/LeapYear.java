import java.util.Scanner;

public class LeapYear {
    
    public static void main(String[] args) {
        boolean leapYear = false;
        Scanner scan = new Scanner(System.in);
        int year = scan.nextInt();
        if (year%4 == 0 && (year%400 == 0 || year%100 != 0))
            leapYear = true;

        System.out.println("The statement year " + year + " is a leap year is " + leapYear);
    }
}
