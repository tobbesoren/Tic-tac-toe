public class Player {
    /*
    The Player class is used for a human player and holds the players name, the symbol used on the board,
    and the cumulated score.
    It has methods for getting score and name, increasing score, and making a move on a board.
     */
    protected final String name;
    protected final String symbol;
    protected int score;

    public Player (String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = 0;

    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        this.score++;
    }

    public int[] makeMove(PlayGrid grid, UserInput input, Game game) {
        /* Lets a player make a move. Calls grid.setCell(), which checks that the cell is unoccupied.
        Uses catch - try to make sure the move is within the board.
        Returns the coordinates as an array of ints. */

        System.out.println(getName() + ", make your move!");

        int[] coordinates = new int[2];

        boolean endLoop = false;

        while(!endLoop) {
            try {
                coordinates = input.coordinatesInput();
                endLoop = grid.setCell(coordinates[0], coordinates[1], symbol);
            } catch(IndexOutOfBoundsException i) {
                System.out.println("Please keep your moves within the board! Try again.");
            }
        }
        game.increaseMoveCount();

        return coordinates;
    }
}
