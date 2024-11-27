import java.util.Scanner;

public class Main {
	
	private static final char BLACK = 'S';
	private static final char WHITE = 'V';

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Välkommen till Grupp 1's ultimata Wigello algoritm Jarvis!\n(Shout out till Zhifei Zhang och Yuechuan Chen)");
		char player = ' ';
		boolean validInput = false;
        
		while (!validInput) {
		    System.out.print("Vilken färg är motståndaren jag ska krossa?\n[Vit] eller [Svart]: ");
		    String input = s.nextLine().toLowerCase();

		    switch (input) {
		        case "vit" -> {
		            player = WHITE;
		            validInput = true;
		        }
		        case "svart" -> {
		            player = BLACK;
		            validInput = true;
		        }
		        default -> System.out.println("Ogiltig färg. Skriv färgens namn.");
		    }
		}
		Game game = new Game();
		game.play(player);
		s.close();
	}
}
