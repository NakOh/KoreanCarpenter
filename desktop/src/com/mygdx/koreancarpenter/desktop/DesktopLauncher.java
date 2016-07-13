package com.mygdx.koreancarpenter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.koreancarpenter.KoreanCarpenter;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Korean Carpenter";
		config.width = 1080;
		config.height = 920;
		config.resizable = false;
		new LwjglApplication(new KoreanCarpenter(new ActionResolverDesktop(), new AdsMobControllerDesktop()), config);
	}
}
