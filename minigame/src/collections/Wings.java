package collections;

import SpriteSheet.SpriteSheet;
import main.miniApp;
import objectSetting.ViewDirect;
import imgSize.*;
import imgSize.CollectionsImgSize.WingsSize;

public class Wings extends Collections {

	public Wings(miniApp app) {
		super(app);
	}

	@Override
	public void init() {
		setSsCollection(
				new SpriteSheet("collections/sprite_img/wings.png", "wings", 0, 0, WingsSize.XWINGS, WingsSize.YWINGS));
		super.init();
	}

	@Override
	public void setting() {
		super.setting();
	}

	@Override
	public void batch() {
		getApp().add(getSsCollection());
		super.batch();
	}

	@Override
	public void motion() {
		new Thread(() -> {
			int i = 0;
			while (!getApp().getIssac().isDead()) {
				if (i == 7)
					i = 0;
				if (getApp().getIssac().getViewDirect() == ViewDirect.UP) {
					getSsCollection().setXPos(WingsSize.XWINGS * i);
					getSsCollection().setWidth(WingsSize.XWINGS);
				}else if (getApp().getIssac().getViewDirect() == ViewDirect.DOWN) {
					getSsCollection().setXPos(WingsSize.XWINGS * i);
					getSsCollection().setWidth(WingsSize.XWINGS);
				}else if (getApp().getIssac().getViewDirect() == ViewDirect.LEFT) {
					getSsCollection().setXPos(WingsSize.XWINGS * i+WingsSize.XWINGS/2);
					getSsCollection().setWidth(WingsSize.XWINGS/2);
				}else if (getApp().getIssac().getViewDirect() == ViewDirect.RIGHT) {
					getSsCollection().setXPos(WingsSize.XWINGS * i);
					getSsCollection().setWidth(WingsSize.XWINGS/2);
				}
				i++;
				try {
					Thread.sleep(70);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		super.motion();
	}

	@Override
	public void position() {
		new Thread(() -> {
			while (!getApp().getIssac().isDead()) {
				if (getApp().getIssac().getViewDirect() == ViewDirect.UP) {
					getApp().remove(getSsCollection());
					getApp().add(getSsCollection(),0);
					setCollectionX(getApp().getIssac().getXPlayerCenter()-23);
					setCollectionY(getApp().getIssac().getYPlayerCenter()-9);
					getSsCollection().drawObj(getCollectionX(), getCollectionY());
				}else if (getApp().getIssac().getViewDirect() == ViewDirect.DOWN) {
					getApp().remove(getSsCollection());
					getApp().add(getSsCollection());
					setCollectionX(getApp().getIssac().getXPlayerCenter()-23);
					setCollectionY(getApp().getIssac().getYPlayerCenter()-9);
					getSsCollection().drawObj(getCollectionX(), getCollectionY());
				}else if (getApp().getIssac().getViewDirect() == ViewDirect.LEFT) {
					getApp().remove(getSsCollection());
					getApp().add(getSsCollection(),0);
					setCollectionX(getApp().getIssac().getXPlayerCenter());
					setCollectionY(getApp().getIssac().getYPlayerCenter()-8);
					getSsCollection().drawObj(getCollectionX(), getCollectionY());
				}else if (getApp().getIssac().getViewDirect() == ViewDirect.RIGHT) {
					getApp().remove(getSsCollection());
					getApp().add(getSsCollection(),0);
					setCollectionX(getApp().getIssac().getXPlayerCenter()-23);
					setCollectionY(getApp().getIssac().getYPlayerCenter()-8);
					getSsCollection().drawObj(getCollectionX(), getCollectionY());
				}
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
