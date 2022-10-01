import java.util.Scanner;

public class UserInput {

    Scanner input;

    public UserInput() {
        this.input = new Scanner(System.in);
    }

    public String stringInput() {
        while (true) {


            String string = this.input.nextLine();
            if (string.equals("")) {
                System.out.println("You must enter something! Try again.");
            } else {
                return string;
            }
        }
    }

    public int intInput() {
        /* Using string input for easier error handling. This code only allows ONE integer to be entered; when I used
        nextInt, it was possible to enter two integers separated by space (but only the first one was used)
         */
        String currentInputString;
        while(true) {
            try {
                 currentInputString = input.nextLine();
                 return Integer.parseInt(currentInputString);
            } catch(NumberFormatException n) {
                System.out.println("Please enter a single integer! Try again.");
            }
        }

    }

    public int[] coordinatesInput() {

        String[] coordinatesString; // for raw input
        int[] coordinates = new int[2]; // for converted input

        while(true) {
            try {
                coordinatesString = this.input.nextLine().split(" ");
                for (int i = 0; i < 2; i++) {
                    coordinates[i] = Integer.parseInt(coordinatesString[i]) - 1; // converts from user input to
                                                                                 // zero-indexing!
                }
                return coordinates;
            } catch (NumberFormatException n) {
                System.out.println("Please enter co-ordinates (row and column) with just a space in between.");
            } catch(IndexOutOfBoundsException i) {
                System.out.println("Please enter TWO numbers; row and column.");
            }
        }
    }

    public void pressEnterToProceed() {
        this.input.nextLine();
    }
}
