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
public class MultiPlayerMenuState extends GameState {

    private SpriteBatch sb;
    private BitmapFont titleFont;
    private BitmapFont font;
    private final String title = "Select Option"; // Get this from properties
    private int currentItem;
    private String[] menuItems;

    public MultiPlayerMenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        sb = new SpriteBatch();

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Hyperspace-Bold.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = 56;
        parameter.color = Color.GREEN;
        titleFont = gen.generateFont(parameter);

        parameter.color = Color.WHITE;
        parameter.size = 36;
        font = gen.generateFont(parameter);

        menuItems = new String[]{"Join Game", "Host Game", "Main Menu"};
        gen.dispose();

//        Jukebox.playMusic(name);
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
        titleFont.draw(sb, title, (Application.WIDTH - width )/ 2, 300);


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

        // TODO: Check for mouse input
        // TODO: Get mouse input location
        // TODO: Check if it collides with text
        // TODO: If so, set the text color
        // TODO: If action is pressed on text, go to next state

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
            // Join Game
        }
        if (currentItem == 1) {
            // Host Game
        }
        if (currentItem == 2) {
            gameStateManager.setState(GameStateManager.MAIN_MENU);
        }
    }

    @Override
    public void dispose() {

    }
}
