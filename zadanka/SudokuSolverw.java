import java.util.Arrays;

/**
 * Class that solves the Asterisk Sudoku.
 * Prints the number of solutions of a Sudoku if there are multiple. If there is only a single solution, prints this solution instead.
 *
 * by Witold Gręda, number: 1702009
 * and Michelle Rose van Teeffelen, number: 1457276
 * as group 192
 */
class SudokuSolverw {

    int SUDOKU_SIZE = 9;          // Size of the grid
    int SUDOKU_MIN_NUMBER = 1;    // Minimum digit to be filled in
    int SUDOKU_MAX_NUMBER = 9;    // Maximum digit to be filled in
    int SUDOKU_BOX_DIMENSION = 3; // Dimension of the boxes (sub-grids that should contain all digits)

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

    int solutionCounter = 0; // Solution counter

    int[] empty = {0, 0}; // Last empty cell's coordinates
    int rempty = 0; // Last empty cell's row coordinates
    int cempty = 0; // Last empty cell's column coordinates

    int[][] oneSolution = new int[SUDOKU_SIZE][SUDOKU_SIZE];


    // Is there a conflict when we fill in d at position (r, c)?
    boolean givesConflict(int r, int c, int d) {
        // Check for all instances
        if (rowConflict(r, d) ||
            columnConflict(c, d) ||
            boxConflict(r, c, d) ||
            asteriskConflict(r, c, d)) {
                return true;
            }
        return false;
    }


    // Is there a conflict when we fill in d in row r?
    boolean rowConflict(int r, int d) {
        // -1 in for loop because these are the indexes
        for (int i = SUDOKU_MIN_NUMBER-1; i <= SUDOKU_MAX_NUMBER-1; i++) {
            if (grid[r][i] == d) {
                return true;
            }
        }
        return false;
    }


    // Is there a conflict in column c when we fill in d?
    boolean columnConflict(int c, int d) {
        // -1 in for loop because these are the indexes
        for (int i = SUDOKU_MIN_NUMBER-1; i <= SUDOKU_MAX_NUMBER-1; i++) {
            if (grid[i][c] == d) {
                return true;
            }
        }
        return false;
    }


    // Is there a conflict in the box at (r, c) when we fill in d?
    boolean boxConflict(int r, int c, int d) {
        
        // Initializing values
        int row = 0;
        int column = 0;
        int rowIterate = 0;
        int colIterate = 0;
        
        // Assigning values
        if (0 <= r && r <= 2) {
            row = 1;
        } else if (3 <= r && r <= 5) {
            row = 2;
        } else if (6 <= r && r <= 8) {
            row = 3;
        }

        if (0 <= c && c <= 2) {
            column = 1;
        } else if (3 <= c && c <= 5) {
            column = 2;
        } else if (6 <= c && c <= 8) {
            column = 3;
        }

        // Switching values to index values
        if (row == 1) {
            rowIterate = 0;
        } else if (row == 2) {
            rowIterate = 3;
        } else if (row == 3) {
            rowIterate = 6;
        }

        if (column == 1) {
            colIterate = 0;
        } else if (column == 2) {
            colIterate = 3;
        } else if (column == 3) {
            colIterate = 6;
        }

        // x interates horizontally (columns), y iterates vertically (rows)
        int y = rowIterate;
        for (int x = colIterate; x <= (colIterate + 2); x++) {
            if (grid[y][x] == d) {
                return true;
            }
            // If row ends, switch to the next one (from top to bottom and from left to right) 
            if (x >= colIterate + 2) {
                x = colIterate-1;
                y++;
            }
            // All rows end
            if (y > rowIterate + 2) {
                break;
            }
        }
        return false;
    }
	

	// Is there a conflict in the asterisk when we fill in d?
    boolean asteriskConflict (int r, int c, int d) {
        
        // Values of all asterisk cells
        int a1 = grid[1][4];
        int a2 = grid[2][2];
        int a3 = grid[2][6];
        int a4 = grid[4][1];
        int a5 = grid[4][4];
        int a6 = grid[4][7];
        int a7 = grid[6][2];
        int a8 = grid[6][6];
        int a9 = grid[7][4]; 

        // Coordinates of all asterisk cells
        int[] cor1 = {1, 4};
        int[] cor2 = {2, 2};
        int[] cor3 = {2, 6};
        int[] cor4 = {4, 1};
        int[] cor5 = {4, 4};
        int[] cor6 = {4, 7};
        int[] cor7 = {6, 2};
        int[] cor8 = {6, 6};
        int[] cor9 = {7, 4};

        // Array with values of asterisk cells
        int [] asterisk = new int[] {a1,a2,a3,a4,a5,a6,a7,a8,a9};

        // Array with coordinates of asterisk cells
        int[][] coordinates = {cor1, cor2, cor3, cor4, cor5, cor6, cor7, cor8, cor9};

        int[] cell = {r, c};

        // Determine if cell is an asterisk cell
        boolean search = false;
        for (int element[] : coordinates) {
            if (element[0] == cell[0] && element[1] == cell[1]) {
                search = true;
                break;
            }
        }

        // Compare to values from all asterisk cells
        for (int h = 0; h < 9; h++) {
            if (search == true && d == asterisk[h] && (asterisk[h] != 0)) {
                return true;
            }
        }
        return false;
    }
	

	// Finds the next empty square
    int[] findEmptySquare() {

        int y = rempty;
        for (int x = cempty; x <= SUDOKU_MAX_NUMBER-1; x++) {
            if (grid[y][x] == 0) {
                rempty = y;
                cempty = x;
                return new int[]{y, x}; 
            }
            if (x >= 8) {
                x = -1;
                y++;
            }
            if (y > 8) {
                break;
            }
        }
        return null;
    }


    // Find all solutions, store the grid (for only one solution)
    void solve() {
        
        empty = findEmptySquare();

        if (empty != null) {

            int row = empty[0];
            int col = empty[1];
            for (int i = 1; i <= 9; i++) {
                if (givesConflict(row, col, i) == false) {
                        grid[row][col] = i;
                        solve();
                    }
                }
                
                grid[row][col] = 0;
                rempty = row;
                cempty = col;

        } else {
            if (solutionCounter == 0) {

                for (int y = 0; y < SUDOKU_SIZE; y++) {
                    for (int x = 0; x < SUDOKU_SIZE; x++) {
                        oneSolution[y][x] = grid[y][x];
                    }
                }
            }
            solutionCounter++;
        }
    }


    // Print the sudoku grid
    void print(int[][] grid) {
        
        System.out.println("+-----------------+");
        
        int x = 0;
        int y = 0;
        for (int i = 0; i <= 18; i++) {
            if ((x == 1 && i == 8) ||
                (x == 2 && i == 4) ||
                (x == 4 && (i == 2 || i == 8 || i == 14)) ||
                (x == 6 && i == 4) ||
                (x == 7 && i == 8)) {
                System.out.print(">");
            } else if ((x == 1 && i == 10) ||
                        (x == 2 && i == 14) ||
                        (x == 4 && (i == 4 || i == 10 || i == 16)) ||
                        (x == 6 && i == 14) ||
                        (x == 7 && i == 10)) {
                            System.out.print("<");
            } else if (i == 0 || i == 6 || i == 12 || i == 18) {
                System.out.print("|");
            } else if (i == 2 || i == 4 || i == 8 || i == 10 || i == 14 || i == 16) {
                System.out.print(" ");
            } else if (grid[x][y] != 0) {
                System.out.print(grid[x][y]);
                y++;
            } else {
                System.out.print(" ");
                y++;
            }

            if ((x == 2 || x == 5) && i >= 18) {
                System.out.printf("%n+-----------------+");
            }

            if (i >= 18) {
                x++;
                i = -1;
                System.out.printf("%n");
                y = 0;
            }

            if (x > 8) {
                System.out.println("+-----------------+");
                break;
            }
        } 
    }


    // Run the actual solver
    void solveIt() {
        solve();
        if (solutionCounter == 1) {
            print(oneSolution);
        } else {
            System.out.println(solutionCounter);
        }   
    }


    public static void main(String[] args) {
        (new SudokuSolverw()).solveIt();
        
    }
}
