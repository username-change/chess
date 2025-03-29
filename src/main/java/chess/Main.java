package chess;

public class Main {
	public static void main(String[] args) {
		
		Board board = new BoardFactory().fromFEN("k7/8/1b6/8/4K3/8/8/8 w - - 0 1");

		Game game = new Game(board);
		game.gameLoop();
		
 	}
}
