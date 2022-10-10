import java.util.ArrayList;
import java.util.Random;

public class BetterBot extends Player{
    /*
    The Better Bot checks for winning moves, and to prevent the opponents winning moves.
    Doesn't work well on bigger boards, since all other moves are totally random. On a three-by-three grid,
    however, it is a definitive improvement.
     */
    Random rand;

    public BetterBot(String name, String symbol) {
        super(name + " The Better Bot", symbol);
        this.rand = new Random();
    }

    @Override
    public int[] makeMove(PlayGrid grid, UserInput input, Game game) {

        System.out.println("\n" + getName() + " (" + symbol +")" + " is thinking of their next move." +
                "\nPress enter to continue.");
        input.pressEnterToProceed(); // we pause until enter is pressed. For suspense!

        ArrayList<int[]> availableCells = new ArrayList<>(grid.getAvailableCells());
        // Checks for a winning move
        for(int[] move : availableCells) {
            if(grid.tryCell(move[0], move[1], symbol)) {
                grid.setCell(move[0], move[1], symbol);
                game.increaseMoveCount();
                System.out.println("Move: " + (move[0] + 1) + ", " + (move[1] + 1) + "\n");
                return move;
            }
        }
        // Checks if the opponent is about to win; if so, blocks the cell
        for(int[] move : availableCells) {
            if(grid.tryCell(move[0], move[1], opponentPlayer.symbol)) {
                grid.setCell(move[0], move[1], symbol);
                game.increaseMoveCount();
                System.out.println("Move: " + (move[0] + 1) + ", " + (move[1] + 1) + "\n");
                return move;
            }
        }

        // Otherwise, makes a random move
        int botMove = rand.nextInt(availableCells.size());

        int[] randomMove = new int[]{availableCells.get(botMove)[0], availableCells.get(botMove)[1]};
        grid.setCell(randomMove[0], randomMove[1], symbol);
        game.increaseMoveCount();
        System.out.println("Move: " + (randomMove[0] + 1) + ", " + (randomMove[1] + 1) + "\n");

        return randomMove;
    }
}
