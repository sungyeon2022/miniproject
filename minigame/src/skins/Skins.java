package skins;

import SpriteSheet.SpriteSheet;
import lombok.Data;
import main.miniApp;

@Data

public class Skins {
	private miniApp app;
	private SpriteSheet ssSkin;
	private int skinX;
	private int skinY;
	
	
	public Skins(miniApp app) {
		this.app = app;
		init();
		setting();
		batch();
		motion();
	}
	public void init() {}
	public void setting() {}
	public void batch() {}
	public void motion() {}
}
