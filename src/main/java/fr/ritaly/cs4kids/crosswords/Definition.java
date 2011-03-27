package fr.ritaly.cs4kids.crosswords;

class Definition {

	private final String text;
	
	private final Direction direction;

	public Definition(String text, Direction direction) {
		this.text = text;
		this.direction = direction;
	}

	public String getText() {
		return text;
	}

	public Direction getDirection() {
		return direction;
	}	
}