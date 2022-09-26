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
    ArrayList<ArrayList<String>> grid = new ArrayList<>();

    public PlayGrid(int size) {
        /* Creates a size * size empty grid*/
        for(int row = 0; row < size; row++) {
            grid.add(new ArrayList<>());
            for(int column = 0; column < size; column++) {
                grid.get(row).add("o");
            }
        }
    }

    public void setCell(int row, int column, String symbol) {
        grid.get(row).set(column, symbol);
    }

    public void printGrid() {
        printLine();
        for(int row = 0; row < grid.get(0).size(); row++) {
            printRow(row);
            printLine();
        }

    }

   public void printRow(int row) {
        for(int column = 0; column < grid.get(row).size(); column++){
            System.out.print("|  " + grid.get(row).get(column) + "  ");
        }
        System.out.println("|");
   }

   public void printLine() {
        for(int column = 0; column < grid.get(0).size(); column++) {
            System.out.print("+-----");
        }
       System.out.print("+\n");
   }
}
