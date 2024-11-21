
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;



public class Chess {

    // ANSI color codes
    public static final String RESET = "\033[0m"; // Reset text color
    public static final String BOLD = "\033[1m"; // Bold text
    public static final String WHITE_BG = "\033[47m"; // White background
    public static final String BLACK_BG = "\033[40m"; // Black background
    public static final String GREEN_TEXT = "\033[38;2;0;128;0m"; // Green text
    public static final String RED_TEXT = "\033[38;2;255;69;0m"; // Red text
    public static final String WHITE_TEXT = "\033[37m"; // White text
    public static final String BLACK_TEXT = "\033[30m"; // Black text

    // Global variables
    public static String fontStyle = BLACK_BG + WHITE_TEXT; // Default font style
    public static final String[] WHITE_PIECES = {"R", "N", "B", "Q", "K", "B", "N", "R", "P"}; // White pieces
    public static final String[] BLACK_PIECES = {"r", "n", "b", "q", "k", "b", "n", "r", "p"}; // Black pieces
    public static String player1; String player2; // Players
    public static Scanner input = new Scanner(System.in); // Scanner object

    // Menu options (hasmap so i can have the user input the number and use the key in the code)
    public static final HashMap<String, Integer> MENU_OPTIONS = new LinkedHashMap<>() {{  
        put("New Game", 1);
        put("Continue Game", 2); // Maybe in the future?
        put("Leadboard", 3);
        put("Exit", 4);
    }}; 


    public static void main(String[] args) {

        // Title
        System.out.println("The Chess Game");
        System.out.println("Play a game of chess in the terminal with your friends.");
        System.out.println();


        // Variables for main
         int menuChoice;

         // 8x8 board represented as a 2D array of strings
            String[][] board = new String[8][8];
        
            menuLoop:
            do {

                // Print the menu soptions
                System.out.println("Menu Options: ");
                MENU_OPTIONS.forEach((key, value) -> System.out.printf("%d: %s \n", value, key));
                System.out.println();


                // Get the user's choice
                System.out.print("Enter the number of the option you would like to select: ");
                menuChoice = input.nextInt();

                switch (menuChoice) {
                    case 1:
                        clearScreen();
                        newGame(board);
                        break menuLoop;
                    case 2:
                        System.out.println("Continue Game");
                        break;
                    case 3:
                        System.out.println("Leadboard");
                        break;
                    case 4:
                        System.out.println("Exiting the game. Goodbye!");
                        continue;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } while(menuChoice != 4 );
    } // End of main method

        // Method to create the board with the chess pieces.
        public static void createBoard(String[][] board) {
            
            for (int row = 0; row < board.length; row++) {

                if (row == 0 || row == 2 || row == 4 || row == 6) {
                    fontStyle = WHITE_BG + BLACK_TEXT + BOLD;
                } else {
                    fontStyle = BLACK_BG + WHITE_TEXT + BOLD;
                }

                for (int col = 0; col < board[row].length; col++) {
                    
                    
                    if (fontStyle.contains(BLACK_BG)) {
                        
                        fontStyle = WHITE_BG +  BOLD;
                    } else {
                        fontStyle = BLACK_BG + BOLD;
                    }

                    if (row == 6 || row == 7) {
                        fontStyle += RED_TEXT;
                    } else if (row == 1 || row == 0) {
                        fontStyle += GREEN_TEXT;
                    }
                    
                    switch (row) {
                        case 0:
                        board[row][col]= fontStyle + " " + WHITE_PIECES[col] + " " + RESET;
                        break;
                        case 7:
                            board[row][col]= fontStyle + " " + BLACK_PIECES[col] + " " + RESET;
                            break;
                        case 1:
                            board[row][col] = fontStyle + " " + WHITE_PIECES[8] + " " + RESET;
                            break;
                        case 6:
                            board[row][col] = fontStyle + " " + BLACK_PIECES[8] + " " + RESET;
                            break;
                        default:
                            board[row][col] = fontStyle + "   " + RESET;
                            break;
                    }
                    
                    
                }
            }
        } // End of createBoard method

    // Method to print the board.
    public static void printBoard(String[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (row == 0 && col == 0) {
                    System.out.print("   a  b  c  d  e  f  g  h\n");
                } 

                if (col == 0) {
                    System.out.print(1 + row + " ");
                }
                System.out.printf("%s", board[row][col]);
            }
            
            System.out.println();
        }
    }
    
    // Method to start a new game
    public static void newGame(String[][] board) {
        System.out.println("New Game Selected");

        // buffer flush
        input.nextLine();

        // Get the names of the players
        
        
        String player1 = (String) getUserInput(input, "Enter name of player 2: ", "nextLine");
        String player2 = (String) getUserInput(input, "Enter name of player 2: ", "nextLine");

        System.out.println("Player 1: " + player1);
        System.out.println("Player 2: " + player2);

        String controlsChoice;
        
        
        do { 
            System.out.println("Do you know the controls? (Y/N)");
            controlsChoice = input.nextLine().toLowerCase();

            if (controlsChoice.equals("n")) {
                System.out.println("The controls are as follows: ");
                System.out.println("1. To move a piece, enter the coordinates of the piece you want to move.");
                System.out.println("2. Then enter the coordinates of the square you want to move the piece to.");
                System.out.println("3. The coordinates are in the format 'row column'.");
                System.out.println("4. For example, to move the piece at row 2, column 1 to row 4, column 1, you would enter '2 1 4 1'.");
                System.out.println("5. The game will alternate between player 1 and player 2.");
                System.out.println("6. The game will end when a player wins or there is a stalemate.");
                System.out.println();

                System.out.println("Are you ready to play? (Y/N)");
                controlsChoice = input.nextLine().toLowerCase();
            } else {
                System.out.println("Great! Let's start the game.");
                
            }
        } while (controlsChoice.equals("n"));
        

        // Create the board and print the board
        createBoard(board);
        printBoard(board);

    } // End of newGame method

    // Method to clear the screen
    public static void clearScreen() {  
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Run the Windows 'cls' command
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Run the Linux/Mac 'clear' command
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error clearing the screen.");
        }
    } // End of clearScreen method 
    

    // Utility method to get user input and check for 'quit'
    public static Object getUserInput(Scanner input, String options, String type) {
        System.out.print(options);
        String userChoice;
        switch (type.toLowerCase()) {
            case "next":
                userChoice = input.next().trim().toLowerCase();
                break;

            case "nextline":
                userChoice = input.nextLine().trim().toLowerCase();
                break;

            default:
                throw new IllegalArgumentException("Invalid input type specified.");
        }
    
        if (userChoice.equalsIgnoreCase("quit") || userChoice.equalsIgnoreCase("exit") || userChoice.equalsIgnoreCase("q")) {
            System.out.println("Exiting the program...");
            System.exit(0); // Terminate the program immediately
        }

        // Determine which type of input to return
        try {
            switch (type.toLowerCase()) {
                case "int":
                    return Integer.parseInt(userChoice);
                case "double":
                    return Double.parseDouble(userChoice);
                case "next":
                case "nextline":
                    return userChoice; // For strings, just return the input
                default:
                    throw new IllegalArgumentException("Invalid input type specified.");
            }
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Expected a valid " + type + " value.");
        }
    
        
    }


}
