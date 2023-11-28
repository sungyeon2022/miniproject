package skins;

import SpriteSheet.SpriteSheet;
import main.miniApp;

public class Skins {
	private miniApp app;
	private int xSkin;
	private int ySkin;
	private SpriteSheet ssSkin;
	public Skins(miniApp app) {
		this.app = app;
		init();
		setting();
		batch();
		motion();
		position();
	}
	private void init() {}
	private void setting() {}
	private void batch() {}
	private void motion() {}
	private void position() {} //Collection와 동일
}
