/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids.kezako;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import fr.ritaly.cs4kids.CustomButton;
import fr.ritaly.cs4kids.Utils;

public class Kezako extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6439933497070619285L;

	private final List<CustomButton> buttons = new ArrayList<CustomButton>();

	private final LinkedList<Question> questions = new LinkedList<Question>();

	private int score = 0;

	private int maxScore = 0;

	private JLabel scoreLabel;

	private Question question;

	public Kezako() {
		super("CS4Kids - Kezako - v1.0");

		questions.addAll(Arrays.asList(Question.values()));

		Collections.shuffle(questions);

		// Général, Colonnes, Lignes
		setLayout(new MigLayout("", "[fill, grow][][]", "[][][][]"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		setSize(800, 600);
		setState(JFrame.NORMAL);

		buildUI();
		// pack();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void buildUI() {
		final Font font = new Font("Arial", Font.BOLD, 40);

		if (questions.isEmpty()) {
			questions.addAll(Arrays.asList(Question.values()));

			Collections.shuffle(questions);
		}

		question = questions.removeFirst();

		final JLabel questionLabel = new JLabel(question.getQuestion(),
				JLabel.CENTER);
		questionLabel.setFont(font);

		getContentPane().add(questionLabel, "");

		scoreLabel = new JLabel(Integer.toString(score) + "/"
				+ Integer.toString(maxScore), JLabel.CENTER);
		scoreLabel.setFont(font);

		if (score < 0) {
			scoreLabel.setForeground(Color.RED);
		} else {
			scoreLabel.setForeground(new Color(0, 200, 0));
		}

		final JLabel label = new JLabel("Score: ");
		label.setFont(font);

		getContentPane().add(label, "");
		getContentPane().add(scoreLabel, "wrap");

		getContentPane().add(new JLabel(question.getImage()),
				"spanx 3, gap 10 10 10 10, wrap");

		final List<String> proposals = new ArrayList<String>(
				question.getProposals());

		Collections.shuffle(proposals);

		for (String proposal : proposals) {
			final CustomButton button = new CustomButton(proposal);
			button.setFont(font);
			button.addActionListener(this);

			buttons.add(button);

			getContentPane().add(button, "spanx 3, wrap");
		}
	}

	public static void main(String[] args) {
		Utils.setLookAndFeel();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Kezako();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == null) {
			return;
		}

		if (buttons.contains(e.getSource())) {
			final CustomButton button = (CustomButton) e.getSource();

			if (question.getProposals().get(0).equals(button.getText())) {
				// Bonne réponse
				score++;

				scoreLabel.setText(Integer.toString(score) + "/"
						+ Integer.toString(maxScore));

				if (score < 0) {
					scoreLabel.setForeground(Color.RED);
				} else {
					scoreLabel.setForeground(new Color(0, 200, 0));
				}

				rebuildUI();
			} else {
				// Mauvaise réponse. On désactive le bouton
				button.setEnabled(false);

				score--;

				scoreLabel.setText(Integer.toString(score) + "/"
						+ Integer.toString(maxScore));

				if (score < 0) {
					scoreLabel.setForeground(Color.RED);
				} else {
					scoreLabel.setForeground(new Color(0, 200, 0));
				}

				int disabledCount = 0;

				for (CustomButton customButton : buttons) {
					if (!customButton.isEnabled()) {
						disabledCount++;
					}
				}

				if (disabledCount == buttons.size() - 1) {
					// Si au moins deux mauvaises réponses, on indique la bonne
					// et on passe à l'image suivante
					rebuildUI();
				}
			}

			// button.fadeOut();
		}
	}

	private void rebuildUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				maxScore++;

				// Supprimer les listeners
				for (CustomButton button : buttons) {
					button.removeActionListener(Kezako.this);
				}

				buttons.clear();

				// Supprimer tous les composants d'un coup
				getContentPane().removeAll();

				buildUI();

				validate();
			}
		});
	}
}