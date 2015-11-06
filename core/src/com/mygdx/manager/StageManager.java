package com.mygdx.manager;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.stage.MainStage;

public class StageManager {
	private static StageManager instance;
	private Stage stage;

	public static StageManager getInstance() {
		if (instance == null) {
			instance = new StageManager();
		}
		return instance;
	}

	public Stage getStage(String stage) {
		if (stage.equals("main")) {
			this.stage = new MainStage().makeStage();
		}
		return this.stage;
	}

}
