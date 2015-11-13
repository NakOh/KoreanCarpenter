package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.stage.LoadingStage;

public class LoadingScreen implements Screen {

	private LoadingStage loadingStage;

	@Override
	public void show() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		loadingStage = new LoadingStage();
		loadingStage.makeStage();
		setInputProcessor();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
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
