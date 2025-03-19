package chess;

import java.util.Set;

import chess.piece.Piece;

public class Main {
	public static void main(String[] args) {
		Board board = new Board();
		board.setupDefaultPiecePositions();

		BoardConsoleRenderer renderer = new BoardConsoleRenderer();
		renderer.render(board);

		Piece piece = board.getPiece(new Coordinates(File.G, 8));
		Set<Coordinates> availableMoveSquare =  piece.getAvailableMoveSquare(board);
		
	}
}
