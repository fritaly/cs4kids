package fr.ritaly.cs4kids.sudoku;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.lang.Validate;

public class Sudoku4x4 {

	public static Sudoku4x4 SUDOKU_1 = new Sudoku4x4(new String[] { " 32 ",
			"2  3", "1  4", " 4  " });
	public static Sudoku4x4 SUDOKU_2 = new Sudoku4x4(new String[] { " 3 2",
			"4  1", "2 1 ", " 1 4" });
	public static Sudoku4x4 SUDOKU_3 = new Sudoku4x4(new String[] { " 1 3",
			"4 2 ", " 2  ", "1   " });
	public static Sudoku4x4 SUDOKU_4 = new Sudoku4x4(new String[] { "1   ",
			" 3 1", "3 2 ", "   3" });

	private final char[][] digits = new char[4][4];
	
	public static Sudoku4x4 getRandom() {
		final ArrayList<Sudoku4x4> list = new ArrayList<Sudoku4x4>();
		list.add(SUDOKU_1);
		list.add(SUDOKU_2);
		list.add(SUDOKU_3);
		list.add(SUDOKU_4);
		
		Collections.shuffle(list);
		
		return list.get(0);
	}

	private Sudoku4x4(String[] lines) {
		Validate.notNull(lines, "The given array is null");
		Validate.isTrue(lines.length == 4);

		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];

			for (int j = 0; j < lines.length; j++) {
				final char c = line.charAt(j);

				digits[i][j] = c;
			}
		}
	}

	public char getDigit(int x, int y) {
		return digits[x][y];
	}

	public int getRowCount() {
		return digits.length;
	}

	public int getColumnCount() {
		return digits[0].length;
	}
}
