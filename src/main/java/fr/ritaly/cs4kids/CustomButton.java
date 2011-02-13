/* *******************************************************
 * � 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import org.pushingpixels.trident.Timeline;

public final class CustomButton extends JButton {

	private static final long serialVersionUID = -3240555115238126604L;

	private final Timeline timeline;

	private float transparency = 1.0f;

	public CustomButton(String text) {
		super(text);

		this.timeline = new Timeline(this);
		this.timeline.addPropertyToInterpolate("transparency", 1.0f, 0.0f);

		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setOpaque(false);
	}

	public CustomButton() {
		super();

		this.timeline = new Timeline(this);
		this.timeline.addPropertyToInterpolate("transparency", 1.0f, 0.0f);

		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setOpaque(false);
	}

	public CustomButton(Action a) {
		super(a);

		this.timeline = new Timeline(this);
		this.timeline.addPropertyToInterpolate("transparency", 1.0f, 0.0f);

		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setOpaque(false);
	}

	public CustomButton(Icon icon) {
		super(icon);

		this.timeline = new Timeline(this);
		this.timeline.addPropertyToInterpolate("transparency", 1.0f, 0.0f);

		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setOpaque(false);
	}

	public CustomButton(String text, Icon icon) {
		super(text, icon);

		this.timeline = new Timeline(this);
		this.timeline.addPropertyToInterpolate("transparency", 0.0f, 1.0f);

		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setOpaque(false);
	}

	@Override
	public void paint(Graphics g) {
		final Graphics2D graphics2D = (Graphics2D) g.create();
		graphics2D.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, transparency));
		super.paint(graphics2D);
		graphics2D.dispose();
	}

	public void fadeIn() {
		timeline.playReverse();
	}

	public void fadeOut() {
		timeline.play();
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
}