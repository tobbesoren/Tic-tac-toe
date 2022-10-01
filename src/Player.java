public class Player {
    private final String name;
    private final String symbol;
    private int score;

    public Player (String name, String symbol) {
        this.name = name;
        this.symbol = symbol;

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

    public boolean makeMove(PlayGrid grid, UserInput input, Game game) {
        /* Lets a player make a move. */
        boolean gameOver = false;
        System.out.println(getName() + ", make your move!(rowNumber columnNumber)");

        int[] coordinates = new int[2]; // for converted input

        boolean endLoop = false;

        while(!endLoop) {
            try {
                coordinates = input.coordinatesInput();
                endLoop = grid.setCell(coordinates[0], coordinates[1], symbol);
            } catch(IndexOutOfBoundsException i) {
                System.out.println("Please keep your moves within the board! Try again.");
            }
        }
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
