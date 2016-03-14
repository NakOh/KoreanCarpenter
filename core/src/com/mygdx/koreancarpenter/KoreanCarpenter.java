package com.mygdx.koreancarpenter;

import com.badlogic.gdx.Game;
import com.mygdx.manager.ScreenManager;
import com.mygdx.service.AdsController;
import com.mygdx.service.IGoogleServices;
import com.mygdx.service.LabServices;
import com.mygdx.service.Screens;

public class KoreanCarpenter extends Game {
	private IGoogleServices actionResolver;
	private AdsController adsController;
	private LabServices labServices;

	public KoreanCarpenter(IGoogleServices actionResolver, AdsController adsController, LabServices labServices) {
		this.actionResolver = actionResolver;
		this.adsController = adsController;
		this.labServices = labServices;
	}

	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this, this.actionResolver, this.adsController, this.labServices);
		ScreenManager.getInstance().show(Screens.LOAD);
	}

	@Override
	public void dispose() {
		super.dispose();
		ScreenManager.getInstance().dispose();
	}

}
