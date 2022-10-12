import java.util.ArrayList;
import java.util.Random;

public class TheDiceMan extends Player{
    /*
    Extends Player, by overriding makeMove() method. Uses a Random object to choose among available grid cells.
     */
    Random rand;

    public TheDiceMan(String name, String symbol) {
        super(name + " the Dice Man", symbol);
        this.rand = new Random();
    }

    @Override
    public int[] makeMove(PlayGrid grid, UserInput input, Game game) {
        /*
        Subclass of the Player class.
        The bot randomly selects a move from the available cells, calling grid.availableCells().
        Returns the coordinates as an array of ints.
        */

        System.out.println("\n" + getName() + " (" + symbol +")" + " is thinking of his next move. \nPress enter to continue.");
        input.pressEnterToProceed(); // we pause until enter is pressed. For suspense!

        ArrayList<int[]> availableCells = new ArrayList<>(grid.getAvailableCells());

        int botMove = rand.nextInt(availableCells.size());

        int[] coordinates = new int[]{availableCells.get(botMove)[0], availableCells.get(botMove)[1]};
        grid.setCell(coordinates[0], coordinates[1], symbol);
        game.increaseMoveCount();
        System.out.println("Move: " + (coordinates[0] + 1) + ", " + (coordinates[1] + 1) + "\n");

        return coordinates;
    }
}
