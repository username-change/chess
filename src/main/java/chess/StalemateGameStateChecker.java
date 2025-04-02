package chess;

import java.util.List;
import java.util.Set;

import chess.board.Board;
import chess.piece.Piece;

public class StalemateGameStateChecker extends GameStateChecker {
	@Override
	public GameState check(Board board, Color color) {
		List<Piece> pieces = board.getPieceByColor(color);

		for (Piece piece : pieces) {
			Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquare(board);

			if (availableMoveSquares.size() > 0) {
				return GameState.ONGOING;
			}
		}

		return GameState.STALEMATE;
	}

}
