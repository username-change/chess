package chess;

import java.util.List;
import java.util.Set;

import chess.board.Board;
import chess.board.BoardFactory;
import chess.board.Move;
import chess.piece.King;
import chess.piece.Piece;

public class CheckmateGameStateChecker extends GameStateChecker {

	@Override
	public GameState check(Board board, Color color) {
		Piece king = board.getPieceByColor(color).stream().filter(piece -> piece instanceof King).findFirst().get();

		if (!board.isSquareAttackedByColor(king.coordinates, color.opposite())) {
			return GameState.ONGOING;
		}

		List<Piece> pieces = board.getPieceByColor(color);
		for (Piece piece : pieces) {
			Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquare(board);

			for (Coordinates coordinates : availableMoveSquares) {
				Board clone = new BoardFactory().copy(board);
				clone.makeMove(new Move(piece.coordinates, coordinates));

				Piece clonedKing = clone.getPieceByColor(color).stream().filter(p -> p instanceof King).findFirst().get();

				if (!clone.isSquareAttackedByColor(clonedKing.coordinates, color.opposite())) {
					return GameState.ONGOING;
				}
			}
		}

		if (color == Color.WHITE) {
			return GameState.CHECKMATE_TO_WHITE_KING;
		} else {
			return GameState.CHECKMATE_TO_BLACK_KING;
		}
	}

}
