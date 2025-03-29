package chess.piece;

import java.util.HashSet;
import java.util.Set;

import chess.Board;
import chess.Color;
import chess.Coordinates;

public abstract class Piece {
	public final Color color;
	public Coordinates coordinates;

	public Piece(Color color, Coordinates coordinates) {
		this.color = color;
		this.coordinates = coordinates;
	}

	public Set<Coordinates> getAvailableMoveSquare(Board board) {
		Set<Coordinates> result = new HashSet<Coordinates>();

		for (CoordinatesShift shift : getPieceMoves()) {
			if (coordinates.canShift(shift)) {
				Coordinates newCoordinates = coordinates.shitft(shift);

				if (isSquareAvailableForMove(newCoordinates, board)) {
					result.add(newCoordinates);
				}

			}
		}

		return result;
	}

	protected boolean isSquareAvailableForMove(Coordinates newCoordinates, Board board) {
		return board.isSquareEmpty(newCoordinates) || board.getPiece(newCoordinates).color != color;
	}

	protected abstract Set<CoordinatesShift> getPieceMoves();

	protected Set<CoordinatesShift> getPieceAttacks() {
		return getPieceMoves();
	}

	public Set<Coordinates> getAttackedSquares(Board board) {
		Set<CoordinatesShift> pieceAttacks = getPieceAttacks();
		Set<Coordinates> result = new HashSet<>();

		for (CoordinatesShift pieceAttack : pieceAttacks) {
			if (coordinates.canShift(pieceAttack)) {
				Coordinates shiftedCoordinates = coordinates.shitft(pieceAttack);

				if (isSquareAvailableForMove(shiftedCoordinates, board)) {
					result.add(shiftedCoordinates);
				}
			}
		}

		return result;
	}

	protected boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
		return true;
	}
}
