import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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
    private ArrayList<ArrayList<String>> grid = new ArrayList<>();


    public PlayGrid(int size) {
        /* Creates a size * size empty grid*/
        for(int row = 0; row < size; row++) {
            grid.add(new ArrayList<>());
            for(int column = 0; column < size; column++) {
                grid.get(row).add(" ");
            }
        }
    }

    public boolean setCell(int row, int column, String symbol) {
        /* Sets the grid cell(row, column) to String symbol */
        if (getCell(row, column).equals(" ")) {
            grid.get(row).set(column, symbol);
            //grid.moveCount++;
            //System.out.println("!" + coordinates[0] + coordinates[1]);
            //player.setLastMove(coordinates[0], coordinates[1]);
            return true; // if the player input is ok, we hit the break-statement and get out of the loop.
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

        for (int i = - 2; i <= 1; i++) {

                try {
                    if (getCell(row + i, column).equals(getCell(row + i + 1, column)) &&
                    getCell(row + i, column).equals(symbol)) {
                        count++;
                    } else {
                        count = 1;
                    }
                    if (count == 3) {
                        return true;
                    }
                } catch(IndexOutOfBoundsException e) {

                }

        }
        // checks for horizontal win
        count = 1;

        for (int i = - 2; i <= 1; i++) {

            try {
                if (getCell(row, column + i).equals(getCell(row, column + i + 1)) &&
                        getCell(row, column + i).equals(symbol)) {
                    count++;
                } else {
                    count = 1;
                }
                if (count == 3) {
                    return true;
                }
            } catch(IndexOutOfBoundsException e) {

            }

        }

        // checking for downward slope diagonal win
        count = 1;

        for (int i = - 2; i <= 1; i++) {

            try {
                if (getCell(row + i, column + i).equals(getCell(row + i + 1, column + i + 1)) &&
                        getCell(row + i, column + i).equals(symbol)) {
                    count++;
                } else {
                    count = 1;
                }
                if (count == 3) {
                    return true;
                }
            } catch(IndexOutOfBoundsException e) {

            }

        }
        // checking for upwards slope diagonal win
        count = 1;

        for (int i = - 2; i <= 1; i++) {

            try {
                if (getCell(row - i, column + i).equals(getCell(row - i - 1, column + i + 1)) &&
                        getCell(row - i, column + i).equals(symbol)) {
                    count++;
                } else {
                    count = 1;
                }
                if (count == 3) {
                    return true;
                }
            } catch(IndexOutOfBoundsException e) {

            }

        }
    return false;
    }
}
