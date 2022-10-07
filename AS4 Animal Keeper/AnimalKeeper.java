import java.io.EOFException;
import java.util.Scanner;

class AnimalKeeper {
    Scanner sc = new Scanner(System.in);
    Scanner scLine = new Scanner(System.in);
    MyZoo zoo = new MyZoo();

    public void run() {
        String command;
        int commandNumber;
        command = sc.next();

        while(true) {
            
            if (command.equals("c")) {
                break;
            } else {
                commandNumber = Integer.parseInt(command);
            }

            if (commandNumber == 0) {

                int animalType = sc.nextInt();
                String name = sc.next(); 
                int homeId = sc.nextInt();
                System.out.print(zoo.addAnimal(animalType, name, homeId) + " ");

            } else if (commandNumber == 1) {

                String name = sc.next();
                int newHomeId = sc.nextInt();
                System.out.print(zoo.moveAnimal(name, newHomeId) + " ");

            } else if (commandNumber == 2) {

                String name = sc.next();
                System.out.print(zoo.removeAnimal(name) + " ");

            } else if (commandNumber == 3) {

                int foodType = sc.nextInt();
                int amount = sc.nextInt();
                System.out.print(zoo.buyFood(foodType, amount) + " ");

            } else if (commandNumber == 4) {

                int foodType = sc.nextInt();
                int amount = sc.nextInt();
                int homeId = sc.nextInt();
                System.out.print(zoo.feedFood(foodType, amount, homeId) + " ");

            }
            command = sc.next();

        }
    }


    public static void main(String args[]) {
        (new AnimalKeeper()).run();
    }
}