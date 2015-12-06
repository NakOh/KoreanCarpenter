package com.mygdx.stage;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.data.GameData;
import com.mygdx.manager.AssetManager;
import com.mygdx.manager.SaveManager;
import com.mygdx.model.Bar;
import com.mygdx.model.NormalTree;
import com.mygdx.model.ObjectImage;
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
	private Table hpBarTable;

	private BagTable bagTable;
	private EndingTable endingTable;
	private ItemTable itemTable;

	// 임시 Button, 실제 사용할 UI로 대체
	private TextButton bagButton;
	private TextButton itemButton;
	private TextButton endingButton;
	private TextButton sellButton;
	private TextButton leftButton;
	private TextButton rightButton;

	private TextButton itemButton1;
	private TextButton itemButton2;

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
	private boolean[] zazinmoriIsLeft = { true, false, true, false, true, false, true, false, true, false, true, false,
			true };

	private Texture bigTexture;
	private Texture smallLeftTexture;
	private Texture smallRightTexture;
	private Texture treeTexture;

	private Image big;
	private ArrayList<ObjectImage> imageList;

	private Bar hpBar;
	private int index;
	private boolean isLeftButton;

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
		hpBarTable = makeTable("hp");

		makeBigObject();
		addListener();

		addActor(hpBarTable);
		addActor(bottomTable);
		addActor(levelTable);
		addActor(gameTable);
		addActor(midTable);

		return this;
	}

	private void makeBigObject() {
		bigTexture = assetManager.get("texture/object/big.png");
		imageList = new ArrayList<ObjectImage>();
		big = new Image(bigTexture);
		big.setSize(stageX / 7, 2 * stageY / 19);
		big.setPosition(2 * stageX / 19, 17 * stageY / 19);
		addActor(big);
	}

	private void makeLeftObject() {
		smallLeftTexture = assetManager.get("texture/object/smallLeft.png");
		ObjectImage image = new ObjectImage(smallLeftTexture);
		image.setLeft(true);
		image.setSize(stageX / 7, 2 * stageY / 19);
		image.setPosition(stageX, 17 * stageY / 19);
		addActor(image);
		imageList.add(image);
	}

	private void makeRightObject() {
		smallRightTexture = assetManager.get("texture/object/smallRight.png");
		ObjectImage image = new ObjectImage(smallRightTexture);
		image.setLeft(false);
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
			Table subTable1 = new Table();
			Table subTable2 = new Table();
			Table subTable3 = new Table();
			table.setFillParent(true);
			moneyLabel = new Label("현재 돈" + gameData.getMoney(), skin);
			comboLabel = new Label("현재 Combo" + combo, skin);
			moneyLabel.setFontScale(0.8f);
			comboLabel.setFontScale(0.8f);
			itemButton1 = new TextButton("아이템1", skin);
			itemButton2 = new TextButton("아이템2", skin);
			comboLabel.setAlignment(Align.center);
			moneyLabel.setAlignment(Align.center);
			hpBar = new Bar("hp", skin);
			hpBar.setValue(tree.getHp());
			treeTexture = assetManager.get("texture/tree/tree100%.png");
			tree.setTreeImage(new Image(treeTexture));
			table.bottom();
			table.padBottom(10 * stageY / 19);
			subTable1.add(tree.getTreeImage()).size(stageX / 2, 7 * stageY / 19);
			subTable2.add(comboLabel);
			subTable2.row();
			subTable2.add(moneyLabel);
			subTable3.add(itemButton1);
			subTable3.row();
			subTable3.add(itemButton2);
			table.add(subTable2).width(stageX / 3).top().left();
			table.add(subTable1).width(stageX / 3);
			table.add(subTable3).width(stageX / 3).bottom().right();
		} else if (tableName.equals("mid")) {
			table.setFillParent(true);
			sellButton = new TextButton(gameData.getTree() + "\n" + "나무 판매", skin);
			leftButton = new TextButton("왼쪽 버튼", skin);
			rightButton = new TextButton("오른쪽 버튼", skin);
			table.bottom();
			table.add(leftButton).padRight(stageX / 4).height(2 * stageY / 19);
			table.add(sellButton).padRight(stageX / 4).height(2 * stageY / 19);
			table.add(rightButton).height(2 * stageY / 19 + 10);
			table.padBottom(7 * stageY / 19);
		} else if (tableName.equals("hp")) {
			table.setFillParent(true);
			table.add(hpBar).width(stageX).height(stageY / 19);
			table.bottom();
			table.padBottom(9 * stageY / 19);
		}
		return table;
	}

	private void addListener() {
		bagButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				updateLevelTable(BagTable.getInstance(stageX, stageY));
			}
		});
		itemButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				updateLevelTable(ItemTable.getInstance(stageX, stageY));
			}
		});
		endingButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				updateLevelTable(EndingTable.getInstance(stageX, stageY));
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
				isLeftButton = true;
				attackTree(checkRhythm(x, y));
			}
		});
		rightButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// 리듬 체크 후 공격 x,y좌표는 오브젝트 좌표를 받아와야함 (현재는 버튼 좌표)
				isLeftButton = false;
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
			updateTreeImage((int) damage);
		} else if (check.equals(good)) {
			// 약간 어긋났을 때
			float damage;
			combo++;
			gameData.setFeverGauge(gameData.getFeverGauge() + 7);
			damage = (float) (gameData.getAttack() * (1.5 + combo * 0.005));
			updateTreeImage((int) damage);
		} else {
			// 완전 틀림, 콤보 취소, 자동으로 진행될 때 bad
			// 넘기면 set에서 자동으로 max인지 아닌지 체크해줌
			if (coolTime > 3) {
				gameData.setMaxCombo(combo);
				combo = 0;
				gameData.setFeverGauge(gameData.getFeverGauge() + 5);
				if (checkAccuracy()) {
					updateTreeImage(gameData.getAttack());
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

	private void updateTreeImage(int damage) {
		tree.setHp(tree.getHp() - damage);
		if ((100 * tree.getHp() / tree.getMaxHp()) <= 33) {
			treeTexture = assetManager.get("texture/tree/tree33%.png");
			tree.getTreeImage().setDrawable(new SpriteDrawable(new Sprite(treeTexture)));
			tree.getTreeImage().act(1f);
		} else if ((100 * tree.getHp() / tree.getMaxHp()) <= 66) {
			treeTexture = assetManager.get("texture/tree/tree66%.png");
			tree.getTreeImage().setDrawable(new SpriteDrawable(new Sprite(treeTexture)));
			tree.getTreeImage().act(1f);
		}

		if (checkDieTree()) {
			// 죽었으면 새로 만들자
			if (checkStorage()) {
				gameData.setTree(gameData.getTree() + 1);
			}
			tree = makeRandomTree();
		}

	}

	private String checkRhythm(float x, float y) {
		// 리듬 체크하는 로직 추가
		if (imageList.get(0).getX() > big.getX() - 2 && imageList.get(0).getX() < big.getX() + 20) {
			if (isLeftButton != imageList.get(0).isLeft()) {
				Gdx.app.log(tag, "왼쪽이나 오른쪽이 맞지 않습니다");
				return bad;
			} else {
				Gdx.app.log(tag, perfect);
				return perfect;
			}
		} else if (imageList.get(0).getX() > big.getX() + 20 && imageList.get(0).getX() < big.getX() + 60) {
			// 현재 눌러야할 버튼이 조준점보다 크고, 그 범위가 20이내라면
			if (isLeftButton != imageList.get(0).isLeft()) {
				Gdx.app.log(tag, "왼쪽이나 오른쪽이 맞지 않습니다");
				return bad;
			} else {
				Gdx.app.log(tag, good);
				return good;
			}
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
		treeTexture = assetManager.get("texture/tree/tree100%.png");
		newTree.setTreeImage(new Image(treeTexture));
		return newTree;
	}

	private void zazinmori(float delta) {
		delayTime += delta;
		if (delayTime > zazinmoriTime[index]) {
			if (zazinmoriIsLeft[index]) {
				makeLeftObject();
			} else {
				makeRightObject();
			}
			delayTime = 0;
			index++;
			// 무한 반복
			if (index == zazinmoriTime.length) {
				index = 0;
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
				updateTreeImage(gameData.getAttack());
				gameTime = 0;
			}
		}

		moneyLabel.setText("현재 돈 " + gameData.getMoney());
		comboLabel.setText("현재 Combo " + combo);
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
