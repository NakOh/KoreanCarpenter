package com.mygdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Bar extends ProgressBar {
	private String name;

	private ProgressBarStyle barstyle_hp;
	private ProgressBarStyle barstyle_fever;

	private Skin skin;

	public Bar(String name, float min, float max, float stepSize, boolean vertical, Skin skin) {
		super(min, max, stepSize, vertical,
				skin.get("default-" + (vertical ? "vertical" : "horizontal"), ProgressBarStyle.class));
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		{
			Pixmap pixmap = new Pixmap(1, 22, Format.RGBA8888);
			pixmap.setColor(Color.WHITE);
			pixmap.fill();
			skin.add("WHITE", new Texture(pixmap));
		}
		{
			Pixmap pixmap = new Pixmap(1, 22, Format.RGBA8888);
			pixmap.setColor(Color.RED);
			pixmap.fill();
			skin.add("RED", new Texture(pixmap));
		}
		{
			Pixmap pixmap = new Pixmap(1, 22, Format.RGBA8888);
			pixmap.setColor(Color.GREEN);
			pixmap.fill();
			skin.add("GREEN", new Texture(pixmap));
		}

		barstyle_hp = new ProgressBarStyle(skin.getDrawable("WHITE"), skin.getDrawable("RED"));
		barstyle_fever = new ProgressBarStyle(skin.getDrawable("WHITE"), skin.getDrawable("GREEN"));
		this.name = name;
		ProgressBarStyle progressBarStyle = barsStyle(this.name);
		progressBarStyle.knobBefore = progressBarStyle.knob;
		setStyle(barsStyle(this.name));
	}

	public Bar(String name, Skin skin) {
		this(name, 0, 100, 1, false, skin);
	}

	public ProgressBarStyle barsStyle(String barStyle) {
		switch (barStyle) {
		case "hp":
			return barstyle_hp;
		case "fever":
			return barstyle_fever;
		default:
			Gdx.app.log("StatusBarUi", "barstyle type error");
			return barstyle_hp;
		}
	}
}
