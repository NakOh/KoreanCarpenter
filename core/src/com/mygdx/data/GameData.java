package com.mygdx.data;

public class GameData {

	private static GameData instance;

	private boolean firstPlay;

	private final int MAX_LEVEL = 5;

	private int axLevel = 1;
	private int gloveLevel = 1;
	private int wagonLevel = 1;
	private int money;
	private int tree;
	private int treeHp;

	private int[] axMoneyTable = { 0, 100, 200, 300, 400 };
	private int[] gloveMoneyTable = { 0, 100, 200, 300, 400 };
	private int[] wagonMoneyTable = { 0, 100, 200, 300, 400 };

	public static GameData getInstance() {
		if (instance == null) {
			instance = new GameData();
		}
		return instance;
	}

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

	public int getTreeHp() {
		return treeHp;
	}

	public void setTreeHp(int treeHp) {
		this.treeHp = treeHp;
	}

	public int[] getAxMoneyTable() {
		return axMoneyTable;
	}

	public void setAxMoneyTable(int[] axMoneyTable) {
		this.axMoneyTable = axMoneyTable;
	}

	public int[] getGloveMoneyTable() {
		return gloveMoneyTable;
	}

	public void setGloveMoneyTable(int[] gloveMoneyTable) {
		this.gloveMoneyTable = gloveMoneyTable;
	}

	public int getMAX_LEVEL() {
		return MAX_LEVEL;
	}

	public int[] getWagonMoneyTable() {
		return wagonMoneyTable;
	}

	public void setWagonMoneyTable(int[] wagonMoneyTable) {
		this.wagonMoneyTable = wagonMoneyTable;
	}
}
