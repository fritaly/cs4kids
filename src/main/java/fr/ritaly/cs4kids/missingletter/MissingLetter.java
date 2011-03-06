/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids.missingletter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.lang.math.RandomUtils;

import fr.ritaly.cs4kids.CustomButton;
import fr.ritaly.cs4kids.Images64x64;
import fr.ritaly.cs4kids.ScorePanel;
import fr.ritaly.cs4kids.Word;
import fr.ritaly.cs4kids.audio.AudioClip;
import fr.ritaly.cs4kids.audio.SoundSystem;

public class MissingLetter extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6439933497070619285L;
	
	private final List<CustomButton> topButtons = new ArrayList<CustomButton>();

	private final List<CustomButton> letterButtons = new ArrayList<CustomButton>();

	private final LinkedList<Word> words = new LinkedList<Word>();

	private final ScorePanel scorePanel = new ScorePanel();

	private Word word;

	private char letter;
	
	private int errors;

	public MissingLetter() {
		super("CS4Kids - La lettre manquante - v1.0");

		words.addAll(Arrays.asList(Word.values()));

		Collections.shuffle(words);

		// Général, Colonnes, Lignes
		setLayout(new MigLayout("", "[][][][][][][][][]",
				"[nogrid][][][][]"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		setSize(800, 600);
		setState(JFrame.NORMAL);

		buildUI();
		pack();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void buildUI() {
		if (words.isEmpty()) {
			words.addAll(Arrays.asList(Word.values()));

			Collections.shuffle(words);
		}

		word = words.removeFirst();
		
		// Réinitialiser le compteur d'erreurs
		errors = 0;
		
		getContentPane().add(scorePanel, "spanx, wrap, al right");

		// Supprimer l'une des lettres du mot
		final int index = RandomUtils.nextInt(word.getName().length());

		letter = word.getName().toUpperCase().charAt(index);

		// Afficher le mot avec la lettre manquante
		for (int i = 0; i < word.getName().length(); i++) {
			final ImageIcon icon;

			if (i == index) {
				icon = Images64x64.LETTER_BLANK;
			} else {
				icon = Images64x64.getLetterIcon(word.getName().toUpperCase()
						.charAt(i));
			}

			final CustomButton button = new CustomButton(icon);

			// Bidouille sous Nimbus pour avoir un composant transparent
			button.setOpaque(false);
			button.setBorder(BorderFactory.createEmptyBorder());
			button.setBackground(new Color(0, 0, 0, 0));
			
			topButtons.add(button);

			if (i == word.getName().length() - 1) {
				getContentPane().add(button, "wrap");
			} else {
				getContentPane().add(button, "");
			}
		}

		getContentPane().add(new JLabel(word.getImage()),
				"spanx 9, gap 10 10 10 10, grow, wrap");

		for (char c = 'A'; c <= 'Z'; c++) {
			final CustomButton button = new CustomButton(
					Images64x64.getLetterIcon(c));
			button.addActionListener(this);

			// Bidouille sous Nimbus pour avoir un composant transparent
			button.setOpaque(false);
			button.setBorder(BorderFactory.createEmptyBorder());
			button.setBackground(new Color(0, 0, 0, 0));

			letterButtons.add(button);

			if ((c == 'I') || (c == 'R')) {
				// Chasser toutes les 9 lettres
				getContentPane().add(button, "wrap");
			} else {
				getContentPane().add(button, "");
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == null) {
			return;
		}

		if (letterButtons.contains(e.getSource())) {
			final CustomButton button = (CustomButton) e.getSource();

			if (Images64x64.getLetter(button.getIcon()) == letter) {
				// Bonne réponse
				SoundSystem.getInstance().play(AudioClip.SUCCESS);
				
				// Masquer la lettre
				button.fadeOut();
				
				for (CustomButton customButton : topButtons) {
					if (Images64x64.getLetter(customButton.getIcon()) == ' ') {
						customButton.setIcon(Images64x64.getLetterIcon(letter));
						break;
					}
				}

				scorePanel.incCorrectAnswers();

				// Attendre 1 seconde avant de changer l'IU
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
						
						rebuildUI();						
					}
				});
			} else {
				// Mauvaise réponse. On masque la lettre
				SoundSystem.getInstance().play(AudioClip.ERROR);

				button.fadeOut();
				
				scorePanel.incIncorrectAnswers();

				errors++;
				
				if (errors == 3) {
					// Si au moins 3 mauvaises réponses, on donne la réponse et
					// on attend que l'utilisateur clique sur la dernière lettre
					// affichée
					for (final CustomButton customButton : letterButtons) {
						if (Images64x64.getLetter(customButton.getIcon()) != letter) {
							if (!customButton.isTransparent()) {
								customButton.fadeOut();
							}
						}
					}
				}
			}
		}
	}

	private void rebuildUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Supprimer les listeners
				for (CustomButton button : letterButtons) {
					button.removeActionListener(MissingLetter.this);
				}

				letterButtons.clear();

				// Supprimer tous les composants d'un coup
				getContentPane().removeAll();

				buildUI();

				validate();

				// Nécessaire afin de redimensionner la fenêtre si le mot
				// dépasse
				pack();
			}
		});
	}
}