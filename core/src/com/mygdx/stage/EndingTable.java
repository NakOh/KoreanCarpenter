package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class EndingTable extends Table {
	private final String tag = "ENDING_STAGE";
	private Skin skin;
	private ScrollPane scroll;
	private TextButton[] endingListButton;
	private Table inTable;

	public Table makeTable(int x, int y) {
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		endingListButton = new TextButton[100];
		inTable = new Table();
		for (int i = 0; i < 100; i++) {
			endingListButton[i] = new TextButton("EndingList" + i, skin);
			inTable.add(endingListButton[i]).size(x - 2, y / 19);
			inTable.row();
		}
		this.reset();
		this.setFillParent(true);
		this.bottom();
		scroll = new ScrollPane(inTable, skin);
		scroll.setForceScroll(false, true);
		scroll.setFlickScroll(true);
		scroll.setOverscroll(false, false);

		this.add(scroll).size(x, 6 * y / 19);
		this.padBottom(y / 19);
		return this;
	}
}
