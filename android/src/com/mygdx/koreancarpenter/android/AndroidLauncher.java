package com.mygdx.koreancarpenter.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;
import com.mygdx.koreancarpenter.KoreanCarpenter;
import com.mygdx.koreancarpenter.util.Constants.IAB;
import com.mygdx.koreancarpenter.util.IabHelper;
import com.mygdx.koreancarpenter.util.IabResult;
import com.mygdx.koreancarpenter.util.Purchase;
import com.mygdx.service.IGoogleServices;
import com.mygdx.service.LabServices;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class AndroidLauncher extends AndroidApplication implements GameHelperListener, IGoogleServices, LabServices {
	private final String TAG = "android";
	private GameHelper gameHelper;
	private IabHelper mHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHelper = new IabHelper(this, IAB.KEY1 + IAB.KEY2 + IAB.KEY3 + IAB.KEY4);

		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
			public void onIabSetupFinished(IabResult result) {
				if (!result.isSuccess()) {
					Log.d("IAB", "Problem setting up In-app Billing: " + result);
				}
				Log.d("IAB", "Billing Success: " + result);
			}
		});

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new KoreanCarpenter(this), config);

		if (gameHelper == null) {
			gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
			gameHelper.enableDebugLog(true);
		}

		gameHelper.setup(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onStart() {
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		gameHelper.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mHelper != null)
			mHelper.dispose();
		mHelper = null;
	}

	@Override
	public void onActivityResult(int request, int response, Intent data) {
		super.onActivityResult(request, response, data);
		gameHelper.onActivityResult(request, response, data);
	}

	@Override
	public void onSignInFailed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSignInSucceeded() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getSignedInGPGS() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void loginGPGS() {
		try {
			runOnUiThread(new Runnable() {
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		} catch (final Exception ex) {
		}
	}

	@Override
	public void submitScoreGPGS(int score) {
		if (gameHelper.isSignedIn()) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(), "CgkI3rio7OYWEAIQAQ", score);
		} else if (!gameHelper.isConnecting()) {
			loginGPGS();
		}
	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
		Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
	}

	@Override
	public void getLeaderboardGPGS() {
		if (gameHelper.isSignedIn()) {
			startActivityForResult(
					Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), "CgkI3rio7OYWEAIQAQ"), 100);
		} else if (!gameHelper.isConnecting()) {
			loginGPGS();
		}
	}

	@Override
	public void getAchievementsGPGS() {
		if (gameHelper.isSignedIn()) {
			startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 101);
		} else if (!gameHelper.isConnecting()) {
			loginGPGS();
		}
	}

	@Override
	public void incrementAchivementGPGS(String achievementId) {
		if (gameHelper.isSignedIn()) {
			Games.Achievements.increment(gameHelper.getApiClient(), achievementId, 1);
		} else if (!gameHelper.isConnecting()) {
			loginGPGS();
		}
	}

	IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
		public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
			if (purchase == null)
				return;
			Log.d("IAB", "Purchase finished: " + result + ", purchase: " + purchase);

			// if we were disposed of in the meantime, quit.
			if (mHelper == null)
				return;

			if (result.isFailure()) {
				return;
			}

			Log.d("IAB", "Purchase successful.");

			if (purchase.getSku().equals(IAB.JEWELRY)) {
				Log.d("IAB", "보석을 구매하였습니다");
			}
		}
	};

	@Override
	public void buyJewelry() {
		Gdx.app.log("IAB", "보석을 구매합니다");
		mHelper.launchPurchaseFlow(this, IAB.JEWELRY, RC_REQUEST, mPurchaseFinishedListener, "HANDLE_PAYLOADS");
	}
}
