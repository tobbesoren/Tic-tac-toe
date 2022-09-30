import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static Scanner input = new Scanner(System.in);
    private ArrayList<Player> players = new ArrayList<>(); // maybe this should be an Array instead?
    private PlayGrid grid;
    public int moveCount = 0; // keeps track of total number of moves made by both players

    public Game(Player p1, Player p2, PlayGrid grid) {
        players.add(p1);
        players.add(p2);
        this.grid = grid;
    }

    /*public void makeMove(Player player) {
        *//* Lets a player make a move. Uses a static Scanner object to read input as String.
         Tries to convert String to array of ints. Catches NumberFormatException and
         IndexOutOfBoundsException to prevent the program from crashing if invalid input is given. *//*

        System.out.println(player.getName() + ", make your move!(rowNumber columnNumber)");

        String[] coordinatesString; // for raw input

        int[] coordinates = new int[2]; // for converted input

        while(true) {
            try {
                coordinatesString = input.nextLine().split(" ");
                for(int i = 0; i < coordinatesString.length; i++) {
                    coordinates[i] = Integer.parseInt(coordinatesString[i]) - 1;
                }
                if (grid.getCell(coordinates[0], coordinates[1]).equals(" ")) {
                    grid.setCell(coordinates[0], coordinates[1], player.getSymbol());
                    moveCount++;
                    //System.out.println("!" + coordinates[0] + coordinates[1]);
                    //player.setLastMove(coordinates[0], coordinates[1]);
                    player.setLastRow(coordinates[0]);
                    player.setLastColumn(coordinates[1]);
                    break; // if the player input is ok, we hit the break-statement and get out of the loop.
                } else {
                    System.out.println("Space occupied! Try again.");
                }
            }
            catch(NumberFormatException e1) { // invalid input prints a message and runs the loop again
                System.out.println("Please enter co-ordinates (row and column) with just a space in between.");
            }
            catch(IndexOutOfBoundsException e2) {
                System.out.println("Keep your moves within the board!");
            }
        }
    }*/

    public int getMoveCount() {
        return moveCount;
    }

    public void takeTurns() {
        players.get(0).makeMove(grid);
        players.get(1).makeMove(grid);
    }
}
