package blackjack.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import blackjack.enums.Numbers;
import blackjack.enums.Suit;

public class Deck implements Serializable {
	Random rand = new Random();
	private ArrayList<Card> CurrentDeck = new ArrayList<Card>();
	//collection of the 52 cards in a deck

	public Card DrawACard() {
		//draws a card
		if (CurrentDeck.size() == 0) {
			ReFillDeck();
		}
		Card DeckAfterDrawn = CurrentDeck.get(CurrentDeck.size() - 1);
		CurrentDeck.remove(DeckAfterDrawn);
		return DeckAfterDrawn;

	}

	public void ReFillDeck() {
//refills the deck
		if (CurrentDeck.size() == 0 || CurrentDeck == null) {

			for (Suit suit : Suit.values()) {
				for (Numbers number : Numbers.values()) {
					CurrentDeck.add(new Card(number, suit));
				}
			}
		}

	}

	public void ShuffleDeck() {
		Collections.shuffle(CurrentDeck);
		for (Card card : CurrentDeck) {
			
		}
		
	}
}
