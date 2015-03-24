package com.drem.games.ggs.game.state;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.drem.games.ggs.game.state.manager.GameStateManager;

/**
 * Created by drem on 3/23/15.
 */
public class MainMenuState extends MenuState {

    private SpriteBatch sb;
    private BitmapFont font;
    private final String title = "Gun Gun Shoot";

    private String[] menuItems;
    public MainMenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        sb = new SpriteBatch();

        menuItems = new String[];
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw() {

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }
}
