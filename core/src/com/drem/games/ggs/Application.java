package com.drem.games.ggs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.drem.games.ggs.game.manager.GameStateManager;

public class Application extends ApplicationAdapter {
	public static OrthographicCamera camera;
	public static int WIDTH;
	public static int HEIGHT;

	private GameStateManager gameStateManager;

	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.translate(WIDTH / 2, HEIGHT / 2);
		camera.update();

		Jukebox.loadSound("sounds/block.mp3", "block");
		Jukebox.loadSound("sounds/bullet_hit.mp3", "hit");
		Jukebox.loadSound("sounds/crack.mp3", "crack");
		Jukebox.loadSound("sounds/gasp.mp3", "gasp");
		Jukebox.loadSound("sounds/womp_womp.mp3", "womp");
		Jukebox.loadSound("sounds/pew_pew.mp3", "pew_pew");
		Jukebox.loadSound("sounds/reload.mp3", "reload");
		Jukebox.loadSound("sounds/scared.mp3", "scared");
		Jukebox.loadSound("sounds/spit.mp3", "spit");
//		Jukebox.loadMusic(path, name);



		gameStateManager = new GameStateManager();
		gameStateManager.setState(GameStateManager.SINGLE_PLAYER_MENU);
	}

	@Override
	public void render () {
		// Clear screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// The timespan between the current frame and last frame in seconds
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.draw();
	}
}
