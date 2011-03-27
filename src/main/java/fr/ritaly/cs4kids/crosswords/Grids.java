package fr.ritaly.cs4kids.crosswords;

import java.util.ArrayList;
import java.util.List;

public class Grids {

	public static List<Grid> getAllGrids() {
		final ArrayList<Grid> grids = new ArrayList<Grid>();

		{
			final Grid grid = new Grid();
			grid.setLines("    *   ",
					"*BONBON ",
					"    I   ",
					"    B   ",
					"  *MELON",
					"    R   ",
					"    O   ",
					" *MONTRE");
			grid.addDefinition(0, 4, new Definition(
					"Il donne du lait au bébé", Direction.DOWN));
			grid.addDefinition(1, 0, new Definition(
					"Il donne des caries si on en mange trop", Direction.RIGHT));
			grid.addDefinition(4, 2, new Definition(
					"Fruit d'été orange avec des pépins à l'intérieur",
					Direction.RIGHT));
			grid.addDefinition(7, 1, new Definition(
					"Donne l'heure à ton poignet", Direction.RIGHT));

			grids.add(grid);
		}
		{
			final Grid grid = new Grid();
			grid.setLines(
					" * * *  ",
					"*VALISE ",
					" O I I  ",
					" I O R  ",
					" T N O  ",
					" U   P  ",
					" R      ",
					"*ETOILE ");
			grid.addDefinition(0, 1, new Definition(
					"Autre nom pour une automobile", Direction.DOWN));
			grid.addDefinition(0, 3, new Definition(
					"C'est le roi des animaux", Direction.DOWN));
			grid.addDefinition(0, 5, new Definition(
					"On le boît avec de l'eau et des glaçons en été.\nPeut être à la fraise ou à la menthe par exemple", Direction.DOWN));
			grid.addDefinition(1, 0, new Definition(
					"On y range les habits pour partir en vacances",
					Direction.RIGHT));
			grid.addDefinition(7, 0, new Definition(
					"La nuit, le ciel est en plein", Direction.RIGHT));

			grids.add(grid);
		}
		{
			final Grid grid = new Grid();
			grid.setLines(
					" *  *   ",
					"*CANARD ",
					" O  N * ",
					"*CHIENS ",
					" H    A ",
					"*OURS L ",
					" N    E ",
					"*STYLO  ");
			grid.addDefinition(0, 1, new Definition(
					"Il a la queue en tire-bouchon (Au pluriel)", Direction.DOWN));
			grid.addDefinition(0, 4, new Definition(
					"Animal dont le cri est 'Hi Han'", Direction.DOWN));
			grid.addDefinition(1, 0, new Definition(
					"Animal dont le cri est 'Coin Coin'", Direction.RIGHT));
			grid.addDefinition(3, 0, new Definition(
					"Animal dont le cri est 'Ouaf Ouaf' (Au pluriel)", Direction.RIGHT));
			grid.addDefinition(2, 6, new Definition(
					"Pas propre",
					Direction.DOWN));
			grid.addDefinition(5, 0, new Definition(
					"Animal qui aime le miel", Direction.RIGHT));
			grid.addDefinition(7, 0, new Definition(
					"On l'utilise pour écrire", Direction.RIGHT));

			grids.add(grid);
		}
		{
			final Grid grid = new Grid();
			grid.setLines(
					" *    * ",
					" E    D ",
					"*PIRATE ",
					" E *  S ",
					"*ECOLE  ",
					"   I    ",
					"   E    ",
					"*BOSSE  ");
			grid.addDefinition(0, 1, new Definition(
					"Arme du chevalier", Direction.DOWN));
			grid.addDefinition(0, 6, new Definition(
					"On les tire pour jouer au 421", Direction.DOWN));
			grid.addDefinition(2, 0, new Definition(
					"Chasseur de trésor avec une jambe de bois", Direction.RIGHT));
			grid.addDefinition(3, 3, new Definition(
					"Animal de la ferme qui fait 'Coin Coin' et qui a son propre jeu (Au pluriel)", Direction.DOWN));
			grid.addDefinition(4, 0, new Definition(
					"Les enfants y apprennent à lire",
					Direction.RIGHT));
			grid.addDefinition(7, 0, new Definition(
					"Quasimodo l'avait dans le dos", Direction.RIGHT));

			grids.add(grid);
		}
		{
			final Grid grid = new Grid();
			grid.setLines(
					" *     *",
					"*COUCHES",
					" H     I",
					"*AVION E",
					" P     S",
					" E*JOUET",
					" A     E",
					"*URNE   ");
			grid.addDefinition(0, 1, new Definition(
					"On le porte sur la tête", Direction.DOWN));
			grid.addDefinition(0, 7, new Definition(
					"Sommeil fait l'après-midi", Direction.DOWN));
			grid.addDefinition(1, 0, new Definition(
					"Le bébé en porte en guise de culotte (Au pluriel)", Direction.RIGHT));
			grid.addDefinition(3, 0, new Definition(
					"Permet de voler au-dessus des mers par exemple", Direction.RIGHT));
			grid.addDefinition(5, 2, new Definition(
					"Amuse les enfants",
					Direction.RIGHT));
			grid.addDefinition(7, 0, new Definition(
					"Boîte dans laquelle on met le bulletin de vote", Direction.RIGHT));

			grids.add(grid);
		}

		return grids;
	}
}