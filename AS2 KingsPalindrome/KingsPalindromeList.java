import java.util.Scanner;
/**
 * Reads a list of numbers, and can reconstruct the corresponding list of Palindromes,
 * produce the size of the largest magic set, and the content of that magic set.
 * 
 * Usage:
 * The program can do 3 different tasks: 
 * 1. Correct a list of numbers into palindromes and output it
 * 2. Output the size of the largest magic set from the corrected list of palindromes from the input
 * 3. Output the numbers that are contained in the largest magic set in the ascending order
 * (or the one that contains the largest element, if there are multiple magic sets of the same size)
 * 
 * Firstly, input the number of the task you would like to get the output of.
 * Then, input the amount of numbers that you are going to input next.
 * Finally, input the numbers (separate them with a spacebar)
 * that you would like the program to perform the tasks on.
 * 
 * After pressing enter, the program will output you the answer/numbers
 * depending on which task you chose.
 * 
 * @author Tomasz Soróbka
 * @ID 1808982
 * 
 */

class KingsPalindromeList {
    Scanner sc = new Scanner(System.in);
    int n; //the amount of numbers inputted on the third line (the list from the King's advisor)
    long[] listOfNumbers; // array for the King's advisor list
    long[] currentMagicSet; // array for looking for the largest magic sets
    long[] maxMagicSet; // array used to store the numbers from the largest magic set

    // a method that returns a boolean value whether a number is a palindrome or not
    // @param number - number to be checked whether it is a palindrome
    public boolean isPalindrome(long number) {
        long remainder;
        long temporaryNumber = 0;
        long storeNumber = number;

        // a loop that flips a number and stores it in temporaryNumber
        while (number > 0) {    
            remainder = number % 10;  
            temporaryNumber = (temporaryNumber * 10) + remainder;    
            number = number / 10;    
        }   

        // checking whether temporaryNumber is the same as the original number
        if (storeNumber == temporaryNumber) { 
            return true;
        } 
        return false;
    }

    /* a method that returns a boolean value whether the variable b is in
    *  the same magic set as variable a (so a is contained in the middle of b)
    *  @param a - palindrome
    *  @param b - palindrome that is evaluated whether it contains variable a in the middle
    */
    public boolean isMagic(long a, long b) {
        if (a == b) {
            return true;
        }
        int aNoDigits = (int) Math.floor(Math.log10(a)) + 1; // number of digits in a
        int bNoDigits = (int) Math.floor(Math.log10(b)) + 1; // number of digits in b
        if (aNoDigits >= bNoDigits) {
            return false;
        } 
        
        // deleting several digits of b from the rights, so it can be checked whether it
        // contains the number a in the middle
        b = b / (long) (Math.pow(10.0, (bNoDigits - aNoDigits) / 2)); 
        for (int i = 0; i < aNoDigits; i++) {
            // if the digits of b in the middle are different than a, then the method returns false
            if (a % 10 != b % 10) { 
                return false;
            }
            a = a / 10;
            b = b / 10;
        }
        return true;
    }

    // a method that returns a largest value in the array
    // @param array - an array of numbers
    public long maxValue(long[] array) {
        long maxNumber = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] > maxNumber) {
                maxNumber = array[i];
            }
        }
        return maxNumber;
    }

    /* a method that sorts numbers in an array
    * @param array - an unsorted array of numbers
    * @param len - a number of elements in the array
    */
    public void bubbleSort(long[] array, int len) { 
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    long swap = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = swap;
                }
            }
        }
    }

    /* a method responsible for executing task 1: 
     * correcting a list of palindromes received from the King's advisor
     * in addition it stores those corrected palindromes in an array
     * so it can be easily used in task 2 and 3
     */
    public void taskOne() {
        long newNumber;
        for (int i = 0; i < n; i++) {
            newNumber = sc.nextLong();
            // if a number is not a palindrome, add one and check once again
            // repeat until the number becomes a palindrome
            while (!isPalindrome(newNumber)) { 
                newNumber++;
            }
            
            listOfNumbers[i] = newNumber;
        }
        return;
    }

    /* a method responsible for executing task 2:
     * it returns a number that is a size of the largest magic set
     */
    public int taskTwo() {
        int maxMagicSetSize = 1; // a variable to store the size of the largest magic set
        int sizeCounter; // a variable to store a size of the currently examined magic set
        for (int i = 0; i < n; i++) {
            sizeCounter = 0;
            for (int j = 0; j < n; j++) { // a loop that examines every possible magic set 
                if (isMagic(listOfNumbers[i], listOfNumbers[j])) {
                    currentMagicSet[sizeCounter] = listOfNumbers[j];
                    sizeCounter++;
                    currentMagicSet[sizeCounter] = -1;
                }
            }
            // if a size of the currently examined magic set is greater than previous max,
            // then update the max size and copy the values to an array maxMagicSet
            if (sizeCounter > maxMagicSetSize) { 
                maxMagicSetSize = sizeCounter;
                maxMagicSet = currentMagicSet.clone();
            // if the size of the currently examined magic set is the same as previous max,
            // copy the values only if the currently examined set contains a larger element     
            } else if (sizeCounter == maxMagicSetSize) {
                if (maxValue(currentMagicSet) > maxValue(maxMagicSet)) {
                    maxMagicSet = currentMagicSet.clone();
                }
            }
        }
        return maxMagicSetSize;
    }

    // a method that connects all of the methods and runs them at the appropriate moment
    public void run() {
        int taskNumber;
        taskNumber = sc.nextInt(); // input the number of the task
        n = sc.nextInt(); //input the amount of numbers from the King's advisor list
        listOfNumbers = new long[n];
        currentMagicSet = new long[n + 1];
        maxMagicSet = new long[n + 1];

        // initialize arrays with a value -1
        for (int i = 0; i < (n + 1); i++) {
            currentMagicSet[i] = -1;
            maxMagicSet[i] = -1;
        }
        
        // a switch statement to perform appropriate methods according to the chosen task
        switch (taskNumber) {
            case 1:
                taskOne();
                // print the corrected values from the King's advisor list
                for (int i = 0; i < n; i++) {
                    System.out.print(listOfNumbers[i] + " "); 
                }
                break;
            case 2:
                taskOne();
                // print the largest magic set 
                System.out.println(taskTwo());
                break;
            case 3:
                taskOne();
                if (taskTwo() == 1) { // if the magic set does not exist, print largest value
                    System.out.println(maxValue(listOfNumbers));
                } else {
                    bubbleSort(maxMagicSet, n + 1); // sorting largest magic set
                    for (int i = 0; i < n + 1; i++) { //listing values from the largest magic set
                        if (maxMagicSet[i] != -1) {
                            System.out.print(maxMagicSet[i] + " ");
                        }
                    }       
                }
                break;
            default:
                System.out.println("Something went wrong");
        }
    }

    public static void main(String[] args) {
        (new KingsPalindromeList()).run();
    }
}