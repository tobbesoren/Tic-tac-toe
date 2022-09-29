public class Player {
    private String name;
    private String symbol;
    private int score;
    private int[] lastMove;

    public Player (String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.lastMove = new int[2];
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        this.score++;
    }

    public int[] getLastMove() {
        return lastMove;
    }

    public void setLastMove(int row, int column) {
        this.lastMove[0] = row;
        this.lastMove[1] = column;
    }
}
