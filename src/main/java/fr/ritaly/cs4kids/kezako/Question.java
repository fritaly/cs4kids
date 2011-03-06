/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids.kezako;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

import org.apache.commons.lang.math.RandomUtils;

import fr.ritaly.cs4kids.Images256x256;

enum Question {
	LOCK("Qu'est-ce-que c'est ?", Images256x256.PADLOCK, "Un cadenas", "Un cheval", "Un cerf-volant"),
	BALLONS("Qu'est-ce-que c'est ?", Images256x256.BALLOONS, "Des ballons", "Des bateaux", "Une voiture"),
	BASEBALL_BALL("Qu'est-ce-que c'est ?", Images256x256.BASEBALL_BALL, "Une balle de base-ball", "Un ballon de football", "Une balle de tennis"),
	BASKETBALL_BALL("Qu'est-ce-que c'est ?", Images256x256.BASKETBALL_BALL, "Un ballon de basket", "Un ballon de football", "Une balle de base-ball"),
	BIN("Qu'est-ce-que c'est ?", Images256x256.BIN, "Une poubelle", "Un verre", "Une assiette"),
	PLANET_EARTH("Qu'est-ce-que c'est ?", Images256x256.PLANET_EARTH, "La planète Terre", "Un ballon", "Une bulle de savon"),
	CALENDAR("Qu'est-ce-que c'est ?", Images256x256.CALENDAR, "Un calendrier", "Un tableau", "Une poupée"),
	CART("Qu'est-ce-que c'est ?", Images256x256.CART, "Un panier", "Un camion", "Une pomme"),
	CASE("Qu'est-ce-que c'est ?", Images256x256.CASE, "Une valise", "Du chocolat", "Une étoile"),
	CHRISTMAS_TREE_BALL("Qu'est-ce-que c'est ?", Images256x256.CHRISTMAS_TREE_BALL, "Une boule de Noël", "Un ballon", "Un flocon de neige"),
	COFFEE_CUP("Qu'est-ce-que c'est ?", Images256x256.COFFEE, "Une boule de Noël", "Un ballon", "Un flocon de neige"),
	COMPASS("Qu'est-ce-que c'est ?", Images256x256.COMPASS, "Une boussole", "Une bouteille", "Un ballon"),
	CREDIT_CARD("Qu'est-ce-que c'est ?", Images256x256.CREDIT_CARD, "Une carte de crédit", "Un tableau vert", "Une table"),
	MAIL("Qu'est-ce-que c'est ?", Images256x256.EMAIL2, "Une enveloppe", "Un pinceau", "Un livre"),
	CHRISMAS_TREE("Qu'est-ce-que c'est ?", Images256x256.FORREST, "Un sapin", "Une plante", "Un sac"),
	GIFT("Qu'est-ce-que c'est ?", Images256x256.GIFT, "Un paquet cadeau", "Une boîte", "Une chaise"),
	HAT("Qu'est-ce-que c'est ?", Images256x256.HAT, "Un chapeau de sorcière", "Une grenouille", "Une cape"),
	HELP("Qu'est-ce-que c'est ?", Images256x256.HELP, "Un livre", "Une citrouille", "Un lit"),
	ICE_SKATE("Qu'est-ce-que c'est ?", Images256x256.ICE_SKATE, "Des patins à glace", "Des chaussures", "Des chaussons"),
	HOUSE("Qu'est-ce-que c'est ?", Images256x256.HOUSE, "Une maison", "Une maîtresse", "Une tente"),
	PUMPKIN("Qu'est-ce-que c'est ?", Images256x256.PUMPKIN, "Une citrouille", "Une sorcière", "Une tomate"),
	CAR("Qu'est-ce-que c'est ?", Images256x256.CAR, "Une voiture rouge", "Une voiture jaune", "Un camion bleu"),
	MAGIC_RABBIT("Qu'est-ce-que c'est ?", Images256x256.MAGIC_RABBIT, "Un chapeau de magicien", "Une casquette", "Une baguette de pain"),
	HELMET("Qu'est-ce-que c'est ?", Images256x256.HELMET, "Un casque", "Un astronaute", "Un chevalier"),
	PENCIL_CAN("Qu'est-ce-que c'est ?", Images256x256.PENCIL_CAN, "Un pot de crayons", "Un dessert", "Un verre de lait"),
	PHONE("Qu'est-ce-que c'est ?", Images256x256.PHONE, "Un téléphone", "Une banane verte", "Un ordinateur"),
	DEER("Qu'est-ce-que c'est ?", Images256x256.DEER, "Un renne (du père Noël)", "Un cheval", "Un chien"),
	RING("Qu'est-ce-que c'est ?", Images256x256.RINGS, "Deux bagues", "Deux pièces de monnaie", "Deux cercles"),
	CLOCK("Qu'est-ce-que c'est ?", Images256x256.CLOCK, "Une horloge", "Un cerf-volant", "Un carré"),
	SKATE_BOARD("Qu'est-ce-que c'est ?", Images256x256.SKATE_BOARD, "Une planche à roulettes", "Un surf", "Un vaisseau spatial"),
	SNOWMAN("Qu'est-ce-que c'est ?", Images256x256.SNOWMAN, "Un bonhomme de neige", "Une glace", "Un monstre blanc"),
	SOCCER_BALL("Qu'est-ce-que c'est ?", Images256x256.SOCCER_BALL, "Un ballon de foot", "Un ballon de basket", "Une bouée"),
	STAR("Qu'est-ce-que c'est ?", Images256x256.STAR, "Une étoile à 5 branches", "Une étoile rouge", "Une étoile à 4 branches"),
	STRAWBERRY("Qu'est-ce-que c'est ?", Images256x256.STRAWBERRY, "Une fraise avec du chocolat", "Du chocolat tout seul", "Une fraise toute seule"),
	TEDDY_BEAR("Qu'est-ce-que c'est ?", Images256x256.TEDDY_BEAR, "Un ours en peluche", "Un monstre gris", "Un méchant ours"),
	TREASURE_CHEST("Qu'est-ce-que c'est ?", Images256x256.TREASURE_CHEST, "Un coffre à trésor", "Un lingot d'or", "Une caisse de jouets"),
	TROPHY("Qu'est-ce-que c'est ?", Images256x256.TROPHY, "Un trophée", "Un verre", "Un vase à fleurs"),
	WOMAN_SHOES("Qu'est-ce-que c'est ?", Images256x256.WOMAN_SHOES, "Des chaussures", "Des chaussons", "Des tongs"),
	
	// Seconde série de questions
	DUCK("Qu'est-ce-que c'est ?", Images256x256.DUCK, "Un canard", "Un coussin", "Un lion"),
	AIR_PLANE("Qu'est-ce-que c'est ?", Images256x256.AIR_PLANE, "Un avion", "Un aviron", "Une voiture"),
	HEADPHONES("Qu'est-ce-que c'est ?", Images256x256.HEAD_PHONE, "Des écouteurs", "Un casque de moto", "Une poire"),
	LAPTOP("Qu'est-ce-que c'est ?", Images256x256.MAC_BOOK, "Un ordinateur portable", "Un piano", "Une imprimante"),
	ATV("Qu'est-ce-que c'est ?", Images256x256.ATV, "Un quad", "Une voiture", "Une lampe"),
	ROBOT("Qu'est-ce-que c'est ?", Images256x256.ROBOT, "Un robot", "Une personne", "Un bureau"),
	BALLOONING("Qu'est-ce-que c'est ?", Images256x256.BALLOON, "Une montgolfière", "Une balle", "Une ampoule"),
	MOBILE("Qu'est-ce-que c'est ?", Images256x256.BLACK_BERRY, "Un téléphone portable", "Un téléphone", "Un clavier d'ordinateur"),
	BOX("Qu'est-ce-que c'est ?", Images256x256.BOX, "Un colis", "Un paquet cadeau", "Une brique"),
	BULLDOZER("Qu'est-ce-que c'est ?", Images256x256.BULLDOZER, "Un bulldozer", "Une voiture jaune", "Une grue"),
	CAMERA("Qu'est-ce-que c'est ?", Images256x256.CAMERA, "Un appareil photographique", "Une caméra", "Une loupe"),
	FLAG("Qu'est-ce-que c'est ?", Images256x256.FLAG, "Un drapeau", "Un échiquier", "Une nappe"),
	CLOCK2("Qu'est-ce-que c'est ?", Images256x256.CLOCK, "Un réveil", "Une montre", "Une boussole"),
	BUILDINGS("Qu'est-ce-que c'est ?", Images256x256.BUILDINGS, "Des immeubles", "Des cubes", "Des allumettes"),
	COTTAGE("Qu'est-ce-que c'est ?", Images256x256.COTTAGE, "Une maison de campagne", "Un appartement", "Une cabane de jardin"),
	CRUISE_BIKE("Qu'est-ce-que c'est ?", Images256x256.CRUISE_BIKE, "Une moto", "Une auto", "Un chapeau"),
	DESKTOP("Qu'est-ce-que c'est ?", Images256x256.DESKTOP, "Un ordinateur de bureau", "Une télévision", "Un poster"),
	BOAT("Qu'est-ce-que c'est ?", Images256x256.BOAT, "Un bateau", "Une cabane", "Un canot"),
	LOCOMOTIVE("Qu'est-ce-que c'est ?", Images256x256.LOCOMOTIVE, "Une locomotive", "Une automobile", "Un wagon"),
	FLIPS_FLOPS("Qu'est-ce-que c'est ?", Images256x256.FLIP_FLOPS, "Des tongs", "Des chaussons", "Des bottes"),
	FLIPPERS("Qu'est-ce-que c'est ?", Images256x256.FLIPPERS, "Des palmes pour nager", "Deux pelles", "Des gants"),
	GHOST("Qu'est-ce-que c'est ?", Images256x256.GHOST, "Un fantôme", "Un drap", "Une nappe tâchée"),
	APPLE("Qu'est-ce-que c'est ?", Images256x256.APPLE, "Une pomme", "Un kiwi", "Une banane"),
	GUITAR("Qu'est-ce-que c'est ?", Images256x256.GUITAR, "Une guitare", "Un piano", "Une raquette de tennis"),
	DRILLER("Qu'est-ce-que c'est ?", Images256x256.DRILLER, "Une perceuse", "Un pistolet", "Une flûte"),
	MONKEY("Qu'est-ce-que c'est ?", Images256x256.MONKEY, "Un singe", "Un ours", "Un chat"),
	MOUSE("Qu'est-ce-que c'est ?", Images256x256.MOUSE, "Une souris", "Un lapin", "Un chien"),
	PIANO("Qu'est-ce-que c'est ?", Images256x256.PIANO, "Un piano", "Une machine à écrire", "Un clavier d'ordinateur"),
	PAGE("Qu'est-ce-que c'est ?", Images256x256.PAGE, "Une feuille", "Un cahier", "Un poster"),
	PANDA("Qu'est-ce-que c'est ?", Images256x256.PANDA, "Un panda", "Un cheval", "Un chat"),
	PICKUP("Qu'est-ce-que c'est ?", Images256x256.PICKUP, "Une camionnette", "Un train", "Un avion"),
	PRINTER("Qu'est-ce-que c'est ?", Images256x256.PRINTER, "Une imprimante", "Un grille-pain", "Un machine à laver"),
	BUOY("Qu'est-ce-que c'est ?", Images256x256.BUOY, "Une bouée de sauvetage", "Un cerceau", "Une roue"),
	ROLLER_SKATES("Qu'est-ce-que c'est ?", Images256x256.ROLLER_SKATES, "Des patins à roulettes", "Des chaussures", "Des bonbons"),
	RUBY("Qu'est-ce-que c'est ?", Images256x256.RUBY, "Une pierre précieuse", "Un diamant (blanc)", "Une émeraude (verte)"),
	CANDLE("Qu'est-ce-que c'est ?", Images256x256.CANDLE, "Une bougie", "Une lampe", "Un cube"),
	TOOLKIT("Qu'est-ce-que c'est ?", Images256x256.TOOLKIT, "Une boîte à outils", "Un escalier", "Une trousse de stylos"),
	UMBRELLA("Qu'est-ce-que c'est ?", Images256x256.UMBRELLA, "Une parapluie", "Une tente", "Un parachute"),
	WHEEL_BARROW("Qu'est-ce-que c'est ?", Images256x256.WHEEL_BARROW, "Une brouette", "Une luge", "Un scooter")
	;
	
	private final ImageIcon image;
	
	private final String question;
	
	private final List<String> proposals;
	
	private Question(String question, ImageIcon image, String... proposals) {
		this.question = question;
		this.image = image;
		this.proposals = Arrays.asList(proposals);
	}

	public ImageIcon getImage() {
		return image;
	}

	public String getQuestion() {
		return question;
	}

	public List<String> getProposals() {
		return Collections.unmodifiableList(proposals);
	}
	
	public static Question random() {
		final Question[] values = values();
		
		return values[RandomUtils.nextInt(values.length)];
	}
}