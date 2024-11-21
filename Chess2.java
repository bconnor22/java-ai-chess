import java.util.*;

public class Chess2 {
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        char[][] board = new char[8][8];

        System.out.println(board.length);

        //create board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                
                if (i == 0 || i == 7) {
                    switch (j) {
                        case 0:
                        case 7:
                            board[i][j] = 'r';
                            break;
                        case 1:
                        case 6:
                            board[i][j] = 'k';
                            break;
                        case 2:
                        case 5:
                            board[i][j] = 'b';
                            break;
                        case 3:
                            board[i][j] = 'K';
                            break;
                        case 4: 
                            board[i][j] = 'Q';
                            break;
                    }

                } else if (i == 1 || i == 6) {
                    //Pawns
                    board[i][j] = 'p';
                } else {
                    board[i][j] = '.';
                }
            }
        }
    }
}