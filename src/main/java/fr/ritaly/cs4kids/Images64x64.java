/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Images64x64 {

	public static final ImageIcon THUMBS_UP = new ImageIcon(Images64x64.class
			.getClassLoader().getResource("images/check_64x64.png"));

	public static final ImageIcon NEXT = new ImageIcon(Images64x64.class
			.getClassLoader().getResource("images/refresh_64x64.png"));

	public static final ImageIcon CLEAR = new ImageIcon(Images64x64.class
			.getClassLoader().getResource("images/remove_64x64.png"));

	public static final ImageIcon POSITIVE = new ImageIcon(Images64x64.class
			.getClassLoader().getResource("images/wink_64x64.png"));

	public static final ImageIcon NEGATIVE = new ImageIcon(Images64x64.class
			.getClassLoader().getResource("images/sad_64x64.png"));
	
	public static final ImageIcon DOWN = new ImageIcon(Images64x64.class
			.getClassLoader().getResource("images/Down_64x64.png"));
	
	public static final ImageIcon RIGHT = new ImageIcon(Images64x64.class
			.getClassLoader().getResource("images/Right_64x64.png"));
	
	public static final ImageIcon LETTER_BLANK = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/Blank_64x64.png"));

	public static final ImageIcon LETTER_A = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/a_64x64.png"));

	public static final ImageIcon LETTER_B = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/b_64x64.png"));

	public static final ImageIcon LETTER_C = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/c_64x64.png"));

	public static final ImageIcon LETTER_D = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/d_64x64.png"));

	public static final ImageIcon LETTER_E = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/e_64x64.png"));

	public static final ImageIcon LETTER_F = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/f_64x64.png"));

	public static final ImageIcon LETTER_G = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/g_64x64.png"));

	public static final ImageIcon LETTER_H = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/h_64x64.png"));

	public static final ImageIcon LETTER_I = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/i_64x64.png"));

	public static final ImageIcon LETTER_J = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/j_64x64.png"));

	public static final ImageIcon LETTER_K = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/k_64x64.png"));

	public static final ImageIcon LETTER_L = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/l_64x64.png"));

	public static final ImageIcon LETTER_M = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/m_64x64.png"));

	public static final ImageIcon LETTER_N = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/n_64x64.png"));

	public static final ImageIcon LETTER_O = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/o_64x64.png"));

	public static final ImageIcon LETTER_P = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/p_64x64.png"));

	public static final ImageIcon LETTER_Q = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/q_64x64.png"));

	public static final ImageIcon LETTER_R = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/r_64x64.png"));

	public static final ImageIcon LETTER_S = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/s_64x64.png"));

	public static final ImageIcon LETTER_T = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/t_64x64.png"));

	public static final ImageIcon LETTER_U = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/u_64x64.png"));

	public static final ImageIcon LETTER_V = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/v_64x64.png"));

	public static final ImageIcon LETTER_W = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/w_64x64.png"));

	public static final ImageIcon LETTER_X = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/x_64x64.png"));

	public static final ImageIcon LETTER_Y = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/y_64x64.png"));

	public static final ImageIcon LETTER_Z = new ImageIcon(Images64x64.class
			.getClassLoader().getResource(
					"images/alphabet/scrabble/z_64x64.png"));

	private static final Map<ImageIcon, Character> LETTER_PER_ICON = new LinkedHashMap<ImageIcon, Character>();
	
	static {
		LETTER_PER_ICON.put(LETTER_A, 'A');
		LETTER_PER_ICON.put(LETTER_B, 'B');
		LETTER_PER_ICON.put(LETTER_C, 'C');
		LETTER_PER_ICON.put(LETTER_D, 'D');
		LETTER_PER_ICON.put(LETTER_E, 'E');
		LETTER_PER_ICON.put(LETTER_F, 'F');
		LETTER_PER_ICON.put(LETTER_G, 'G');
		LETTER_PER_ICON.put(LETTER_H, 'H');
		LETTER_PER_ICON.put(LETTER_I, 'I');
		LETTER_PER_ICON.put(LETTER_J, 'J');
		LETTER_PER_ICON.put(LETTER_K, 'K');
		LETTER_PER_ICON.put(LETTER_L, 'L');
		LETTER_PER_ICON.put(LETTER_M, 'M');
		LETTER_PER_ICON.put(LETTER_N, 'N');
		LETTER_PER_ICON.put(LETTER_O, 'O');
		LETTER_PER_ICON.put(LETTER_P, 'P');
		LETTER_PER_ICON.put(LETTER_Q, 'Q');
		LETTER_PER_ICON.put(LETTER_R, 'R');
		LETTER_PER_ICON.put(LETTER_S, 'S');
		LETTER_PER_ICON.put(LETTER_T, 'T');
		LETTER_PER_ICON.put(LETTER_U, 'U');
		LETTER_PER_ICON.put(LETTER_V, 'V');
		LETTER_PER_ICON.put(LETTER_W, 'W');
		LETTER_PER_ICON.put(LETTER_X, 'X');
		LETTER_PER_ICON.put(LETTER_Y, 'Y');
		LETTER_PER_ICON.put(LETTER_Z, 'Z');
		LETTER_PER_ICON.put(LETTER_BLANK, ' ');
	}
	
	public static char getLetter(Icon icon) {
		return LETTER_PER_ICON.get(icon);
	}

	public static ImageIcon getLetterIcon(char c) {
		switch (c) {
		case 'A':
			return LETTER_A;
		case 'B':
			return LETTER_B;
		case 'C':
			return LETTER_C;
		case 'D':
			return LETTER_D;
		case 'E':
			return LETTER_E;
		case 'F':
			return LETTER_F;
		case 'G':
			return LETTER_G;
		case 'H':
			return LETTER_H;
		case 'I':
			return LETTER_I;
		case 'J':
			return LETTER_J;
		case 'K':
			return LETTER_K;
		case 'L':
			return LETTER_L;
		case 'M':
			return LETTER_M;
		case 'N':
			return LETTER_N;
		case 'O':
			return LETTER_O;
		case 'P':
			return LETTER_P;
		case 'Q':
			return LETTER_Q;
		case 'R':
			return LETTER_R;
		case 'S':
			return LETTER_S;
		case 'T':
			return LETTER_T;
		case 'U':
			return LETTER_U;
		case 'V':
			return LETTER_V;
		case 'W':
			return LETTER_W;
		case 'X':
			return LETTER_X;
		case 'Y':
			return LETTER_Y;
		case 'Z':
			return LETTER_Z;
		case ' ':
			return LETTER_BLANK;
		default:
			return null;
		}
	}
}