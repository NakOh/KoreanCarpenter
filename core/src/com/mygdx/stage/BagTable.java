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
import com.mygdx.data.GameData;
import com.mygdx.manager.AssetManager;

public class BagTable extends Table {
	private final String tag = "BAG_STAGE";
	private com.badlogic.gdx.assets.AssetManager assetManager;
	private Skin skin;

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

	public BagTable() {
		gameData = GameData.getInstance();
		assetManager = AssetManager.getInstance();
	}

	public Table makeTable(int x, int y) {
		getTexture();
		this.reset();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		axLabel = new Label("돌도끼 LV" + gameData.getAxLevel() + "\n" + gameData.getAxMoneyTable()[gameData.getAxLevel()],
				skin);
		gloveLabel = new Label(
				"맨 손 LV" + gameData.getGloveLevel() + "\n" + gameData.getAxMoneyTable()[gameData.getGloveLevel()],
				skin);
		wagonLabel = new Label(
				"손수레 LV" + gameData.getWagonLevel() + "\n" + gameData.getWagonMoneyTable()[gameData.getWagonLevel()],
				skin);

		axUpgradeButton = new TextButton("도끼버튼", skin);
		gloveUpgradeButton = new TextButton("장갑버튼", skin);
		wagonUpgradeButton = new TextButton("수레버튼", skin);

		addListener();
		this.setFillParent(true);
		this.bottom();
		this.add(new Image(axImage)).size(x / 4, 100f);
		this.add(axLabel).center().size((x / 4) * 2, 100f);
		this.add(axUpgradeButton).size(x / 4, 100f);
		this.row();
		this.add(new Image(gloveImage)).left().size(x / 4, 100f);
		this.add(gloveLabel).center().size((x / 4) * 2, 100f);
		this.add(gloveUpgradeButton).size(x / 4, 100f);
		this.row();
		this.add(new Image(wagonImage)).left().size(x / 4, 100f);
		this.add(wagonLabel).center().size((x / 4) * 2, 100f);
		this.add(wagonUpgradeButton).size(x / 4, 100f);
		this.padBottom(100f);
		return this;
	}

	private void getTexture() {
		axImage = assetManager.get("texture/ax.png");
		gloveImage = assetManager.get("texture/glove.png");
		wagonImage = assetManager.get("texture/wagon.png");
	}

	private void addListener() {
		axUpgradeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// 현재 레벨 업그레이드에 필요한 돈이 현재 가진 돈 보다 작을 때 업그레이드 가능
				if (gameData.getAxLevel() < gameData.getMAX_LEVEL()) {
					if (gameData.getAxMoneyTable()[gameData.getAxLevel()] <= gameData.getMoney()) {
						gameData.setMoney(gameData.getMoney() - gameData.getAxMoneyTable()[gameData.getAxLevel()]);
						gameData.setAxLevel(gameData.getAxLevel() + 1);
						axLabel.setText("돌도끼 LV" + gameData.getAxLevel() + "\n"
								+ gameData.getAxMoneyTable()[gameData.getAxLevel()]);
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
					if (gameData.getGloveMoneyTable()[gameData.getGloveLevel()] <= gameData.getMoney()) {
						gameData.setMoney(
								gameData.getMoney() - gameData.getGloveMoneyTable()[gameData.getGloveLevel()]);
						gameData.setGloveLevel(gameData.getGloveLevel() + 1);
						gloveLabel.setText("맨 손 LV" + gameData.getGloveLevel() + "\n"
								+ gameData.getAxMoneyTable()[gameData.getGloveLevel()]);
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
					if (gameData.getWagonMoneyTable()[gameData.getWagonLevel()] <= gameData.getMoney()) {
						gameData.setMoney(
								gameData.getMoney() - gameData.getWagonMoneyTable()[gameData.getWagonLevel()]);
						gameData.setWagonLevel(gameData.getWagonLevel() + 1);
						wagonLabel.setText("손수레 LV" + gameData.getWagonLevel() + "\n"
								+ gameData.getWagonMoneyTable()[gameData.getWagonLevel()]);
					}
				} else {
					Gdx.app.log(tag, "업데이트 최대");
				}
			}
		});
	}

}
