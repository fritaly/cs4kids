package fr.ritaly.cs4kids.kezako;

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
import fr.ritaly.cs4kids.ScorePanel;
import fr.ritaly.cs4kids.audio.AudioClip;
import fr.ritaly.cs4kids.audio.SoundSystem;

public class Kezako extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6439933497070619285L;

	private final List<CustomButton> buttons = new ArrayList<CustomButton>();

	private final LinkedList<Question> questions = new LinkedList<Question>();

	private final ScorePanel scorePanel = new ScorePanel();

	private Question question;

	public Kezako() {
		super("CS4Kids - Kezako - v1.0");

		questions.addAll(Arrays.asList(Question.values()));

		Collections.shuffle(questions);

		// G�n�ral, Colonnes, Lignes
		setLayout(new MigLayout("", "[fill, grow][][]", "[][][][]"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		getContentPane().add(scorePanel, "wrap");

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

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == null) {
			return;
		}

		if (buttons.contains(e.getSource())) {
			final CustomButton button = (CustomButton) e.getSource();

			if (question.getProposals().get(0).equals(button.getText())) {
				// Bonne r�ponse
				SoundSystem.getInstance().play(AudioClip.SUCCESS);
				
				scorePanel.incCorrectAnswers();

				rebuildUI();
			} else {
				// Mauvaise r�ponse. On d�sactive le bouton
				SoundSystem.getInstance().play(AudioClip.ERROR);
				
				button.setEnabled(false);
				
				scorePanel.incIncorrectAnswers();

				int disabledCount = 0;

				for (CustomButton customButton : buttons) {
					if (!customButton.isEnabled()) {
						disabledCount++;
					}
				}

				if (disabledCount == buttons.size() - 1) {
					// Si au moins deux mauvaises r�ponses, on indique la bonne
					// et on passe � l'image suivante
					rebuildUI();
				}
			}

			// button.fadeOut();
		}
	}

	private void rebuildUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
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
