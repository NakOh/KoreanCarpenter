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
	private Table table;

	public Table makeTable(int x, int y) {
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		endingListButton = new TextButton[100];
		table = new Table();
		for (int i = 0; i < 100; i++) {
			endingListButton[i] = new TextButton("EndingList" + i, skin);
			table.add(endingListButton[i]).size(x - 5, 100f);
			table.row();
		}
		this.reset();
		this.setFillParent(true);
		this.bottom();
		// this.add(endingList1).size(x, 100f);
		scroll = new ScrollPane(table, skin);
		scroll.setSmoothScrolling(true);
		scroll.setupOverscroll(10, 30, 200);

		this.add(scroll).size(x, 350f);
		this.padBottom(100f);
		return this;
	}
}
