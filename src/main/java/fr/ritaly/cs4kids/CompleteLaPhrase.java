/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;


public class CompleteLaPhrase extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -6439933497070619285L;

	private CustomButton nextButton;

	private CustomButton validateButton;

	private Quizz quizz;

	private DefaultListModel listModel;

	private JList list;

	public CompleteLaPhrase() {
		super("CS4Kids - Complète la phrase - v1.0");

		// Général, Colonnes, Lignes
		setLayout(new MigLayout("", "[fill, grow][][]", "[][]"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setSize(1024, 768);

		buildUI();

		setVisible(true);
	}

	private void buildUI() {
		final Font font60 = new Font("Courier", Font.BOLD, 60);

		quizz = Quizz.QUIZZ1;

		final JLabel label = new JLabel("<html>" + quizz.getIncompleteSentence() + "</html>");
		label.setFont(font60);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		add(label);

		nextButton = new CustomButton(">>");
		nextButton.setFont(font60);
		nextButton.addActionListener(this);

		add(nextButton);

		validateButton = new CustomButton("OK");
		validateButton.setFont(font60);
		validateButton.addActionListener(this);
		validateButton.setEnabled(false);
		validateButton.setForeground(new Color(0, 200, 0));

		add(validateButton, "wrap");

		listModel = new DefaultListModel();

		for (String word : quizz.getProposals()) {
			// L'espace terminal permet d'espacer les entrées dans la liste
			listModel.addElement(word + " ");
		}

		list = new JList(listModel);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setFont(font60);
		list.setVisibleRowCount(6);

		add(list, "spanx 3");
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel((LookAndFeel) Class.forName(
					"com.jgoodies.looks.windows.WindowsLookAndFeel")
					.newInstance());
		} catch (Exception e) {
			// Classe non trouvée, utiliser le look & feel standard
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CompleteLaPhrase();
			}
		});
	}

	private void refreshValidateButton() {
//		for (CustomButton topButton : topButtons) {
//			if (topButton.getText().equals(" ")) {
//				validateButton.setEnabled(false);
//				return;
//			}
//		}
//
//		validateButton.setEnabled(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == null) {
			return;
		}

//		if (nextButton == e.getSource()) {
//			SwingUtilities.invokeLater(new Runnable() {
//				public void run() {
//					for (CustomButton letterButton : letterButtons) {
//						letterButton.removeActionListener(CompleteLaPhrase.this);
//						remove(letterButton);
//					}
//					letterButtons.clear();
//
//					for (CustomButton topButton : topButtons) {
//						topButton.removeActionListener(CompleteLaPhrase.this);
//						remove(topButton);
//					}
//					topButtons.clear();
//
//					nextButton.removeActionListener(CompleteLaPhrase.this);
//					remove(nextButton);
//
//					validateButton.removeActionListener(CompleteLaPhrase.this);
//					remove(validateButton);
//
//					listModel.clear();
//					remove(list);
//
//					buildUI();
//
//					validate();
//				}
//			});
//		} else if (validateButton == e.getSource()) {
//			// Mot formé ?
//			StringBuilder builder = new StringBuilder();
//
//			for (CustomButton topButton : topButtons) {
//				builder.append(topButton.getText());
//			}
//
//			if (builder.toString().equals(word.name())) {
//				// Réponse correcte
//				JOptionPane
//						.showMessageDialog(this, "Réponse correcte !",
//								"As-tu bien répondu ?",
//								JOptionPane.INFORMATION_MESSAGE);
//			} else {
//				// Réponse fausse
//				JOptionPane.showMessageDialog(this, "Mauvaise réponse !",
//						"As-tu bien répondu ?", JOptionPane.ERROR_MESSAGE);
//			}
//		}
	}
}