package com.mygdx.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.manager.AssetManager;

public class LoadingStage extends Stage {
	private AssetManager assetManager;

	public void makeStage() {
		assetManager.getInstance();
	}

}
