package com.mygdx.model;

public interface IGoogleServices {
	public boolean getSignedInGPGS();

	public void loginGPGS();

	public void submitScoreGPGS(int score);

	public void unlockAchievementGPGS(String achievementId);

	public void getLeaderboardGPGS();

	public void getAchievementsGPGS();

	public void incrementAchivementGPGS(String achievementId);
}
