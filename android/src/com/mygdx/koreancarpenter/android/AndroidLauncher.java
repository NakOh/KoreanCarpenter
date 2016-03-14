package com.mygdx.koreancarpenter.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;
import com.mygdx.data.GameData;
import com.mygdx.koreancarpenter.KoreanCarpenter;
import com.mygdx.koreancarpenter.util.Constants.IAB;
import com.mygdx.koreancarpenter.util.IabHelper;
import com.mygdx.koreancarpenter.util.IabResult;
import com.mygdx.koreancarpenter.util.Inventory;
import com.mygdx.koreancarpenter.util.Purchase;
import com.mygdx.service.AdsController;
import com.mygdx.service.IGoogleServices;
import com.mygdx.service.LabServices;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class AndroidLauncher extends AndroidApplication
		implements GameHelperListener, IGoogleServices, LabServices, AdsController {
	private final String TAG = "android";
	private static final String BANNER_AD_UNIT_ID = "ca-app-pub-1040234573987867/4011271831";
	AdView bannerAd;
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
		View gameView = initializeForView(new KoreanCarpenter(this, this, this), config);
		setupAds();

		RelativeLayout layout = new RelativeLayout(this);
		layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		layout.addView(bannerAd, params);

		setContentView(layout);

		if (gameHelper == null) {
			gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
			gameHelper.enableDebugLog(true);
		}

		gameHelper.setup(this);
	}

	public void setupAds() {
		bannerAd = new AdView(this);
		bannerAd.setVisibility(View.INVISIBLE);
		bannerAd.setBackgroundColor(0xff000000); // black
		bannerAd.setAdUnitId(BANNER_AD_UNIT_ID);
		bannerAd.setAdSize(AdSize.SMART_BANNER);
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
				GameData.getInstance().setJewelry(GameData.getInstance().getJewelry() + 10);
			}
		}
	};

	@Override
	public void buyJewelry() {
		Gdx.app.log("IAB", "보석을 구매합니다");
		mHelper.launchPurchaseFlow(this, IAB.JEWELRY, RC_REQUEST, mPurchaseFinishedListener, "HANDLE_PAYLOADS");
	}

	@Override
	public void showBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bannerAd.setVisibility(View.VISIBLE);
				AdRequest.Builder builder = new AdRequest.Builder();
				AdRequest ad = builder.build();
				bannerAd.loadAd(ad);
			}
		});
	}

	@Override
	public void hideBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bannerAd.setVisibility(View.INVISIBLE);
			}
		});

	}

	@Override
	public boolean isWifiConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		return (ni != null && ni.isConnected());
	}

	// Listener that's called when we finish querying the items and
	// subscriptions we own
	IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
		public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
			Log.d("IAB", "Query inventory finished.");

			// Have we been disposed of in the meantime? If so, quit.
			if (mHelper == null)
				return;

			// Is it a failure?
			if (result.isFailure()) {
				// handle failure here
				return;
			}

			// Do we have the premium upgrade?
			// Purchase removeAdPurchase = inventory.getPurchase(JEWELRY);
			// mAdsRemoved = (removeAdPurchase != null);
		}
	};

	@Override
	public void processPurchases() {
		mHelper.queryInventoryAsync(mGotInventoryListener);
	}

}
