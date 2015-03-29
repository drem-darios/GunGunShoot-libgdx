package com.drem.games.ggs.player.action;

import com.badlogic.gdx.graphics.Texture;
import com.drem.games.ggs.Jukebox;
import com.drem.games.ggs.api.IPlayerAction;
import com.drem.games.ggs.api.IWeapon;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.PlayerOutcome;
import com.drem.games.ggs.weapon.WeaponFactory;

/**
 * @author drem
 */
public class ShootAction implements IPlayerAction {

	private static final long serialVersionUID = -7203015274842062139L;
	
	@Override
	public PlayerOutcome getOutcome(Player player1, Player player2, IPlayerAction player2Action) {

		if (player1.hasWeapon()) {
			if (player2.hasWeapon() && player2Action.getActionName() == ActionName.SHOOT) {
				IWeapon pWeapon = WeaponFactory.getWeapon(player1
						.getBulletCount());
				IWeapon cWeapon = WeaponFactory.getWeapon(player2
						.getBulletCount());
				int result = pWeapon.compareTo(cWeapon);
				if (result == 0) {
					return PlayerOutcome.DEAD;
				} else if (result == 1) {
					doAction(player1);
					return PlayerOutcome.OK;
				} else {
					return PlayerOutcome.DEAD;
				}
			} else {
				doAction(player1);
				return PlayerOutcome.OK;
			}
		} else {
			if (player2.hasWeapon() && player2Action.getActionName() == ActionName.SHOOT) {
				return PlayerOutcome.DEAD;
			}

			doAction(player1);
			return PlayerOutcome.OK;
		}
	}

	@Override
	public void doAction(Player player) {
		if (player.hasWeapon()) {
			Jukebox.playSound("pew_pew");
			player.useBullet();
		} else {
			Jukebox.playSound("spit");
		}
	}

	@Override
	public Texture getTexture() {
		return new Texture("textures/shoot.png");
	}

	@Override
	public ActionName getActionName() {
		return ActionName.SHOOT;
	}

}
