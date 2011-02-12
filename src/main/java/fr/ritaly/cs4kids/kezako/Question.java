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

public enum Question {
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