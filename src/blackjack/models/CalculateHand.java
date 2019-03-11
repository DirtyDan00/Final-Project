package blackjack.models;

import java.io.Serializable;

import blackjack.enums.Numbers;
import blackjack.models.Card;
import blackjack.models.Deck;
import blackjack.models.Player;

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
