package chess.piece;

import java.util.Set;

import chess.Color;
import chess.Coordinates;

public class Bishop extends Piece {

	public Bishop(Color color, Coordinates coordinates) {
		super(color, coordinates);
	}

	@Override
	protected Set<CoordinatesShift> getPieceMove() {
		return  ;
	}

}
