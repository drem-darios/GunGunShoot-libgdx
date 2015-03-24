package com.drem.games.ggs.game.state.manager;

import com.drem.games.ggs.game.state.GameState;
import com.drem.games.ggs.game.state.MainMenuState;
import com.drem.games.ggs.game.state.PlayState;

/**
 * Created by drem on 3/23/15.
 */
public class GameStateManager {
    private GameState gameState;

    public static final int MAIN_MENU = 0;
    public static final int SINGLE_PLAYER_GAME = 1;
    public static final int MULTIPLAYER_PLAYER_GAME = 2;

    public GameStateManager() {
        setState(MAIN_MENU);
    }

    public void setState(int state) {
        if (gameState != null) {
            gameState.dispose();
        }

        switch(state) {
            case MAIN_MENU:
                // switch to menu state
                gameState = new MainMenuState(this);
                break;
            case SINGLE_PLAYER_GAME:
                gameState = new PlayState(this);
                // switch to play state
                break;

            case MULTIPLAYER_PLAYER_GAME:
                gameState = new PlayState(this);
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
