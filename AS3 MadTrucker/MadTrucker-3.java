import java.util.HashSet;
import java.util.Scanner;


/**
 * Quick description
 *  The program calculates a sequence of all the cans in the back
 *  of the truck, that, when used, does not lead to the truck stopping at any of the
 *  places where stopping is not possible.
 * 
 * Usage: 
 * Firstly, input an integer n that is a number of cans in the back of the truck.
 * Then input n integers separated with spaces that correspond to the number of kilometers
 * that a truck can go when fueled solely by n-th can.
 * Lastly, input n - 1 integers separated with spaces that correspond to the places where stopping
 * is not allowed. The integers are the numbers of kilometers that such a place is from the start.
 * 
 * After that, the program will output the sequence of all cans, which meet the restrictions
 * of the program, namely: all the cans have to be used and the car cannot stop to refuel
 * on places that were indicated in the input.
 * 
 * @author Tomasz Soróbka
 * @ID 1808982
 * 
 */

class MadTrucker {
    Scanner sc = new Scanner(System.in);
    // an integer that corresponds to the number of cans 
    int numberOfCans;

    // an array of size n used to store the amount of kilometers that the truck will be fueled
    // for then using n-th can
    int[] fuelCans;

    // a boolean array used to track which cans were already used
    boolean[] usedCans;

    // an integer used to track the distance travelled by the truck from the starting point
    int distanceTravelled = 0;

    // an integer array that stores the sequence of cans
    int[] solArray;

    /*  An array that is used to store original order of the fuel cans
    * it is needed because in order to improve the speed of the program, the fuelCans 
    * array will be sorted in the ascending order.
    * To ouput the correct order of cans to be used with original indices, we
    * need this array to keep track of original indices.
    */
    int[] indicesOfCans;

    // a hashset used for storing the places where the truck cannot stop
    HashSet<Integer> cannotStop;

    /* A bubble sort is used to sort the fuelCans array in order to improve the speed 
    *  of the program. As the fuelCans array is being sorted, so is the indicesOfCans array
    * in order to keep track of original indices of cans.

    */ 
    public void bubbleSort(int[] arr) {

        int n = arr.length;  
        int temp = 0;  
        for (int i = 0; i < n; i++) {

            for (int j = 1; j < (n - i); j++) {  

                if (arr[j - 1] > arr[j]) {  
                    
                    // swap the values in an array if two consecutive values
                    // are not in the ascending order
                    temp = arr[j - 1];  
                    arr[j - 1] = arr[j];  
                    arr[j] = temp;  

                    // do exactly the same operation for indicesOfCans as 
                    // for arr (in this case fuelCans array)
                    // so the original order of indices of cans is stored
                    temp = indicesOfCans[j - 1];  
                    indicesOfCans[j - 1] = indicesOfCans[j];  
                    indicesOfCans[j] = temp; 

                }  
                          
            }  
        }  
  
    }

    // a method used for printing the solution
    public void outputSolution() {

        System.out.print(indicesOfCans[solArray[0]]);
        
        for (int i = 1; i < numberOfCans; i++) {
            // iterate through every can and in the solArray
            // and adjust the cans indices by outputing the value 
            // found in the indicesOfCans array that hold original indices
            System.out.print(" " + indicesOfCans[solArray[i]] );
        }
        return;
    }

    /*
     * Check whether using a certain can meets the restrictions
     * 
     * @param index         the index in the solArray where we try to input the value
     * @param canNumber     the number of the can that we want to input in the solArray at index
     */
    public boolean isSafeToStop(int index, int canNumber) {
        
        // if using a certain can puts the truck in place where stopping is not allowed
        // or if the can was already used, signal that this combination is invalid
        // by returning false
        if (cannotStop.contains(distanceTravelled + fuelCans[canNumber]) || usedCans[canNumber]) {
            return false;
        }  
        // if the restrictions are met, return true   
        return true;
    }


    /*
     * The recursive method that obtains the correct order of cans to be used in order
     * to meet all of the restrictions of the task 
     * 
     * @param index     the index in the solArray that the function 
     *                  is solving/obtaining the number of a can
     */
    public boolean solveRecursively(int index) {
        
        // if index is greater than the number of cans, it means that 
        // solArray has been filled correctly and the method can end its processes by returning true
        if (index >= numberOfCans) { 
            return true;
        }

        // iterate for every can
        for (int canIndex = 0; canIndex < numberOfCans; canIndex++) {
            // if the can of index "canIndex"" can be put in the solArray at index
            if (isSafeToStop(index, canIndex)) {
                // then add the distance that can be traveled using this can to the current distance
                distanceTravelled += fuelCans[canIndex];
                // set solArray at index to the canIndex
                solArray[index] = canIndex;
                // mark can at canIndex as used
                usedCans[canIndex] = true;

                // solve/obtain the can for the next place/index in the solArray
                if (solveRecursively(index + 1)) {
                    return true;
                }
                
                // if it gets to this place, it means that given can cannot be used in 
                // this combination so the changes have to be reverted

                // subtract the last can's potential distance from the current distance
                distanceTravelled -= fuelCans[canIndex];
                // set solArray at index to -1 to mark is as empty place
                solArray[index] = -1;
                // mark the last can as false so it can be used one more time 
                usedCans[canIndex] = false;
                
            }
        }
        return false;
    }

    /* a method used for setting up the variables, declaring arrays, hashset, 
    * initializing solArray with -1 (which marks a place where the can
    * has not yet been entered)
    * and initializing indicesOfCans array with values of indices (0, 1, 2, 3, ... , n - 1)
    */
    public void setValues() {

        fuelCans = new int[numberOfCans];
        cannotStop = new HashSet<Integer>(numberOfCans - 1);
        usedCans = new boolean[numberOfCans];
        solArray = new int[numberOfCans];
        indicesOfCans = new int[numberOfCans];

        // initialize the values for solArray and indicesOfCans array
        for (int i = 0; i < numberOfCans; i++) {
            solArray[i] = -1;
            indicesOfCans[i] = i;
        }

    }

    public void run() {
        // input for the number of cans
        numberOfCans = sc.nextInt();
        
        // set up all the needed variables
        setValues();
        
        // input all of the values for how long the cans can last
        for (int i = 0; i < numberOfCans; i++) {
            fuelCans[i] = sc.nextInt();
        }
        
        // input all of the places where truck cannot stop
        for (int i = 0; i < numberOfCans - 1; i++) {
            cannotStop.add(sc.nextInt());
        }
        // sort fuelCans array (and sort indicesOfCans array as well)
        bubbleSort(fuelCans);

        // obtain the correct order of cans to be used by solving recursively the task
        // while meeting requirements
        solveRecursively(0);

        // output the solution 
        outputSolution();
    }

    public static void main(String[] args) {
        (new MadTrucker()).run();
    }
}