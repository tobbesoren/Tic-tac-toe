public class Main {
    public static void main(String[] args) {
        /* Creating a PlayGrid, two Players and a Game. Testing taking turns to make moves.*/
        int size = 2;

        PlayGrid myGrid = new PlayGrid(size);

        Player p1 = new Player("A", "x");
        Player p2 = new Player("B", "o");

        Game game = new Game(p1, p2, myGrid);

        myGrid.printGrid();


        while(game.getMoveCount() < size * size) { // The program stops when all cells are set
            game.makeMove(p1);
            myGrid.printGrid();
            if(game.getMoveCount() == size * size) {
                break;
            }
            game.makeMove(p2);
            myGrid.printGrid();
        }
    }
}