import java.util.Scanner;
public class GuessingGameAdvanced {
    Scanner sc = new Scanner(System.in);

    public void run() {
        boolean found = true;
        int counter=0, guess = 500, low = 0, high = 1000;
        System.out.println("Think of a secret number not smaller than 0 and not greater than 1000. Type 'go' when you have one.");
        String ans = sc.nextLine(), res;
        if (ans.equals("go")) {
            while (found && (counter < 10)) {
                guess = (low+high)/2;
                System.out.println(guess);
                res = sc.nextLine();
                if(res.equals("higher") || res.equals("h") ) 
                    low = guess;
                else if (res.equals("lower") || res.equals("l") ) 
                    high = guess;
                else if (res.equals("guessed"))
                    found = false;
                counter++;
            }
            if (found) {
                System.out.println("I give up");
            }
        
        }
    }

    public static void main(String[] args) { 
        (new GuessingGameAdvanced()).run();
    }
    
}
