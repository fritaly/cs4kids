/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids;

import javax.swing.ImageIcon;

import org.apache.commons.lang.math.RandomUtils;

public enum Word {
	LOCK("Cadenas", Images256x256.PADLOCK),
	BALLONS("Ballon", Images256x256.BALLOONS),
	BIN("Poubelle", Images256x256.BIN),
	PLANET_EARTH("Planete", Images256x256.PLANET_EARTH),
	CALENDAR("Calendrier", Images256x256.CALENDAR),
	CART("Caddy", Images256x256.CART),
	CASE("Valise", Images256x256.CASE),
	COMPASS("Boussole", Images256x256.COMPASS),
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
	
	// Seconde série de mots
	DUCK("Canard", Images256x256.DUCK),
	AIRPLANE("Avion", Images256x256.AIR_PLANE),
	HEADPHONE("Ecouteurs", Images256x256.HEAD_PHONE),
	LAPTOP("Ordinateur", Images256x256.MAC_BOOK),
	ROBOT("Robot", Images256x256.ROBOT),
	BALLOONING("Montgolfiere", Images256x256.BALLOON),
	BOX("Paquet", Images256x256.BOX),
	BULLDOZER("Bulldozer", Images256x256.BULLDOZER),
	FLAG("Drapeau", Images256x256.FLAG),
	CLOCK2("Reveil", Images256x256.CLOCK2),
	BUILDINGS("Immeubles", Images256x256.BUILDINGS),
	CRUISE_BIKE("Moto", Images256x256.CRUISE_BIKE),
	BOAT("Bateau", Images256x256.BOAT),
	LOCOMOTIVE("Locomotive", Images256x256.LOCOMOTIVE),
	GHOST("Fantome", Images256x256.GHOST),
	APPLE("Pomme", Images256x256.APPLE),
	GUITAR("Guitare", Images256x256.GUITAR),
	DRILLER("Perceuse", Images256x256.DRILLER),
	MICROSCOPE("Microscope", Images256x256.MICROSCOPE),
	MONKEY("Singe", Images256x256.MONKEY),
	MOUSE("Souris", Images256x256.MOUSE),
	PIANO("Piano", Images256x256.PIANO),
	PAGE("Feuille", Images256x256.PAGE),
	PANDA("Panda", Images256x256.PANDA),
	PRINTER("Imprimante", Images256x256.PRINTER),
	RABBIT("Lapin", Images256x256.RABBIT),
	ROLLER_SKATES("Rollers", Images256x256.ROLLER_SKATES),
	CANDLE("Chandelle", Images256x256.CANDLE),
	UMBRELLA("Parapluie", Images256x256.UMBRELLA),
	WHEEL_BARROW("Brouette", Images256x256.WHEEL_BARROW),
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