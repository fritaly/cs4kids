package fr.ritaly.cs4kids.audio;

public enum AudioClip {
	CLICK("beep1b.wav"),
	SUCCESS("success.wav"),
	ERROR("67454__Splashdust__negativebeep.wav");
	
	private final String sound;
	
	private AudioClip(String sound) {
		this.sound = sound;
	}

	public String getSound() {
		return sound;
	}
}