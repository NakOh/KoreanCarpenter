package com.mygdx.manager;

public class AssetManager {
	private static AssetManager instance = new AssetManager();

	public static AssetManager getInstance() {
		return instance;
	}

	public boolean load() {
		return true;
	}

	public boolean unload() {
		return true;
	}
}
