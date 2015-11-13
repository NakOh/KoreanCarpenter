package com.mygdx.manager;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.stage.GameStage;
import com.mygdx.stage.LoadingStage;
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
		} else if (stage.equals("game")) {
			this.stage = new GameStage().makeStage();
		} else if (stage.equals("loading")) {
			this.stage = new LoadingStage().makeStage();
		}
		return this.stage;
	}

}
