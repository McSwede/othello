import java.util.Scanner;

public class Game {
	private char currentTurn = ' ';
	public Board board = new Board();
	
	public Game() {}
	
	public char getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(char currentTurn) {
		this.currentTurn = currentTurn;
	}

	public void resolveInput() {
		Scanner s = new Scanner(System.in);
		String input = s.nextLine().toUpperCase();
		
		int column = input.charAt(0) - 65;
		int row = Character.getNumericValue(input.charAt(1) - 1);

		board.setToken(column, row, currentTurn);
	}
	
	
}
