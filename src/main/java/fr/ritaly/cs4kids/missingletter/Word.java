/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids.missingletter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

import org.apache.commons.lang.math.RandomUtils;

import fr.ritaly.cs4kids.Images256x256;

enum Word {
	LOCK("Cadenas", Images256x256.PADLOCK),
	BALLONS("Ballon", Images256x256.BALLOONS),
	BIN("Poubelle", Images256x256.BIN),
	PLANET_EARTH("Planete", Images256x256.PLANET_EARTH),
	CALENDAR("Calendrier", Images256x256.CALENDAR),
	CART("Caddy", Images256x256.CART),
	CASE("Valise", Images256x256.CASE),
	COMPASS("Compas", Images256x256.COMPASS),
	MAIL("Enveloppe", Images256x256.EMAIL2),
	CHRISMAS_TREE("Sapin", Images256x256.FORREST),
	GIFT("Cadeau", Images256x256.GIFT),
	HAT("Chapeau", Images256x256.HAT),
	HELP("Livre", Images256x256.HELP),
	ICE_SKATE("Patins", Images256x256.ICE_SKATE),
	HOUSE("Maison", Images256x256.HOUSE),
	PUMPKIN("Citrouille", Images256x256.PUMPKIN),
	CAR("Voiture", Images256x256.CAR),
	MAGIC_RABBIT("Lapin", Images256x256.MAGIC_RABBIT),
	HELMET("Casque", Images256x256.HELMET),
	PENCIL_CAN("Crayon", Images256x256.PENCIL_CAN),
	PHONE("Telephone", Images256x256.PHONE),
	DEER("Renne", Images256x256.DEER),
	RING("Bague", Images256x256.RINGS),
	CLOCK("Horloge", Images256x256.CLOCK),
	STAR("Etoile", Images256x256.STAR),
	STRAWBERRY("Fraise", Images256x256.STRAWBERRY),
	TEDDY_BEAR("Ourson", Images256x256.TEDDY_BEAR),
	TREASURE_CHEST("Coffre", Images256x256.TREASURE_CHEST),
	TROPHY("Coupe", Images256x256.TROPHY),
	WOMAN_SHOES("Chaussures", Images256x256.WOMAN_SHOES),
	;
	
	private final ImageIcon image;
	
	private final String name;
	
	private Word(String name, ImageIcon image) {
		this.name = name;
		this.image = image;
	}

	public ImageIcon getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public static Word random() {
		final Word[] values = values();
		
		return values[RandomUtils.nextInt(values.length)];
	}
}