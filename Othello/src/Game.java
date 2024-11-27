import java.util.Scanner;

public class Game {
	private static final char BLACK = 'S';
	private static final char WHITE = 'V';
	// We set currentTurn to black as black always starts
	private char currentTurn = 'S';
	private Board board;
	
	public Game() {
		board = new Board();
	}
	
	public void play(char player) {
        Scanner s = new Scanner(System.in);
        char computer = (player == BLACK) ? WHITE : BLACK;

        while (true) {
            board.printBoard();
            if (!board.hasValidMove(currentTurn)) {
                System.out.println((currentTurn == BLACK ? "Svart" : "Vit") + " har inga giltiga drag.");
                currentTurn = (currentTurn == BLACK) ? WHITE : BLACK;
                if (!board.hasValidMove(currentTurn)) {
                    System.out.println("Det finns inga fler giltiga drag, spelet är slut!");
                    printWinner();
                    break;
                }
                continue;
            }

            if (currentTurn == player) {
                System.out.print("Din tur (" + (currentTurn == BLACK ? "Svart" : "Vit") + ") : ");
//                String move = s.next().toUpperCase();
//                int row = Character.getNumericValue(move.charAt(1) - 1);
//                int column = move.charAt(0) - 65;
//                if (board.validMove(row, column, currentTurn)) {
//                    board.makeMove(row, column, currentTurn);
//                    currentTurn = computer;
//                } else {
//                    System.out.println("Ogiltigt drag. Försök igen.");
//                }
                int[] autoMove = board.findMove(currentTurn);
                if (autoMove != null) {
                    board.makeMove(autoMove[0], autoMove[1], currentTurn);
                    System.out.println(convertMoveToString(autoMove[0], autoMove[1]));
                }
                currentTurn = computer;
            } else {
                System.out.print("Datorns tur (" + (currentTurn == BLACK ? "Svart" : "Vit") + ") : ");
                int[] computerMove = board.findBetterMove(currentTurn);
                if (computerMove != null) {
                    board.makeMove(computerMove[0], computerMove[1], currentTurn);
                    System.out.println(convertMoveToString(computerMove[0], computerMove[1]));
                }
                currentTurn = player;
            }
        }

        s.close();
    }

    private void printWinner() {
        int blackCount = board.countTokens(BLACK);
        int whiteCount = board.countTokens(WHITE);
        if (blackCount > whiteCount) {
            System.out.println("Svart vinner med " + blackCount + " pjäser över vitas " + whiteCount);
        } else if (whiteCount > blackCount) {
            System.out.println("Vit vinner med " + whiteCount + " pjäser över svartas " + blackCount);
        } else {
            System.out.println("Oavgjort?");
        }
    }

    private String convertMoveToString(int row, int column) {
        char columnLetter = (char) (65 + column);
        int rowNumber = row + 1;
        return "" + columnLetter + rowNumber;
    }
	
	
}
