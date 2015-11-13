package com.mygdx.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.manager.AssetManager;
import com.mygdx.manager.ScreenManager;
import com.mygdx.service.Screens;

public class LoadingStage extends Stage {

	private com.badlogic.gdx.assets.AssetManager assetManager;

	public void makeStage() {
		assetManager = AssetManager.getInstance();
		AssetManager.allAssetsload();
	}

	@Override
	public void act() {
		if (assetManager.update()) {
			ScreenManager.getInstance().show(Screens.MAIN);
		}
	}
}
