package com.drem.games.ggs.game.manager;

import com.drem.games.ggs.game.state.GameState;
import com.drem.games.ggs.game.state.MainMenuState;
import com.drem.games.ggs.game.state.MultiPlayerMenuState;
import com.drem.games.ggs.game.state.PlayState;
import com.drem.games.ggs.game.state.SinglePlayerMenuState;

/**
 * Created by drem on 3/23/15.
 */
public class GameStateManager {
    private GameState gameState;

    public static final int MAIN_MENU = 0;
    public static final int SINGLE_PLAYER_MENU = 1;
    public static final int MULTIPLAYER_MENU = 2;

    public GameStateManager() {
        setState(MAIN_MENU);
    }

    public void setState(int state) {
        if (gameState != null) {
            gameState.dispose();
        }

        switch (state) {
            case MAIN_MENU:
                // switch to menu state
                gameState = new MainMenuState(this);
                break;
            case SINGLE_PLAYER_MENU:
                gameState = new SinglePlayerMenuState(this);
                // switch to play state
                break;

            case MULTIPLAYER_MENU:
                gameState = new MultiPlayerMenuState(this);
                break;
            default:
                // INVALID
                break;
        }
    }

    public void update(float dt) {
        gameState.update(dt);
    }

    public void draw() {
        gameState.draw();
    }
}
