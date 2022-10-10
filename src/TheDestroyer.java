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

        int bestScore = -1000; // set bestVal so all results will be better!
        /*String opponentSymbol;
        if (symbol.equals("X")) opponentSymbol = "O";
        else opponentSymbol = "X";*/

        ArrayList<int[]> availableMoves = new ArrayList<>(grid.getAvailableCells());// These are the initial moves to be checked

        int[] optimalMove = new int[2];

        System.out.println("\n" + getName() + " (" + symbol +")" + " is thinking of their next move." +
                "\nPress enter to continue.");
        input.pressEnterToProceed(); // we pause until enter is pressed. For suspense!

        for(int[] move : availableMoves){
            // make the move
            grid.setCell(move[0], move[1], this.symbol);
            game.increaseMoveCount();

            // evaluate the move
            int moveScore = minMax(grid, opponentPlayer, game, grid.getAvailableCells(), move, 0);
            System.out.println("moveScore: " + moveScore + ", moveCount: " + game.getMoveCount());

            // Undo the move
            grid.resetCell(move[0], move[1]);
            game.decreaseMoveCount();

            // check if the new score is better than the previous best one, if so, update optimalMove and bestScore
            if(moveScore > bestScore) {
                bestScore = moveScore;
                optimalMove[0] = move[0];
                optimalMove[1] = move[1];
            }
        }
        grid.setCell(optimalMove[0], optimalMove[1], symbol);
        game.increaseMoveCount();
        return optimalMove;
    }

    public int minMax(PlayGrid playState, Player currentPlayer, Game game,
                          ArrayList<int[]> availableMoves, int[] move, int depth) {
        int score;
        if(playState.checkWin(move[0], move [1], currentPlayer.symbol)) {
            if(currentPlayer == this) {
                score = 10;
            } else {
                score = -10;
            }
            return score;
        } else if(game.getMoveCount() == playState.getSize() * playState.getSize()) {
            return 0;
        }


        if(currentPlayer == this) {
            int best = -1000;
            for(int[] nextMove: availableMoves) {
                playState.setCell(nextMove[0], nextMove[1], currentPlayer.symbol);
                game.increaseMoveCount();
                currentPlayer = currentPlayer.opponentPlayer;
                int currentScore = minMax(playState, currentPlayer, game,
                        playState.getAvailableCells(), nextMove, depth + 1);
                if(currentScore > best) {
                    best = currentScore;
                }
                playState.resetCell(nextMove[0], nextMove[1]);
                game.decreaseMoveCount();
            }
            return best;
        } else {
            int best = 1000;
            for(int[] nextMove: availableMoves) {
                playState.setCell(nextMove[0], nextMove[1], currentPlayer.symbol);
                game.increaseMoveCount();
                currentPlayer = this;
                int currentScore = minMax(playState, currentPlayer, game,
                        playState.getAvailableCells(), nextMove, depth + 1);
                if(currentScore < best) {
                    best = currentScore;
                }
                playState.resetCell(nextMove[0], nextMove[1]);
                game.decreaseMoveCount();
            }
            return best;
        }



    }
}
