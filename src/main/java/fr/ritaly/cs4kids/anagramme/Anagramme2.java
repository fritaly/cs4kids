/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids.anagramme;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import fr.ritaly.cs4kids.CustomButton;
import fr.ritaly.cs4kids.Images64x64;
import fr.ritaly.cs4kids.Utils;

import net.miginfocom.swing.MigLayout;

public class Anagramme2 extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6439933497070619285L;

	private final List<CustomButton> letterButtons = new ArrayList<CustomButton>();

	private final List<CustomButton> topButtons = new ArrayList<CustomButton>();

	private CustomButton nextButton;

	private CustomButton validateButton;

	private CustomButton clearButton;

	private int success = 0;

	private JLabel scoreLabel;

	private Word word;

	private DefaultListModel listModel;

	private JList list;

	public Anagramme2() {
		super("CS4Kids - Anagramme - v1.0");

		// Général, Colonnes, Lignes
		setLayout(new MigLayout("gap 0px", "[fill, grow]", "[][][][]"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		setSize(1024, 768);

		buildUI();
		pack();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void buildUI() {
		final Font font60 = new Font("Courier", Font.BOLD, 60);

		final Word previousWord = word;
		
		if (previousWord == null) {
			word = Word.random();
		} else {
			do {
				// Ne pas tirer 2 fois de suite le même mot
				word = Word.random();	
			} while (word.equals(previousWord));
		}

		final List<Character> chars = word.getCharacters();
		
		StringBuilder builder = new StringBuilder();
		
		do {
			// Mélanger les lettres tant qu'elles ne sont pas dans le désordre !
			Collections.shuffle(chars);

			builder.setLength(0);

			for (Character character : chars) {
				builder.append(character);
			}
		} while (builder.toString().equals(word.name()));


		for (int i = 0; i < Word.getLongestWordLength(); i++) {
			if (i < chars.size()) {
				final CustomButton button = new CustomButton();

				// Bidouille sous Nimbus pour avoir un composant transparent
				button.setOpaque(false);
				button.setBorder(BorderFactory.createEmptyBorder());
				button.setBackground(new Color(0, 0, 0, 0));
				
				button.addActionListener(this);
				button.setTransparency(0.0f);

				getContentPane().add(button, "sg letter");

				topButtons.add(button);
			} else {
				final JLabel label = new JLabel();
				label.setForeground(label.getBackground());

				getContentPane().add(label, "sg letter");
			}
		}

		nextButton = new CustomButton(Images64x64.NEXT);
		nextButton.setEnabled(true);
		nextButton.addActionListener(this);

		getContentPane().add(nextButton, "grow, sg button");

		scoreLabel = new JLabel(Integer.toString(success));
		scoreLabel.setFont(font60);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);

		getContentPane().add(scoreLabel, "wrap, sg button");

		for (int i = 0; i < Word.getLongestWordLength(); i++) {

			if (i < chars.size()) {
				final Character character = chars.get(i);

				final CustomButton button = new CustomButton(Images64x64
						.getLetterIcon(character));

				// Bidouille sous Nimbus pour avoir un composant transparent
				button.setOpaque(false);
				button.setBorder(BorderFactory.createEmptyBorder());
				button.setBackground(new Color(0, 0, 0, 0));

				button.addActionListener(this);

				getContentPane().add(button, "sg letter");

				letterButtons.add(button);
			} else {
				final JLabel label = new JLabel();

				getContentPane().add(label, "sg letter");
			}
		}

		validateButton = new CustomButton(Images64x64.THUMBS_UP);
		validateButton.addActionListener(this);
		validateButton.setEnabled(false);

		getContentPane().add(validateButton, "grow, sg button");

		clearButton = new CustomButton(Images64x64.CLEAR);
		clearButton.addActionListener(this);
		clearButton.setEnabled(false);

		getContentPane().add(clearButton, "wrap, sg button");

		listModel = new DefaultListModel();

		// Mots de même longueur ?
		final List<Word> sameLengthWords = Word.getWords(word.name().length());
		sameLengthWords.remove(word);

		Collections.shuffle(sameLengthWords);

		while (sameLengthWords.size() > 8) {
			sameLengthWords.remove(0);
		}

		sameLengthWords.add(word);

		// Remélanger les mots encore une fois
		Collections.shuffle(sameLengthWords);

		for (Word aWord : sameLengthWords) {
			// L'espace terminal permet d'espacer les entrées dans la liste
			listModel.addElement(aWord.name() + " ");
		}

		list = new JList(listModel);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setFont(font60);
		list.setVisibleRowCount(5);
		list.setOpaque(false);

		getContentPane().add(list, "spanx " + Word.getLongestWordLength() + 1);
	}

	public static void main(String[] args) {
		Utils.setLookAndFeel();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Anagramme2();
			}
		});
	}

	private void refreshValidateButton() {
		int letterCount = 0;

		for (CustomButton button : topButtons) {
			if (button.getIcon() != null) {
				letterCount++;
			}
		}

		validateButton.setEnabled(letterCount == word.name().length());
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == null) {
			return;
		}

		if (letterButtons.contains(e.getSource())) {
			final CustomButton button = (CustomButton) e.getSource();

			if (button.getTransparency() != 1.0f) {
				// Dès que la transparence du bouton n'est plus bonne, on ignore
				// les clics dessus !
				return;
			}

			// Rechercher le premier bouton du haut vide
			for (CustomButton topButton : topButtons) {
				if (topButton.getIcon() == null) {
					topButton.setIcon(button.getIcon());
					topButton.fadeIn();
					break;
				}
			}

			button.fadeOut();

			refreshValidateButton();
		} else if (topButtons.contains(e.getSource())) {
			final CustomButton button = (CustomButton) e.getSource();

			if (button.getTransparency() != 1.0f) {
				// Dès que la transparence du bouton n'est plus bonne, on ignore
				// les clics dessus !
				return;
			}

			final Icon icon = button.getIcon();
			button.setIcon(null);
			button.fadeOut();

			// On réactive le bouton désactivé
			for (CustomButton letterButton : letterButtons) {
				if (letterButton.getIcon().equals(icon)
						&& letterButton.isTransparent()) {

					letterButton.fadeIn();
					break;
				}
			}

			button.fadeOut();

			refreshValidateButton();
		} else if (nextButton == e.getSource()) {
			rebuildUI();
		} else if (validateButton == e.getSource()) {
			// Mot formé ?
			StringBuilder builder = new StringBuilder();

			for (CustomButton topButton : topButtons) {
				if (topButton.getIcon() != null) {
					builder.append(Images64x64.getLetter(topButton.getIcon()));
				}
			}

			if (builder.toString().equals(word.name())) {
				// Réponse correcte
				JOptionPane optionPane = new JOptionPane("Réponse correcte !",
						JOptionPane.PLAIN_MESSAGE,
						JOptionPane.OK_CANCEL_OPTION, Images64x64.POSITIVE);

				JDialog dialog = optionPane.createDialog(optionPane,
						"As-tu bien répondu ?");
				dialog.setModal(true);
				dialog.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentHidden(ComponentEvent e) {
						success++;
						rebuildUI();
					}
				});
				dialog.setLocationRelativeTo(this);

				// Afficher le dialogue
				dialog.setVisible(true);
			} else {
				// Réponse fausse
				JOptionPane optionPane = new JOptionPane("Mauvaise réponse !",
						JOptionPane.ERROR_MESSAGE,
						JOptionPane.OK_CANCEL_OPTION, Images64x64.NEGATIVE);

				JDialog dialog = optionPane.createDialog(optionPane,
						"As-tu bien répondu ?");
				dialog.setModal(true);
				dialog.setLocationRelativeTo(this);

				// Afficher le dialogue
				dialog.setVisible(true);
			}
		}
	}

	private void rebuildUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Supprimer les listeners
				for (CustomButton letterButton : letterButtons) {
					letterButton.removeActionListener(Anagramme2.this);
				}
				for (CustomButton topButton : topButtons) {
					topButton.removeActionListener(Anagramme2.this);
				}
				nextButton.removeActionListener(Anagramme2.this);
				validateButton.removeActionListener(Anagramme2.this);

				letterButtons.clear();
				topButtons.clear();

				listModel.clear();

				// Supprimer tous les composants d'un coup
				getContentPane().removeAll();

				buildUI();

				validate();
			}
		});
	}
}