package com.mygdx.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.manager.StageManager;

public class MainScreen implements Screen {

	private StageManager stageManager;
	private Stage mainStage;

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stageManager = stageManager.getInstance();
		mainStage = stageManager.getStage("main");
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		mainStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
