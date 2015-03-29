package com.drem.games.ggs.game;

import com.drem.games.ggs.api.IPlayerAction;
import com.drem.games.ggs.player.ComputerPlayer;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.PlayerOutcome;
import com.drem.games.ggs.player.action.ActionName;
import com.drem.games.ggs.player.action.PlayerActionFactory;
import com.drem.games.ggs.weapon.WeaponFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author drem
 */
public class SinglePlayerGame extends AbstractGame {

	private Scanner inputScanner;
	public SinglePlayerGame(Player player1, ComputerPlayer player2) {
		super(player1, player2);
	}

	@Override
	public void printRules() {
		System.out.println("************************");
		System.out.println("How to play:");
		System.out.println(" 1 - Block \n 2 - Shoot \n 3 - Reload "
				+ "\n 4 - Game State \n 0 - QUIT");
		System.out.println("************************");
		System.out.println();
	}

	@Override
	public void exit() {
		inputScanner.close();
		System.out.println("Goodbye!!!!");
		System.exit(0);
	}

	@Override
	public void play() {
		inputScanner = new Scanner(System.in);
		try {
			int choice = inputScanner.nextInt();
			while (choice != 0) {
				if (choice == 4) {
					System.out.println("Player:");
					printPlayer(player1);
					System.out.println();
					System.out.println("Computer:");
					printPlayer(player2);
					System.out.println();
					choice = inputScanner.nextInt();
					continue;
				} else if (choice > 4) {
					System.out.println("Please stick to the options given.");
					printRules();
					choice = inputScanner.nextInt();
					continue;
				}
				
				IPlayerAction playerAction = PlayerActionFactory.getPlayerAction(ActionName.RELOAD);
				IPlayerAction computerAction = ((ComputerPlayer) player2).makeMove();
				
				System.out.print("Player1: ");
				// Apply the action
				PlayerOutcome playerOutcome = playerAction.getOutcome(player1, player2, computerAction);
				System.out.print("Computer: ");
				PlayerOutcome computerOutcome = computerAction.getOutcome(player2, player1, playerAction);
				
				updateGameState(playerOutcome, computerOutcome);

				choice = inputScanner.nextInt();
			}
		} catch (InputMismatchException e) {
			System.out
					.println("Numbers only please! Take a look at the rules again.");
			printRules();
			play();
		}

	}

	@Override
	protected void initGame() {
		printRules();
	}

	private void updateGameState(PlayerOutcome playerOutcome,
			PlayerOutcome computerOutcome) {
		if (playerOutcome == PlayerOutcome.DEAD && computerOutcome == PlayerOutcome.DEAD) {
			declareDraw();
		}
		if (playerOutcome == PlayerOutcome.DEAD) {
			declareWinner(player2);
		} else if (computerOutcome == PlayerOutcome.DEAD) {
			declareWinner(player1);
		}
	}

	private void declareDraw() {
		System.out.println("Violence solves nothing! Everyone dies. It's a draw.");
	}

	private void declareWinner(Player winner) {
		System.out.println(winner.getClass().getSimpleName()
				+ " has won the game!");
	}

	private void printPlayer(Player player) {
		System.out.println("%`%`%`%`%`%`%`%`%`%");
		System.out.println("Bullets: " + player.getBulletCount());
		System.out.println("Shield: " + player.getShieldStrength());
		if (player.hasWeapon()) {
			System.out.println("Gun: " + WeaponFactory.getWeapon(player.getBulletCount())
					.getClass().getSimpleName());
		}
		System.out.println("%_%_%_%_%_%_%_%_%_%");
	}

}
