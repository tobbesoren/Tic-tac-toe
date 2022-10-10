import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class TheDestroyer extends Player{
    public TheDestroyer(String name, String symbol) {
        super(name + " The Destroyer", symbol);
    }

    @Override
    public int[] makeMove(PlayGrid grid, UserInput input, Game game) {

        String opponentSymbol;
        if (symbol.equals("X")) opponentSymbol = "O";
        else opponentSymbol = "X";

        ArrayList<int[]> availableMoves = new ArrayList<>(grid.getAvailableCells());
        ArrayList<Integer> scores = new ArrayList<>();
        int[] optimalMove;

        System.out.println("\n" + getName() + " (" + symbol +")" + " is thinking of their next move." +
                "\nPress enter to continue.");
        input.pressEnterToProceed(); // we pause until enter is pressed. For suspense!

        for(int[] move : availableMoves){
            int depth = 0;
            scores.add(minMax(grid, this, move, depth));
        }
        int bestScore = Collections.max(scores);
        optimalMove = availableMoves.get(scores.indexOf(bestScore));

        return optimalMove;
    }

    public Integer minMax(PlayGrid playState, Player thisPlayer, int[] move, int depth) {
        return 0;
    }
}
