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
					"Le bébé boit son lait grâce à lui", Direction.DOWN));
			grid.addDefinition(1, 0, new Definition(
					"Il donne des caries si on en mange trop", Direction.RIGHT));
			grid.addDefinition(4, 2, new Definition(
					"Fruit d'été orange avec des pépins à l'intérieur",
					Direction.RIGHT));
			grid.addDefinition(7, 1, new Definition(
					"Donne l'heure à ton poignet", Direction.RIGHT));

			grids.add(grid);

		}

		return grids;
	}
}