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
			String[] prompt = { "Start the Game", "Create a player profile", "Load a player Profile" };

			int playerInput = ConsoleIO.promptForMenuSelection(prompt, true);

			if (playerInput == 1) {
				PlayGame();
			}
			if (playerInput == 2) {
				PromptForUserName();

			}
			if (playerInput == 3) {
				LoadPlayer();
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
		System.out.println("Alright! Lets get this game on the move!");
		d.ReFillDeck();
		d.ShuffleDeck();
		p.ReciveCard(d.DrawACard());
		p.ReciveCard(d.DrawACard());
		
		System.out.println(p.getHandOfCards());

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
	}

	private static void WinCondition() {

	}

	private static void CalculateWinnings() {

	}

	private static void CalculateLosses() {

	}

	private static void ResetGame() {

	}
}
