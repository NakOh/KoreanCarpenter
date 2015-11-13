package com.mygdx.manager;

import com.badlogic.gdx.graphics.Texture;

public class AssetManager {
	private static com.badlogic.gdx.assets.AssetManager instance;

	public static com.badlogic.gdx.assets.AssetManager getInstance() {
		if (null == instance) {
			instance = new com.badlogic.gdx.assets.AssetManager();
		}
		return instance;
	}

	public static void allAssetsload() {
		if (null == instance) {
			instance = new com.badlogic.gdx.assets.AssetManager();
		}
		// 이곳에 로드할 모든 리소스를 추가한다.
		// 많아질 경우 JSON으로 관리하자.
		instance.load("texture/title.jpg", Texture.class);
		instance.finishLoading();
	}

}
