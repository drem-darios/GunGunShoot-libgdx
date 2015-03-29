package com.drem.games.ggs.player.action;

import com.badlogic.gdx.graphics.Texture;
import com.drem.games.ggs.Jukebox;
import com.drem.games.ggs.api.IPlayerAction;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.PlayerOutcome;

/**
 * @author drem
 */
public class ReloadAction implements IPlayerAction {

	private static final long serialVersionUID = -7203015274842062139L;

	@Override
	public PlayerOutcome getOutcome(Player player1, Player player2, IPlayerAction player2Action) {
		if (player2.hasWeapon() && player2Action.getActionName() == ActionName.SHOOT) {
			return PlayerOutcome.DEAD;
		}
		doAction(player1);
		
		return PlayerOutcome.OK;
	}

	@Override
	public void doAction(Player player) {
		Jukebox.playSound("reload");
		player.addBullet();
	}

	@Override
	public Texture getTexture() {
		return new Texture("textures/reload.png");
	}

	@Override
	public ActionName getActionName() {
		return ActionName.RELOAD;
	}

}
