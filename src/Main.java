import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /* Creating a PlayGrid, two Players and a Game. Testing taking turns to make moves.*/

        int size = 3;

        PlayGrid myGrid = new PlayGrid(size);

        UserInput input = new UserInput();

        Player p1 = new Player("A", "X");
        Player p2 = new Player("B", "O");

        Game game = new Game(p1, p2, myGrid, input);

        myGrid.printGrid();


        while(game.getMoveCount() < size * size) { // The program stops when all cells are set
            p1.makeMove(myGrid, input);
            game.moveCount++;
            myGrid.printGrid();


            if(game.getMoveCount() == size * size) {
                break;
            }
            p2.makeMove(myGrid, input);
            game.moveCount++;
            myGrid.printGrid();


        }
        /*UserInput inputTest = new UserInput();
        System.out.println("Press enter to proceed.");
        inputTest.pressEnterToProceed();*/
    }
}