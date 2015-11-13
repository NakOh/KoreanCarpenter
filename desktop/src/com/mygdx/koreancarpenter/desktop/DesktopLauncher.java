package com.mygdx.koreancarpenter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.koreancarpenter.KoreanCarpenter;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Korean Carpenter";
		config.width = 1600;
		config.height = 900;
		config.resizable = false;
		new LwjglApplication(new KoreanCarpenter(), config);
	}
}
