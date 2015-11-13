package com.mygdx.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.manager.AssetManager;
import com.mygdx.manager.ScreenManager;
import com.mygdx.service.Screens;

public class LoadingStage extends Stage {

	private com.badlogic.gdx.assets.AssetManager assetManager;

	public Stage makeStage() {
		assetManager = AssetManager.getInstance();
		AssetManager.allAssetsload();
		return this;
	}

	@Override
	public void act() {
		if (assetManager.update()) {
			// 처음일때는 프롤로그 스크린으로 넘어가도록 하자.
			ScreenManager.getInstance().show(Screens.MAIN);
		}
	}
}
