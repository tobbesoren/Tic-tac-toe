import java.util.ArrayList;
import java.util.Random;

public class TheDiceMan extends Player{

    public TheDiceMan() {
        super("The Dice Man", "O");
    }

    public boolean makeMove(PlayGrid grid, UserInput input, Game game) {
        /* Lets a player make a move. */
        boolean gameOver = false;
        System.out.println(getName() + ", is thinking of his next move. \nPress enter to continue.");
        input.pressEnterToProceed();

        ArrayList<int[]> availableCells = new ArrayList<>(grid.getAvailableCells());

        Random rand = new Random();

        int botMove = rand.nextInt(availableCells.size());

        int[] coordinates = new int[]{availableCells.get(botMove)[0], availableCells.get(botMove)[1]};// for converted input
        grid.setCell(coordinates[0], coordinates[1], symbol);
        game.increaseMoveCount();

        // I think I should move this to Game class...
        if(grid.checkWin(coordinates[0], coordinates[1], symbol)) {
            System.out.println(getName() + " wins!");
            increaseScore();
            gameOver = true;
        } else if(game.getMoveCount() == grid.getSize() * grid.getSize()) {
            System.out.println("It's a draw!");
            gameOver = true;
        }
        return gameOver;
    }
}
