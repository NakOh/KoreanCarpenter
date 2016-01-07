package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.data.GameData;
import com.mygdx.manager.AssetManager;
import com.mygdx.manager.FormulaManager;

public class BagTable extends Table {
	private final String tag = "BAG_STAGE";

	private com.badlogic.gdx.assets.AssetManager assetManager;
	private Skin skin;
	private static Table instance;

	// Label
	private Label axLabel;
	private Label gloveLabel;
	private Label wagonLabel;

	// UpgradeButton;
	private TextButton axUpgradeButton;
	private TextButton gloveUpgradeButton;
	private TextButton wagonUpgradeButton;

	// Image
	private Texture axImage;
	private Texture gloveImage;
	private Texture wagonImage;

	private GameData gameData;
	private FormulaManager formulaManager;

	public BagTable() {
		gameData = GameData.getInstance();
		formulaManager = new FormulaManager();
		assetManager = AssetManager.getInstance();
	}

	public static Table getInstance(int x, int y) {
		if (instance == null) {
			instance = new BagTable().makeTable(x, y);
		}
		return instance;
	}

	public Table makeTable(int x, int y) {
		getTexture();
		this.reset();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		// 공식을 거쳐 각각의 업그레이드 비용을 측정하자.
		gameData.setAxMoney(formulaManager.axMoneyFormula(gameData.getAxLevel()));
		gameData.setGloveMoney(formulaManager.gloveMoneyFormula(gameData.getGloveLevel()));
		gameData.setWagonMoney(formulaManager.wagonMoneyFormula(gameData.getWagonLevel()));
		gameData.setAttack(formulaManager.axUpgradeFormula(gameData.getAxLevel() - 1));

		axLabel = new Label(gameData.getAxName()[gameData.getAxNameIndex()] + " LV" + gameData.getAxLevel() + "\n"
				+ gameData.getAxMoney(), skin);
		gloveLabel = new Label(gameData.getGloveName()[gameData.getGloveNameIndex()] + " LV" + gameData.getGloveLevel()
				+ "\n" + gameData.getGloveMoney(), skin);
		wagonLabel = new Label(gameData.getWagonName()[gameData.getWagonNameIndex()] + " LV" + gameData.getWagonLevel()
				+ "\n" + gameData.getWagonMoney(), skin);

		axLabel.setAlignment(Align.center);
		gloveLabel.setAlignment(Align.center);
		wagonLabel.setAlignment(Align.center);
		axUpgradeButton = new TextButton("레벨 업 \n 데미지" + formulaManager.axUpgradeFormula(gameData.getAxLevel()), skin);
		gloveUpgradeButton = new TextButton("레벨 업 \n 적중률" + (gameData.getAccuracy() + 1), skin);
		wagonUpgradeButton = new TextButton("레벨 업 \n 저장량" + (gameData.getStorage() + 1000), skin);

		addListener();
		this.setFillParent(true);
		this.bottom();
		this.add(new Image(axImage)).size(x * 0.2f, y * 0.1f);
		this.add(axLabel).center().size(x * 0.466f, y * 0.1f);
		this.add(axUpgradeButton).size(x * 0.334f, y * 0.1f);
		this.row();
		this.add(new Image(gloveImage)).left().size(x * 0.2f, y * 0.1f);
		this.add(gloveLabel).center().size(x / 4, 2 * y / 19);
		this.add(gloveUpgradeButton).size(x * 0.334f, y * 0.1f);
		this.row();
		this.add(new Image(wagonImage)).left().size(x * 0.2f, y * 0.1f);
		this.add(wagonLabel).center().size(x / 4, 2 * y / 19);
		this.add(wagonUpgradeButton).size(x * 0.334f, y * 0.1f);
		this.padBottom(y * (0.005f + 0.09005f));
		return this;
	}

	private void getTexture() {
		axImage = assetManager.get("texture/ax/ax.png");
		gloveImage = assetManager.get("texture/glove/glove.png");
		wagonImage = assetManager.get("texture/wagon/wagon.png");
	}

	private void checkJewelry(int jewelry) {
		gameData.setJewelry(gameData.getJewelry() - jewelry);
		gameData.setAxLevel(gameData.getAxLevel() + 1);
		gameData.setAxMoney(formulaManager.axMoneyFormula(gameData.getAxLevel()));
		gameData.setAttack(formulaManager.axUpgradeFormula(gameData.getAxLevel()));
		axLabel.setText(gameData.getAxName()[gameData.getAxNameIndex()] + " LV" + gameData.getAxLevel() + "\n"
				+ gameData.getAxMoney());
		axUpgradeButton.setText("레벨 업 \n 데미지" + formulaManager.axUpgradeFormula(gameData.getAxLevel()));
	}

	private void checkMoney(int index) {
		switch (index) {
		case 0:
			gameData.setMoney(gameData.getMoney() - gameData.getAxMoney());
			gameData.setAxLevel(gameData.getAxLevel() + 1);
			gameData.setAxMoney(formulaManager.axMoneyFormula(gameData.getAxLevel()));
			gameData.setAttack(formulaManager.axUpgradeFormula(gameData.getAxLevel()));
			// 10단위 일때는 돈이 아니라 보석으로 표시하도록 수정
			axLabel.setText(gameData.getAxName()[gameData.getAxNameIndex()] + " LV" + gameData.getAxLevel() + "\n"
					+ gameData.getAxMoney());
			axUpgradeButton.setText("레벨 업 \n 데미지" + formulaManager.axUpgradeFormula(gameData.getAxLevel()));
			break;
		case 1:
			gameData.setMoney(gameData.getMoney() - gameData.getGloveMoney());
			gameData.setGloveLevel(gameData.getGloveLevel() + 1);
			gameData.setAccuracy(gameData.getAccuracy() + 1);
			gameData.setGloveMoney(formulaManager.gloveMoneyFormula(gameData.getGloveLevel()));
			gloveLabel.setText(gameData.getGloveName()[gameData.getGloveNameIndex()] + " LV" + gameData.getGloveLevel()
					+ "\n" + gameData.getGloveMoney());
			gloveUpgradeButton.setText("레벨 업 \n 적중률" + (gameData.getAccuracy() + 1));
			break;
		case 2:
			gameData.setMoney(gameData.getMoney() - gameData.getWagonMoney());
			gameData.setWagonLevel(gameData.getWagonLevel() + 1);
			gameData.setStorage(gameData.getStorage() + 1000);
			gameData.setWagonMoney(formulaManager.wagonMoneyFormula(gameData.getWagonLevel()));
			wagonLabel.setText(gameData.getWagonName()[gameData.getWagonNameIndex()] + " LV" + gameData.getWagonLevel()
					+ "\n" + gameData.getWagonMoney());
			wagonUpgradeButton.setText("레벨 업 \n 저장량" + (gameData.getStorage() + 1000));
			break;
		default:
			break;
		}

	}

	private void addListener() {
		axUpgradeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// 현재 레벨 업그레이드에 필요한 돈이 현재 가진 돈 보다 작을 때 업그레이드 가능
				if (gameData.getAxLevel() < gameData.getMAX_LEVEL()) {
					// 보석이 필요할때와 아닐 때를 구분하자.
					if (gameData.getAxLevel() % 10 == 0) {
						// 10으로 나누어 떨어질 때는 보석으로 업글
						if (gameData.getAxLevel() == 10) {
							if (gameData.getJewelry() >= 100) {
								checkJewelry(100);
							}
						} else if (gameData.getAxLevel() == 20) {
							if (gameData.getJewelry() >= 150) {
								checkJewelry(150);
							}
						}
					} else if (gameData.getAxMoney() <= gameData.getMoney()) {
						checkMoney(0);
					}
				} else {
					Gdx.app.log(tag, "업데이트 최대");
				}
			}
		});
		gloveUpgradeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (gameData.getGloveLevel() < gameData.getMAX_LEVEL()) {
					if (gameData.getGloveMoney() <= gameData.getMoney()) {
						checkMoney(1);
					}
				} else {
					Gdx.app.log(tag, "업데이트 최대");
				}
			}
		});
		wagonUpgradeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (gameData.getWagonLevel() < gameData.getMAX_LEVEL()) {
					if (gameData.getWagonMoney() <= gameData.getMoney()) {
						checkMoney(2);
					}
				} else {
					Gdx.app.log(tag, "업데이트 최대");
				}
			}
		});
	}

}
