public class Main {
    public static void main(String[] args) {
        /* Testing to set up grid and adding a char to it.*/
        PlayGrid myGrid = new PlayGrid(30);
        myGrid.setCell(0,0,"x");
        myGrid.printGrid();

    }
}