package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.manager.StageManager;

public class MainScreen implements Screen {

	private StageManager stageManager;
	private Stage mainStage;

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stageManager = StageManager.getInstance();
		mainStage = stageManager.getStage("main");
		setInputProcessor();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		mainStage.draw();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, mainStage);
		Gdx.input.setInputProcessor(multiplexer);

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
