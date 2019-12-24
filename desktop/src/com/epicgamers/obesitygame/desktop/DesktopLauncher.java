package com.epicgamers.obesitygame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.epicgamers.obesitygame.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Obesity Game";
		config.width = 1280;
		config.height = 720;
		config.x = -1;
		config.y = -1;
		new LwjglApplication(new MainGame(), config);
	}
}
