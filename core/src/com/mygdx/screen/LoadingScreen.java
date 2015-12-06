package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.manager.ScreenManager;
import com.mygdx.manager.StageManager;
import com.mygdx.service.Screens;

public class LoadingScreen implements Screen {
	private final String tag = "LOADING_SCREEN";
	private StageManager stageManager;
	private Stage loadingStage;

	@Override
	public void show() {
		stageManager = StageManager.getInstance();
		loadingStage = stageManager.getStage("loading");
		setInputProcessor();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		loadingStage.act();
		loadingStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, loadingStage);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void pause() {
		Gdx.app.log(tag, "pause");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		Gdx.app.log(tag, "hide");
		loadingStage.dispose();
		ScreenManager.getInstance().dispose(Screens.LOAD);
		this.dispose();
	}

	@Override
	public void dispose() {
		Gdx.app.log(tag, "dispose");
	}

}
