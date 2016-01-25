package com.mygdx.koreancarpenter.desktop;

import com.mygdx.service.AdsController;

public class AdsMobControllerDesktop implements AdsController {

	@Override
	public void showBannerAd() {
		System.out.println("광고를 보여줍니다");
	}

	@Override
	public void hideBannerAd() {
		System.out.println("광고를 숨깁니다");
	}

	@Override
	public boolean isWifiConnected() {
		return false;
	}

}
