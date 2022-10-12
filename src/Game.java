import java.util.ArrayList;
import java.util.Random;


public class Game {
    /*
    A class that sets up and runs the game. Holds Players, PlayGrid and userInput instances.
    Keeps track of total moves made (in moveCount) and the playing order of the Players (in playOrder).
    Has methods for menus, for adding Players and PlayGrid, and for running the game.
     */
    private Player playerOne;
    private Player playerTwo;
    private final ArrayList<Player> playOrder = new ArrayList<>(); // keeps track of who is going first
    private PlayGrid playGrid;
    private int moveCount; // keeps track of total number of moves made by both players
    private final UserInput input;

    public Game() {
        this.input = new UserInput();
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void increaseMoveCount() {
        moveCount++;
    }

    public void decreaseMoveCount() {
        moveCount--;
    }

    public void mainMenu() {

        System.out.println("""
                
                
                *** Tic-Tac-Toe DX ***
                
                ---Make a selection:---
                   1: Start game
                   2: Exit
                -----------------------""");
        while(true) {
            int selection = input.intInput();

            switch (selection) {
                case 1 -> startGame();
                case 2 -> {
                    System.out.println("Bye!");
                    System.exit(0);
                }
                default -> System.out.println("Please choose one of the options: 1 or 2");

            }
        }
    }

    public void startGame() {
        /*
        Lets the user set up the game, creates Players and PlayGrid, and runs takeTurns().
        */

        playerOne = addPlayer(1, "X");
        playerTwo = addPlayer(2, "O");

        playerOne.setOpponentPlayer(playerTwo);
        playerTwo.setOpponentPlayer(playerOne);

        int size;
        int winningRowLength;
        boolean wrongInput = true;

        // TheDestroyer is a conservative bot; it only plays classic three-by-three tic-tac-toe
        if(playerOne instanceof TheDestroyer || playerTwo instanceof TheDestroyer) {
            size = 3;
        } else {
            // We other may choose play field size
            System.out.println("Enter playing board size (a number between 3 and 15): ");

            do {
                size = input.intInput();
                if (size >= 3 && size <= 15) {
                    wrongInput = false;
                } else {
                    System.out.println("I think I told you to enter a number between 3 and 15!? Try again.");
                }
            } while (wrongInput);
        }


            // when grid size is 3, winningRowLength is also set to 3.
            if (size == 3) {
                winningRowLength = 3;
            } else {
                // With a bigger board, the user get to choose
                System.out.println("Enter how many symbols in a row is needed to win (a number between 3 and " + size + "):");
                wrongInput = true;

                do {
                    winningRowLength = input.intInput();
                    if (winningRowLength >= 3 && winningRowLength <= size) {
                        wrongInput = false;
                    } else {
                        System.out.println("*Sigh* Try again. A number between 3 and " + size + ", please.");
                    }
                } while (wrongInput);
            }


        initGrid(size, winningRowLength);

        takeTurns();
    }

    public Player addPlayer(int playerNumber, String symbol) {

        System.out.print("---Is player " + playerNumber + ": -------\n");
        System.out.println("""
                   1. Human
                      or
                   2. The Dice Man
                      or
                   3. The Better Bot
                      or
                   4. The Destroyer
                      ?
                -----------------------""");

        Player newPlayer;

        while(true) {
            int selection = input.intInput();
            switch (selection) {
                case 1 -> {
                    System.out.println("Enter your name:");
                    newPlayer = new HumanPlayer(input.stringInput(), symbol);
                    return newPlayer;
                }
                case 2 -> {
                    System.out.println("Enter the bot's nickname:");
                    newPlayer = new TheDiceMan(input.stringInput(), symbol);
                    return newPlayer;
                }
                case 3 -> {
                    System.out.println("Enter the bot's nickname:");
                    newPlayer = new BetterBot(input.stringInput(), symbol);
                    return newPlayer;
                }
                case 4 -> {
                    System.out.println("Enter the bot's nickname:");
                    newPlayer = new TheDestroyer(input.stringInput(), symbol);
                    return newPlayer;
                }
                default -> System.out.println("Please enter a single number between 1 and 4! Nothing else. How hard could it be?");
            }
        }
    }

    public void initGrid(int size, int winningRowLength) {
        /*
        Sets up, or resets, the grid and sets moveCount to 0, clears playOrder list and randomly builds it by
        calling coinToss.
        */
        playGrid = new PlayGrid(size, winningRowLength);
        moveCount = 0;
        playOrder.clear();
    }

    public void coinToss() {
        /*
        Adds the Players to playOrder, in random order, and prints a message telling who goes first.
         */
        Random rand = new Random();
        int coinToss = rand.nextInt(2);

        if(coinToss == 1) {
            playOrder.add(playerOne);
            playOrder.add(playerTwo);

        } else {
            playOrder.add(playerTwo);
            playOrder.add(playerOne);
        }
        System.out.println("The coin is tossed. " + playOrder.get(0).getName() + " makes the first move!");
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void takeTurns() {

        playGrid.printGrid();
        coinToss();

        while (true) {

            int[] coordinates;
            boolean gameOver = false;

            for (Player currentPlayer : playOrder) {
                coordinates = currentPlayer.makeMove(playGrid, input, this);

                if(playGrid.checkWin(coordinates[0], coordinates[1], currentPlayer.symbol)) {
                    System.out.println(currentPlayer.getName() + " wins!\n");
                    currentPlayer.increaseScore();
                    gameOver = true;
                } else if(getMoveCount() == playGrid.getSize() * playGrid.getSize()) {
                    System.out.println("It's a draw!\n");
                    gameOver = true;
                }

                playGrid.printGrid();
                if (gameOver) {
                    System.out.print("\n");
                    System.out.println(playerOne.getName() + ": " + playerOne.getScore() + " points.");
                    System.out.println(playerTwo.getName() + ": " + playerTwo.getScore() + " points.");
                    continueMenu();
                }
            }
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void continueMenu() {

        System.out.println("""
                            
                            ---Wanna play again?---
                               1. Yes
                               2. No
                            -----------------------""");

        while (true) {
            int selection = input.intInput();

            switch (selection) {
                case 1 -> {
                    initGrid(playGrid.getSize(), playGrid.getWinningRowLength());
                    takeTurns();
                }
                case 2 -> {
                    playOrder.clear();
                    moveCount = 0;
                    mainMenu();
                }
                default -> System.out.println("Please choose one of the options: 1 or 2");

            }
        }
    }
}
