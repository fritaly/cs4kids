package fr.ritaly.cs4kids.crosswords;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import fr.ritaly.cs4kids.CustomButton;
import fr.ritaly.cs4kids.Images64x64;
import fr.ritaly.cs4kids.ScorePanel;
import fr.ritaly.cs4kids.audio.AudioClip;
import fr.ritaly.cs4kids.audio.SoundSystem;

public class CrossWords extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6439933497070619285L;

	private final List<CustomButton> gridButtons = new ArrayList<CustomButton>();

	private final List<CustomButton> letterButtons = new ArrayList<CustomButton>();

	private final LinkedList<Grid> grids = new LinkedList<Grid>();

	private final ScorePanel scorePanel = new ScorePanel();

	private Grid grid;

	private int errors;

	/**
	 * Bouton actuellement sélectionné.
	 */
	private CustomButton selection;

	public CrossWords() {
		super("CS4Kids - Mots croisés - v1.0");

		grids.addAll(Grids.getAllGrids());

		Collections.shuffle(grids);

		// Général, Colonnes, Lignes
		setLayout(new MigLayout("", "[][][][][][][][][][][][][]",
				"[nogrid]20[]20[][]"));
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
		if (grids.isEmpty()) {
			grids.addAll(Grids.getAllGrids());

			Collections.shuffle(grids);
		}

		grid = grids.removeFirst();

		// Réinitialiser le compteur d'erreurs
		errors = 0;

		getContentPane().add(scorePanel, "spanx, wrap, al right");

		final JPanel panel = new JPanel(new MigLayout());

		getContentPane().add(panel, "spanx, wrap, al center");

		// Afficher la grille du mot croisé
		for (int x = 0; x < grid.getHeight(); x++) {
			final String line = grid.getLine(x);

			for (int y = 0; y < line.length(); y++) {
				final char c = line.charAt(y);

				if ((c != ' ') && (c != '*')) {
					final CustomButton button = new CustomButton(
							Images64x64.LETTER_BLANK);
					button.addActionListener(this);

					// Bidouille sous Nimbus pour avoir un composant transparent
					button.setOpaque(false);
					button.setBorder(BorderFactory.createEmptyBorder());
					button.setBackground(new Color(0, 0, 0, 0));

					if (y == line.length() - 1) {
						panel.add(button, "wrap");
					} else {
						panel.add(button, "");
					}

					gridButtons.add(button);
				} else if (c == ' ') {
					final CustomButton button = new CustomButton();
					button.setTransparency(0.0f);

					if (y == line.length() - 1) {
						panel.add(button, "wrap");
					} else {
						panel.add(button, "");
					}

					gridButtons.add(button);
				} else if (c == '*') {
					final Definition definition = grid.getDefinition(x, y);

					final ImageIcon icon;

					if (definition.getDirection() == Direction.DOWN) {
						icon = Images64x64.DOWN;
					} else if (definition.getDirection() == Direction.RIGHT) {
						icon = Images64x64.RIGHT;
					} else {
						throw new IllegalStateException(
								"Unexpected direction: "
										+ definition.getDirection());
					}

					final CustomButton button = new CustomButton(icon);

					// Bidouille sous Nimbus pour avoir un composant transparent
					button.setOpaque(false);
					button.setBorder(BorderFactory.createEmptyBorder());
					button.setBackground(new Color(0, 0, 0, 0));
					button.setToolTipText(definition.getText());

					if (y == line.length() - 1) {
						panel.add(button, "wrap");
					} else {
						panel.add(button, "");
					}

					gridButtons.add(button);
				} else {
					throw new IllegalStateException("Unexpected char: <" + c
							+ ">");
				}
			}
		}

		for (char c = 'A'; c <= 'Z'; c++) {
			final CustomButton button = new CustomButton(
					Images64x64.getLetterIcon(c));
			button.addActionListener(this);

			// Bidouille sous Nimbus pour avoir un composant transparent
			button.setOpaque(false);
			button.setBorder(BorderFactory.createEmptyBorder());
			button.setBackground(new Color(0, 0, 0, 0));

			letterButtons.add(button);

			if ((c == 'M')) {
				// Chasser toutes les 13 lettres
				getContentPane().add(button, "wrap");
			} else {
				getContentPane().add(button, "");
			}
		}
	}

	private void validateGrid() {
		final StringBuilder builder = new StringBuilder(256);

		for (CustomButton button : gridButtons) {
			final Icon icon = button.getIcon();

			if (icon == null) {
				builder.append(' ');
			} else {
				if (icon == Images64x64.DOWN) {
					builder.append(' ');
				} else if (icon == Images64x64.RIGHT) {
					builder.append(' ');
				} else {
					final Character character = Images64x64.getLetter(icon);

					if (character != null) {
						builder.append(character);
					} else {
						builder.append(' ');
					}
				}
			}
		}

		System.out.println(builder);

		if (builder.toString().equals(grid.getId())) {
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
			// Grille pas encore remplie
			System.err.println(builder);
			System.err.println(grid.getId());
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == null) {
			return;
		}

		if (gridButtons.contains(e.getSource())) {
			// Clic sur un bouton dans la grille
			final CustomButton button = (CustomButton) e.getSource();

			if (selection == null) {
				// Première sélection
				button.startBlink();
				selection = button;
			} else if ((selection != null) && (selection != button)) {
				// Changement de sélection
				selection.stopBlink();
				button.startBlink();
				selection = button;
			} else if ((selection != null) && (selection == button)) {
				// Désélection
				selection.stopBlink();
				selection = null;
			}
		} else if (letterButtons.contains(e.getSource())) {
			if (selection == null) {
				// Ignorer le clic
				return;
			}

			final CustomButton button = (CustomButton) e.getSource();

			final Icon icon = button.getIcon();

			selection.stopBlink();
			selection.setIcon(icon);

			// Valider la grille
			validateGrid();
		}
	}

	private void rebuildUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Supprimer les listeners
				for (CustomButton button : letterButtons) {
					button.removeActionListener(CrossWords.this);
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
