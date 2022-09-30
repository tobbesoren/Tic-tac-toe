import java.util.Scanner;

public class Player {
    private String name;
    private String symbol;
    private int score;
    //private int[] lastMove;

    int lastRow;
    int lastColumn;

    public Player (String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        //this.lastMove = new int[2];
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

    /*public int[] getLastMove() {
        return lastMove;
    }

    public void setLastMove(int row, int column) {
        this.lastMove[0] = row;
        this.lastMove[1] = column;
    } */

    public int getLastRow() {
        return lastRow;
    }

    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }

    public int getLastColumn() {
        return lastColumn;
    }

    public void setLastColumn(int lastColumn) {
        this.lastColumn = lastColumn;
    }

    public void makeMove(PlayGrid grid) {
        /* Lets a player make a move. Uses a static Scanner object to read input as String.
         Tries to convert String to array of ints. Catches NumberFormatException and
         IndexOutOfBoundsException to prevent the program from crashing if invalid input is given. */

        System.out.println(getName() + ", make your move!(rowNumber columnNumber)");

        String[] coordinatesString; // for raw input

        int[] coordinates = new int[2]; // for converted input

        boolean endLoop = false;

        while(!endLoop) {
            try {
                coordinatesString = Game.input.nextLine().split(" ");
                for(int i = 0; i < coordinatesString.length; i++) {
                    coordinates[i] = Integer.parseInt(coordinatesString[i]) - 1;
                }
                endLoop = grid.setCell(coordinates[0], coordinates[1], symbol);
            }
            catch(NumberFormatException e1) { // invalid input prints a message and runs the loop again
                System.out.println("Please enter co-ordinates (row and column) with just a space in between.");
            }
            catch(IndexOutOfBoundsException e2) {
                System.out.println("Keep your moves within the board!");
            }
        }
        if(grid.checkWin(coordinates[0], coordinates[1], symbol)) {
            System.out.println("You win!");
            System.exit(0);
        }
    }
}
