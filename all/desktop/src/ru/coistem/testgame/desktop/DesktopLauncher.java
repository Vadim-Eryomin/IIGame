package ru.coistem.testgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.coistem.testgame.Game;
import ru.coistem.testgame.Physics;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = true;
		config.width = 1920;
		config.height = 1080;
		new LwjglApplication(new Game(), config);
	}
}
