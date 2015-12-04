package com.mygdx.stage;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.data.GameData;
import com.mygdx.manager.AssetManager;
import com.mygdx.manager.SaveManager;
import com.mygdx.model.Bar;
import com.mygdx.model.NormalTree;
import com.mygdx.model.Tree;

public class GameStage extends Stage {
	private final String tag = "GAME_STAGE";
	private final String perfect = "PERFECT";
	private final String good = "GOOD";
	private final String bad = "BAD";

	private com.badlogic.gdx.assets.AssetManager assetManager;
	private SaveManager saveManager;
	// Table
	private Table levelTable;
	private Table bottomTable;
	private Table gameTable;
	private Table midTable;
	private Table topTable;

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

	private Label moneyLabel;
	private Label comboLabel;

	private int combo = 0;

	private Tree tree;
	private Skin skin;
	private int stageX, stageY;

	private GameData gameData;
	private float gameTime;
	private float coolTime;
	private float delayTime;
	private float saveTime;
	private float[] zazinmoriTime = { 0.8f, 0.6f, 0.2f, 0.4f, 0.4f, 0.2f, 0.2f, 0.2f, 0.2f, 0.6f, 0.2f, 0.4f, 0.4f };
	private boolean[] isLeft = { true, false, true, false, true, false, true, false, true, false, true, false, true };

	private Texture bigTexture;
	private Texture smallLeftTexture;
	private Texture smallRightTexture;

	private Image big;
	private ArrayList<Image> imageList;

	private Bar hpBar;
	private int i;

	public Stage makeStage() {
		assetManager = AssetManager.getInstance();
		gameData = GameData.getInstance();
		saveManager = SaveManager.getInstance();

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
		topTable = makeTable("top");

		makeBigObject();
		addListener();

		addActor(topTable);
		addActor(bottomTable);
		addActor(levelTable);
		addActor(gameTable);
		addActor(midTable);

		return this;
	}

	private void makeBigObject() {
		bigTexture = assetManager.get("texture/big.png");
		imageList = new ArrayList<Image>();
		big = new Image(bigTexture);
		big.setPosition(2 * stageX / 19, 17 * stageY / 19);
		addActor(big);
	}

	private void makeLeftObject() {
		smallLeftTexture = assetManager.get("texture/smallLeft.png");
		Image image = new Image(smallLeftTexture);
		image.setSize(stageX / 7, 2 * stageY / 19);
		image.setPosition(stageX, 17 * stageY / 19);
		addActor(image);
		imageList.add(image);
	}

	private void makeRightObject() {
		smallRightTexture = assetManager.get("texture/smallRight.png");
		Image image = new Image(smallRightTexture);
		image.setSize(stageX / 7, 2 * stageY / 19);
		image.setPosition(stageX, 17 * stageY / 19);
		addActor(image);
		imageList.add(image);
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
			table.add(bagButton).size(stageX / 3, stageY / 19);
			table.add(itemButton).size(stageX / 3, stageY / 19);
			table.add(endingButton).size(stageX / 3, stageY / 19);
		} else if (tableName.equals("game")) {
			table.setFillParent(true);
			moneyLabel = new Label("" + gameData.getMoney(), skin);
			comboLabel = new Label("" + combo, skin);
			comboLabel.setAlignment(Align.center);
			moneyLabel.setAlignment(Align.center);
			hpBar = new Bar("hp", skin);
			hpBar.setValue(tree.getHp());
			treeButton = new TextButton("이것은 나무", skin);
			table.top();
			table.add(treeButton).size(stageX / 2, 7 * stageY / 19);
			table.row();
			table.add(moneyLabel).size(stageX / 2, stageY / 19);
			table.row();
			table.add(comboLabel).size(stageX / 2, stageY / 19);
			table.row();
			table.add(hpBar).size(stageX, stageY / 19);
			table.padTop(stageY / 19);
		} else if (tableName.equals("mid")) {
			table.setFillParent(true);
			sellButton = new TextButton(gameData.getTree() + "\n" + "나무 판매", skin);
			leftButton = new TextButton("왼쪽 버튼", skin);
			rightButton = new TextButton("오른쪽 버튼", skin);
			table.bottom();
			table.add(leftButton).padRight(stageX / 4).height(2 * stageY / 19);
			table.add(sellButton).padRight(stageX / 4).height(2 * stageY / 19);
			table.add(rightButton).height(2 * stageY / 19);
			table.padBottom(7 * stageY / 19);
		} else if (tableName.equals("top")) {
			table.setFillParent(true);
			table.top();
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
				// gameData.setTree(gameData.getTree() + 1);
			}
		});
		sellButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				gameData.setMoney(gameData.getMoney() + gameData.getTree() * 10);
				moneyLabel.setText("" + gameData.getMoney());
				gameData.setTree(0);
			}
		});
		leftButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// 리듬 체크 후 공격 x,y좌표는 오브젝트 좌표를 받아와야함 (현재는 버튼 좌표)
				attackTree(checkRhythm(x, y));
			}
		});
		rightButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// 리듬 체크 후 공격 x,y좌표는 오브젝트 좌표를 받아와야함 (현재는 버튼 좌표)
				attackTree(checkRhythm(x, y));
			}
		});
	}

	private void attackTree(String check) {
		if (check.equals(perfect)) {
			// 정확하게 일치할 때
			float damage;
			combo++;
			gameData.setFeverGauge(gameData.getFeverGauge() + 10);
			damage = (float) (gameData.getAttack() * (2 + combo * 0.01));
			tree.setHp(tree.getHp() - (int) damage);
			if (checkDieTree()) {
				// 죽었으면 새로 만들자
				if (checkStorage()) {
					gameData.setTree(gameData.getTree() + 1);
				}
				tree = makeRandomTree();
			}
		} else if (check.equals(good)) {
			// 약간 어긋났을 때
			float damage;
			combo++;
			gameData.setFeverGauge(gameData.getFeverGauge() + 7);
			damage = (float) (gameData.getAttack() * (1.5 + combo * 0.005));
			tree.setHp(tree.getHp() - (int) damage);
			if (checkDieTree()) {
				// 죽었으면 새로 만들자
				if (checkStorage()) {
					gameData.setTree(gameData.getTree() + 1);
				}
				tree = makeRandomTree();
			}
		} else {
			// 완전 틀림, 콤보 취소, 자동으로 진행될 때 bad
			// 넘기면 set에서 자동으로 max인지 아닌지 체크해줌

			if (coolTime > 3) {
				gameData.setMaxCombo(combo);
				combo = 0;
				gameData.setFeverGauge(gameData.getFeverGauge() + 5);
				if (checkAccuracy()) {
					tree.setHp(tree.getHp() - gameData.getAttack());
					if (checkDieTree()) {
						// 죽었으면 새로 만들자
						if (checkStorage()) {
							gameData.setTree(gameData.getTree() + 1);
						}
						tree = makeRandomTree();
					}
				}
				coolTime = 0;
			} else {
				Gdx.app.log(tag, "coolTime입니다");
			}
		}
	}

	private boolean checkAccuracy() {
		int random = (int) (Math.random() * 100);
		if (gameData.getAccuracy() > random) {
			return true;
		} else {
			Gdx.app.log(tag, "빗나감");
			return false;
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
		if (imageList.get(0).getX() > big.getX() - 2 && imageList.get(0).getX() < big.getX() + 20) {
			Gdx.app.log(tag, perfect);
			return perfect;
		} else if (imageList.get(0).getX() > big.getX() + 20 && imageList.get(0).getX() < big.getX() + 40) {
			// 현재 눌러야할 버튼이 조준점보다 크고, 그 범위가 20이내라면
			Gdx.app.log(tag, good);
			return good;
		} else {
			Gdx.app.log(tag, bad);
			return bad;
		}
	}

	private boolean checkStorage() {
		// 용량을 초과했는가?
		if (gameData.getStorage() >= gameData.getTree()) {
			return true;
		} else {
			return false;
		}
	}

	private Tree makeRandomTree() {
		// 나무 생성 기준에 따라 로직을 추가하도록 합시다. 아직 정해진 바 없음
		Tree newTree;
		newTree = new NormalTree();
		return newTree;
	}

	private void zazinmori(float delta) {
		delayTime += delta;
		if (delayTime > zazinmoriTime[i]) {
			if (isLeft[i]) {
				makeLeftObject();
			} else {
				makeRightObject();
			}
			delayTime = 0;
			i++;
			// 무한 반복
			if (i == zazinmoriTime.length) {
				i = 0;
			}
		}
	}

	@Override
	public void act(float delta) {
		// 실사간 머니 증가 반영
		gameTime += delta;
		coolTime += delta;
		saveTime += delta;
		zazinmori(delta);

		for (Image image : imageList) {
			image.setPosition(image.getX() - 3, image.getY());
		}
		if (!imageList.isEmpty()) {
			if (imageList.get(0).getX() < big.getX() - 3) {
				imageList.get(0).remove();
				imageList.remove(0);
				gameData.setMaxCombo(combo);
			}
		}

		// 게임 시간 상으로 1초가 지날 때 마다 작동하는 로직
		if (gameTime > 5) {
			// 나무 피가 달게 한다.
			if (checkAccuracy()) {
				tree.setHp(tree.getHp() - gameData.getAttack());
				checkDieTree();
				if (checkDieTree()) {
					// 죽었으면 새로 만들자
					if (checkStorage()) {
						gameData.setTree(gameData.getTree() + 1);
					}
					tree = makeRandomTree();
				}
			}
			gameTime = 0;
		}
		moneyLabel.setText("" + gameData.getMoney());
		comboLabel.setText("" + combo);
		sellButton.setText(gameData.getTree() + "\n" + "나무 판매");
		endingTable.act(delta);
		hpBar.setValue(tree.getHp());
		hpBar.act(delta);
		if (saveTime > 5) {
			saveManager.save();
			saveTime = 0;
		}

	}

}
