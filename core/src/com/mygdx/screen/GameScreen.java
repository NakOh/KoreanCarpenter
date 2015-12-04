package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.manager.SaveManager;
import com.mygdx.manager.StageManager;

public class GameScreen implements Screen {
	private final String tag = "GAME_SCREEN";
	private StageManager stageManager;
	private Stage gameStage;
	private SaveManager saveManager;

	@Override
	public void show() {
		saveManager = SaveManager.getInstance();
		stageManager = StageManager.getInstance();
		gameStage = stageManager.getStage("game");
		setInputProcessor();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStage.draw();
		gameStage.act();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, gameStage);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		Gdx.app.log(tag, "pause");
		saveManager.save();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		Gdx.app.log(tag, "hide");
		saveManager.save();
		gameStage.dispose();
	}

	@Override
	public void dispose() {
		Gdx.app.log(tag, "dispose");
		saveManager.save();
	}

}
