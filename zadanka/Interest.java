import java.util.Scanner;

 class Interest {
    Scanner sc = new Scanner(System.in);

    public void calcInterest() {
        double balance;
        double rate, tax; // percentage
        System.out.println("Input balance ");
        balance = sc.nextDouble();
        System.out.println("Give interest rate as percentage ");
        rate = sc.nextDouble();
        System.out.println("Give tax as percentage ");
        tax = sc.nextDouble();
        balance = ((balance * rate / 100.0) + balance);
        System.out.println("After a year you have: " + balance);

        System.out.println("After a year you have to pay: " +
            (balance * tax / 100.0));
    }

    public static void main(String[] args) {
        (new Interest() ).calcInterest();
    }
 }