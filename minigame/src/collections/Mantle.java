package collections;

import SpriteSheet.SpriteSheet;
import imgSize.CollectionsImgSize.DarkWingsSize;
import imgSize.CollectionsImgSize.MantleSize;
import main.miniApp;

public class Mantle extends Collections{

	public Mantle(miniApp app) {
		super(app);
	}
	@Override
	public void init() {
		setSsCollection(new SpriteSheet("collections/sprite_img/mantle.png", "Mantle", 0, 0, MantleSize.xMantle,
				MantleSize.yMantle));
		super.init();
	}
	@Override
	public void setting() {
		// TODO Auto-generated method stub
		super.setting();
	}
	@Override
	public void batch() {
		getApp().add(getSsCollection(),0);
		super.batch();
	}
	@Override
	public void motion() {
		new Thread(()->{
			int i = 0;
			while (!getApp().getIssac().isDead()) {
				if(i==4)
					i=0;
				getSsCollection().setXPos(MantleSize.xMantle*i);
				i++;
				try {
					Thread.sleep(60);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		super.motion();
	}
	@Override
	public void position() {
		new Thread(()->{
			while (!getApp().getIssac().isDead()) {
				getSsCollection().drawObj(getApp().getIssac().getXPlayer()-19,getApp().getIssac().getYPlayer()-16);
				try {
					Thread.sleep(getApp().getIssac().getMoveSpeed());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		super.position();
	}
	
}
