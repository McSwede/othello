
public class Board {
	final char BLANK = ' ';
	final char BLACK = 'V';
	final char WHITE = 'S';
	
	private char[][] board = new char[8][8];
	
	// Konstruktorn initierar ett tomt 8x8 bräde
	public Board () {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = BLANK;
			}
		}
		board[3][3] = WHITE;
		board[3][4] = BLACK;
		board[4][3] = BLACK;
		board[4][4] = WHITE;
	}
	
	public void printBoard() {
		System.out.println("  | A | B | C | D | E | F | G | H |");
		for(int i = 0; i < 8; i++) {
			System.out.println("   ________________________________");
			System.out.print(i+1 + " ");
			for(int j = 0; j < 8; j++) {
				System.out.print("| " + board[i][j] + " ");
			}
			System.out.print("|\n");
		}
	}
	
	public void setToken(int row, int column, char x) {
		board[column][row] = x;
	}
}
