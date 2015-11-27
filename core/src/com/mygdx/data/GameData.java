package com.mygdx.data;

public class GameData {

	private static GameData instance;

	private boolean firstPlay;

	private final int MAX_LEVEL = 50;

	private int maxCombo;

	private int axLevel = 1;
	private int gloveLevel = 1;
	private int wagonLevel = 1;

	private int attack;
	private int accuracy = 50;
	private int storage = 1000;

	private int axMoney;
	private int gloveMoney;

	private int wagonMoney;

	private int money = 999999999;
	private int tree;

	private int axNameIndex = 0;
	private int gloveNameIndex = 0;
	private int wagonNameIndex = 0;

	private String[] axName = { "흙 도끼", "돌 도끼", "양철 도끼", "전기 톱", "장미 칼", "투명 도끼" };
	private String[] gloveName = { "맨 손", "면 장갑", "고무 장갑", "목 장갑", "가죽 장갑", "투명 장갑" };
	private String[] wagonName = { "등지게", "손수레", "짐마차", "트랙터", "스피드웨건", "투명 수레" };

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

	public int getMAX_LEVEL() {
		return MAX_LEVEL;
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

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public int getAxMoney() {
		return axMoney;
	}

	public void setAxMoney(int axMoney) {
		this.axMoney = axMoney;
	}

	public int getGloveMoney() {
		return gloveMoney;
	}

	public void setGloveMoney(int gloveMoney) {
		this.gloveMoney = gloveMoney;
	}

	public int getWagonMoney() {
		return wagonMoney;
	}

	public void setWagonMoney(int wagonMoney) {
		this.wagonMoney = wagonMoney;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public String[] getAxName() {
		return axName;
	}

	public void setAxName(String[] axName) {
		this.axName = axName;
	}

	public String[] getGloveName() {
		return gloveName;
	}

	public void setGloveName(String[] gloveName) {
		this.gloveName = gloveName;
	}

	public String[] getWagonName() {
		return wagonName;
	}

	public void setWagonName(String[] wagonName) {
		this.wagonName = wagonName;
	}

	public int getAxNameIndex() {
		if (getAxLevel() >= 50) {
			setAxNameIndex(5);
		} else if (getAxLevel() >= 40) {
			setAxNameIndex(4);
		} else if (getAxLevel() >= 30) {
			setAxNameIndex(3);
		} else if (getAxLevel() >= 20) {
			setAxNameIndex(2);
		} else if (getAxLevel() >= 10) {
			setAxNameIndex(1);
		} else {
			setAxNameIndex(0);
		}
		return axNameIndex;
	}

	public void setAxNameIndex(int axNameIndex) {
		this.axNameIndex = axNameIndex;
	}

	public int getGloveNameIndex() {
		if (getGloveLevel() >= 50) {
			setGloveNameIndex(5);
		} else if (getGloveLevel() >= 40) {
			setGloveNameIndex(4);
		} else if (getGloveLevel() >= 30) {
			setGloveNameIndex(3);
		} else if (getGloveLevel() >= 20) {
			setGloveNameIndex(2);
		} else if (getGloveLevel() >= 10) {
			setGloveNameIndex(1);
		} else {
			setGloveNameIndex(0);
		}
		return gloveNameIndex;
	}

	public void setGloveNameIndex(int gloveNameIndex) {
		this.gloveNameIndex = gloveNameIndex;
	}

	public int getWagonNameIndex() {
		if (getWagonLevel() >= 50) {
			setWagonNameIndex(5);
		} else if (getWagonLevel() >= 40) {
			setWagonNameIndex(4);
		} else if (getWagonLevel() >= 30) {
			setWagonNameIndex(3);
		} else if (getWagonLevel() >= 20) {
			setWagonNameIndex(2);
		} else if (getWagonLevel() >= 10) {
			setWagonNameIndex(1);
		} else {
			setWagonNameIndex(0);
		}
		return wagonNameIndex;
	}

	public void setWagonNameIndex(int wagonNameIndex) {
		this.wagonNameIndex = wagonNameIndex;
	}
}
