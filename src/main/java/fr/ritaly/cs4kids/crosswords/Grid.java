package fr.ritaly.cs4kids.crosswords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {

	private final List<String> lines = new ArrayList<String>();

	private final Map<String, Definition> definitions = new HashMap<String, Definition>();

	public int getHeight() {
		return lines.size();
	}

	public int getWidth() {
		return lines.iterator().next().length();
	}

	public char charAt(int x, int y) {
		return lines.get(x).charAt(y);
	}

	public void setLines(String... lines) {
		this.lines.clear();

		if (lines != null) {
			this.lines.addAll(Arrays.asList(lines));
		}
	}

	public void addDefinition(int x, int y, Definition definition) {
		definitions.put(Integer.toString(x) + "." + Integer.toString(y),
				definition);
	}

	public Definition getDefinition(int x, int y) {
		return definitions.get(Integer.toString(x) + "." + Integer.toString(y));
	}
	
	public String getLine(int n) {
		return lines.get(n);
	}
	
	public String getId() {
		final StringBuilder builder = new StringBuilder(256);
		
		for (String line : lines) {
			for (Character character : line.toCharArray()) {
				if (character != '*') {
					builder.append(character);
				} else {
					// Supprimer les astérisques
					builder.append(' ');
				}
			}
		}
		
		return builder.toString();
	}
}