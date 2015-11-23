package com.mygdx.data;

public class GameData {
	private boolean firstPlay;
	private int axLevel;
	private int gloveLevel;
	private int wagonLevel;
	private int money;
	private int tree;

	public boolean isFirstPlay() {
		return firstPlay;
	}

	public void setFirstPlay(boolean firstPlay) {
		this.firstPlay = firstPlay;
	}

	public int getAxLevel() {
		return axLevel;
	}

	public void setAxLevel(int axLevel) {
		this.axLevel = axLevel;
	}

	public int getGloveLevel() {
		return gloveLevel;
	}

	public void setGloveLevel(int gloveLevel) {
		this.gloveLevel = gloveLevel;
	}

	public int getWagonLevel() {
		return wagonLevel;
	}

	public void setWagonLevel(int wagonLevel) {
		this.wagonLevel = wagonLevel;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getTree() {
		return tree;
	}

	public void setTree(int tree) {
		this.tree = tree;
	}
}
