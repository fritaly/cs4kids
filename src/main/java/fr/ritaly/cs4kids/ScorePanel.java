package fr.ritaly.cs4kids;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ScorePanel extends JPanel {

	private static final long serialVersionUID = 5480178258083584605L;

	private int correctAnswers;

	private int incorrectAnswers;
	
	private final JLabel label;

	public ScorePanel() {
		setLayout(new MigLayout());

		final Font font = new Font("Arial", Font.BOLD, 40);

		final JLabel textLabel = new JLabel("Score: ");
		textLabel.setFont(font);

		add(textLabel);

		label = new JLabel(Integer.toString(correctAnswers) + "/"
				+ Integer.toString(getAnswers()), JLabel.CENTER);
		label.setFont(font);

		add(label);
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public int getIncorrectAnswers() {
		return incorrectAnswers;
	}

	public int getAnswers() {
		return correctAnswers + incorrectAnswers;
	}

	public void incCorrectAnswers() {
		correctAnswers++;

		updateLabel();
	}

	public void incIncorrectAnswers() {
		incorrectAnswers++;

		updateLabel();
	}

	private void updateLabel() {
		label.setText(Integer.toString(correctAnswers) + "/"
				+ Integer.toString(getAnswers()));
	}
}