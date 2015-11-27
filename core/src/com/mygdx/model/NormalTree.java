package com.mygdx.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class NormalTree implements Tree {
	private int hp = 100;
	private Image treeImage;

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if (hp < 0) {
			hp = 0;
		}
		this.hp = hp;
	}

	public Image getTreeImage() {
		return treeImage;
	}

	public void setTreeImage(Image treeImage) {
		this.treeImage = treeImage;
	}
}
