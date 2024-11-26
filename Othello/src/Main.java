import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.print("Välkommen till Wigello!\nVilken färg vill du spela som?\n[Vit] eller [Svart]: ");
		Scanner s = new Scanner(System.in);
		char turn = ' ';
		switch (s.nextLine().toLowerCase()) {
		case "vit" -> turn = 'V';
		case "svart" -> turn = 'S';
		}
		Game game = new Game();
		while(true) {
			if (turn == 'V') {
				game.setCurrentTurn('V');
				// Do move
			}
			System.out.print("Ange position: ");
			game.resolveInput();
			game.board.printBoard();
			
		}
	}

}
