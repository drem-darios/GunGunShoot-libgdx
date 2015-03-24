package com.drem.games.ggs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.drem.games.ggs.game.state.manager.GameStateManager;

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

		gameStateManager = new GameStateManager();
		gameStateManager.setState(GameStateManager.MAIN_MENU);
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
