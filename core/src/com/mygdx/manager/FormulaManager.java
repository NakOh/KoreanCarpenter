package com.mygdx.manager;

public class FormulaManager {
	public int axUpgradeFormula(int level) {
		return 4 + level * 3;
	}

	public int axMoneyFormula(int level) {
		return 500 + 1000 * ((level * level) / 5);
	}

	public int gloveMoneyFormula(int level) {
		return 300 + 1500 * ((level * level) / 10);
	}

	public int wagonMoneyFormula(int level) {
		return 500 + 1000 * ((level * level) / 10);
	}

}
