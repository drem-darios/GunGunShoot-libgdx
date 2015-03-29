package com.drem.games.ggs.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.drem.games.ggs.Application;
import com.drem.games.ggs.Jukebox;
import com.drem.games.ggs.game.manager.GameStateManager;

/**
 * Created by drem on 3/28/15.
 */
public class GameEndState extends GameState {

    private SpriteBatch sb;
    private BitmapFont titleFont;
    private BitmapFont font;
    private int currentItem;
    private String[] menuItems;
    public static enum GameOutcome {
        WIN, LOSE, DRAW
    }

    private GameOutcome outcome;
    protected GameEndState(GameStateManager gsm, GameOutcome outcome) {
        super(gsm);
        this.outcome = outcome;
    }

    @Override
    public void init() {
        sb = new SpriteBatch();

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Hyperspace-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 56;
        parameter.color = Color.WHITE;
        titleFont = gen.generateFont(parameter);


        parameter.size = 26;
        parameter.color = Color.WHITE;
        font = gen.generateFont(parameter);

        menuItems = new String[] {"Play Again", "Main Menu", "Exit"};

        gen.dispose();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        sb.begin();
        renderOutcome();
        renderMenu();
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

    @Override
    public void dispose() {

    }

    private void select() {
        if (currentItem == 0) {
            gameStateManager.setState(GameStateManager.SINGLE_PLAYER_MENU);
        }
        if (currentItem == 1) {
            gameStateManager.setState(GameStateManager.MAIN_MENU);
        }
        if (currentItem == 2) {
            Gdx.app.exit();
        }
    }

    private void renderOutcome() {
        String title = null;
        switch (outcome) {
            case WIN:
                title = "YOU WIN!";
                break;
            case LOSE:
                title = "YOU LOSE!";
                break;
            case DRAW:
                title = "IT'S A DRAW!";
                break;
        }

        // get size of title to center
        float width = titleFont.getBounds(title).width;
        // draw title
        titleFont.draw(sb, title, (Application.WIDTH - width )/ 2, 300);
    }

    private void renderMenu() {
        // draw menu
        for (int i = 0; i < menuItems.length; i++) {
            // get size of item to center
            float width = font.getBounds(menuItems[i]).width;
            if (currentItem == i) {
                font.setColor(Color.BLUE);
            } else {
                font.setColor(Color.WHITE);
            }
            // draw item
            font.draw(sb, menuItems[i], (Application.WIDTH - width) / 2, 180 - 35 * i);
        }
    }
}
