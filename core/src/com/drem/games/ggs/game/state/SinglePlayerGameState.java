package com.drem.games.ggs.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.drem.games.ggs.Application;
import com.drem.games.ggs.Jukebox;
import com.drem.games.ggs.api.IPlayerAction;
import com.drem.games.ggs.game.manager.GameStateManager;
import com.drem.games.ggs.game.state.GameEndState.GameOutcome;
import com.drem.games.ggs.player.ComputerPlayer;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.PlayerOutcome;
import com.drem.games.ggs.player.action.ActionName;
import com.drem.games.ggs.player.action.PlayerActionFactory;
import com.drem.games.ggs.weapon.WeaponFactory;

/**
 * Created by drem on 3/27/15.
 */
public class SinglePlayerGameState extends GameState {

    private SpriteBatch sb;
    private Texture background;
    private Texture player1Tx;
    private Texture player2Tx;
    private BitmapFont defaultFont;
    private BitmapFont playerStatsFont;
    private int currentItem;
    private String[] actionItems;
    private Player player1;
    private Player player2;
    private IPlayerAction player1Action;
    private IPlayerAction player2Action;
    protected SinglePlayerGameState(GameStateManager gsm, ComputerPlayer player2) {
        super(gsm);
        this.player1 = new Player();
        this.player2 = player2;
    }

    @Override
    public void init() {
        sb = new SpriteBatch();
        background = new Texture("textures/background_day.png");
        player1Tx = new Texture("textures/player1.png");
        player2Tx = new Texture("textures/player2.png");
        actionItems = new String [] {"BLOCK", "SHOOT", "RELOAD"};

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Hyperspace-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 26;
        parameter.color = Color.WHITE;
        defaultFont = gen.generateFont(parameter);

        parameter.size = 10;
        parameter.color = Color.MAROON;

        playerStatsFont = gen.generateFont(parameter);

        gen.dispose();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        sb.begin();
        renderBackground();
        renderActions();
        renderPlayers();
        renderPlayerStates();
        renderPlayerAction();
        sb.end();
    }

    @Override
    public void handleInput() {
        // Apply the action
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (currentItem > 0) {
                currentItem --;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (currentItem < actionItems.length - 1) {
                currentItem ++;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            gameStateManager.setState(new MainMenuState(gameStateManager));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            select();
        }
    }

    @Override
    public void dispose() {

    }

    private void select() {

        this.player1Action = null;
        this.player2Action = ((ComputerPlayer) player2).makeMove();

        if (currentItem == 0) {
            player1Action = PlayerActionFactory.getPlayerAction(ActionName.BLOCK);
        }
        if (currentItem == 1) {
            player1Action = PlayerActionFactory.getPlayerAction(ActionName.SHOOT);
        }
        if (currentItem == 2) {
            player1Action = PlayerActionFactory.getPlayerAction(ActionName.RELOAD);
        }

        if (player1Action == null) {
            return;
        }

        Player player1Clone = player1;
        Player player2Clone = player2;

        PlayerOutcome playerOutcome = player1Action.getOutcome(player1, player2Clone, player2Action);
        try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
        PlayerOutcome computerOutcome = player2Action.getOutcome(player2, player1Clone, player1Action);
        // FIXME: This should draw the outcome of what happened. If a bullet is shot, this should
        // animate the shot. If death happened, show it, etc...
        updateGameState(playerOutcome, computerOutcome);
    }

    private void updateGameState(PlayerOutcome playerOutcome,
                                 PlayerOutcome computerOutcome) {
        if (playerOutcome == PlayerOutcome.DEAD && computerOutcome == PlayerOutcome.DEAD) {
            declareDraw();
        }
        else if (playerOutcome == PlayerOutcome.DEAD) {
            declareLoser();
        }
        else if (computerOutcome == PlayerOutcome.DEAD) {
            declareWinner();
        }
    }

    private void declareDraw() {
        gameStateManager.setState(new GameEndState(gameStateManager, GameOutcome.DRAW));
    }

    private void declareWinner() {
        // TODO: Check if shield was broken to play broken sound.
        gameStateManager.setState(new GameEndState(gameStateManager, GameOutcome.WIN));
    }

    private void declareLoser() {
        // TODO: Check if shield was broken to play broken sound.
        gameStateManager.setState(new GameEndState(gameStateManager, GameOutcome.LOSE));
    }

    private void renderBackground() {
        sb.draw(background, 0, 0);
    }

    private void renderPlayers() {
        sb.draw(player1Tx, 0, player1Tx.getHeight());
        sb.draw(player2Tx, Application.WIDTH - player2Tx.getWidth(), player2Tx.getHeight());
    }

    private void renderPlayerAction() {
        if (player1Action != null) {
            sb.draw(player1Action.getTexture(), player1Tx.getWidth() / 2, player1Tx.getHeight() * 2);
        }
        if (player2Action != null) {
            Texture texture = player2Action.getTexture();
            TextureRegion tr = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
            tr.flip(true, false);
            sb.draw(tr, Application.WIDTH - (player2Tx.getWidth() / 2) - 30, player2Tx.getHeight() * 2);
        }

    }

    private void renderPlayerStates() {
        // Player 1 stats
        playerStatsFont.draw(sb, "S: " + player1.getShieldStrength(), 200, 250);
        playerStatsFont.draw(sb, "B: " + player1.getBulletCount(), 240, 250);
        if (player1.hasWeapon()) {
            playerStatsFont.draw(sb, "G: " + WeaponFactory.getWeapon(player1.getBulletCount()).getName(), 280, 250);
        }

        // Player 2 stats
        playerStatsFont.draw(sb, "S: " + player2.getShieldStrength(), 200, 270);
        playerStatsFont.draw(sb, "B: " + player2.getBulletCount(), 240, 270);
        if (player2.hasWeapon()) {
            playerStatsFont.draw(sb, "G: " + WeaponFactory.getWeapon(player2.getBulletCount()).getName(), 280, 270);
        }

    }

    private void renderActions() {
        for (int i = 0; i < actionItems.length; i++) {
            // get size of item to center
            float width = 125;
            if (currentItem == i) {
                defaultFont.setColor(Color.BLACK);
            } else {
                defaultFont.setColor(Color.WHITE);
            }
            // draw item
            defaultFont.draw(sb, actionItems[i], Application.WIDTH / 6 + (width * i), Application.HEIGHT - 50);
        }
    }
}
