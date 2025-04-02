package chess;

import java.util.Objects;

import chess.piece.CoordinatesShift;

public class Coordinates {
	public final File file;
	public final Integer rank; 

	public Coordinates(File file, Integer rank) {
		this.file = file;
		this.rank = rank;
	}

	public Coordinates shift(CoordinatesShift shift) {
		return new Coordinates(File.values()[this.file.ordinal() + shift.fileShift], this.rank + shift.rankShift);
	}

	public boolean canShift(CoordinatesShift shift) {
		int f = file.ordinal() + shift.fileShift;
		int r = rank + shift.rankShift;
		
		if((f < 0) || (f > 7)) return false;
		if((r < 1) || (r > 8)) return false;
		
		return true; 
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(file, rank);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		return file == other.file && Objects.equals(rank, other.rank);
	}

	@Override
	public String toString() {
		return file + String.valueOf(rank);
	}
	
	 

}
