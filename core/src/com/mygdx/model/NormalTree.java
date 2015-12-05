package com.mygdx.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class NormalTree implements Tree {
	private int hp = 100;
	private int maxHp = 100;
	private Image treeImage;

	@Override
	public int getHp() {
		return hp;
	}

	@Override
	public void setHp(int hp) {
		if (hp < 0) {
			hp = 0;
		}
		this.hp = hp;
	}

	@Override
	public Image getTreeImage() {
		return treeImage;
	}

	@Override
	public void setTreeImage(Image treeImage) {
		this.treeImage = treeImage;
	}

	@Override
	public int getMaxHp() {
		return this.maxHp;
	}
}
