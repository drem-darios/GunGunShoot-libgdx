package com.drem.games.ggs.player.action;

import java.util.HashMap;
import java.util.Map;

import com.drem.games.ggs.api.IPlayerAction;

import javax.swing.Action;

/**
 * @author drem
 */
public class PlayerActionFactory {
	
	private static Map<ActionName, IPlayerAction> actions; // TODO: Inject values here!!!

	static {
		actions = new HashMap<ActionName, IPlayerAction>();
		actions.put(ActionName.BLOCK, new BlockAction());
		actions.put(ActionName.SHOOT, new ShootAction());
		actions.put(ActionName.RELOAD, new ReloadAction());
	}

	public static IPlayerAction getPlayerAction(ActionName action) {
		return actions.get(action);
	}
}
