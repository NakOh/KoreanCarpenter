package com.mygdx.manager;

import java.util.Map;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class AssetManager {
	private static com.badlogic.gdx.assets.AssetManager instance;
	private Map<String, String> filePathMap;

	public static com.badlogic.gdx.assets.AssetManager getInstance() {
		if (null == instance) {
			instance = new com.badlogic.gdx.assets.AssetManager();
		}
		return instance;
	}

	public static void loadTexture() {
		FileHandle file = Gdx.files.local("textureMap.json");
		Json json = new Json();

		if (Gdx.app.getType() != ApplicationType.Android) {
			directoryTextureMapperRecursive(textureMap, Gdx.files.internal("./bin/texture"));
			file.writeString(json.prettyPrint(textureMap), false);
		}
	}

}
