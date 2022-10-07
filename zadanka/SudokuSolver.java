/**
 * Class that solves the Asterisk Sudoku.
 * Prints the number of solutions of a Sudoku if there are multiple. If there is 
only a single solution, prints this solution instead.
 *
 * by <<TODO YOUR NAME AND ID HERE>>
 * and <<TODO YOUR PARTNERS NAME AND ID HERE>>
 * as group <<TODO GROUP NUMBER HERE>>
 */
class SudokuSolver {
    int SUDOKU_SIZE = 9;          // Size of the grid.
    int SUDOKU_MIN_NUMBER = 1;    // Minimum digit to be filled in.
    int SUDOKU_MAX_NUMBER = 9;    // Maximum digit to be filled in.
    int SUDOKU_BOX_DIMENSION = 3; // Dimension of the boxes (sub-grids that should contain all digits).
    int rempty = 0, cempty = 0;
    int[][] grid = new int[][] {  // The puzzle grid; 0 represents empty.
        { 0, 9, 0,   7, 3, 0,    4, 0, 0 },    // One solution.
        { 0, 0, 0,   0, 0, 0,    5, 0, 0 },
        { 3, 0, 0,   0, 0, 6,    0, 0, 0 },
        { 0, 0, 0,   0, 0, 2,    6, 4, 0 },
        { 0, 0, 0,   6, 5, 1,    0, 0, 0 },
        { 0, 0, 6,   9, 0, 7,    0, 0, 0 },
        { 5, 8, 0,   0, 0, 0,    0, 0, 0 },
        { 9, 0, 0,   0, 0, 3,    0, 2, 5 },
        { 6, 0, 3,   0, 0, 0,    8, 0, 0 },
    };
    int[] empty = {0, 0}; // Last empty cell's coordinates
    int solutionCounter = 0; // Solution counter
    int[][] theSolution = new int[SUDOKU_SIZE][SUDOKU_SIZE];
    // Is there a conflict when we fill in d at position (r, c)?
    boolean givesConflict(int r, int c, int d) {
        boolean conflict = false;
        if (rowConflict(r, d) || columnConflict(c, d) || boxConflict(r, c, d) || asteriskConflict(r, c, d))
            conflict = true;
        return conflict;
    }
    // Is there a conflict when we fill in d in row r?
    boolean rowConflict(int r, int d) {
        boolean conflict = false;
        for (int i = 0; i<9; i++) {
            if (grid[r][i] == d)
                conflict = true;
        }
        return conflict;
    }
    // Is there a conflict in column c when we fill in d?
    boolean columnConflict(int c, int d) {
        boolean conflict = false;
        for (int i = 0; i<9; i++) {
            if (grid[i][c] == d)
                conflict = true;
        }
        return conflict;
    }
    // Is there a conflict in the box at (r, c) when we fill in d?
    boolean boxConflict(int r, int c, int d) {
        boolean conflict = false;
        int rowshift = 0, colshift = 0;

        if (r/SUDOKU_BOX_DIMENSION == 1)
            rowshift = 3;
        else if (r/SUDOKU_BOX_DIMENSION == 2)
            rowshift = 6;

        if (c/SUDOKU_BOX_DIMENSION == 1)
            colshift = 3;
        else if (c/SUDOKU_BOX_DIMENSION == 2)
            colshift = 6;

        for (int i = 0; i<SUDOKU_BOX_DIMENSION; i++) {
            for(int j = 0; j<SUDOKU_BOX_DIMENSION; j++) {
                if (grid[i+rowshift][j+colshift] == d)
                    conflict = true;
            }
        }
        return conflict;
    }

// Is there a conflict in the asterisk when we fill in d?
    boolean asteriskConflict(int r, int c, int d) {
        boolean conflict = false, isAsterisk = false;
        int[] row = {1, 2, 2, 4, 4, 4, 6, 6, 7};
        int[] col = {4, 2, 6, 1, 4, 7, 2, 6, 4};
        for(int i = 0; i<9; i++){

            if (row[i] == r && col[i] == c)
                isAsterisk = true;
        
            if (grid[row[i]][col[i]] == d)
                conflict = true;
        }

        if (!isAsterisk)
            return false;
        else
            return conflict;
    }
// Finds the next empty square (in "reading order").
    int[] findEmptySquare() {
        boolean found = false;
        while(!found) {
            if (grid[rempty][cempty] == 0) {
                found = true;
                return new int[]{rempty, cempty};
            } else {
                if (cempty != 8)
                    cempty++;
                else {
                    cempty = 0;
                    rempty++;
                }

                if (rempty == 9)
                    return null;
            }
        }
        return null;
    }

// Find all solutions for the grid, and stores the final solution.
    void solve() {
        int[] empty = findEmptySquare();
        int row, col;
        if (empty != null) {
            row = empty[0];
            col = empty[1];
            
            for(int i = 1; i<10; i++) {
                if (givesConflict(row, col, i) != true) {
                    grid[row][col] = i;
                    solve();
                }
            }
            grid[row][col] = 0;
            rempty = row;
            cempty = col;
        } else {
            if (solutionCounter == 0) {
                for (int i = 0; i < SUDOKU_SIZE; i++) {
                    for (int j = 0; j < SUDOKU_SIZE; j++) {
                        theSolution[i][j] = grid[i][j];
                    }
                }
            }
            solutionCounter++;
        }
    }
// Print the sudoku grid.
    void print(int[][] gridSolution) {
        String s = " ";
        System.out.println("+-----------------+");
        for(int i = 0; i<SUDOKU_SIZE; i++) {
            System.out.print("|" + gridSolution[i][0]);
            for(int j = 1; j<SUDOKU_SIZE; j++) {
                if (j%3 == 0)
                    s = "|";
                else if ((i%3 == 1 && j == 4) || (j == 2 && (i == 2 || i == 6)) || (i == 4 && j%3 == 1))
                    s = ">";
                else if ((i%3 == 1 && j == 5) || (j == 7 && (i == 2 || i == 6)) || (i == 4 && j%3 == 2))
                    s = "<";
                else 
                    s = " ";

                System.out.print(s + gridSolution[i][j]);
            }
            System.out.print("|\n");
            if (i%3 == 2)
                System.out.println("+-----------------+");

        }
    }
// Run the actual solver.
    void solveIt() {
        solve();
        if (solutionCounter == 1) {
            print(theSolution);
        } else {
            System.out.println(solutionCounter);
        }   
    }
    public static void main(String[] args) {
        (new SudokuSolver()).solveIt();
    }
}