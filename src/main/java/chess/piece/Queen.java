package chess.piece;

import java.util.Set;

import chess.Color;
import chess.Coordinates;
import chess.IRook;

public class Queen extends LongRangePiece implements IBishop, IRook{

	public Queen(Color color, Coordinates coordinates) {
		super(color, coordinates);
	}

	@Override
	protected Set<CoordinatesShift> getPieceMoves() {
		Set<CoordinatesShift> moves = getBishopMoves();
		moves.addAll(getRookMoves());
		return moves;
	}
	
	

}
