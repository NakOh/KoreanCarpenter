package com.mygdx.data;

public class GameData {

	private static GameData instance;

	private boolean firstPlay;

	private final int MAX_LEVEL = 5;

	private int maxCombo;

	private int axLevel = 1;
	private int gloveLevel = 1;
	private int wagonLevel = 1;
	private int money;
	private int tree;

	private int[] axMoneyTable = { 0, 100, 200, 300, 400 };
	private int[] gloveMoneyTable = { 0, 100, 200, 300, 400 };
	private int[] wagonMoneyTable = { 0, 100, 200, 300, 400 };

	private int[] axAttackTable = { 10, 20, 30, 40, 50 };
	private float[] glovePercentTable = { 50.0f, 60.0f, 70.0f, 80.0f, 90.0f };
	private int[] wagonStorageTable = { 100, 200, 300, 400, 500 };

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

	public int getTree() {
		return tree;
	}

	public void setTree(int tree) {
		this.tree = tree;
	}

	public int getMaxCombo() {
		return maxCombo;
	}

	public void setMaxCombo(int maxCombo) {
		if (maxCombo > this.maxCombo) {
			this.maxCombo = maxCombo;
		}
	}

	public int[] getAxAttackTable() {
		return axAttackTable;
	}

	public void setAxAttackTable(int[] axAttackTable) {
		this.axAttackTable = axAttackTable;
	}

	public float[] getGlovePercentTable() {
		return glovePercentTable;
	}

	public void setGlovePercentTable(float[] glovePercentTable) {
		this.glovePercentTable = glovePercentTable;
	}

	public int[] getWagonStorageTable() {
		return wagonStorageTable;
	}

	public void setWagonStorageTable(int[] wagonStorageTable) {
		this.wagonStorageTable = wagonStorageTable;
	}

}
