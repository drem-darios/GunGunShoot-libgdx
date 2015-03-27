package com.drem.games.ggs.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.drem.games.ggs.Application;
import com.drem.games.ggs.game.manager.GameStateManager;

/**
 * Created by drem on 3/23/15.
 */
public class SinglePlayerMenuState extends GameState {

    private SpriteBatch sb;
    private BitmapFont titleFont;
    private BitmapFont font;
    private final String title = "Difficulty"; // Get this from properties
    private int currentItem;
    private String[] menuItems;

    public SinglePlayerMenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        sb = new SpriteBatch();

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Hyperspace-Bold.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = 56;
        parameter.color = Color.RED;
        titleFont = gen.generateFont(parameter);

        parameter.color = Color.WHITE;
        parameter.size = 36;
        font = gen.generateFont(parameter);

        menuItems = new String[]{"1.Simple Computer", "2.Advanced Computer", "3.Super Computer", "Main Menu"};
        gen.dispose();

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        sb.setProjectionMatrix(Application.camera.combined);
        sb.begin();

        // get size of title to center
        float width = titleFont.getBounds(title).width;
        // draw title
        titleFont.draw(sb, title, (Application.WIDTH - width) / 2, 300);


        // draw menu
        for (int i = 0; i < menuItems.length; i++) {
            // get size of item to center
            width = font.getBounds(menuItems[i]).width;
            if (currentItem == i) {
                font.setColor(Color.BLUE);
            } else {
                font.setColor(Color.WHITE);
            }
            // draw item
            font.draw(sb, menuItems[i], (Application.WIDTH - width) / 2, 180 - 35 * i);
        }
        sb.end();
    }

    @Override
    public void handleInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (currentItem > 0) {
                currentItem --;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (currentItem < menuItems.length - 1) {
                currentItem ++;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            select();
        }
    }

    private void select() {
        if (currentItem == 0) {
            gameStateManager.setState(GameStateManager.SINGLE_PLAYER_MENU);
        }
        if (currentItem == 1) {
            // Advanced computer
        }
        if (currentItem == 2) {
            // Super computer
        }
        if (currentItem == 3) {
            gameStateManager.setState(GameStateManager.MAIN_MENU);
        }
    }

    @Override
    public void dispose() {

    }
}
