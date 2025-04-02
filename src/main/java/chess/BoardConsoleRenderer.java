package chess;

import java.util.Collections;
import java.util.Set;

import chess.board.Board;
import chess.piece.Piece;

public class BoardConsoleRenderer {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
	public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";
	public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";
	public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";
	public static final String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = "\u001B[45m";

	public void render(Board board, Piece pieceToMove) {
		Set<Coordinates> availableMoveSquare = Collections.emptySet();
		if(pieceToMove != null)
			availableMoveSquare = pieceToMove.getAvailableMoveSquare(board);
		
		for (int rank = 8; rank >= 1; rank--) {
			String line = "";
			for (File file : File.values()) {
				Coordinates coordinates = new Coordinates(file, rank);
				boolean isHighlight = availableMoveSquare.contains(coordinates);
				
				if (board.isSquareEmpty(coordinates)) {
					line += getSpriteForEmpthySquare(coordinates, isHighlight);
				} else {
					line += getPieceSprite(board.getPiece(coordinates), isHighlight);
				}
			}

			line += ANSI_RESET;
			System.out.println(line);
		}
	}
	public void render(Board board) {
		render(board, null);
	}

	private String colorizeSprite(String spite, Color pieceColor, boolean isSquareDark, boolean isHighlight) {
		String result = spite;

		if (pieceColor == Color.WHITE) {
			result = ANSI_WHITE_PIECE_COLOR + result;
		} else {
			result = ANSI_BLACK_PIECE_COLOR + result;
		}

		if (isHighlight) {
			result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result;
		   } else if (isSquareDark) {
			result = ANSI_BLACK_SQUARE_BACKGROUND + result;
		} else {
			result = ANSI_WHITE_SQUARE_BACKGROUND + result;
		}

		return result;
	}

	private String getSpriteForEmpthySquare(Coordinates coordinates, boolean isHighlight) {
		return colorizeSprite("   ", Color.WHITE, Board.isSquareDark(coordinates), isHighlight);
	}

	private String selectUnicodeSpriteForPiece(Piece piece) {
		switch (piece.getClass().getSimpleName()) {
		case "Pawn":
			return "♟︎";
		case "Knight":
			return "♞";
		case "Bishop":
			return "♝";
		case "Rook":
			return "♜";
		case "Queen":
			return "♛";
		case "King":
			return "♚";
		}
		return "";
	}

	private String getPieceSprite(Piece piece, boolean isHighlight) {
		return colorizeSprite(" " + selectUnicodeSpriteForPiece(piece) + " ", piece.color,
				Board.isSquareDark(piece.coordinates), isHighlight);
	}
}
