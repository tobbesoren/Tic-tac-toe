public abstract class Player {
    /*
    The Player class is used as a template for HumanPlayer and TheDiceMan.
    It defines variables to hold the players name, the symbol used on the board, and the accumulated score.
    It has methods for getting score and name, increasing score, and an abstract makeMove method which is defined in
    the subclasses.
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

    public abstract int[] makeMove(PlayGrid grid, UserInput input, Game game);
}
