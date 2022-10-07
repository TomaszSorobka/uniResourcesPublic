// Cellulitis TEMPLATE
// Homework Assignment 3 2ip90 
/**
 * @name(s) //TODO
 * @id(s)   //TODO
 * @group   //TODO
 * @date    //TODO
 */
import java.util.Scanner;
class Cellulitis {
  Scanner scanner = new Scanner(System.in);
  int L, G;
  

  void draw(boolean[] currentGeneration) {
    for(int i = 0; i<L+1; i++) {
        if (currentGeneration[i])
            System.out.print("*");
        else
            System.out.print(" ");
    }
    System.out.println();
  }

  void run() {
    String temp_str, opt = scanner.next();
    int temp;
    L = scanner.nextInt();
    G = scanner.nextInt();
    boolean[] currentGeneration = new boolean[L+2];
    boolean end = false;

    temp_str = scanner.next();
    while (!end) {
        temp_str = scanner.next();
        if(temp_str.equals("init_end")) 
            end = true;
        else {
            temp = Integer.parseInt(temp_str);
            currentGeneration[temp+1] = true;
        }
    }
    draw(currentGeneration);
    for(int i = 0; i<G; i++) {
        currentGeneration = nextGen(currentGeneration, opt);
        draw(currentGeneration);   
    }
  }

  boolean[] nextGen(boolean[] currentGeneration, String opt) {
    boolean[] newGeneration = new boolean[L+2];
    if (opt.equals("A")) {
        for(int i = 1; i<L+1; i++) {
            if(currentGeneration[i]) {
                if(currentGeneration[i-1] ^ currentGeneration[i+1])
                    newGeneration[i] = true;
                else 
                    newGeneration[i] = false;
            } else {
                if(!currentGeneration[i-1] && !currentGeneration[i+1])  
                    newGeneration[i] = false;
                else   
                    newGeneration[i] = true;
            }
        }
    } else if (opt.equals("B")) {
        for(int i = 1; i<L+1; i++) {
            if(currentGeneration[i]) {
                if(!currentGeneration[i+1])
                    newGeneration[i] = true;
                else 
                    newGeneration[i] = false;
            } else {
                if(currentGeneration[i-1] ^ currentGeneration[i+1])  
                    newGeneration[i] = true;
                else   
                    newGeneration[i] = false;
            }
        }
    }
    return newGeneration;
  }

  
  
  public static void main(String[] args) { 
    (new Cellulitis()).run();
  }
}