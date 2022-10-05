import java.util.ArrayList;


public class Game {
    /*
    A class that sets up and runs the game. Holds Players, PlayGrid and userInput instances.
    Keeps track of total moves made (in moveCount).
     */
    private final ArrayList<Player> players = new ArrayList<>(); // maybe this should be an Array instead?
    private PlayGrid grid;
    public int moveCount = 0; // keeps track of total number of moves made by both players
    public UserInput input;

    public Game() {
        this.input = new UserInput();
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void increaseMoveCount() {
        moveCount++;
    }

    public void mainMenu() {
        System.out.println("""
                *** Tic-Tac-Toe DX ***
                
                ---Make a selection---
                   1: One player game
                   2: Two player game
                   3: Exit
                ----------------------""");
        while(true) {
            int selection = input.intInput();

            switch (selection) {
                case 1 -> onePlayerGame();

                case 2 -> twoPlayerGame();
                case 3 -> {
                    System.out.println("Bye!");
                    System.exit(0);
                }
                default -> System.out.println("Please choose one of the options: 1, 2 or 3.");

            }
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void continueMenu() {
        System.out.println(players.get(0).getName() + ": " + players.get(0).getScore() + " points.");
        System.out.println(players.get(1).getName() + ": " + players.get(1).getScore() + " points.");
        System.out.println("""
                                                    
                                                    
                                                    
                            ---Wanna play again?---
                               1. Yes
                               2. No
                            -----------------------""");

        while (true) {
            int selection = input.intInput();

            switch (selection) {
                case 1 -> {
                    grid = new PlayGrid(grid.getSize(), grid.getWinningRowLength());
                    grid.printGrid();
                    moveCount = 0;
                    takeTurns();
                }
                case 2 -> {
                    players.clear();
                    moveCount = 0;
                    mainMenu();
                }
                default -> System.out.println("Please choose one of the options: 1 or 2");

            }
        }
    }

    public void onePlayerGame() {
        System.out.println("Player one, enter your name: ");
        players.add(new Player(input.stringInput(), "X"));
        System.out.println("Playing against THE DICE MAN! ");
        players.add(new TheDiceMan());

        System.out.println("Enter playing board size (3 is strongly advised): ");
        int size = input.intInput();
        System.out.println("Enter how many symbols in a row is needed to win");
        int winningRowLength = input.intInput();
        grid = new PlayGrid(size, winningRowLength);
        grid.printGrid();

        takeTurns();

    }

    public void twoPlayerGame() {

        System.out.println("Player one, enter your name: ");
        players.add(new Player(input.stringInput(), "X"));
        System.out.println("Player two, enter your name: ");
        players.add(new Player(input.stringInput(), "O"));

        System.out.println("Enter playing board size (3 is strongly advised): ");
        int size = input.intInput();
        System.out.println("Enter how many symbols in a row is needed to win");
        int winningRowLength = input.intInput();
        grid = new PlayGrid(size, winningRowLength);
        grid.printGrid();

        takeTurns();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void takeTurns() {

        while (true) {

            int[] coordinates;
            boolean gameOver = false;

            for (Player currentPlayer : players) {
                coordinates = currentPlayer.makeMove(grid, input, this);

                if(grid.checkWin(coordinates[0], coordinates[1], currentPlayer.symbol)) {
                    System.out.println(currentPlayer.getName() + " wins!");
                    currentPlayer.increaseScore();
                    gameOver = true;
                } else if(getMoveCount() == grid.getSize() * grid.getSize()) {
                    System.out.println("It's a draw!");
                    gameOver = true;
                }

                grid.printGrid();
                if (gameOver) {
                    continueMenu();
                }


            }
        }
    }


}
