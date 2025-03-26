package chess;

import java.util.Set;

import chess.piece.Piece;

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

			if(isWhiteToMove) {
				System.out.println("white to move");
			} else {
				System.out.println("black to move");
			}
			
			Coordinates sourceCoordinates = InputCoordinates.inputPieceCoordinatesForColor(
					isWhiteToMove ? Color.WHITE : Color.BLACK, board
							
					);

			Piece piece = board.getPiece(sourceCoordinates);
			Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquare(board);
			
			renderer.render(board, piece);
			Coordinates targetCoordinates = InputCoordinates.inputAvailableSquare(availableMoveSquares);
			
			board.movePiece(sourceCoordinates, targetCoordinates);
			
			isWhiteToMove = !isWhiteToMove;
		}
	}
}
