package com.drem.games.ggs.game.state;

import com.drem.games.ggs.game.manager.GameStateManager;

/**
 * Created by drem on 3/23/15.
 */
public abstract class GameState {
    protected GameStateManager gameStateManager;

    protected GameState(GameStateManager gsm) {
        this.gameStateManager = gsm;
        init();
    }

    /**
     * Initializes game state when starts up
     */
    public abstract void init();

    /**
     * Delta time. How much do we want to move the game forward.
     * @param dt
     */
    public abstract void update(float dt);

    /**
     * Render this game state
     */
    public abstract void draw();

    /**
     * Do stuff with game keys
     */
    public abstract void handleInput();

    /**
     * Switch to new game state
     */
    public abstract void dispose();

}
