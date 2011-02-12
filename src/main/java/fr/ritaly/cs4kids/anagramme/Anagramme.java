/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids.anagramme;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.lang.StringUtils;

import fr.ritaly.cs4kids.CustomButton;
import fr.ritaly.cs4kids.Utils;

public class Anagramme extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6439933497070619285L;

	private static final ImageIcon THUMBS_UP_ICON = new ImageIcon(
			Anagramme.class.getClassLoader().getResource(
					"images/check_64x64.png"));

	private static final ImageIcon NEXT_ICON = new ImageIcon(Anagramme.class
			.getClassLoader().getResource("images/refresh_64x64.png"));
	
	private static final ImageIcon CLEAR_ICON = new ImageIcon(Anagramme.class
			.getClassLoader().getResource("images/remove_64x64.png"));
	
	private static final ImageIcon POSITIVE_ICON = new ImageIcon(Anagramme.class
			.getClassLoader().getResource("images/positive_64x64.png"));
	
	private static final ImageIcon NEGATIVE_ICON = new ImageIcon(Anagramme.class
			.getClassLoader().getResource("images/negative_64x64.png"));

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

	public Anagramme() {
		super("CS4Kids - Anagramme - v1.0");

		// Général, Colonnes, Lignes
		setLayout(new MigLayout("", "[fill, grow]", "[][][][]"));
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

		word = Word.random();

		final List<Character> chars = word.getCharacters();

		Collections.shuffle(chars);

		for (int i = 0; i < Word.getLongestWordLength(); i++) {
			if (i < chars.size()) {
				final CustomButton button = new CustomButton(" ");
				button.setFont(font60);
				button.setForeground(button.getBackground());
				button.addActionListener(this);
				button.setTransparency(0.0f);

				getContentPane().add(button, "sg");

				topButtons.add(button);
			} else {
				final JLabel label = new JLabel();
				label.setFont(font60);
				label.setForeground(label.getBackground());

				getContentPane().add(label, "sg");
			}
		}

		nextButton = new CustomButton(NEXT_ICON);
		nextButton.setFont(font60);
		nextButton.setEnabled(true);
		nextButton.addActionListener(this);

		getContentPane().add(nextButton, "grow, sg");

		scoreLabel = new JLabel(Integer.toString(success));
		scoreLabel.setFont(font60);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);

		getContentPane().add(scoreLabel, "wrap, sg");

		for (int i = 0; i < Word.getLongestWordLength(); i++) {

			if (i < chars.size()) {
				final Character character = chars.get(i);

				final CustomButton button = new CustomButton(Character
						.toString(character));
				button.setFont(font60);
				button.addActionListener(this);

				getContentPane().add(button, "sg");

				letterButtons.add(button);
			} else {
				final JLabel label = new JLabel();

				getContentPane().add(label, "sg");
			}
		}

		validateButton = new CustomButton(THUMBS_UP_ICON);
		validateButton.addActionListener(this);
		validateButton.setEnabled(false);
		
		getContentPane().add(validateButton, "grow, sg");
		
		clearButton = new CustomButton(CLEAR_ICON);
		clearButton.addActionListener(this);
		clearButton.setEnabled(false);

		getContentPane().add(clearButton, "wrap, sg");

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
				new Anagramme();
			}
		});
	}

	private void refreshValidateButton() {
		int letterCount = 0;

		for (CustomButton topButton : topButtons) {
			if (!StringUtils.isBlank(topButton.getText())) {
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

			if (button.isTransparent()) {
				return;
			}

			final String letter = button.getText();

			// Rechercher le premier bouton du haut vide
			for (CustomButton topButton : topButtons) {
				if (topButton.getText().equals(" ")) {
					topButton.setText(letter);
					topButton.fadeIn();
					break;
				}
			}

			button.fadeOut();

			refreshValidateButton();
		} else if (topButtons.contains(e.getSource())) {
			final CustomButton button = (CustomButton) e.getSource();

			if (button.isTransparent()) {
				return;
			}

			final String letter = button.getText();
			button.setText(" ");
			button.fadeOut();

			// On réactive le bouton désactivé
			for (CustomButton letterButton : letterButtons) {
				if (letterButton.getText().equals(letter)
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
				if (!StringUtils.isBlank(topButton.getText())) {
					builder.append(topButton.getText());
				}
			}

			if (builder.toString().equals(word.name())) {
				// Réponse correcte
				JOptionPane optionPane = new JOptionPane("Réponse correcte !",
						JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION,
						POSITIVE_ICON);

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
						JOptionPane.ERROR_MESSAGE, JOptionPane.OK_CANCEL_OPTION,
						NEGATIVE_ICON);

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
					letterButton.removeActionListener(Anagramme.this);
				}
				for (CustomButton topButton : topButtons) {
					topButton.removeActionListener(Anagramme.this);
				}
				nextButton.removeActionListener(Anagramme.this);
				validateButton.removeActionListener(Anagramme.this);
				
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