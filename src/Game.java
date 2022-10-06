import java.util.ArrayList;
import java.util.Random;


public class Game {
    /*
    A class that sets up and runs the game. Holds Players, PlayGrid and userInput instances.
    Keeps track of total moves made (in moveCount) and the playing order of the Players (in playOrder).
     */
    private Player playerOne;
    private Player playerTwo;
    private final ArrayList<Player> playOrder = new ArrayList<>(); // keeps track of who is going first
    private PlayGrid grid;
    public int moveCount; // keeps track of total number of moves made by both players
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

    public void initGrid(int size, int winningRowLength) {
        /*
        Sets up, or resets, the grid and sets moveCount to 0, clears playOrder list and randomly builds it by
        calling coinToss.
        */
        grid = new PlayGrid(size, winningRowLength);
        moveCount = 0;
        playOrder.clear();
        coinToss();
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
        System.out.println("The coin is tossed. " + playOrder.get(0).getName() + " makes the first move!\n");
    }
    
    public void mainMenu() {
        System.out.println("""
                *** Tic-Tac-Toe DX ***
                
                ---Make a selection:---
                   1: Start game
                   2: Exit
                ----------------------""");
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
                    initGrid(grid.getSize(), grid.getWinningRowLength());
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

    public void drawLine() {
        System.out.println("----------------------");
    }

    /*public void onePlayerGame() {
        System.out.println("Player one, enter your name: ");
        playerOne = new Player(input.stringInput(), "X");
        drawLine();
        System.out.println("Playing against THE DICE MAN! ");
        drawLine();
        playerTwo = new TheDiceMan();

        System.out.println("Enter playing board size (a number between 3 and 15): ");
        boolean wrongInput = true;
        int size = 0;
        do {
            int selection = input.intInput();
            if(selection  >= 3 && selection <= 15) {
                size = selection;
                wrongInput = false;
            } else {
                System.out.println("I think I told you to enter a number between 3 and 15!? Try again.");
            }
        } while(wrongInput);
        
        drawLine();
        System.out.println("Enter how many symbols in a row is needed to win");
        int winningRowLength = input.intInput();
        drawLine();
        initGrid(size, winningRowLength);

        takeTurns();

    }

    public void twoPlayerGame() {

        System.out.println("Player one, enter your name: ");
        playOrder.add(new Player(input.stringInput(), "X"));
        drawLine();
        System.out.println("Player two, enter your name: ");
        playOrder.add(new Player(input.stringInput(), "O"));
        drawLine();

        System.out.println("Enter playing board size (3 is strongly advised): ");
        int size = input.intInput();
        drawLine();
        System.out.println("Enter how many symbols in a row is needed to win");
        int winningRowLength = input.intInput();
        drawLine();

        initGrid(size, winningRowLength);

        takeTurns();
    }*/

    public void startGame() {
        /*
        Lets the user set up the game, creates Players and PlayGrid, and runs takeTurns().
        */

        playerOne = addPlayer(1, "X");
        playerTwo = addPlayer(2, "O");

        System.out.println("Enter playing board size (a number between 3 and 15): ");

        boolean wrongInput = true;
        int size;

        do {
            size = input.intInput();
            if(size  >= 3 && size <= 15) {
                wrongInput = false;
            } else {
                System.out.println("I think I told you to enter a number between 3 and 15!? Try again.");
            }
        } while(wrongInput);


        int winningRowLength;

        if(size == 3) {
            winningRowLength = 3;
        } else {
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

        System.out.print("---Is player " + playerNumber + ": ------\n");
        System.out.println("""
                   1. Human
                      or
                   2. Bot
                      ?
                ----------------------""");

        Player newPlayer;

        while(true) {
            int selection = input.intInput();
            switch (selection) {
                case 1 -> {
                    System.out.println("Enter your name:");
                    newPlayer = new Player(input.stringInput(), symbol);
                    return newPlayer;
                }
                case 2 -> {
                    System.out.println("Enter the bot's nickname:");
                    newPlayer = new TheDiceMan(input.stringInput(), symbol);
                    return newPlayer;
                }
                default -> System.out.println("Please enter 1 or 2! Nothing else. How hard could it be?");
            }
        }
    }



    @SuppressWarnings("InfiniteLoopStatement")
    public void takeTurns() {

        grid.printGrid();

        while (true) {

            int[] coordinates;
            boolean gameOver = false;

            for (Player currentPlayer : playOrder) {
                coordinates = currentPlayer.makeMove(grid, input, this);

                if(grid.checkWin(coordinates[0], coordinates[1], currentPlayer.symbol)) {
                    System.out.println(currentPlayer.getName() + " wins!");
                    drawLine();
                    currentPlayer.increaseScore();
                    gameOver = true;
                } else if(getMoveCount() == grid.getSize() * grid.getSize()) {
                    System.out.println("It's a draw!");
                    drawLine();
                    gameOver = true;
                }

                grid.printGrid();
                if (gameOver) {
                    System.out.print("\n");
                    System.out.println(playOrder.get(0).getName() + ": " + playOrder.get(0).getScore() + " points.");
                    System.out.println(playOrder.get(1).getName() + ": " + playOrder.get(1).getScore() + " points.");
                    continueMenu();
                }
            }
        }
    }


}
