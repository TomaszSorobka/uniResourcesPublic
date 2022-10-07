import java.util.Scanner;

 class InterestWithChoice {
    Scanner sc = new Scanner(System.in);

    public void calcInterest() {
        double balance, penalty = -0.5, interest = 2.5;
        System.out.println("Input balance ");
        balance = sc.nextDouble();
        if (balance >= 10000)
            interest+=0.5;

        if (balance > 0)
            balance += (balance * interest / 100);
        else 
            balance -= (balance * penalty / 100);
        System.out.println("Your balance after a year is " + balance);
           
    }

    public static void main(String[] args) {
        (new InterestWithChoice()).calcInterest();
    }
 }