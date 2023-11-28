package collections;

import SpriteSheet.SpriteSheet;
import imgSize.CollectionsImgSize.CrownSize;
import main.miniApp;

public class Crown extends Collections{

	public Crown(miniApp app) {
		super(app);
	}
	@Override
	public void init() {
		setSsCollection(new SpriteSheet("collections/sprite_img/crown.png", "Crown", 0, 0, CrownSize.xCrown, CrownSize.yCrown));
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
		super.motion();
	}
	@Override
	public void position() {
		new Thread(()->{
			int i = 0;
			while (!getApp().getIssac().isDead()) {
				if(i==91)
					i=0;
				setCollectionX(getApp().getIssac().getXPlayer()+4);
				setCollectionY(getApp().getIssac().getYPlayer()-24-(i/30));
				getSsCollection().drawObj(getCollectionX(),getCollectionY());
				i++;
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
