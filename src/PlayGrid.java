
import java.util.ArrayList;


public class PlayGrid {
    /*
    +-----+-----+-----+
    |  x  |  o  |  x  |
    +-----+-----+-----+
    |     |  o  |  x  |
    +-----+-----+-----+
    |  o  |  x  |     |
    +-----+-----+-----+
     */
    /*An ArrayList of ArrayLists of Strings holds the playing grid*/
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

    public void printGrid() {
        /* Prints the grid to the terminal, using printRow() and printLine()*/
        printLine();
        for(int row = 0; row < grid.get(0).size(); row++) {
            printRow(row);
            printLine();
        }
    }

    public int getSize() {
        return size;
    }

    public int getWinningRowLength() {
        return winningRowLength;
    }

    public void printRow(int row) {
        /* Prints one row from the grid. Used by printGrid() */
        for(int column = 0; column < grid.get(row).size(); column++){
            System.out.print("|  " + grid.get(row).get(column) + "  ");
        }
        System.out.println("|");
   }

   public void printLine() {
        /* Prints the lines in-between rows of the grid. Used by printGrid(). */
        for(int column = 0; column < grid.get(0).size(); column++) {
            System.out.print("+-----");
        }
       System.out.print("+\n");
   }

    public boolean checkWin(int row, int column, String symbol) {

        /* This method will check if the last move made the player win. It should be generalized, too much code
        duplication. It seems to work, though.*/

        // Checking for vertical win
        int count = 1;

        for (int i = - (winningRowLength - 1); i <= (winningRowLength-2); i++) {

                try {
                    if (getCell(row + i, column).equals(getCell(row + i + 1, column)) &&
                    getCell(row + i, column).equals(symbol)) {
                        count++;
                    } else {
                        count = 1;
                    }
                    if (count == winningRowLength) {
                        return true;
                    }
                } catch(IndexOutOfBoundsException e) {
                    /* This catches IndexOutOfBoundsException that will happen if the move made is too close to
                    the edge. Since we just want the program to move on, nothing is done here!
                     */
                }

        }
        // checks for horizontal win
        count = 1;

        for (int i = - (winningRowLength - 1); i <= (winningRowLength-2); i++) {

            try {
                if (getCell(row, column + i).equals(getCell(row, column + i + 1)) &&
                        getCell(row, column + i).equals(symbol)) {
                    count++;
                } else {
                    count = 1;
                }
                if (count == winningRowLength) {
                    return true;
                }
            } catch(IndexOutOfBoundsException e) {
                /* This catches IndexOutOfBoundsException that will happen if the move made is too close to
                    the edge. Since we just want the program to move on, nothing is done here!
                     */
            }

        }

        // checking for downward slope diagonal win
        count = 1;

        for (int i = - (winningRowLength - 1); i <= (winningRowLength-2); i++) {

            try {
                if (getCell(row + i, column + i).equals(getCell(row + i + 1, column + i + 1)) &&
                        getCell(row + i, column + i).equals(symbol)) {
                    count++;
                } else {
                    count = 1;
                }
                if (count == winningRowLength) {
                    return true;
                }
            } catch(IndexOutOfBoundsException e) {
                /* This catches IndexOutOfBoundsException that will happen if the move made is too close to
                    the edge. Since we just want the program to move on, nothing is done here!
                     */
            }
        }
        // checking for upwards slope diagonal win
        count = 1;

        for (int i = - (winningRowLength - 1); i <= (winningRowLength-2); i++) {

            try {
                if (getCell(row - i, column + i).equals(getCell(row - i - 1, column + i + 1)) &&
                        getCell(row - i, column + i).equals(symbol)) {
                    count++;
                } else {
                    count = 1;
                }
                if (count == winningRowLength) {
                    return true;
                }
            } catch(IndexOutOfBoundsException e) {
                /* This catches IndexOutOfBoundsException that will happen if the move made is too close to
                    the edge. Since we just want the program to move on, nothing is done here!
                     */
            }
        }
        return false;
    }
}
