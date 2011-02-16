/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import fr.ritaly.cs4kids.anagramme.Anagramme;
import fr.ritaly.cs4kids.audio.SoundSystem;
import fr.ritaly.cs4kids.kezako.Kezako;
import fr.ritaly.cs4kids.sudoku.Sudoku;

public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6439933497070619285L;

	private CustomButton anagrammeButton;

	private CustomButton sudokuButton;

	private CustomButton kezakoButton;
	
	private JFrame anagrammeFrame;
	
	private JFrame sudokuFrame;
	
	private JFrame kezakoFrame;

	public Main() {
		super("CS4Kids - v1.0");

		// Général, Colonnes, Lignes
		setLayout(new MigLayout("", "[fill, grow]", "[]"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		final Font font = new Font("Arial", Font.BOLD, 40);

		anagrammeButton = new CustomButton("Anagramme");
		anagrammeButton.addActionListener(this);
		anagrammeButton.setFont(font);

		getContentPane().add(anagrammeButton, "wrap");

		sudokuButton = new CustomButton("Sudoku");
		sudokuButton.addActionListener(this);
		sudokuButton.setFont(font);

		getContentPane().add(sudokuButton, "wrap");

		kezakoButton = new CustomButton("Kezako");
		kezakoButton.addActionListener(this);
		kezakoButton.setFont(font);

		getContentPane().add(kezakoButton, "wrap");
		
		try {
			// FIXME Si pas de son, ne pas lever d'erreur ? 
			SoundSystem.getInstance().init();
		} catch (IOException e) {
			throw new RuntimeException("Error when initializing sound", e);
		} catch (UnsupportedAudioFileException e) {
			throw new RuntimeException("Error when initializing sound", e);
		} catch (LineUnavailableException e) {
			throw new RuntimeException("Error when initializing sound", e);
		}
	}

	public static void main(String[] args) {
		Utils.setLookAndFeel();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == anagrammeButton) {
			anagrammeFrame = new Anagramme();
		} else if (e.getSource() == sudokuButton) {
			sudokuFrame = new Sudoku();
		} else if (e.getSource() == kezakoButton) {
			kezakoFrame = new Kezako();
		}
	}
}