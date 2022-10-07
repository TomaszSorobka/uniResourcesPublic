import java.util.Scanner;

public class AgeFinder {

    public void findAge() {
        Scanner scan = new Scanner(System.in);
        int birthdate, currentdate, age;
        System.out.println("Enter date of birth: ");
        birthdate = scan.nextInt();
        System.out.println("Enter current date: ");
        currentdate = scan.nextInt();

        age = currentdate/10000 - birthdate/10000;
        if (currentdate%10000 < birthdate%10000)
            age--;
        System.out.println("You are " + age + " years old.");
    }
    public static void main(String[] args) {
        (new AgeFinder()).findAge();

    }
}
