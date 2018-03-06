package com.vladwild.chest.with.gems.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vladwild.chest.with.gems.gamestarter.ChestWithGems;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Chest With Gems";
		config.useGL30 = true;
		config.width = 500;
		config.height = (int) (1.35 * config.width);
		new LwjglApplication(new ChestWithGems(), config);
	}
}
