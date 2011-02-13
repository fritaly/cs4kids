package fr.ritaly.cs4kids.audio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.commons.lang.Validate;

public class SoundSystem {

	private static final SoundSystem INSTANCE = new SoundSystem();

	private ExecutorService executorService;

	private boolean initialized;

	private final Map<AudioClip, Sound> sounds = new HashMap<AudioClip, Sound>();

	private SoundSystem() {
	}

	public static final SoundSystem getInstance() {
		return INSTANCE;
	}

	public synchronized boolean isInitialized() {
		return initialized;
	}

	public synchronized void dispose() {
		executorService.shutdownNow();

		try {
			executorService.awaitTermination(5, TimeUnit.SECONDS);
			executorService = null;
		} catch (InterruptedException e) {
			// Pas grave
		}

		System.exit(0);
	}

	public synchronized void init() throws IOException,
			UnsupportedAudioFileException, LineUnavailableException {

		if (initialized) {
			throw new IllegalStateException(
					"The sound system is already initialized");
		}

		AudioClip[] clips = AudioClip.values();

		for (AudioClip audioClip : clips) {
			final AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(this.getClass().getResource("/sound/" + audioClip.getSound()));

			final AudioFormat format = audioInputStream.getFormat();

			final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			final byte[] buffer = new byte[4096];

			int count = -1;

			while ((count = audioInputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, count);
			}

			final Sound sound = new Sound(outputStream.toByteArray(), format);

			sounds.put(audioClip, sound);
		}

		// On peut jouer au maximum 4 sons en même temps
		this.executorService = Executors.newFixedThreadPool(4);

		initialized = true;
	}

	public void play(final AudioClip clip) {
		if (clip == null) {
			throw new IllegalArgumentException("The given audio clip is null");
		}
		if (!isInitialized()) {
			// On ne lève pas d'erreur pour les tests unitaires
			return;
		}

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				final Sound sound = sounds.get(clip);

				if (sound == null) {
					throw new IllegalArgumentException("Unsupported sound <"
							+ clip + ">");
				}

				try {
					final Clip clip2 = (Clip) AudioSystem
							.getLine(new DataLine.Info(Clip.class, sound
									.getAudioFormat()));

					clip2.addLineListener(new LineListener() {
						@Override
						public void update(LineEvent event) {
							if (event.getType().equals(LineEvent.Type.STOP)) {
								clip2.close();
							}
						}
					});
					clip2.open(sound.getAudioFormat(), sound.getData(), 0,
							sound.getData().length);

					final DecimalFormat decimalFormat = new DecimalFormat(
							"###.#");

					clip2.loop(0);
				} catch (LineUnavailableException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
}