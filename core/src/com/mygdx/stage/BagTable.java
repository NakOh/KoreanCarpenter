package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
	private Texture axTexture;
	private Texture gloveTexture;
	private Texture wagonTexture;

	private Image axImage;
	private Image gloveImage;
	private Image wagonImage;

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
				+ "돈 " + gameData.getAxMoney(), skin);
		gloveLabel = new Label(gameData.getGloveName()[gameData.getGloveNameIndex()] + " LV" + gameData.getGloveLevel()
				+ "\n" + "돈 " + gameData.getGloveMoney(), skin);
		wagonLabel = new Label(gameData.getWagonName()[gameData.getWagonNameIndex()] + " LV" + gameData.getWagonLevel()
				+ "\n" + "돈 " + gameData.getWagonMoney(), skin);

		axLabel.setAlignment(Align.center);
		gloveLabel.setAlignment(Align.center);
		wagonLabel.setAlignment(Align.center);
		axUpgradeButton = new TextButton("레벨 업 \n 데미지" + formulaManager.axUpgradeFormula(gameData.getAxLevel()), skin);
		gloveUpgradeButton = new TextButton("레벨 업 \n 적중률" + (gameData.getAccuracy() + 1), skin);
		wagonUpgradeButton = new TextButton("레벨 업 \n 저장량" + (gameData.getStorage() + 1000), skin);

		axImage = new Image(axTexture);
		gloveImage = new Image(gloveTexture);
		wagonImage = new Image(wagonTexture);

		addListener();
		this.setFillParent(true);
		this.bottom();
		this.add(axImage).size(x * 0.2f, y * 0.1f);
		this.add(axLabel).center().size(x * 0.466f, y * 0.1f);
		this.add(axUpgradeButton).size(x * 0.334f, y * 0.1f);
		this.row();
		this.add(gloveImage).left().size(x * 0.2f, y * 0.1f);
		this.add(gloveLabel).center().size(x / 4, 2 * y / 19);
		this.add(gloveUpgradeButton).size(x * 0.334f, y * 0.1f);
		this.row();
		this.add(wagonImage).left().size(x * 0.2f, y * 0.1f);
		this.add(wagonLabel).center().size(x / 4, 2 * y / 19);
		this.add(wagonUpgradeButton).size(x * 0.334f, y * 0.1f);
		this.padBottom(y * (0.005f + 0.09005f));
		return this;
	}

	private void getTexture() {
		axTexture = assetManager.get("texture/ax/ax.png");
		gloveTexture = assetManager.get("texture/glove/glove.png");
		wagonTexture = assetManager.get("texture/wagon/wagon.png");
	}

	private void checkJewelry(int index, int jewelry) {
		gameData.setJewelry(gameData.getJewelry() - jewelry);
		if (index == 0) {
			gameData.setAxLevel(gameData.getAxLevel() + 1);
			gameData.setAxMoney(formulaManager.axMoneyFormula(gameData.getAxLevel()));
			gameData.setAccuracy(gameData.getAccuracy() + 1);
			axLabel.setText(gameData.getAxName()[gameData.getAxNameIndex()] + " LV" + gameData.getAxLevel() + "\n" + "돈"
					+ gameData.getAxMoney());
			axUpgradeButton.setText("레벨 업 \n 데미지" + formulaManager.axUpgradeFormula(gameData.getAxLevel()));
			// 이미지 바꿔 끼기
			if (gameData.getAxLevel() == 10) {
				axTexture = assetManager.get("texture/ax/ax_01.png");
				axImage.setDrawable(new SpriteDrawable(new Sprite(axTexture)));
			} else if (gameData.getAxLevel() == 20) {
				axTexture = assetManager.get("texture/ax/ax_02.png");
				axImage.setDrawable(new SpriteDrawable(new Sprite(axTexture)));
			} else if (gameData.getAxLevel() == 30) {
				axTexture = assetManager.get("texture/ax/ax_03.png");
				axImage.setDrawable(new SpriteDrawable(new Sprite(axTexture)));
			} else if (gameData.getAxLevel() == 40) {
				axTexture = assetManager.get("texture/ax/ax_04.png");
				axImage.setDrawable(new SpriteDrawable(new Sprite(axTexture)));
			} else if (gameData.getAxLevel() == 50) {
				axTexture = assetManager.get("texture/ax/ax_05.png");
				axImage.setDrawable(new SpriteDrawable(new Sprite(axTexture)));
			}
		} else if (index == 1) {
			gameData.setGloveLevel(gameData.getGloveLevel() + 1);
			gameData.setAccuracy(gameData.getAccuracy() + 1);
			gameData.setGloveMoney(formulaManager.gloveMoneyFormula(gameData.getGloveLevel()));
			gloveLabel.setText(gameData.getGloveName()[gameData.getGloveNameIndex()] + " LV" + gameData.getGloveLevel()
					+ "\n" + gameData.getGloveMoney());
			gloveUpgradeButton.setText("레벨 업 \n 적중률" + (gameData.getAccuracy() + 1));
			if (gameData.getGloveLevel() == 10) {
				gloveTexture = assetManager.get("texture/glove/glove_01.png");
				gloveImage.setDrawable(new SpriteDrawable(new Sprite(gloveTexture)));
			} else if (gameData.getGloveLevel() == 20) {
				gloveTexture = assetManager.get("texture/glove/glove_02.png");
				gloveImage.setDrawable(new SpriteDrawable(new Sprite(gloveTexture)));
			} else if (gameData.getGloveLevel() == 30) {
				gloveTexture = assetManager.get("texture/glove/glove_03.png");
				gloveImage.setDrawable(new SpriteDrawable(new Sprite(gloveTexture)));
			} else if (gameData.getGloveLevel() == 40) {
				gloveTexture = assetManager.get("texture/glove/glove_04.png");
				gloveImage.setDrawable(new SpriteDrawable(new Sprite(gloveTexture)));
			} else if (gameData.getGloveLevel() == 50) {
				gloveTexture = assetManager.get("texture/glove/glove_05.png");
				gloveImage.setDrawable(new SpriteDrawable(new Sprite(gloveTexture)));
			}
		} else if (index == 2) {
			gameData.setWagonLevel(gameData.getWagonLevel() + 1);
			gameData.setStorage(gameData.getStorage() + 1000);
			gameData.setWagonMoney(formulaManager.wagonMoneyFormula(gameData.getWagonLevel()));
			wagonLabel.setText(gameData.getWagonName()[gameData.getWagonNameIndex()] + " LV" + gameData.getWagonLevel()
					+ "\n" + gameData.getWagonMoney());
			wagonUpgradeButton.setText("레벨 업 \n 저장량" + (gameData.getStorage() + 1000));
			if (gameData.getWagonLevel() == 10) {
				wagonTexture = assetManager.get("texture/wagon/wagon_01.png");
				wagonImage.setDrawable(new SpriteDrawable(new Sprite(wagonTexture)));
			} else if (gameData.getWagonLevel() == 20) {
				wagonTexture = assetManager.get("texture/wagon/wagon_02.png");
				wagonImage.setDrawable(new SpriteDrawable(new Sprite(wagonTexture)));
			} else if (gameData.getWagonLevel() == 30) {
				wagonTexture = assetManager.get("texture/wagon/wagon_03.png");
				wagonImage.setDrawable(new SpriteDrawable(new Sprite(wagonTexture)));
			} else if (gameData.getWagonLevel() == 40) {
				wagonTexture = assetManager.get("texture/wagon/wagon_04.png");
				wagonImage.setDrawable(new SpriteDrawable(new Sprite(wagonTexture)));
			} else if (gameData.getWagonLevel() == 50) {
				wagonTexture = assetManager.get("texture/wagon/wagon_05.png");
				wagonImage.setDrawable(new SpriteDrawable(new Sprite(wagonTexture)));
			}
		}
	}

	private void checkMoney(int index) {
		switch (index) {
		case 0:
			gameData.setMoney(gameData.getMoney() - gameData.getAxMoney());
			gameData.setAxLevel(gameData.getAxLevel() + 1);
			gameData.setAxMoney(formulaManager.axMoneyFormula(gameData.getAxLevel()));
			gameData.setAttack(formulaManager.axUpgradeFormula(gameData.getAxLevel()));

			// 10단위 일때는 돈이 아니라 보석으로 표시하도록 수정
			if (gameData.getAxLevel() == 9) {
				axLabel.setText(gameData.getAxName()[gameData.getAxNameIndex()] + " LV" + gameData.getAxLevel() + "\n"
						+ "보석 100개");
			} else if (gameData.getAxLevel() == 19) {
				axLabel.setText(gameData.getAxName()[gameData.getAxNameIndex()] + " LV" + gameData.getAxLevel() + "\n"
						+ "보석 150개");
			} else if (gameData.getAxLevel() == 29) {
				axLabel.setText(gameData.getAxName()[gameData.getAxNameIndex()] + " LV" + gameData.getAxLevel() + "\n"
						+ "보석 200개");
			} else if (gameData.getAxLevel() == 39) {
				axLabel.setText(gameData.getAxName()[gameData.getAxNameIndex()] + " LV" + gameData.getAxLevel() + "\n"
						+ "보석 250개");
			} else if (gameData.getAxLevel() == 49) {
				axLabel.setText(gameData.getAxName()[gameData.getAxNameIndex()] + " LV" + gameData.getAxLevel() + "\n"
						+ "보석 300개");
			} else {
				axLabel.setText(gameData.getAxName()[gameData.getAxNameIndex()] + " LV" + gameData.getAxLevel() + "\n"
						+ "돈 " + gameData.getAxMoney());
			}

			axUpgradeButton.setText("레벨 업 \n 데미지" + formulaManager.axUpgradeFormula(gameData.getAxLevel()));

			break;
		case 1:
			gameData.setMoney(gameData.getMoney() - gameData.getGloveMoney());
			gameData.setGloveLevel(gameData.getGloveLevel() + 1);
			gameData.setAccuracy(gameData.getAccuracy() + 1);
			gameData.setGloveMoney(formulaManager.gloveMoneyFormula(gameData.getGloveLevel()));
			if (gameData.getGloveLevel() == 9) {
				gloveLabel.setText(gameData.getGloveName()[gameData.getGloveNameIndex()] + " LV"
						+ gameData.getGloveLevel() + "\n" + "보석 " + "100개");
			} else if (gameData.getGloveLevel() == 19) {
				gloveLabel.setText(gameData.getGloveName()[gameData.getGloveNameIndex()] + " LV"
						+ gameData.getGloveLevel() + "\n" + "보석 " + "150개");
			} else if (gameData.getGloveLevel() == 29) {
				gloveLabel.setText(gameData.getGloveName()[gameData.getGloveNameIndex()] + " LV"
						+ gameData.getGloveLevel() + "\n" + "보석 " + "200개");
			} else if (gameData.getGloveLevel() == 39) {
				gloveLabel.setText(gameData.getGloveName()[gameData.getGloveNameIndex()] + " LV"
						+ gameData.getGloveLevel() + "\n" + "보석 " + "250개");
			} else if (gameData.getGloveLevel() == 49) {
				gloveLabel.setText(gameData.getGloveName()[gameData.getGloveNameIndex()] + " LV"
						+ gameData.getGloveLevel() + "\n" + "보석 " + "300개");
			} else {
				gloveLabel.setText(gameData.getGloveName()[gameData.getGloveNameIndex()] + " LV"
						+ gameData.getGloveLevel() + "\n" + "돈 " + gameData.getGloveMoney());
			}
			gloveUpgradeButton.setText("레벨 업 \n 적중률" + (gameData.getAccuracy() + 1));
			break;
		case 2:
			gameData.setMoney(gameData.getMoney() - gameData.getWagonMoney());
			gameData.setWagonLevel(gameData.getWagonLevel() + 1);
			gameData.setStorage(gameData.getStorage() + 1000);
			gameData.setWagonMoney(formulaManager.wagonMoneyFormula(gameData.getWagonLevel()));
			if (gameData.getWagonLevel() == 9) {
				wagonLabel.setText(gameData.getWagonName()[gameData.getWagonNameIndex()] + " LV"
						+ gameData.getWagonLevel() + "\n" + "보석 100개");
			} else if (gameData.getWagonLevel() == 19) {
				wagonLabel.setText(gameData.getWagonName()[gameData.getWagonNameIndex()] + " LV"
						+ gameData.getWagonLevel() + "\n" + "보석 150개");
			} else if (gameData.getWagonLevel() == 29) {
				wagonLabel.setText(gameData.getWagonName()[gameData.getWagonNameIndex()] + " LV"
						+ gameData.getWagonLevel() + "\n" + "보석 200개");
			} else if (gameData.getWagonLevel() == 39) {
				wagonLabel.setText(gameData.getWagonName()[gameData.getWagonNameIndex()] + " LV"
						+ gameData.getWagonLevel() + "\n" + "보석 250개");
			} else if (gameData.getWagonLevel() == 49) {
				wagonLabel.setText(gameData.getWagonName()[gameData.getWagonNameIndex()] + " LV"
						+ gameData.getWagonLevel() + "\n" + "보석 300개");
			} else {
				wagonLabel.setText(gameData.getWagonName()[gameData.getWagonNameIndex()] + " LV"
						+ gameData.getWagonLevel() + "\n" + "돈 " + gameData.getWagonMoney());
			}

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
					// 보석이 필요할때와 아닐 때를 구분하자. 9, 19, 29, 39, 49 일때는 보석으로 결제하도록!
					if (gameData.getAxLevel() == 9 || gameData.getAxLevel() == 19 || gameData.getAxLevel() == 29
							|| gameData.getAxLevel() == 39 || gameData.getAxLevel() == 49) {
						if (gameData.getAxLevel() == 9) {
							if (gameData.getJewelry() >= 100) {
								checkJewelry(0, 100);
							}
						} else if (gameData.getAxLevel() == 19) {
							if (gameData.getJewelry() >= 150) {
								checkJewelry(0, 150);
							}
						} else if (gameData.getAxLevel() == 29) {
							if (gameData.getJewelry() >= 200) {
								checkJewelry(0, 200);
							}
						} else if (gameData.getAxLevel() == 39) {
							if (gameData.getJewelry() >= 250) {
								checkJewelry(0, 250);
							}
						} else if (gameData.getAxLevel() == 49) {
							if (gameData.getJewelry() >= 300) {
								checkJewelry(0, 300);
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
					// 보석이 필요할때와 아닐 때를 구분하자. 9, 19, 29, 39, 49 일때는 보석으로 결제하도록!
					if (gameData.getGloveLevel() == 9 || gameData.getGloveLevel() == 19
							|| gameData.getGloveLevel() == 29 || gameData.getGloveLevel() == 39
							|| gameData.getGloveLevel() == 49) {
						if (gameData.getGloveLevel() == 9) {
							if (gameData.getJewelry() >= 100) {
								checkJewelry(1, 100);
							}
						} else if (gameData.getGloveLevel() == 19) {
							if (gameData.getJewelry() >= 150) {
								checkJewelry(1, 150);
							}
						} else if (gameData.getGloveLevel() == 29) {
							if (gameData.getJewelry() >= 200) {
								checkJewelry(1, 200);
							}
						} else if (gameData.getGloveLevel() == 39) {
							if (gameData.getJewelry() >= 250) {
								checkJewelry(1, 250);
							}
						} else if (gameData.getGloveLevel() == 49) {
							if (gameData.getJewelry() >= 300) {
								checkJewelry(1, 300);
							}
						}
					} else if (gameData.getGloveMoney() <= gameData.getMoney()) {
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
					// 보석이 필요할때와 아닐 때를 구분하자. 9, 19, 29, 39, 49 일때는 보석으로 결제하도록!
					if (gameData.getWagonLevel() == 9 || gameData.getWagonLevel() == 19
							|| gameData.getWagonLevel() == 29 || gameData.getWagonLevel() == 39
							|| gameData.getWagonLevel() == 49) {
						if (gameData.getWagonLevel() == 9) {
							if (gameData.getJewelry() >= 100) {
								checkJewelry(1, 100);
							}
						} else if (gameData.getWagonLevel() == 19) {
							if (gameData.getJewelry() >= 150) {
								checkJewelry(1, 150);
							}
						} else if (gameData.getWagonLevel() == 29) {
							if (gameData.getJewelry() >= 200) {
								checkJewelry(1, 200);
							}
						} else if (gameData.getWagonLevel() == 39) {
							if (gameData.getJewelry() >= 250) {
								checkJewelry(1, 250);
							}
						} else if (gameData.getWagonLevel() == 49) {
							if (gameData.getJewelry() >= 300) {
								checkJewelry(1, 300);
							}
						}
					} else if (gameData.getWagonMoney() <= gameData.getMoney()) {
						checkMoney(2);
					}
				} else {
					Gdx.app.log(tag, "업데이트 최대");
				}
			}
		});
	}

}
