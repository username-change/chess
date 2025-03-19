package chess;

import java.util.Scanner;
import java.util.Set;

import chess.piece.Piece;

public class InputCoordinates {
	private static Scanner scanner = new Scanner(System.in);

	public static Coordinates input() {
		while (true) {
			System.out.print("enter coordinates");

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
			System.out.println("enter coordinates for piece to move");
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
		}
		
	}

	public static void main(String[] args) {
		Coordinates coordinates = input();

		System.out.println(coordinates);
	}
}
