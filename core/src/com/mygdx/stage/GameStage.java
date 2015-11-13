package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameStage extends Stage {
	private final String tag = "GAME_STAGE";
	// Table
	private Table levelTable;
	private Table bottomTable;
	private Table gameTable;
	private Table treeTable;

	// 임시 Button, 실제 사용할 UI로 대체
	private TextButton axButton;
	private TextButton gloveButton;
	private TextButton wagonButton;
	private TextButton bagButton;
	private TextButton itemButton;
	private TextButton endingButton;
	private TextButton treeButton;
	private TextButton sellButton;

	private Skin skin;
	private int x, y;

	public Stage makeStage() {
		x = this.getViewport().getScreenWidth();
		y = this.getViewport().getScreenHeight();

		gameTable = makeTable("game");
		bottomTable = makeTable("bottom");
		levelTable = makeTable("level");
		treeTable = makeTable("tree");

		addActor(bottomTable);
		addActor(levelTable);
		addActor(gameTable);
		addActor(treeTable);
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
			table.add(axButton).size(x / 6, 100f);
			table.row();
			table.add(gloveButton).size(x / 6, 100f);
			table.row();
			table.add(wagonButton).size(x / 6, 100f);
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
		} else if (tableName.equals("game")) {
			Table table = new Table();
			table.setFillParent(true);
			treeButton = new TextButton("이것은 나무", skin);
			table.top();
			table.add(treeButton).size(x, 400f);
			return table;
		} else if (tableName.equals("tree")) {
			Table table = new Table();
			table.setFillParent(true);
			sellButton = new TextButton("나무 판매", skin);
			table.top();
			table.add(sellButton);
			table.padTop(400f);
			return table;
		}
		return null;
	}
}
