package com.drem.games.ggs.player.action;

import com.badlogic.gdx.graphics.Texture;
import com.drem.games.ggs.Jukebox;
import com.drem.games.ggs.api.IPlayerAction;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.PlayerOutcome;

/**
 * @author drem
 */
public class BlockAction implements IPlayerAction {

	private static final long serialVersionUID = -7203015274842062139L;

	@Override
	public PlayerOutcome getOutcome(Player player1, Player player2,
			IPlayerAction player2Action) {
		// Check if a bullet was shot. If so, call block. If not, continue.
		if (player2.hasWeapon() && player2Action.getActionName() == ActionName.SHOOT) {
			if (player1.canBlock()) {
				doAction(player1);
				return PlayerOutcome.SHIELD_DMG;
			} else {
				doAction(player1);
				return PlayerOutcome.DEAD;
			}
		}

		doAction(null);
		return PlayerOutcome.OK;
	}

	@Override
	public void doAction(Player player) {
		if (player == null) {
			Jukebox.playSound("block");
			return;
		}

		if (player.canBlock()) {
			Jukebox.playSound("block");
			player.block();
		} else {
			Jukebox.playSound("crack");
		}
	}

	@Override
	public Texture getTexture() {
		return new Texture("textures/shield.png");
	}

	@Override
	public ActionName getActionName() {
		return ActionName.BLOCK;
	}

}
