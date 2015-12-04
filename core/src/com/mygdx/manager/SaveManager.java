package com.mygdx.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.data.GameData;

public class SaveManager {
	private final String tag = "SaveManager";
	private static SaveManager instance;
	private GameData gameData;

	public static SaveManager getInstance() {
		if (instance == null) {
			instance = new SaveManager();
		}
		return instance;
	}

	public SaveManager() {
		gameData = GameData.getInstance();
	}

	public void save() {
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		prefs.putInteger("money", gameData.getMoney());
		prefs.putInteger("axLevel", gameData.getAxLevel());
		prefs.putInteger("gloveLevel", gameData.getGloveLevel());
		prefs.putInteger("wagonLevel", gameData.getWagonLevel());
		prefs.putInteger("maxCombo", gameData.getMaxCombo());
		prefs.putInteger("storage", gameData.getStorage());
		prefs.putBoolean("firstPlay", gameData.isFirstPlay());
		Gdx.app.log(tag, "Save완료");
	}

	public void load() {
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		gameData.setMoney(prefs.getInteger("money", 0));
		gameData.setAxLevel(prefs.getInteger("axLevel", 1));
		gameData.setGloveLevel(prefs.getInteger("gloveLevel", 1));
		gameData.setWagonLevel(prefs.getInteger("wagonLevel", 1));
		gameData.setMaxCombo(prefs.getInteger("maxCombo", 0));
		gameData.setStorage(prefs.getInteger("storage", 1000));
		gameData.setFirstPlay(prefs.getBoolean("firstPlay", false));
		Gdx.app.log(tag, "Load완료");
	}
}
