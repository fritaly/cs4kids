package fr.ritaly.cs4kids.spelltheword;

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
import fr.ritaly.cs4kids.CustomButton;
import fr.ritaly.cs4kids.Images64x64;
import fr.ritaly.cs4kids.ScorePanel;
import fr.ritaly.cs4kids.Word;
import fr.ritaly.cs4kids.audio.AudioClip;
import fr.ritaly.cs4kids.audio.SoundSystem;

public class SpellTheWord extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6439933497070619285L;
	
	private final List<CustomButton> topButtons = new ArrayList<CustomButton>();

	private final List<CustomButton> letterButtons = new ArrayList<CustomButton>();

	private final LinkedList<Word> words = new LinkedList<Word>();

	private final ScorePanel scorePanel = new ScorePanel();

	private Word word;

	private int errors;

	public SpellTheWord() {
		super("CS4Kids - Ecris le mot - v1.0");

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

		// Afficher le mot avec la lettre manquante
		for (int i = 0; i < word.getName().length(); i++) {
			final ImageIcon icon = Images64x64.LETTER_BLANK;

			final CustomButton button = new CustomButton(icon);
			button.addActionListener(this);

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
			
			// Premier emplacement libre ?
			final int firstBlankLetterIndex = getFirstBlankLetterIndex();
			
			if (firstBlankLetterIndex == -1) {
				// Toutes les lettres ont été renseignées
				return;
			}
			
			topButtons.get(firstBlankLetterIndex).setIcon(button.getIcon());
			
			final int nextBlankLetterIndex = getFirstBlankLetterIndex();
			
			if (nextBlankLetterIndex == -1) {
				// Si toutes les lettres ont été renseignées, on valide la 
				// réponse
				final StringBuilder builder = new StringBuilder(16);
				
				for (CustomButton customButton : topButtons) {
					builder.append(Images64x64.getLetter(customButton.getIcon()));
				}
				
				final String answer = builder.toString();
				
				if (answer.equalsIgnoreCase(word.getName())) {
					// Bonne réponse
					SoundSystem.getInstance().play(AudioClip.SUCCESS);
					
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
					// Mauvaise réponse
					
					// Attendre 1 seconde avant continuer pour laisser le temps
					// à la dernière lettre de s'afficher !
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
							}
							
							SoundSystem.getInstance().play(AudioClip.ERROR);

							scorePanel.incIncorrectAnswers();
							
							errors++;
							
							// On retire les lettres qui sont mauvaises
							for (int i = 0; i < answer.length(); i++) {
								final char actual = answer.charAt(i);
								final char expected = word.getName().charAt(i);
								
								if (Character.toUpperCase(actual) == Character.toUpperCase(expected)) {
									// Lettre correcte
								} else {
									// Lettre incorrecte
									topButtons.get(i).setIcon(Images64x64.LETTER_BLANK);
								}
							}
							
							if (errors == 3) {
								// Si au moins 3 mauvaises réponses, on donne la réponse 
								// et on attend que l'utilisateur clique sur la dernière
								// lettre affichée
//								for (final CustomButton customButton : letterButtons) {
//									if (Images64x64.getLetter(customButton.getIcon()) != letter) {
//										if (!customButton.isTransparent()) {
//											customButton.fadeOut();
//										}
//									}
//								}
							}
						}
					});
				}
			}
		} else if (topButtons.contains(e.getSource())) {
			final CustomButton button = (CustomButton) e.getSource();
			
			if (button.getIcon() == Images64x64.LETTER_BLANK) {
				// Ne rien faire
				return;
			}
			
			// Effacer la lettre
			button.setIcon(Images64x64.LETTER_BLANK);
		}
	}
	
	private int getFirstBlankLetterIndex() {
		for (CustomButton button : topButtons) {
			if (button.getIcon() == Images64x64.LETTER_BLANK) {
				return topButtons.indexOf(button);
			}
		}
		
		return -1;
	}

	private void rebuildUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				// Supprimer les listeners
				for (CustomButton button : letterButtons) {
					button.removeActionListener(SpellTheWord.this);
				}
				for (CustomButton button : topButtons) {
					button.removeActionListener(SpellTheWord.this);
				}

				letterButtons.clear();
				topButtons.clear();

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
