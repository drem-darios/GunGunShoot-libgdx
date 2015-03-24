package com.drem.games.ggs.game.state;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.drem.games.ggs.game.entities.PlayerEntity;
import com.drem.games.ggs.game.state.manager.GameStateManager;
import com.drem.games.ggs.player.Player;

/**
 * Created by drem on 3/23/15.
 */
public class PlayState extends GameState {

    private ShapeRenderer shapeRenderer;
    private PlayerEntity player;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void init() {
        shapeRenderer = new ShapeRenderer();
        player = new PlayerEntity();
    }

    @Override
    public void update(float dt) {
        player.update(dt);
    }

    @Override
    public void draw() {
        player.draw(shapeRenderer);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }


}
