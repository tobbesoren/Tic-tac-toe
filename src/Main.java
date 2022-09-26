public class Main {
    public static void main(String[] args) {
        /* Testing to set up grid and adding a char to it.*/
        PlayGrid myGrid = new PlayGrid(5);
        myGrid.setCell(0,0,"x");
        myGrid.printGrid();

        Player p1 = new Player("A");
        Player p2 = new Player("B");

        Game game = new Game(p1, p2, myGrid);

        game.makeMove(p1);
        myGrid.printGrid();
    }
}