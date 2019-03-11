package blackjack.models;

import java.io.Serializable;
import java.util.ArrayList;

import blackjack.models.Card;

public class Player implements Serializable {

	private Deck d = new Deck();
	private int turn = 0;
	private ArrayList<Card> handOfCards = new ArrayList<Card>();
	private ArrayList<Card> AIhandOfCards = new ArrayList<Card>();
	// players hand of cards can only have 5 at a time
	private int money;
	private String playerName = "default";
	private int wins;
	private int losses;
	private String filepath;
	public int HandCount = 0;
	private int AIHandCount = 0;

	public void AiReciveCard(Card card) {

		AIhandOfCards.add(card);
	}

	public ArrayList<Card> getAIhandOfCards() {
		return AIhandOfCards;
	}

	public void setAIhandOfCards(ArrayList<Card> aIhandOfCards) {
		AIhandOfCards = aIhandOfCards;
	}

	public int getHandCount() {
		return handOfCards.size();
	}
	public int getAIHandCount() {
		return AIhandOfCards.size();
	}

	public void ReciveCard(Card card) {

		handOfCards.add(card);
	}

	public ArrayList<Card> getHandOfCards() {
		return handOfCards;
	}

	public void setHandOfCards(ArrayList<Card> handOfCards) {
		this.handOfCards = handOfCards;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Player clearHand() {
		handOfCards.clear();
		AIhandOfCards.clear();
		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player ").append(playerName).append(", Money: ").append(money).append(", wins: ")
				.append(wins).append(", losses: ").append(losses).append("]");
		return builder.toString();
	}

}
