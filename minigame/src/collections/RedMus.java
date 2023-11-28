package collections;

import SpriteSheet.SpriteSheet;
import imgSize.CollectionsImgSize.RedMusSize;
import main.miniApp;

public class RedMus extends Collections {

	public RedMus(miniApp app) {
		super(app);
	}

	@Override
	public void init() {
		setSsCollection(
				new SpriteSheet("collections/sprite_img/redmus.png", "RedMus", 0, 0, RedMusSize.xRed, RedMusSize.yRed));
		super.init();
	}

	@Override
	public void setting() {
		super.setting();
	}

	@Override
	public void batch() {
		getApp().add(getSsCollection(),0);
		super.batch();
	}

	@Override
	public void motion() {
		// TODO Auto-generated method stub
		super.motion();
	}

	@Override
	public void position() {
		new Thread(() -> {
			while (!getApp().getIssac().isDead()) {
				getSsCollection().drawObj(getApp().getIssac().getXPlayer()+5, getApp().getIssac().getYPlayer()-23);
				try {
					Thread.sleep(getApp().getIssac().getMoveSpeed());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		super.position();
	}

}
