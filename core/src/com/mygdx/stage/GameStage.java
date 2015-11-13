package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameStage extends Stage {
	private final String tag = "GAME_STAGE";
	private Table levelTable;
	private Table bottomTable;
	private TextButton axButton;
	private TextButton gloveButton;
	private TextButton wagonButton;
	private TextButton bagButton;
	private TextButton itemButton;
	private TextButton endingButton;
	private Skin skin;
	private int x, y;

	public Stage makeStage() {
		x = this.getViewport().getScreenWidth();
		y = this.getViewport().getScreenHeight();

		bottomTable = makeTable("bottom");
		levelTable = makeTable("level");

		addActor(bottomTable);
		addActor(levelTable);
		return this;
	}

	private Table makeTable(String tableName) {
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		if (tableName.equals("level")) {
			Table table = new Table();
			axButton = new TextButton("도끼버튼", skin);
			gloveButton = new TextButton("장갑버튼", skin);
			wagonButton = new TextButton("수레버튼", skin);
			table.setFillParent(true);
			table.bottom();
			table.add(axButton).size(300f, 100f);
			table.row();
			table.add(gloveButton).size(300f, 100f);
			table.row();
			table.add(wagonButton).size(300f, 100f);
			table.padBottom(100f);
			return table;
		} else if (tableName.equals("bottom")) {
			Table table = new Table();
			table.setFillParent(true);
			bagButton = new TextButton("가방 버튼", skin);
			itemButton = new TextButton("아이템 버튼", skin);
			endingButton = new TextButton("엔딩 버튼", skin);
			table.bottom();
			table.add(bagButton).size(x / 3, 100f);
			table.add(itemButton).size(x / 3, 100f);
			table.add(endingButton).size(x / 3, 100f);
			return table;
		}
		return null;
	}
}
