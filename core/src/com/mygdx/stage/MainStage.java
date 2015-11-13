package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.manager.AssetManager;
import com.mygdx.manager.ScreenManager;
import com.mygdx.service.Screens;

public class MainStage extends Stage {
	private com.badlogic.gdx.assets.AssetManager assetManager;
	private TextButton startButton;
	private Drawable background;
	private Texture backgroundTexture;
	private Table table;
	private Skin skin;

	public Stage makeStage() {
		assetManager = AssetManager.getInstance();
		backgroundTexture = assetManager.get("texture/title.jpg");
		background = new Image(backgroundTexture).getDrawable();
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
		table.setBackground(background);
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
