/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids.anagramme;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;

public enum Word {
	BATEAU,
	VOITURE,
	CHAPEAU,
	CHAT,
	CHIEN,
	BOULE,
	BALLON,
	LAPIN,
	CANARD,
	VACHE,
	RENARD,
	// Jours de la semaine
	LUNDI,
	MARDI,
	MERCREDI,
	JEUDI,
	VENDREDI,
	SAMEDI,
	DIMANCHE,
	// Mois de l'année
	JANVIER,
	FEVRIER,
	MARS,
	AVRIL,
	MAI,
	JUIN,
	JUILLET,
	AOUT,
	SEPTEMBRE,
	OCTOBRE,
	NOVEMBRE,
	DECEMBRE,
	LIT,
	TAPIS,
	PYJAMA,
	CYGNE,
	PETIT,
	LIRE,
	COCHON,
	FERME,
	POULE,
	LOUP,
	SONT,
	TRAIN,
	ECOLE,
	SOURIS,
	TROIS,
	PEUR,
	VELO,
	BICYCLETTE,
	TROTTINETTE,
	CHEVAL,
	GALOP,
	TUTU,
	PUITS,
	PULPE,
	LUI,
	PUR,
	PUIS,
	PLUS,
	TALUS,
	MARIE,
	MAMIE,
	MARI,
	MIE,
	MAT,
	MIS,
	AMIE,
	PUMA,
	LAMA,
	MIME,
	LIME,
	LAME,
	PULL,
	// Couleurs
	NOIR,
	GRIS,
	BLANC,
	BLEU,
	JAUNE,
	ORANGE,
	MARRON,
	ROSE,
	ROUGE,
	VERT,
	VIOLET,
	// Couleurs (fin)
	MOI,
	TOI,
	NOUS,
	VOUS,
	ILS,
	ELLES,
	ELLE,
	GENOUX,
	CHOUX,
	HIBOU,
	AUTOMOBILE,
	ORDINATEUR,
	CHAISE,
	TABLE,
	SOUPE,
	TABOURET,
	MEUBLE,
	COUSSIN,
	CONSOLE,
	LAMPE,
	COFFRE,
	JOUET,
	LIVRE,
	ENIGME,
	DEVINETTE,
	JEU,
	JEUX,
	PLANTE,
	ETAGERE,
	CUISINE,
	CHAMBRE,
	DINETTE;

	// FIXME Accents majuscules

	public static Word random() {
		return values()[RandomUtils.nextInt(values().length)];
	}

	public List<Character> getCharacters() {
		final String name = name();

		final List<Character> characters = new ArrayList<Character>(name
				.length());

		for (int i = 0; i < name.length(); i++) {
			characters.add(Character.valueOf(name.charAt(i)));
		}

		return characters;
	}

	public static List<Word> getWords(int length) {
		final List<Word> words = new ArrayList<Word>();

		for (Word word : values()) {
			if (word.name().length() == length) {
				words.add(word);
			}
		}

		return words;
	}
	
	public static int getLongestWordLength() {
		int length = 0;
		
		for (Word word : values()) {
			if (word.name().length() > length) {
				length = word.name().length();
			}
		}
		
		return length;
	}
}