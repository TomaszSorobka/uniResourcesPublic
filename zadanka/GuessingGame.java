import java.util.Scanner;
import java.util.Random;

public class GuessingGame {

    Scanner sc = new Scanner(System.in);
    Random randomGenerator;
    public void run() {
        boolean check= true;
        long seed;
        int guess, counter = 0, code;
        int[] array = new int[7];
        String answer, temp, str = "....................................................................................................";
        System.out.println("Do you want to enter the secret code yourself?");
        answer = sc.nextLine();

        if (answer.equals("yes")) {
            System.out.println("Secretly type the code");
            code = sc.nextInt();
        } else {
            System.out.println("Type an arbitrary number");
            seed = sc.nextLong();
            randomGenerator = new Random(seed);
            code = randomGenerator.nextInt(99);
        }
        System.out.println("Start guessing!");
        while (check && (counter < 7)) {
            guess = sc.nextInt();
            if (guess < code) {
                System.out.println("higher");
            } else if (guess > code) {
                System.out.println("lower");
            } else {
                System.out.println("Good guess! You won.");
                check = false;
            }
            
            array[counter] = guess;
            counter++;
        }

        System.out.println(counter +" guesses");
        for(int i = 0; i<counter; i++) {
            temp = str;
            temp = str.substring(0,code) + "|" + str.substring(code+1, 99);
            temp = temp.substring(0, array[i]) + "X" + temp.substring(array[i]+1, 99);
            System.out.println(temp);              
        }


    }

    public static void main(String[] args) {
        (new GuessingGame()).run();
    }
}
