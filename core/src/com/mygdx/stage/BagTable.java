package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class BagTable extends Table {
	private final String tag = "BAG_STAGE";
	private TextButton axButton;
	private TextButton gloveButton;
	private TextButton wagonButton;
	private Skin skin;

	public Table makeTable(int x, int y) {
		this.reset();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		axButton = new TextButton("도끼버튼", skin);
		gloveButton = new TextButton("장갑버튼", skin);
		wagonButton = new TextButton("수레버튼", skin);
		this.setFillParent(true);
		this.bottom();
		this.add(axButton).size(x / 6, 100f);
		this.row();
		this.add(gloveButton).size(x / 6, 100f);
		this.row();
		this.add(wagonButton).size(x / 6, 100f);
		this.padBottom(100f);
		return this;
	}

}
