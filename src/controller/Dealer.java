package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lib.ConsoleIO;
import models.Card;
import models.Deck;
import models.Player;

public class Dealer {

	private static Player p = new Player();
	private static Deck d = new Deck();
	private static Card c = new Card(null, null);
	private static boolean doubleDown = false;
	private static boolean awakenAI = false;
	private static boolean fold = false;

	public static void run() {
		Menu();
		// use zombie dice as help for shuffling. dealing and only having a certain
		// ammout of cards

	}

	private static void Menu() {

		boolean loopMenu = true;
		do {

			System.out.println("Welcome to Jakes Blackjack");
			System.out.println("Please choose an option to start this gamblin!");
			String[] prompt = {"Create a player profile", "Load a player Profile" };

			int playerInput = ConsoleIO.promptForMenuSelection(prompt, true);

			if (playerInput == 1) {
				PromptForUserName();
				PlayGame();
			}
			if (playerInput == 2) {
				LoadPlayer();
				PlayGame();
			}
			if (playerInput == 0) {
				loopMenu = false;
				System.out.println("We don't need your service anyways!");
			}
		} while (loopMenu = true);
	}

	private static void PromptForUserName() {
		Player thisPlayer = new Player();
		String prompt = ("So what would you like your name to be?");

		String PlayerName = ConsoleIO.promptForInput(prompt, false);

		thisPlayer.setPlayerName(PlayerName);
		System.out.println("Thats a goofy name, but it works");
		System.out.println("also were giving you $1000 bucks to gamble with, go nuts.");
		p.setMoney(1000);
		p.setLosses(0);
		p.setWins(0);
		SavePlayer();

	}

	private static void PlayGame() {
		boolean continueLooping = true;
		System.out.println("Alright! Lets get this game started!");
		d.ReFillDeck();
		d.ShuffleDeck();
		p.ReciveCard(d.DrawACard());
		p.ReciveCard(d.DrawACard());
		do {
		System.out.println(p.getHandOfCards());
		System.out.println("theres ya cards, now, what would you like to do?");
		String[] options = {"Hit", "Stand", "Double Down", "Fold"};
		int choice = ConsoleIO.promptForMenuSelection(options, false);
		
		if (choice == 1) {
			System.out.println("Aight! here's ya card!");
			p.ReciveCard(d.DrawACard());
			System.out.println(p.getHandOfCards());
			if (p.getHandCount() > 5) {
				System.out.println("Woah woah, you got too many cards there bucko, you only can have 5!");
				continueLooping = true;
				
			}else {
				awakenAI = true;
				DealerAI();
			}
			
			
		}
		if (choice == 2) {
			System.out.println("aight, dont take a card then");
			continueLooping = true;
			awakenAI = true;
			DealerAI();
		}
		if (choice == 3) {
			System.out.println("Oh? You feelin lucky Champ? Well lets see");
			doubleDown = true;
			if (doubleDown == true) {
				continueLooping = false;
				awakenAI = true;
				DealerAI();
			}
		}
		if (choice == 4) {
			System.out.println("You out already? suck to be you Chump.");
			fold = true;
			WinCondition();
			
			continueLooping = false;
		}
		
		}while (continueLooping);
		

	}

	

	private static void SavePlayer() {
		if (p.getFilepath() == null) {
			String prompt = ("Where would you like to save this player to?");
			String input = ConsoleIO.promptForInput(prompt, false);
			p.setFilepath(input);

		}
		try {
			FileOutputStream fileOut = new FileOutputStream(p.getFilepath());
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(p);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in " + p.getFilepath());
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	private static void LoadPlayer() {
		String prompt = ("Where would you like to load the player from?");
		String input = ConsoleIO.promptForInput(prompt, false);
		try {
			FileInputStream fileIn = new FileInputStream(input);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			p = (Player) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException i) {
			System.out.println("no players found");
			i.printStackTrace();
			return;

		}

	}

	private static void DealerAI() {
		//have dealer shoot for 19 on average
		if (awakenAI = true && p.getAIhandOfCards() == null) {
			p.AiReciveCard(d.DrawACard());
			p.AiReciveCard(d.DrawACard());
			
		}
		if (p.getAIHandCount() <= 2) {
			p.AiReciveCard(d.DrawACard());
		}
		
	}

	private static void WinCondition() {
		String WinMessage;
		//the different ways you can win at blackjack
		if (fold = true) {
			System.out.println("You gave up. You lose..");
		}
		
		switch () {
		case 1: WinMessage = "You got An Ace and A face card! BLAKJACK! you win!";
			break;
		case 2: WinMessage = "You got a sum of 21! BlackJack! you win!";
			break;
		case 3: WinMessage = "Your Sum of cards was greater than the dealers! you win!";
			break;
		case 4: WinMessage = "Your Sum of cards was less than the dealers! you lose..";
			break;
		case 5: WinMessage = "You went past 21! what the heck, you had one job. you lose..";
		
		}
	}

	private static void CalculateWinnings() {
		//add a win to their profile and use the double down boolean to check for x2 the winnings 
		if () {
			p.setWins(+1);
			p.setMoney(+1000);
			SavePlayer();
			ResetGame();
		}
		if (doubleDown = true) {
			p.setWins(+1);
			p.setMoney(+2000);
			SavePlayer();
			ResetGame();
		}
	}

	private static void CalculateLosses() {
		//add a loss to their profile and use the double down boolean to check for x2 the losses
		if () {
			p.setLosses(-1);
			p.setMoney(-1000);
		}
		if (doubleDown = true) {
			p.setLosses(+1);
			p.setMoney(-2000);
		}
	}

	private static void ResetGame() {
		p.clearHand();
		d.ReFillDeck();
		Menu();
		
	}
}
