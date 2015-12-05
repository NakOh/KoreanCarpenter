package com.mygdx.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public interface Tree {
	public int getHp();

	public int getMaxHp();

	public void setHp(int hp);

	public Image getTreeImage();

	public void setTreeImage(Image treeImage);
}
