package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.manager.ScreenManager;
import com.mygdx.service.Screens;

public class MainStage extends Stage {
	private TextButton startButton;
	private Table table;
	private Skin skin;

	public Stage makeStage() {
		makeTable();
		addTouchListener();
		return this;
	}

	private void makeTable() {
		table = new Table();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		startButton = new TextButton("GameStart", skin);
		table.setFillParent(true);
		table.add(startButton);
		this.addActor(table);
	}

	private void addTouchListener() {

		startButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				ScreenManager.getInstance().show(Screens.GAME);

			}
		});

	}

}
