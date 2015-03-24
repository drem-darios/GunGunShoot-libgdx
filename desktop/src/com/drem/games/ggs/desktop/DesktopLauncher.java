package com.drem.games.ggs.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.drem.games.ggs.Application;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 500;
		config.height = 400;
		config.resizable = false;
		config.title = "Gun Gun Shoot";

		new LwjglApplication(new Application(), config);
	}
}
