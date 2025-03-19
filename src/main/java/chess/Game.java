package chess;

public class Game {
	private final Board board;

	private BoardConsoleRenderer renderer = new BoardConsoleRenderer();

	public Game(Board board) {
		this.board = board;
	}

	public void gameLoop() {
		boolean isWhiteToMove = true;

		while (true) {
			renderer.render(board);

			InputCoordinates.inputPieceCoordinatesForColor(
					isWhiteToMove ? Color.WHITE : Color.BLACK, board
							
					);

			isWhiteToMove = !isWhiteToMove;
		}
	}
}
