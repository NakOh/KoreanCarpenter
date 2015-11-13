package com.mygdx.service;

import com.badlogic.gdx.Screen;
import com.mygdx.screen.GameScreen;
import com.mygdx.screen.LoadingScreen;
import com.mygdx.screen.MainScreen;

public enum Screens {
	MAIN {
		@Override
		public Screen getScreenInstance() {
			return new MainScreen();
		}
	},
	GAME {
		@Override
		public Screen getScreenInstance() {
			return new GameScreen();
		}
	},
	LOAD {
		@Override
		public Screen getScreenInstance() {
			return new LoadingScreen();
		}
	};

	public abstract Screen getScreenInstance();
}
