import java.util.ArrayList;

public class TheDestroyer extends Player{
    public TheDestroyer(String name, String symbol) {
        super(name + " The Destroyer", symbol);
    }

    @Override
    public int[] makeMove(PlayGrid grid, UserInput input, Game game) {

        // set bestVal so all results will be better!
        int bestScore = -1000;

        // These are the initial moves to be checked
        ArrayList<int[]> availableMoves = new ArrayList<>(grid.getAvailableCells());



        // Holds the best move found so far
        int[] optimalMove = new int[2];

        // We pause until enter is pressed. For suspense!
        System.out.println("\n" + getName() + " (" + symbol +")" + " is thinking of their next move." +
                "\nPress enter to continue.");
        input.pressEnterToProceed();

        // Loop through all free cells of the grid
        for(int[] move : availableMoves){
            // Make the move
            // make the move
            grid.setCell(move[0], move[1], this.symbol);

            // Increase moveCount
            game.increaseMoveCount();

            // For each move, call miniMax to get the score of the move
            int moveScore = minMax(grid, this.opponentPlayer, game, move, 0);
            System.out.println("moveScore: " + moveScore + ", moveCount: " + game.getMoveCount());

            // check if the new score is better than the previous best one, if so, update optimalMove and bestScore
            if(moveScore > bestScore) {
                bestScore = moveScore;
                optimalMove[0] = move[0];
                optimalMove[1] = move[1];
            }
            // Reset the move
            grid.resetCell(move[0], move[1]);

            // Reset moveCount
            game.decreaseMoveCount();
        }
        grid.setCell(optimalMove[0], optimalMove[1], symbol);
        game.increaseMoveCount();
        return optimalMove;
    }

    public int minMax(PlayGrid grid, Player currentPlayer, Game game,
                      int[] move, int depth) {
        int score;
        ArrayList<int[]> availableMoves = new ArrayList<>(grid.getAvailableCells());



        if(grid.checkWin(move[0], move [1], currentPlayer.opponentPlayer.symbol)) {

            grid.printGrid();
            if(currentPlayer.opponentPlayer == this) {
                score = 10;
            } else {
                score = -10;
            }
            return score;
        } else if(game.getMoveCount() == grid.getSize() * grid.getSize()) {
            return 0;
        }


        if(currentPlayer == this) {
            int best = -1000;
            for(int[] nextMove: availableMoves) {
                grid.setCell(nextMove[0], nextMove[1], currentPlayer.symbol);
                game.increaseMoveCount();

                int currentScore = minMax(grid, currentPlayer.opponentPlayer, game,
                        nextMove, depth + 1);
                System.out.println("currentScore: " + currentScore);
                if(currentScore > best) {
                    best = currentScore;
                }
                grid.resetCell(nextMove[0], nextMove[1]);
                game.decreaseMoveCount();
            }
            return best;
        } else {
            int best = 1000;
            for(int[] nextMove: availableMoves) {
                grid.setCell(nextMove[0], nextMove[1], currentPlayer.symbol);
                game.increaseMoveCount();
                currentPlayer = this;
                int currentScore = minMax(grid, currentPlayer.opponentPlayer, game,
                        nextMove, depth + 1);
                if(currentScore < best) {
                    best = currentScore;
                }
                grid.resetCell(nextMove[0], nextMove[1]);
                game.decreaseMoveCount();
            }
            return best;
        }



    }
}
