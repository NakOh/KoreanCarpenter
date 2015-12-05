package com.mygdx.koreancarpenter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.manager.ScreenManager;
import com.mygdx.model.IGoogleServices;
import com.mygdx.service.Screens;

public class KoreanCarpenter extends Game {
	IGoogleServices actionResolver;

	public KoreanCarpenter(IGoogleServices actionResolver) {
		this.actionResolver = actionResolver;
	}

	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().show(Screens.LOAD);
	}

	@Override
	public void dispose() {
		super.dispose();
		ScreenManager.getInstance().dispose();
	}

	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
		}
		return false;
	}
}
