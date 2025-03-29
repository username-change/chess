package chess.piece;

import java.util.Set;

import chess.Color;
import chess.Coordinates;
import chess.IRook;

public class Rook extends LongRangePiece implements IRook {

	public Rook(Color color, Coordinates coordinates) {
		super(color, coordinates);
	}

	@Override
	protected Set<CoordinatesShift> getPieceMoves() {
		return getRookMoves();

	}

}
