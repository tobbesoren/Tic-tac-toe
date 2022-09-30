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

    public boolean checkWin(int row, int column) {
        int count = 1;
        //System.out.println(Arrays.toString(player.getLastMove()) + getCell(player.getLastMove()[0]-1, player.getLastMove()[1]-1 ));
        /* This method will check if the last move made the player win. Right now, it doesn't...*/

        // Checking for vertical win


        System.out.println(getCell(row, column));

        for (int i = row - 2; i < row + 1; i++) {

                try {
                    // !
                    //System.out.println(getCell(i, column) + " " + getCell(i + 1, column));

                    if (getCell(i, column).equals(getCell(i + 1, column))) {
                        count++;
                        // !
                        /*System.out.println("row: " + row + " column: " + player.getLastMove()[1]
                                + " cell1: " + getCell(row - 1, player.getLastMove()[1] - 1) + "cell2" +
                                getCell(row + 1, player.getLastMove()[1]));*/
                    }
                    System.out.println(count);
                    if (count == 3) {
                        return true;
                    } else {
                        count = 1;
                    }
                } catch(Exception e) {

                }

        }


        /*for (int column = player.getLastMove()[1] - 2; column < player.getLastMove()[1] + 2; column++) {
            System.out.println(column);
            try {
                if (getCell(player.getLastMove()[0], column) == getCell(player.getLastMove()[0], column + 1 )){
                    count++;
                    if (count == 3) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            } catch(Exception e) {
            }
        }
        return false;
    }*/

    //public ArrayList<ArrayList<String>> getGrid() {
    //return grid;
    return false;
    }
}
