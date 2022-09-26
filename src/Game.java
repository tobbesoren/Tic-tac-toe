import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players = new ArrayList<>();

    public Game(Player p1, Player p2) {
        players.add(p1);
        players.add(p2);
    }
}
