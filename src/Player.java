public abstract class Player {
    /*
    The Player class is the parent class for HumanPlayer, TheDiceMan, BetterBot and TheDestroyer.
    It defines variables to hold the players name, the symbol used on the board, and the accumulated score.
    It has methods for getting score and name, increasing score, and an abstract makeMove method which is defined in
    the subclasses.
     */
    protected final String name;
    protected final String symbol;
    protected int score;

    protected Player opponentPlayer;

    public Player (String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = 0;

    }

    public String getName() {
        return name;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public void setOpponentPlayer(Player opponentPlayer) {
        this.opponentPlayer = opponentPlayer;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        this.score++;
    }

    public abstract int[] makeMove(PlayGrid grid, UserInput input, Game game);
}
