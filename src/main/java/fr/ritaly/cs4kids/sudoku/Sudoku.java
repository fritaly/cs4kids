/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids.sudoku;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.lang.StringUtils;

import fr.ritaly.cs4kids.CustomButton;
import fr.ritaly.cs4kids.Images64x64;
import fr.ritaly.cs4kids.audio.AudioClip;
import fr.ritaly.cs4kids.audio.SoundSystem;

public class Sudoku extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6439933497070619285L;

	private CustomButton nextButton;

	private CustomButton validateButton;

	private CustomButton clearButton;

	private JLabel scoreLabel;

	private int success = 0;

	private Sudoku4x4 sudoku4x4;

	private final List<CustomButton> buttons = new ArrayList<CustomButton>();

	public Sudoku() {
		super("CS4Kids - Sudoku - v1.0");

		// try {
		// FIXME Icône ?
		// setIconImage(ImageIO.read(HRSwissArmyKnife.class
		// .getResourceAsStream("icons/switzerland.gif")));
		// } catch (IOException e) {
		// // Pas grave
		// }

		// Général, Colonnes, Lignes
		setLayout(new MigLayout(
				"",
				"[fill, grow, sg]1[fill, grow, sg]10[fill, grow, sg]1[fill, grow, sg]",
				"[fill, grow, sg]1[fill, grow, sg]10[fill, grow, sg]1[fill, grow, sg]10[]"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		setSize(1024, 768);

		buildUI();
		pack();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void buildUI() {
		sudoku4x4 = Sudoku4x4.getRandom();

		final Font font60 = new Font("Courier", Font.BOLD, 60);

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				final CustomButton button = new CustomButton(" ");

				if (sudoku4x4.getDigit(x, y) != ' ') {
					button
							.setText(Character.toString(sudoku4x4
									.getDigit(x, y)));
					button.setEnabled(false);
				} else {
					button.addActionListener(this);
				}

				button.setFont(font60);
				buttons.add(button);

				if (y == 3) {
					getContentPane().add(button, "wrap");
				} else {
					getContentPane().add(button);
				}
			}
		}

		nextButton = new CustomButton(Images64x64.NEXT);
		nextButton.setFont(font60);
		nextButton.addActionListener(this);

		getContentPane().add(nextButton, "grow");

		validateButton = new CustomButton(Images64x64.THUMBS_UP);
		validateButton.setFont(font60);
		validateButton.setEnabled(false);
		validateButton.addActionListener(this);

		getContentPane().add(validateButton, "grow");

		scoreLabel = new JLabel(Integer.toString(success));
		scoreLabel.setFont(font60);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);

		getContentPane().add(scoreLabel);

		clearButton = new CustomButton(Images64x64.CLEAR);
		clearButton.addActionListener(this);
		clearButton.setEnabled(false);

		getContentPane().add(clearButton, "wrap, sg");
	}

	private void refreshButton() {
		boolean full = true;
		boolean empty = true;

		for (CustomButton button : buttons) {
			if (StringUtils.isBlank(button.getText()) && full) {
				full = false;
			}
			if (!StringUtils.isBlank(button.getText()) && empty) {
				empty = false;
			}
		}

		validateButton.setEnabled(full);
		clearButton.setEnabled(!empty);
	}

	private void validateSolution() {
		boolean correct = true;

		for (int x = 0; x < sudoku4x4.getRowCount(); x++) {
			final Map<String, CustomButton> digits = new HashMap<String, CustomButton>();

			for (int y = 0; y < sudoku4x4.getColumnCount(); y++) {
				final CustomButton button = buttons.get(x * 4 + y);
				button.setForeground(Color.BLACK);

				if (button.getText().equals(" ")) {
					continue;
				}

				if (digits.containsKey(button.getText())) {
					// On a deux chiffres sur la même colonne
					button.setForeground(Color.RED);
					digits.get(button.getText()).setForeground(Color.RED);
					correct = false;
					break;
				}

				button.setForeground(new Color(0, 200, 0));

				digits.put(button.getText(), button);
			}
		}

		for (int y = 0; y < sudoku4x4.getColumnCount(); y++) {
			final Map<String, CustomButton> digits = new HashMap<String, CustomButton>();

			for (int x = 0; x < sudoku4x4.getRowCount(); x++) {
				final CustomButton button = buttons.get(x * 4 + y);

				if (button.getText().equals(" ")) {
					continue;
				}

				if (digits.containsKey(button.getText())) {
					// On a deux chiffres sur la même colonne
					button.setForeground(Color.RED);
					digits.get(button.getText()).setForeground(Color.RED);
					correct = false;
					break;
				}

				digits.put(button.getText(), button);
			}
		}

		if (correct) {
			SoundSystem.getInstance().play(AudioClip.SUCCESS);
			
			JOptionPane optionPane = new JOptionPane("Réponse correcte !",
					JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.OK_CANCEL_OPTION, Images64x64.POSITIVE);

			JDialog dialog = optionPane.createDialog(optionPane, "Bravo");
			dialog.setModal(true);
			dialog.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentHidden(ComponentEvent e) {
					scoreLabel.setText(Integer.toString(++success));

					rebuildUI();
				}
			});
			dialog.setLocationRelativeTo(this);

			// Afficher le dialogue
			dialog.setVisible(true);
		} else {
			SoundSystem.getInstance().play(AudioClip.ERROR);
			
			JOptionPane optionPane = new JOptionPane("Mauvaise réponse !",
					JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.OK_CANCEL_OPTION, Images64x64.NEGATIVE);

			JDialog dialog = optionPane.createDialog(optionPane,
					"Est-ce correct ?");
			dialog.setModal(true);
			dialog.setLocationRelativeTo(this);

			// Afficher le dialogue
			dialog.setVisible(true);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == null) {
			return;
		}

		if (buttons.contains(e.getSource())) {
			final CustomButton button = (CustomButton) e.getSource();
			button.setForeground(Color.BLACK);

			if (button.getText().equals(" ")) {
				button.setText("1");
			} else if (button.getText().equals("1")) {
				button.setText("2");
			} else if (button.getText().equals("2")) {
				button.setText("3");
			} else if (button.getText().equals("3")) {
				button.setText("4");
			} else if (button.getText().equals("4")) {
				button.setText(" ");
			}

			refreshButton();
		} else if (e.getSource() == validateButton) {
			validateSolution();
		} else if (e.getSource() == nextButton) {
			if (JOptionPane.showConfirmDialog(this,
					"Es-tu sûr de vouloir changer de grille ?", "Attention",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				rebuildUI();
			}
		} else if (e.getSource() == clearButton) {
			if (JOptionPane.showConfirmDialog(this,
					"Es-tu sûr de vouloir effacer la grille ?", "Attention",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {

				for (Component component : getContentPane().getComponents()) {
					if (component instanceof CustomButton) {
						final CustomButton button = (CustomButton) component;

						if (button.isEnabled()) {
							if (!StringUtils.isBlank(button.getText())) {
								button.setText(" ");
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
				// Supprimer tous les listeners
				for (CustomButton button : buttons) {
					button.removeActionListener(Sudoku.this);
				}
				nextButton.removeActionListener(Sudoku.this);
				validateButton.removeActionListener(Sudoku.this);
				clearButton.removeActionListener(Sudoku.this);

				// Supprimer les boutons
				buttons.clear();

				// Retirer tous les composants de l'IU
				getContentPane().removeAll();

				buildUI();

				validate();
			}
		});
	}
}