package chess;

import java.util.Scanner;
import java.util.Set;

import chess.board.Board;
import chess.board.BoardFactory;
import chess.board.Move;
import chess.piece.King;
import chess.piece.Piece;

public class InputCoordinates {
	private static Scanner scanner = new Scanner(System.in);

	public static Coordinates input() {
		while (true) {
			System.out.print("please enter coordinates\n");

			String line = scanner.nextLine();

			if (line.length() != 2) {
				System.out.println("invalid format");
				continue;
			}

			char fileChar = line.charAt(0);
			char rankChar = line.charAt(1);

			if (!Character.isLetter(fileChar)) {
				System.out.println("invalid format");
				continue;
			}

			if (!Character.isDigit(rankChar)) {
				System.out.println("invalid format");
				continue;
			}

			int rank = Character.getNumericValue(rankChar);
			if (rank < 1 || rank > 8) {
				System.out.println("invalid format");
				continue;
			}

			File file = File.fromChar(fileChar);
			if (file == null) {
				System.out.println("invalid format");
				continue;
			}

			return new Coordinates(file, rank);
		}
	}

	public static Coordinates inputPieceCoordinatesForColor(Color color, Board board) {
		while (true) {
			System.out.println("enter coordinates for piece to move (ex. a1)");
			Coordinates coordinates = input();

			if (board.isSquareEmpty(coordinates)) {
				System.out.println("empty square");
				continue;
			}

			Piece piece = board.getPiece(coordinates);
			if (piece.color != color) {
				System.out.println("wronk color");
				continue;
			}

			Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquare(board);
			if (availableMoveSquares.size() == 0) {
				System.out.println("blocked piece");
				continue;
			}

			return coordinates;
		}

	}

	public static Move inputMove(Board board, Color color, BoardConsoleRenderer renderer) {
		while (true) {
			Coordinates sourceCoordinates = InputCoordinates.inputPieceCoordinatesForColor(color, board);

			Piece piece = board.getPiece(sourceCoordinates);
			Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquare(board);

			renderer.render(board, piece);
			Coordinates targetCoordinates = InputCoordinates.inputAvailableSquare(availableMoveSquares);

			Move move = new Move(sourceCoordinates, targetCoordinates);

			if (validateIfKingInCheckAfterMove(board, color, move)) {
				System.out.println("your king is under attack!");
				continue;
			}

			return move;
		}
	}

	private static boolean validateIfKingInCheckAfterMove(Board board, Color color, Move move) {
		Board copy = new BoardFactory().copy(board);
		copy.makeMove(move);
		
		Piece king =  copy.getPieceByColor(color).stream().filter(piece -> piece instanceof King).findFirst().get();
		return copy.isSquareAttackedByColor(king.coordinates, color.opposite());
	}

	public static Coordinates inputAvailableSquare(Set<Coordinates> coordinates) {
		while (true) {
			System.out.println("enter your move for selected piece");
			Coordinates input = input();

			if (!coordinates.contains(input)) {
				System.out.println("non-available square");
				continue;
			}

			return input;
		}
	}
}
