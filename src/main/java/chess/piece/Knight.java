package chess.piece;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import chess.Color;
import chess.Coordinates;

public class Knight extends Piece {

	public Knight(Color color, Coordinates coordinates) {
		super(color, coordinates);
	}

	@Override
	protected Set<CoordinatesShift> getPieceMove() {
		return new HashSet<CoordinatesShift>(Arrays.asList(
				new CoordinatesShift(1, 2),
				new CoordinatesShift(2, 1),
				
				new CoordinatesShift(2, -1),
				new CoordinatesShift(1, -2),
				
				new CoordinatesShift(-2, -1),
				new CoordinatesShift(-1, -2),
				
				new CoordinatesShift(-2, 1),
				new CoordinatesShift(-1, 2)
				));
	}

}
