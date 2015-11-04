package com.mygdx.koreancarpenter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.screen.LoadingScreen;

public class KoreanCarpenter extends Game {
	@Override
	public void create() {
		setScreen(new LoadingScreen());
	}

	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
		}
		return false;
	}
}
