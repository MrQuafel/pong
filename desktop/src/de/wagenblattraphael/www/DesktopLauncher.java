package de.wagenblattraphael.www;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Pong");
		config.setWindowedMode(IConfig.FIELD_WIDTH, IConfig.FIELD_HEIGHT);
		new Lwjgl3Application(new Pong(), config);
	}
}
