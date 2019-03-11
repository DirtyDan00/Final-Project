package models;

import java.io.Serializable;

import enums.Numbers;
import enums.Suit;

public class Card implements Serializable {
	
	private Suit suit;
	private Numbers number;
	
//creates a card with a number and suit
//getters and setters too
	//get value function that returns # on the card
	public Card(Numbers numbers, Suit suit) {
		super();
		this.number = numbers;
		this.suit = suit;
		
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Numbers getNumber() {
		return number;
	}
	public void setNumber(Numbers number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Card: ").append(suit).append(", ").append(number);
		return builder.toString();
	
	
	}

}
