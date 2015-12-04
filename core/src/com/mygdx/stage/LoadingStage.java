package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.mygdx.manager.AssetManager;
import com.mygdx.manager.SaveManager;
import com.mygdx.manager.ScreenManager;
import com.mygdx.service.Screens;

public class LoadingStage extends Stage {
	private final String tag = "LOADING_STAGE";
	private com.badlogic.gdx.assets.AssetManager assetManager;
	private int loadingBarMaxWidth;
	private int loadingBarHeight;
	private Image loadingFrame, loadingBg, screenBg;
	private float percent;
	private final int loadingBarFinishOffset = 10;
	private SaveManager saveManager;

	public Stage makeStage() {
		assetManager = AssetManager.getInstance();
		// 로딩 화면을 만들기 위한 리소스 미리 로딩
		assetManager.load("loading/loading.pack", TextureAtlas.class);
		assetManager.finishLoading();

		TextureAtlas atlas = assetManager.get("loading/loading.pack", TextureAtlas.class);
		loadingFrame = new Image(atlas.findRegion("loading-frame"));
		screenBg = new Image(atlas.findRegion("screen-bg"));
		loadingBg = new Image(atlas.findRegion("loading-frame-bg"));

		Table backgroundTable = new Table();
		backgroundTable.setFillParent(true);
		backgroundTable.setBackground(screenBg.getDrawable());

		Table frameTable = new Table();
		frameTable.setFillParent(true);
		frameTable.align(Align.center);
		frameTable.add(loadingFrame);
		loadingBarMaxWidth = (int) loadingFrame.getWidth();
		loadingBarHeight = (int) loadingFrame.getHeight();

		Table loadingBarTable = new Table();
		loadingBarTable.setFillParent(true);
		loadingBarTable.align(Align.center);
		loadingBarTable.add(loadingBg).padRight(loadingFrame.getWidth() - 5)
				.padTop(loadingFrame.getHeight() - loadingBg.getHeight());
		addActor(backgroundTable);
		addActor(frameTable);
		addActor(loadingBarTable);

		AssetManager.allAssetsload();
		saveManager = SaveManager.getInstance();
		saveManager.load();
		return this;
	}

	@Override
	public void act() {
		if (assetManager.update()) {
			// 처음일때는 프롤로그 스크린으로 넘어가도록 하자.
			Gdx.app.log(tag, "로딩 끝");
			assetManager.unload("loading/loading.pack");
			ScreenManager.getInstance().show(Screens.MAIN);
		}
		percent = Interpolation.linear.apply(percent, assetManager.getProgress(), 0.1f);
		loadingBg.setSize(loadingBarMaxWidth * percent, loadingBarHeight);
		loadingBg.invalidate();
		if (loadingBg.getWidth() > loadingBarMaxWidth - loadingBarFinishOffset) {
			loadingBg.setWidth(loadingBarMaxWidth);
		}
	}
}
