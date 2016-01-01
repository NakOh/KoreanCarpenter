package com.mygdx.koreancarpenter;

import com.badlogic.gdx.Game;
import com.mygdx.manager.ScreenManager;
import com.mygdx.service.IGoogleServices;
import com.mygdx.service.Screens;

public class KoreanCarpenter extends Game {
	private IGoogleServices actionResolver;

	public KoreanCarpenter(IGoogleServices actionResolver) {
		this.actionResolver = actionResolver;
	}

	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this, this.actionResolver);
		ScreenManager.getInstance().show(Screens.LOAD);
	}

	@Override
	public void dispose() {
		super.dispose();
		ScreenManager.getInstance().dispose();
	}

}
