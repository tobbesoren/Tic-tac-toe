import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    //static Scanner input = new Scanner(System.in);
    private ArrayList<Player> players = new ArrayList<>(); // maybe this should be an Array instead?
    private PlayGrid grid;
    public int moveCount = 0; // keeps track of total number of moves made by both players
    public UserInput input;

    public Game(Player p1, Player p2, PlayGrid grid, UserInput input) {
        this.input = input;
        players.add(p1);
        players.add(p2);
        this.grid = grid;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void takeTurns() {
        players.get(0).makeMove(grid, input);
        players.get(1).makeMove(grid, input);
    }
}
