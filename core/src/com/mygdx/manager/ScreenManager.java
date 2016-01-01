package com.mygdx.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.IntMap;
import com.mygdx.service.IGoogleServices;
import com.mygdx.service.Screens;

public class ScreenManager {
	private static ScreenManager instance;
	private Game game;
	private IntMap<Screen> screens;
	private IGoogleServices actionResolver;

	private ScreenManager() {
		screens = new IntMap<Screen>();
	}

	public static ScreenManager getInstance() {
		if (null == instance) {
			instance = new ScreenManager();
		}
		return instance;
	}

	public void initialize(Game game, IGoogleServices actionResolver) {
		this.game = game;
		this.setActionResolver(actionResolver);
	}

	public void show(Screens screen) {
		if (null == game)
			return;
		if (!screens.containsKey(screen.ordinal())) {
			screens.put(screen.ordinal(), screen.getScreenInstance());
		}
		game.setScreen(screens.get(screen.ordinal()));
	}

	public void dispose(Screens screen) {
		if (!screens.containsKey(screen.ordinal()))
			return;
		screens.remove(screen.ordinal()).dispose();
	}

	public void dispose() {
		for (com.badlogic.gdx.Screen screen : screens.values()) {
			screen.dispose();
		}
		screens.clear();
		instance = null;
	}

	public IGoogleServices getActionResolver() {
		return actionResolver;
	}

	public void setActionResolver(IGoogleServices actionResolver) {
		this.actionResolver = actionResolver;
	}

}
