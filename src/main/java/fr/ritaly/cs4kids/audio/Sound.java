package fr.ritaly.cs4kids.audio;

import javax.sound.sampled.AudioFormat;

import org.apache.commons.lang.Validate;

public class Sound {

	private final byte[] data;
	
	private final AudioFormat audioFormat;
	
	public Sound(byte[] data, AudioFormat audioFormat) {
		Validate.isTrue(data != null);
		Validate.isTrue(audioFormat != null);
		
		this.data = data;
		this.audioFormat = audioFormat;
	}

	public byte[] getData() {
		return data;
	}

	public AudioFormat getAudioFormat() {
		return audioFormat;
	}
}
