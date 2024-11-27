
public class Board {
	private static final char BLANK = ' ';
	private static final char BLACK = 'S';
	private static final char WHITE = 'V';
	
	private char[][] board = new char[8][8];
	
	// Constructor initializes a 8x8 board 
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
		System.out.println("    A   B   C   D   E   F   G   H  ");
		System.out.println("  +---+---+---+---+---+---+---+---+");
		for(int i = 0; i < 8; i++) {
			System.out.print((i + 1) + " |");
			for(int j = 0; j < 8; j++) {
				System.out.print(" " + board[i][j] + " |");
			}
			System.out.println();
	        System.out.println("  +---+---+---+---+---+---+---+---+");
		}
	}
	
	public boolean validMove(int row, int column, char turn) {
		// We need to be on the board and not overlapping another token
		if (row < 0 || row >= 8 || column < 0 || column >= 8 || board[row][column] != BLANK) {
            return false;
        }
		// Get the other color
        char opponentColor = (turn == BLACK) ? WHITE : BLACK;

        // We check one tile in each direction
        int[] directions = {-1, 0, 1};
        for (int horizontal : directions) {
            for (int vertical : directions) {
                if (horizontal == 0 && vertical == 0) continue;
                int testRow = row + horizontal, testColumn = column + vertical;
                boolean hasOpponent = false;
                while (testRow >= 0 && testRow < 8 && testColumn >= 0 && testColumn < 8 && board[testRow][testColumn] == opponentColor) {
                    testRow += horizontal;
                    testColumn += vertical;
                    hasOpponent = true;
                }
                if (hasOpponent && testRow >= 0 && testRow < 8 && testColumn >= 0 && testColumn < 8 && board[testRow][testColumn] == turn) {
                    return true;
                }
            }
        }
        return false;
	}
	
	// Is there a valid move at all this turn
	public boolean hasValidMove(char turn) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (validMove(i, j, turn)) {
                    return true;
                }
            }
        }
        return false;
    }
	
	public void makeMove(int row, int column, char turn) {
        board[row][column] = turn;
        char opponentColor = (turn == BLACK) ? WHITE : BLACK;

        int[] directions = {-1, 0, 1};
        for (int horizontal : directions) {
            for (int vertical : directions) {
                if (horizontal == 0 && vertical == 0) continue;
                int testRow = row + horizontal, testColumn = column + vertical;
                boolean hasOpponent = false;
                while (testRow >= 0 && testRow < 8 && testColumn >= 0 && testColumn < 8 && board[testRow][testColumn] == opponentColor) {
                    testRow += horizontal;
                    testColumn += vertical;
                    hasOpponent = true;
                }
                if (hasOpponent && testRow >= 0 && testRow < 8 && testColumn >= 0 && testColumn < 8 && board[testRow][testColumn] == turn) {
                    int flipRow = row + horizontal, flipColumn = column + vertical;
                    while (flipRow != testRow || flipColumn != testColumn) {
                        board[flipRow][flipColumn] = turn;
                        flipRow += horizontal;
                        flipColumn += vertical;
                    }
                }
            }
        }
    }
	
	public int[] findMove(char turn) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (validMove(i, j, turn)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
	
	// Based on Zhifei Zhang and Yuechuan Chen's searching algorithm
	// https://zzutk.github.io/docs/reports/2014.04%20-%20Searching%20Algorithms%20in%20Playing%20Othello.pdf
	public int[] findBetterMove(char turn) {
		
	    int[][] positionsScores = {
	        {120, -20, 20, 5, 5, 20, -20, 120},
	        {-20, -40, -5, -5, -5, -5, -40, -20},
	        {20, -5, 15, 3, 3, 15, -5, 20},
	        {5, -5, 3, 3, 3, 3, -5, 5},
	        {5, -5, 3, 3, 3, 3, -5, 5},
	        {20, -5, 15, 3, 3, 15, -5, 20},
	        {-20, -40, -5, -5, -5, -5, -40, -20},
	        {120, -20, 20, 5, 5, 20, -20, 120}
	    };

	    int bestScore = Integer.MIN_VALUE;
	    int[] bestMove = null;

	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	        	// It needs to be a valid move from the start
	            if (validMove(i, j, turn)) {
	                // Test the move and asses the score
	                int score = positionsScores[i][j];
	                // Save best score
	                if (score > bestScore) {
	                    bestScore = score;
	                    bestMove = new int[]{i, j};
	                }
	            }
	        }
	    }
	    return bestMove;
	}
	
	public int countTokens(char turn) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == turn) {
                	count++;
                }
            }
        }
        return count;
    }
}
