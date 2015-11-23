package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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

	private Texture texture1;
	private Texture texture2;
	private TextButton itemInfo1;
	private TextButton itemInfo2;
	private Image itemImage1;
	private Image itemImage2;

	public ItemTable() {
		assetManager = AssetManager.getInstance();
		backgroundTexture = assetManager.get("texture/white.png");
	}

	public Table makeTable(int x, int y) {
		this.reset();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		background = new Image(backgroundTexture).getDrawable();
		texture1 = assetManager.get("texture/item1.png");
		itemImage1 = new Image(texture1);
		texture2 = assetManager.get("texture/item2.png");
		itemImage2 = new Image(texture2);
		itemInfo1 = new TextButton("아이템 설명1", skin);
		itemInfo2 = new TextButton("아이템 설명2", skin);
		this.setFillParent(true);
		this.bottom();
		this.add(itemImage1).size(x / 2, 100f);
		this.add(itemImage2).size(x / 2, 100f);
		this.row();
		this.add(itemInfo1).size(x / 2, 100f);
		this.add(itemInfo2).size(x / 2, 100f);
		this.padBottom(150f);
		return this;
	}
}
