public class HumanPlayer extends Player {
    /*
    Subclass of Player, used for human players, as the name implies.
    The method makeMove takes user input to add a symbol to the grid.
     */
    public HumanPlayer(String name, String symbol) {
        super(name, symbol);
    }
    @Override
    public int[] makeMove(PlayGrid grid, UserInput input, Game game) {
        /* Lets a player make a move. Calls grid.setCell(), which checks that the cell is unoccupied.
        Uses catch - try to make sure the move is within the board.
        Returns the coordinates as an array of ints. */

        System.out.println(getName() + " (" + symbol +")" + ", make your move! (Enter row number and column number, separated by a space.)");

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

