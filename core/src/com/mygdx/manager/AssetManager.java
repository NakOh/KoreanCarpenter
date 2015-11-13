package com.mygdx.manager;

public class AssetManager {
	private static com.badlogic.gdx.assets.AssetManager instance;

	public static com.badlogic.gdx.assets.AssetManager getInstance() {
		if (null == instance) {
			instance = new com.badlogic.gdx.assets.AssetManager();
		}
		return instance;
	}

}
