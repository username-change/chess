package chess.piece;

import java.util.HashSet;
import java.util.Set;

import chess.Color;
import chess.Coordinates;

public class Rook extends Piece {

	public Rook(Color color, Coordinates coordinates) {
		super(color, coordinates);
	}
	
	@Override
	protected Set<CoordinatesShift> getPieceMoves() {
		Set<CoordinatesShift> result = new HashSet<CoordinatesShift>();
		
		for(int i = -7; i <= 7; i++) {
			if(i == 0) continue;
			
			result.add(new CoordinatesShift(i, 0));
		}
		
		
		
		return result;
	}
}
