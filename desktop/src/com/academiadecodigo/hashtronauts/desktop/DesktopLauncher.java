package com.academiadecodigo.hashtronauts.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.academiadecodigo.hashtronauts.MrDuckHunt;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.height = 600;
		config.width = 800;

		new LwjglApplication(new MrDuckHunt(), config);
	}
}
