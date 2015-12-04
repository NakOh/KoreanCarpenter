package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ObjectImage extends Image {
	private boolean left;

	public ObjectImage(Texture smallLeftTexture) {
		super(smallLeftTexture);
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
}
