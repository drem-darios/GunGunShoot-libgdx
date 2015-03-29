package com.drem.games.ggs.player;

import java.util.Random;

import com.drem.games.ggs.api.IPlayerAction;
import com.drem.games.ggs.player.action.ActionName;
import com.drem.games.ggs.player.action.PlayerActionFactory;

/**
 * @author drem
 */
public class ComputerPlayer extends Player {

	private boolean firstMove = true;
	
	public IPlayerAction makeMove() {
		if (firstMove) {
			firstMove = false;
			return PlayerActionFactory.getPlayerAction(ActionName.RELOAD);
		}
		
		ActionName choice = getRandomChoice();
		if (getBulletCount() == 0 && choice == ActionName.SHOOT) {
			return makeMove(); // make move again bc you can't shoot.
		}
		return PlayerActionFactory.getPlayerAction(choice);

	}
	
	private ActionName getRandomChoice() {
		Random r = new Random();
		ActionName action;
		int random = r.nextInt(3);
		if (random == 0) {
			action = ActionName.BLOCK;
		} else if (random == 1) {
			action = ActionName.SHOOT;
		} else {
			action = ActionName.RELOAD;
		}

		return action;
	}
}
