package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.manager.StageManager;

public class MainScreen implements Screen {
	private final String tag = "MAIN";
	private StageManager stageManager;
	private Stage mainStage;

	@Override
	public void show() {
		stageManager = StageManager.getInstance();
		mainStage = stageManager.getStage("main");
		setInputProcessor();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
		Gdx.app.log(tag, "pause");

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		Gdx.app.log(tag, "hide");
		mainStage.dispose();
	}

	@Override
	public void dispose() {
		Gdx.app.log(tag, "dispose");
	}

}
