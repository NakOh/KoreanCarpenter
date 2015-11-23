package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.manager.AssetManager;

public class BagTable extends Table {
	private final String tag = "BAG_STAGE";
	private com.badlogic.gdx.assets.AssetManager assetManager;
	private TextButton axButton;
	private TextButton gloveButton;
	private TextButton wagonButton;
	private Skin skin;
	// Image
	private Texture axImage;
	private Texture gloveImage;
	private Texture wagonImage;

	public BagTable() {
		assetManager = AssetManager.getInstance();
	}

	public Table makeTable(int x, int y) {
		getTexture();
		this.reset();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		axButton = new TextButton("도끼버튼", skin);
		gloveButton = new TextButton("장갑버튼", skin);
		wagonButton = new TextButton("수레버튼", skin);

		this.setFillParent(true);
		this.bottom();
		this.add(new Image(axImage)).size(x / 4, 100f);
		this.add(axButton).size(3 * x / 4, 100f);
		this.row();
		this.add(new Image(gloveImage)).size(x / 4, 100f);
		this.add(gloveButton).size(3 * x / 4, 100f);
		this.row();
		this.add(new Image(wagonImage)).size(x / 4, 100f);
		this.add(wagonButton).size(3 * x / 4, 100f);
		this.padBottom(100f);
		return this;
	}

	private void getTexture() {
		axImage = assetManager.get("texture/ax.png");
		gloveImage = assetManager.get("texture/glove.png");
		wagonImage = assetManager.get("texture/wagon.png");
	}

	private void addListener() {

	}

}
