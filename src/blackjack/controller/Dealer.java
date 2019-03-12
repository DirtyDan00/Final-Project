package blackjack.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import blackjack.enums.Numbers;
import blackjack.enums.WinCondition;
import blackjack.models.CalculateHand;
import blackjack.models.Card;
import blackjack.models.Deck;
import blackjack.models.Player;
import lib.ConsoleIO;

public class Dealer {

	private static Player p = new Player();
	private static Deck d = new Deck();
	private static Card c = new Card(null, null);
	private static boolean doubleDown = false;
	private static boolean fold = false;
	private static boolean stand = false;
	private static boolean wonGame = false;
	private static boolean EndGame = false;
	private static boolean loopMenu = true;

	public static void run() {
		Menu();
		// use zombie dice as help for shuffling. dealing and only having a certain
		// ammout of cards

	}

	private static void Menu() {

		loopMenu = true;
		do {
			//have another option that keeps playing the game with the same player?
			
			System.out.println("Welcome to Jakes Blackjack");
			System.out.println("Please choose an option to start this gamblin!");
			String[] prompt = { "Create a player profile", "Load a player Profile" };

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
		} while (loopMenu);
	}

	private static void PromptForUserName() {
		String prompt = ("So what would you like your name to be?");

		String playerName = ConsoleIO.promptForInput(prompt, false);

		p.setPlayerName(playerName);
		System.out.println("Thats a goofy name, but it works");
		System.out.println("also were giving you $1000 bucks to gamble with, go nuts.");
		p.setMoney(1000);
		p.setLosses(0);
		p.setWins(0);
		SavePlayer();

	}

	private static void PlayGame() {
		System.out.println(p.toString());
		System.out.println("Alright! Lets get this game started!");
		d.ReFillDeck();
		d.ShuffleDeck();
		p.ReciveCard(d.DrawACard());
		p.ReciveCard(d.DrawACard());
		System.out.println("Aight there's ya cards");
		System.out.println(p.getHandOfCards());
		switch (WinCondition()) {
		case PLAYERBLACKJACK:
			wonGame = true;
			EndGame = true;
			break;
		case AIBLACKJACK:
			wonGame = false;
			EndGame = true;
			
			break;
		}
		while (!EndGame) {
			
			if (stand == true) {
				EndGame = true;
			}
			System.out.println("now, what would you like to do?");
			String[] options = { "Hit", "Stand", "Double Down", "Fold" };
			int choice = ConsoleIO.promptForMenuSelection(options, false);

			if (choice == 1) {
				System.out.println("Aight! here's ya card!");
				p.ReciveCard(d.DrawACard());
				System.out.println(p.getHandOfCards());
				if (p.getHandCount() == 5) {
					System.out.println("Woah woah, you got too many cards there bucko, you only can have 5!");
					continue;
					

				}
				if (WinCondition() == WinCondition.PLAYERBUST ) {
					EndGame = true;
					break;
				}

			}
			if (choice == 2) {
				System.out.println("aight, dont take a card then");
				DealerAI();
				WinCondition();
				if (WinCondition() == WinCondition.AIBUST ) {
					EndGame = true;
					break;
				}
			}
			if (choice == 3) {
				System.out.println("Oh? You feelin lucky Champ? Well lets see");
				doubleDown = true;
				if (doubleDown == true) {
					EndGame = true;
					DealerAI();
					if (WinCondition() == WinCondition.AIBUST ) {
						break;
					}
				}
			}
			if (choice == 4) {
				fold = true;
				WinCondition();
				EndGame = true;
				

			}

		}
		switch (WinCondition()) {
		case AIBLACKJACK: System.out.println("The Dealer got 21, you lose");
		getdealhand();
			wonGame = false;
			CalculateWinnings();
			break;
		case AIBUST: System.out.println("The Dealer Busted, you won!");
		getdealhand();
		wonGame = true;
		CalculateWinnings();
			break;
		case AIGREATER: System.out.println("The Dealers hand was greater than yours, you lose");
		getdealhand();
		wonGame = false;
		CalculateWinnings();
			break;
		case PLAYERBLACKJACK: System.out.println("You got 21! you win, nice job");
		getdealhand();
		wonGame = true;
		CalculateWinnings();
			break;
		case PLAYERBUST: System.out.println("You busted, better luck next time.");
		getdealhand();
		
		wonGame = false;
		CalculateWinnings();
			break;
		case PLAYERFOLD: System.out.println("You out already? suck to be you Chump.");
		getdealhand();
		wonGame = false;
		CalculateWinnings();
			break;
		case PLAYERGREATER: System.out.println("Your hand was greater than the dealers, you won!");
		getdealhand();
		wonGame = true;
		CalculateWinnings();
			break;
		case TIE: System.out.println("You two tied, dealer wins by default");
		getdealhand();
			wonGame = false;
			CalculateWinnings();
			break;
			
		}
		//CalculateWinnings();
		
	}

	private static int checkTheAce() {
		int sum;
		sum = CalculateHand.calculatePlayerHand(p);
		if (sum > 21) {
			ArrayList<Card> Hand = p.getHandOfCards();
			for (Card card : Hand) {
				if (card.getNumber() == Numbers.ACE) {
					sum -= 10;
					if (sum <= 21) {
						break;
					}
				}
			}
		}
		return sum;
	}

	private static int checkTheAceAIEdition() {
		int sum;
		sum = CalculateHand.calculatePlayerAI(p);
		if (sum > 21) {
			ArrayList<Card> Hand = p.getAIhandOfCards();
			for (Card card : Hand) {
				if (card.getNumber() == Numbers.ACE) {
					sum -= 10;
					if (sum <= 21) {
						break;
					}
				}
			}
		}
		return sum;
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
			//System.out.println("Serialized data is saved in " + p.getFilepath());
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
		ResetGame();

	}

	private static void DealerAI() {
		// presume AI is done
		System.out.println("Dealers turn, you dont get to see the dealers hand because THATS CHEATING, no good cheater.");
		if (p.getAIhandOfCards().size() == 0) {
			p.AiReciveCard(d.DrawACard());
			p.AiReciveCard(d.DrawACard());

		}
		if (p.getAIhandOfCards().size() < 5) {
			int sum = checkTheAceAIEdition();
			int playerSum = checkTheAce();
			if (sum <= 17) {
				p.AiReciveCard(d.DrawACard());
			} else if (sum <= playerSum) {
				p.AiReciveCard(d.DrawACard());
			} else {
				stand = true;
				EndGame = true;
			}

		}

	}

	private static WinCondition WinCondition() {
		
		int sumPlayer = checkTheAce();
		int sumAi = checkTheAceAIEdition();
		if (sumAi == 21) {
			return WinCondition.AIBLACKJACK;
		} else if (sumPlayer == 21) {
			return WinCondition.PLAYERBLACKJACK;
		} else if (sumPlayer > 21) {
			return WinCondition.PLAYERBUST;
		} else if (sumAi > 21) {
			return WinCondition.AIGREATER;
		} else if (fold == true) {
			return WinCondition.PLAYERFOLD;
		} else if (sumAi < sumPlayer) {
			return WinCondition.PLAYERGREATER;
		} else {
			return WinCondition.TIE;
		}

		// the different ways you can win at blackjack
		
			
		
	}

	private static void CalculateWinnings() {
		// add a win to their profile and use the double down boolean to check for x2
		// the winnings
			
			 if (wonGame == true) {

				if (doubleDown == true) {
					
					p.clearHand();
					
					p.setWins(p.getWins() + 1);
					p.setMoney(p.getMoney() * 4);
				}
				p.setWins(+1);
				p.setMoney(p.getMoney() * 2);
			}
			if (wonGame == false){
				if (doubleDown == true) {
					p.clearHand();
					
					p.setLosses(p.getLosses() + 1);
					p.setMoney(p.getMoney() * -1);
					
					
				}
				p.setLosses(p.getLosses() + 1);
				p.setMoney(0);
			}

		
		SavePlayer();
		PlayAgain();
	}

	private static void ResetGame() {
		p.clearHand();
		d.ReFillDeck();
		EndGame = false;

	}
	private static void PlayAgain() {
		
		String prompt = ("Would You like to play again? yes/no");
		boolean play = ConsoleIO.promptForBool(prompt, "yes", "no");
		if (play == true) {
			ResetGame();
			playAsTheSamePlayer();

		} else {
			
			loopMenu = false;
			
			
		}
	}
	private static void playAsTheSamePlayer() {
		String prompt = ("Would you like to keep playing as the same player? yes/no");
		boolean samePlayer = ConsoleIO.promptForBool(prompt, "yes", "no");
		if (samePlayer == true) {
			PlayGame();
		} else {
			Menu();
		}
	}
	private static void getdealhand() {
		System.out.println(p.getAIhandOfCards());
	}
}
