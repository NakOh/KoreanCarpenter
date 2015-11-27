package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.data.GameData;
import com.mygdx.model.NormalTree;
import com.mygdx.model.Tree;

public class GameStage extends Stage {
	private final String tag = "GAME_STAGE";
	private final String perfect = "PERFECT";
	private final String good = "GOOD";
	private final String bad = "BAD";
	// Table
	private Table levelTable;
	private Table bottomTable;
	private Table gameTable;
	private Table midTable;

	private BagTable bagTable;
	private EndingTable endingTable;
	private ItemTable itemTable;

	// 임시 Button, 실제 사용할 UI로 대체
	private TextButton bagButton;
	private TextButton itemButton;
	private TextButton endingButton;
	private TextButton treeButton;
	private TextButton sellButton;
	private TextButton leftButton;
	private TextButton rightButton;

	private Label money;

	private int combo = 0;

	private Tree tree;
	private Skin skin;
	private int stageX, stageY;

	private GameData gameData;
	private float gameTime;

	public Stage makeStage() {
		gameData = GameData.getInstance();
		tree = makeRandomTree();

		stageX = this.getViewport().getScreenWidth();
		stageY = this.getViewport().getScreenHeight();

		bagTable = new BagTable();
		itemTable = new ItemTable();
		endingTable = new EndingTable();

		gameTable = makeTable("game");
		bottomTable = makeTable("bottom");
		levelTable = makeTable("level");
		midTable = makeTable("mid");
		addListener();

		addActor(bottomTable);
		addActor(levelTable);
		addActor(gameTable);
		addActor(midTable);
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
			money = new Label("" + gameData.getMoney(), skin);
			treeButton = new TextButton("이것은 나무", skin);
			table.top();
			table.add(money).size(stageX / 2, 100f);
			table.add(treeButton).size(stageX / 2, 400f);
		} else if (tableName.equals("mid")) {
			table.setFillParent(true);
			sellButton = new TextButton(gameData.getTree() + "\n" + "나무 판매", skin);
			leftButton = new TextButton("왼쪽 버튼", skin);
			rightButton = new TextButton("오른쪽 버튼", skin);
			table.top();
			table.add(leftButton).padRight(300f).height(100f);
			table.add(sellButton).padRight(300f).height(100f);
			table.add(rightButton).height(100f);
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
				updateLevelTable(itemTable.makeTable(stageX, stageY));
			}
		});
		endingButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				endingTable = new EndingTable();
				updateLevelTable(endingTable.makeTable(stageX, stageY));
			}
		});
		treeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				gameData.setTree(gameData.getTree() + 1);
			}
		});
		sellButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				gameData.setMoney(gameData.getMoney() + gameData.getTree() * 10);
				money.setText("" + gameData.getMoney());
				gameData.setTree(0);
			}
		});
		leftButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// 리듬 체크
				String check = checkRhythm(x, y);
				attackTree(check);
			}
		});
		rightButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				String check = checkRhythm(x, y);
				attackTree(check);
			}
		});
	}

	private void attackTree(String check) {
		if (check.equals(perfect)) {
			// 정확하게 일치할 때
			combo++;
		} else if (check.equals(good)) {
			// 약간 어긋났을 때
			combo++;
		} else {
			// 완전 틀림, 콤보 취소, 자동으로 진행될 때 bad
			// 넘기면 set에서 자동으로 max인지 아닌지 체크해줌
			gameData.setMaxCombo(combo);
			combo = 0;
			tree.setHp(tree.getHp() - gameData.getAxAttackTable()[gameData.getAxLevel() - 1]);
			if (checkDieTree()) {
				// 죽었으면 새로 만들자
				gameData.setTree(gameData.getTree() + 1);
				tree = makeRandomTree();
			}
		}
	}

	private boolean checkDieTree() {
		if (tree.getHp() == 0) {
			return true;
		} else {
			return false;
		}
	}

	private String checkRhythm(float x, float y) {
		// 리듬 체크하는 로직 추가
		return bad;
	}

	private Tree makeRandomTree() {
		// 나무 생성 기준에 따라 로직을 추가하도록 합시다.
		Tree newTree;
		newTree = new NormalTree();
		return newTree;
	}

	@Override
	public void act(float delta) {
		// 실사간 머니 증가 반영
		gameTime += delta;
		// 게임 시간 상으로 1초가 지날 때 마다 작동하는 로직
		if (gameTime > 1) {
			// 나무 피가 달게 한다.
			tree.setHp(tree.getHp() - gameData.getAxAttackTable()[gameData.getAxLevel() - 1]);
			checkDieTree();
			if (checkDieTree()) {
				// 죽었으면 새로 만들자
				gameData.setTree(gameData.getTree() + 1);
				tree = makeRandomTree();
			}
			Gdx.app.log(tag, String.valueOf(tree.getHp()));
			gameTime = 0;
		}
		money.setText("" + gameData.getMoney());
		sellButton.setText(gameData.getTree() + "\n" + "나무 판매");
	}

}
