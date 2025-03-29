package chess.piece;

import java.util.HashSet;
import java.util.Set;

import chess.Board;
import chess.Color;
import chess.Coordinates;

public class Pawn extends Piece {

	public Pawn(Color color, Coordinates coordinates) {
		super(color, coordinates);
	}

	@Override
	protected Set<CoordinatesShift> getPieceMoves() {
		Set<CoordinatesShift> result = new HashSet<CoordinatesShift>();

		if (color == Color.WHITE) {
			result.add(new CoordinatesShift(0, 1));

			if (coordinates.rank == 2) {
				result.add(new CoordinatesShift(0, 2));
			}

			result.add(new CoordinatesShift(-1, 1));
			result.add(new CoordinatesShift(1, 1));
		} else {
			result.add(new CoordinatesShift(0, -1));

			if (coordinates.rank == 7) {
				result.add(new CoordinatesShift(0, -2));
			}

			result.add(new CoordinatesShift(-1, -1));
			result.add(new CoordinatesShift(1, -1));

		}

		return result;
	}

	@Override
	protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
		if (this.coordinates.file == coordinates.file) {
			return board.isSquareEmpty(coordinates);
		} else {
			if (board.isSquareEmpty(coordinates)) {
				return false;
			} else {
				return board.getPiece(coordinates).color != color;
			}
		}
	}
}