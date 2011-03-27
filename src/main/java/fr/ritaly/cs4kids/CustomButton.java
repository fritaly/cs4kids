/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.Timeline.TimelineState;
import org.pushingpixels.trident.callback.TimelineCallback;

import fr.ritaly.cs4kids.audio.AudioClip;
import fr.ritaly.cs4kids.audio.SoundSystem;

public final class CustomButton extends JButton implements ActionListener,
		TimelineCallback {
	
	private static enum State {
		IDLE, BLINKING;
	}

	private static final long serialVersionUID = -3240555115238126604L;

	private final Timeline timeline;

	private float transparency = 1.0f;
	
	private State state = State.IDLE;

	public CustomButton(String text) {
		super(text);

		this.timeline = new Timeline(this);
		this.timeline.addPropertyToInterpolate("transparency", 1.0f, 0.0f);
		this.timeline.addCallback(this);

		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setOpaque(false);

		addActionListener(this);
	}

	public CustomButton() {
		super();

		this.timeline = new Timeline(this);
		this.timeline.addPropertyToInterpolate("transparency", 1.0f, 0.0f);
		this.timeline.addCallback(this);

		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setOpaque(false);

		addActionListener(this);
	}

	public CustomButton(Action a) {
		super(a);

		this.timeline = new Timeline(this);
		this.timeline.addPropertyToInterpolate("transparency", 1.0f, 0.0f);
		this.timeline.addCallback(this);

		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setOpaque(false);

		addActionListener(this);
	}

	public CustomButton(Icon icon) {
		super(icon);

		this.timeline = new Timeline(this);
		this.timeline.addPropertyToInterpolate("transparency", 1.0f, 0.0f);
		this.timeline.addCallback(this);

		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setOpaque(false);

		addActionListener(this);
	}

	public CustomButton(String text, Icon icon) {
		super(text, icon);

		this.timeline = new Timeline(this);
		this.timeline.addPropertyToInterpolate("transparency", 0.0f, 1.0f);
		this.timeline.addCallback(this);

		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setOpaque(false);

		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this) {
			// Bouton cliqué, jouer un son
			if (!isTransparent()) {
				// Ne pas jouer de son si bouton invisible
				SoundSystem.getInstance().play(AudioClip.CLICK);
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		final Graphics2D graphics2D = (Graphics2D) g.create();
		graphics2D.setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC_OVER, transparency));
		super.paint(graphics2D);
		graphics2D.dispose();
	}

	public synchronized void fadeIn() {
		timeline.playReverse();
	}

	public synchronized void fadeOut() {
		timeline.play();
	}

	public synchronized void startBlink() {
		if (state == State.IDLE) {
			state = State.BLINKING;
			
			// Démarrer l'animation
			if (isTransparent()) {
				fadeIn();
			} else {
				fadeOut();
			}
		}
	}
	
	public synchronized void stopBlink() {
		if (state == State.BLINKING) {
			state = State.IDLE;

			// On arrête l'animation
			timeline.abort();
			
			setTransparency(1.0f);
		}
	}
	
	public float getTransparency() {
		return transparency;
	}

	public void setTransparency(float transparency) {
		this.transparency = transparency;

		repaint();
	}

	public boolean isTransparent() {
		return getTransparency() == 0.0f;
	}

	@Override
	public Cursor getCursor() {
		if (isTransparent()) {
			// Ne pas afficher de curseur si bouton transparent
			return new Cursor(Cursor.DEFAULT_CURSOR);
		}

		return super.getCursor();
	}
	
	@Override
	public void onTimelinePulse(float arg0, float arg1) {
	}
	
	@Override
	public void onTimelineStateChanged(TimelineState arg0,
			TimelineState arg1,
			float arg2,
			float arg3) {

		// System.out.println("Timeline's state: " + arg0 + " -> " + arg1);
		
		if (state == State.BLINKING) {
			// Continuer l'animation
			if (isTransparent()) {
				fadeIn();
			} else {
				fadeOut();
			}
		}
	}
}