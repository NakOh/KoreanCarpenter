package com.mygdx.manager;

public class AssetManager {
	private AssetManager instance = new AssetManager();

	public AssetManager() {
	}

	public AssetManager getInstance() {
		return instance;
	}
}
