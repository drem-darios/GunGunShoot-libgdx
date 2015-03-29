package com.drem.games.ggs.api;

import java.io.Serializable;

import com.badlogic.gdx.graphics.Texture;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.PlayerOutcome;
import com.drem.games.ggs.player.action.ActionName;

/**
 * @author drem
 */
public interface IPlayerAction extends Serializable {

	public PlayerOutcome getOutcome(Player player1, Player player2, IPlayerAction otherPlayerAction);
	public void doAction(Player player);
	public ActionName getActionName();
	public Texture getTexture();
}
