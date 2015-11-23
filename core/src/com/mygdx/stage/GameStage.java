package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameStage extends Stage {
	private final String tag = "GAME_STAGE";
	// Table
	private Table levelTable;
	private Table bottomTable;
	private Table gameTable;
	private Table treeTable;

	private BagTable bagTable;
	private EndingTable endingTable;
	private ItemTable itemTable;

	// 임시 Button, 실제 사용할 UI로 대체
	private TextButton bagButton;
	private TextButton itemButton;
	private TextButton endingButton;
	private TextButton treeButton;
	private TextButton sellButton;

	private Skin skin;
	private int stageX, stageY;

	public Stage makeStage() {
		stageX = this.getViewport().getScreenWidth();
		stageY = this.getViewport().getScreenHeight();

		bagTable = new BagTable();
		itemTable = new ItemTable();
		endingTable = new EndingTable();

		gameTable = makeTable("game");
		bottomTable = makeTable("bottom");
		levelTable = makeTable("level");
		treeTable = makeTable("tree");
		addListener();

		addActor(bottomTable);
		addActor(levelTable);
		addActor(gameTable);
		addActor(treeTable);
		return this;
	}

	private boolean updateLevelTable(Table table) {
		levelTable.reset();
		levelTable.addActor(table);
		return true;
	}

	private Table makeTable(String tableName) {
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		Table table = new Table();
		if (tableName.equals("level")) {
			table = bagTable.makeTable(stageX, stageY);
		} else if (tableName.equals("bottom")) {
			table.setFillParent(true);
			bagButton = new TextButton("가방 버튼", skin);
			itemButton = new TextButton("아이템 버튼", skin);
			endingButton = new TextButton("엔딩 버튼", skin);
			table.bottom();
			table.add(bagButton).size(stageX / 3, 100f);
			table.add(itemButton).size(stageX / 3, 100f);
			table.add(endingButton).size(stageX / 3, 100f);
		} else if (tableName.equals("game")) {
			table.setFillParent(true);
			treeButton = new TextButton("이것은 나무", skin);
			table.top();
			table.add(treeButton).size(stageX, 400f);
		} else if (tableName.equals("tree")) {
			table.setFillParent(true);
			sellButton = new TextButton("나무 판매", skin);
			table.top();
			table.add(sellButton);
			table.padTop(400f);
		}

		return table;
	}

	private void addListener() {
		bagButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				bagTable = new BagTable();
				updateLevelTable(bagTable.makeTable(stageX, stageY));
			}
		});

		itemButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

				itemTable = new ItemTable();
				if (updateLevelTable(itemTable.makeTable(stageX, stageY)))
					Gdx.app.log(tag, "itemTable이 만들어 졌습니다");
				else
					Gdx.app.log(tag, "itemTable 제작 실패");
			}
		});

		endingButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

				endingTable = new EndingTable();

				updateLevelTable(endingTable.makeTable(stageX, stageY));
			}
		});
	}
}
