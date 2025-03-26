package chess;

public class Main {
	public static void main(String[] args) {
//		Board board = new Board();
//		board.setupDefaultPiecePositions();
		
		Board board = new BoardFactory().fromFEN("3k4/8/5n2/2N5/3B4/8/8/3K4 w - - 0 1");

//		BoardConsoleRenderer renderer = new BoardConsoleRenderer();
//		renderer.render(board);
//
//		Piece piece = board.getPiece(new Coordinates(File.G, 8));
//		Set<Coordinates> availableMoveSquare =  piece.getAvailableMoveSquare(board);
		
		Game game = new Game(board);
		game.gameLoop();
		
 	}
}
