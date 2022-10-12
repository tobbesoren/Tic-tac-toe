import java.util.ArrayList;

public class TheDestroyer extends Player{
    /*
    Subclass of the Player class.
    Uses the miniMax algorithm to play a perfect game of tic-tac-toe.
    A grid by the size of 3 x 3 is what my computer can handle; more than that and the complexity is too large.
    I think it should be possible to optimize it, though. If we check for symmetry by flipping and rotating the board,
    there are a lot of duplicate moves, especially the first one. By checking this, it should be possible to reduce
    the runtime and maybe, MAYBE have a larger grid.
    I read up on the miniMax algorithm on different places on internet, and especially this page helped me:
    https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/
    It was written by Akshay L Aradhya, and if this works, it's because of their fine work.
    If it doesn't, it's because I screwed up something.
     */
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
            grid.setCell(move[0], move[1], this.symbol);

            // Increase moveCount
            game.increaseMoveCount();

            // For each move, call miniMax to get the score of the move
            int moveScore = minMax(grid, this.opponentPlayer, game, move, 0);

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

        // Score will hold the move's score if the game is ended
        int score;

        // Check if the last was a winning one. Remember, it was the currentPlayer's opponent who made that move.
        if(grid.checkWin(move[0], move [1], currentPlayer.opponentPlayer.symbol)) {

            // If the winning player was theDestroyer (the maximizer), set score to (number of cells +1) - depth
            // This gives a faster win a higher score
            if(currentPlayer.opponentPlayer == this) {
                score = (grid.getSize() * grid.getSize() + 1) - depth;
            }
            // If the winner was the opponent (the minimizer), set score to -(number of cells + 1) + depth
            // This gives a faster loss a lower score.
            else {
                score = -(grid.getSize() * grid.getSize() + 1) + depth;
            }
            // Return the score
            return score;
        }
        // If there are no moves left, it is a draw and 0 is returned.
        else if(game.getMoveCount() == grid.getSize() * grid.getSize()) {
            return 0;
        }

        // Let's get the remaining free cells from the grid so we can loop through them.
        ArrayList<int[]> availableMoves = new ArrayList<>(grid.getAvailableCells());

        // If it is theDestroyer's turn
        if(currentPlayer == this) {
            // We start by setting best score to very low...
            int best = -1000;

            // Loop through the available moves
            for(int[] nextMove: availableMoves) {

                // Make the move
                grid.setCell(nextMove[0], nextMove[1], currentPlayer.symbol);
                // Increase moveCount
                game.increaseMoveCount();

                // Call miniMax to get the best score for the move
                int currentScore = minMax(grid, currentPlayer.opponentPlayer, game,
                        nextMove, depth + 1);

                // If the score is higher than the currently best score, set best to score.
                if(currentScore > best) {
                    best = currentScore;
                }
                // Reset the move
                grid.resetCell(nextMove[0], nextMove[1]);
                // Reset moveCount
                game.decreaseMoveCount();
            }
            // Return the highest score
            return best;
        }

        // If it is the opponent's turn
        else {
            // Set best to a high value
            int best = 1000;
            // Loop through the available moves
            for(int[] nextMove: availableMoves) {
                // Make the move
                grid.setCell(nextMove[0], nextMove[1], currentPlayer.symbol);
                // Increase moveCount
                game.increaseMoveCount();

                // Call miniMax to get the lowest score for the move
                int currentScore = minMax(grid, currentPlayer.opponentPlayer, game,
                        nextMove, depth + 1);
                // If the current score is lower than the best one, set best to currentScore
                if(currentScore < best) {
                    best = currentScore;
                }
                // Reset move
                grid.resetCell(nextMove[0], nextMove[1]);
                // Decrease moveCount
                game.decreaseMoveCount();
            }
            // Return the lowest score
            return best;
        }
    }
}
