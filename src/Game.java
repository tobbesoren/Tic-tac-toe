import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static Scanner input = new Scanner(System.in);
    private ArrayList<Player> players = new ArrayList<>();

    private PlayGrid grid;

    public Game(Player p1, Player p2, PlayGrid grid) {
        players.add(p1);
        players.add(p2);
        this.grid = grid;
    }

    public void makeMove(Player player) {

        System.out.println(player.getName() + ", make your move!");

        String[] moveStringArray = input.nextLine().split(", ");

        int[] moveIntArray = new int[2];

        for(int i = 0; i < moveStringArray.length; i++) {
            moveIntArray[i] = Integer.parseInt(moveStringArray[i]) - 1;
        }
        System.out.println(moveIntArray[0] + ", " + moveIntArray[1]);
        grid.getGrid().get(moveIntArray[0]).set(moveIntArray[1], "x");

    }
}
