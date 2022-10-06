
import java.util.ArrayList;


public class PlayGrid {
    /* Used for the playing board. An ArrayList of ArrayLists of Strings holds the playing grid.
    Takes size and winningRowLength as arguments when created.
    Contains methods for setting a symbol to a specific cell, returning a cells symbol, getting an arraylist of the
    available cells, printing the grid and checking winning condition, as well as getters.
    +-----+-----+-----+
    |  x  |  o  |  x  |
    +-----+-----+-----+
    |     |  o  |  x  |
    +-----+-----+-----+
    |  o  |  x  |     |
    +-----+-----+-----+
     */
    /**/
    private final ArrayList<ArrayList<String>> grid = new ArrayList<>();
    private final int size;
    private final int winningRowLength;


    public PlayGrid(int size, int winningRowLength) {
        this.size = size;
        this.winningRowLength = winningRowLength;
        /* Creates a size * size empty grid*/
        for(int row = 0; row < this.size; row++) {
            grid.add(new ArrayList<>());
            for(int column = 0; column < size; column++) {
                grid.get(row).add(" ");
            }
        }
    }

    public int getSize() {
        return size;
    }

    public int getWinningRowLength() {
        return winningRowLength;
    }

    public boolean setCell(int row, int column, String symbol) {
        /* Sets the grid cell(row, column) to String symbol if the space is unoccupied */
        if (getCell(row, column).equals(" ")) {
            grid.get(row).set(column, symbol);
            return true;
        } else {
            System.out.println("Space occupied! Try again.");
            return false;
        }
    }

    public String getCell(int row, int column) {
        return grid.get(row).get(column);
    }

    public ArrayList<int[]> getAvailableCells() {
        ArrayList<int[]> result = new ArrayList<>();
        for(int row = 0; row < size; row++) {
            for(int column = 0; column < size; column++) {
                if(getCell(row, column).equals(" ")) {
                    int[] coordinates = new int[2];
                    coordinates[0] = row;
                    coordinates[1] = column;
                    result.add(coordinates);
                }
            }
        }
        return result;
    }

    public void printGrid() {
        /* Prints the grid to the terminal, using printRow() and printLine()*/
        System.out.print("      ");
        for(int i = 1; i <= getSize(); i++) {
            System.out.print("  " + String.format("%2d", i) + "  ");
        }
        System.out.print("\n");
        printGridLine();
        for(int row = 1; row <= getSize(); row++) {
            printRow(row);
            printGridLine();
        }
    }

    public void printRow(int row) {
        /* Prints one row from the grid. Used by printGrid() */
        System.out.print("  " + String.format("%2d", row) + "  ");
        for(int column = 1; column <= getSize(); column++){
            System.out.print("|  " + getCell(row - 1, column - 1) + "  ");
        }
        System.out.println("|");
   }

   public void printGridLine() {
        /* Prints the lines in-between rows of the grid. Used by printGrid(). */
        System.out.print("      ");
        for(int column = 1; column <= getSize(); column++) {
            System.out.print("+-----");
        }
       System.out.print("+\n");
   }

    public boolean checkWin(int row, int column, String symbol) {

        /* This method will check if the last move made the player win. It uses checkRow() four times with different
        modifiers to do so. It returns true if the player made a winning move, and false otherwise
        */

        // checks for vertical win
        if(checkRow(row, column, symbol, 1, 0)) {
            return true;

            // checks for horizontal win
        } else if(checkRow(row, column, symbol, 0, 1)){
            return true;

            // checks for diagonal downward slope win
        } else if(checkRow(row, column, symbol, 1, 1)) {
            return true;

            // checks for diagonal upwards slope win
        } else if(checkRow(row, column, symbol, -1, 1)) {
            return true;
        }
        return false;

    }
    private boolean checkRow(int row,  int column, String symbol, int rowModifier, int columnModifier) {
        /*
        Checks for a winning row, returns true or false. Used by checkWin().
        Modifiers:
        Vertical: (1, 0)
        Horizontal: (0, 1)
        Diagonal, downward: (1, 1)
        Diagonal, upward: (-1, 1)
        */
        int count = 1;

        for (int i = - (winningRowLength - 1); i <= (winningRowLength-2); i++) {

            try {
                if (getCell(row + (i * rowModifier), column + (i * columnModifier)).equals(
                        getCell(row + (i * rowModifier) + rowModifier, column + (i * columnModifier) + columnModifier)) &&
                        getCell(row + (i * rowModifier), column + (i * columnModifier)).equals(symbol)) {
                    count++;
                } else {
                    count = 1;
                }
                if (count == winningRowLength) {
                    return true;
                }
            } catch(IndexOutOfBoundsException e) {
                    /* This catches IndexOutOfBoundsException that will happen if the move made is too close to
                    the edge. Let's just pretend nothing happened and move on.
                     */
            }
        }
        return false;
    }
}
