package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.manager.AssetManager;

public class ItemTable extends Table {
	private final String tag = "ITEM_STAGE";
	private com.badlogic.gdx.assets.AssetManager assetManager;

	private Skin skin;

	private Texture backgroundTexture;
	private Drawable background;
	private static Table instance;

	private Texture texture1;
	private Texture texture2;
	private Label itemInfo1;
	private Label itemInfo2;
	private Image itemImage1;
	private Image itemImage2;
	private TextButton itemBuyButton1;
	private TextButton itemBuyButton2;

	public ItemTable() {
		assetManager = AssetManager.getInstance();
		backgroundTexture = assetManager.get("texture/white.png");

	}

	public static Table getInstance(int x, int y) {
		if (instance == null) {
			instance = new ItemTable().makeTable(x, y);
		}
		return instance;
	}

	public Table makeTable(int x, int y) {
		this.reset();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		background = new Image(backgroundTexture).getDrawable();
		texture1 = assetManager.get("texture/item1.png");
		itemImage1 = new Image(texture1);
		texture2 = assetManager.get("texture/item2.png");
		itemImage2 = new Image(texture2);
		itemInfo1 = new Label("아드레날린 \n 다음 피버 타임에서 일시적으로 공격력이 300% 증가", skin);
		itemInfo2 = new Label("바르카스 \n 즉시 피버타임 돌입", skin);
		itemInfo1.setFontScale(0.5f);
		itemInfo2.setFontScale(0.5f);
		itemInfo1.setWrap(true);
		itemInfo2.setWrap(true);
		itemBuyButton1 = new TextButton("10 보석", skin);
		itemBuyButton2 = new TextButton("10 보석", skin);
		this.setFillParent(true);
		this.bottom();
		this.add(itemImage1).size(x / 2, y * 0.08f);
		this.add(itemImage2).size(x / 2, y * 0.08f);
		this.row();
		this.add(itemInfo1).size(x / 2, y * 0.17f);
		this.add(itemInfo2).size(x / 2, y * 0.17f);
		this.row();
		this.add(itemBuyButton1).size(x / 2, y * 0.05f);
		this.add(itemBuyButton2).size(x / 2, y * 0.05f);
		this.padBottom(y * (0.09005f + 0.005f));
		addListener();
		return this;
	}

	private void addListener() {

	}
}
