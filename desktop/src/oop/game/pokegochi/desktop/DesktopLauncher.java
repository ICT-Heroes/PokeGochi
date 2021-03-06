package oop.game.pokegochi.desktop;

import oop.game.pokegochi.PokeGochi;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 720;
		config.height = 580;
		config.resizable = false;
		new LwjglApplication(new PokeGochi(), config);
	}
}
