package models;

import java.io.Serializable;

import enums.Numbers;
import models.Card;
import models.Deck;
import models.Player;
import enums.Numbers;
//use to convert the enmus into actual numbers to calculate the players and dealers hand

public class CalculateHand{

	public static int calculatePlayerHand(Player p) {
		int valueOfHand = 0;
		
		for (Card card : p.getHandOfCards()) {
			int ValueOfCard = card.getNumber().VALUE;
			
			valueOfHand += ValueOfCard;

		}
		return valueOfHand;
		
		
	}
	public static int calculatePlayerAI(Player p) {
		int valueOfHand = 0;
		
		for (Card card : p.getAIhandOfCards()) {
			int ValueOfCard = card.getNumber().VALUE;
			
			valueOfHand += ValueOfCard;

		}
		return valueOfHand;
		
		
	}
}
