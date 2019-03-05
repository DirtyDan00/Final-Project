package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import enums.Numbers;
import enums.Suit;

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
		System.out.println(CurrentDeck);
		if (CurrentDeck.size() == 0 || CurrentDeck == null) {

			for (Suit suit : Suit.values()) {
				for (Numbers number : Numbers.values()) {
					CurrentDeck.add(new Card(number, suit));
				}
			}
		}

	}

	public void ShuffleDeck() {
		//refills the deck, then shuffles
		//create a shuffle method like in zombie dice
		//ReFillDeck();
		Collections.shuffle(CurrentDeck);
		for (Card card : CurrentDeck) {
			System.out.println(card);
		}
		
	}
}
