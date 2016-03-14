package com.mygdx.popup;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.manager.ScreenManager;

public class ShopPopup extends Dialog {
	private final String tag = "SHOP_POPUP";

	private ScrollPane scroll;
	private Skin skin;
	private Table table;
	private Table leftTable;
	private Table rightTable;

	private TextButton[] buyButton;
	private Label[] buyLabel;

	private TextButton closeButton;

	public ShopPopup(String title, Skin skin) {
		super(title, skin);
		this.skin = skin;
		initialize();
	}

	private void initialize() {
		makeLeftTable();
		makeRightTable();
		addListener();

		table = new Table();
		table.add(leftTable);
		table.add(rightTable);

		scroll = new ScrollPane(table, skin);
		scroll.setForceScroll(false, true);
		scroll.setFlickScroll(true);
		scroll.setOverscroll(false, false);

		getContentTable().add(scroll).size(300, 200);

		closeButton = new TextButton("닫기", skin);

		closeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				setVisible(false);
				clear();
			}
		});

		getButtonTable().add(closeButton);

	}

	private void makeLeftTable() {
		leftTable = new Table();
		buyLabel = new Label[100];
		for (int i = 0; i < 100; i++) {
			buyLabel[i] = new Label("보석 ", skin);
			leftTable.add(buyLabel[i]);
			leftTable.row();
		}
	}

	private void makeRightTable() {
		rightTable = new Table();
		buyButton = new TextButton[100];
		for (int i = 0; i < 100; i++) {
			buyButton[i] = new TextButton("구매", skin);
			rightTable.add(buyButton[i]);
			rightTable.row();
		}
	}

	private void addListener() {

		for (int i = 0; i < 100; i++) {
			buyButton[i].addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					ScreenManager.getInstance().getLabServices().buyJewelry();
				}
			});

		}
	}
}