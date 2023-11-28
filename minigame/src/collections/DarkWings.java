package collections;

import SpriteSheet.SpriteSheet;
import imgSize.CollectionsImgSize.DarkWingsSize;
import imgSize.CollectionsImgSize.WingsSize;
import main.miniApp;
import objectSetting.ViewDirect;

public class DarkWings extends Collections {

	public DarkWings(miniApp app) {
		super(app);
	}

	@Override
	public void init() {
		setSsCollection(new SpriteSheet("collections/sprite_img/darkwings.png", "DarkWings", 0, 0, DarkWingsSize.xDark,
				DarkWingsSize.yDark));
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
				if (i == 5)
					i = 0;
				if (getApp().getIssac().getViewDirect() == ViewDirect.UP) {
					getSsCollection().setXPos(DarkWingsSize.xDark * i);
					getSsCollection().setWidth(DarkWingsSize.xDark);
				} else if (getApp().getIssac().getViewDirect() == ViewDirect.DOWN) {
					getSsCollection().setXPos(DarkWingsSize.xDark * i);
					getSsCollection().setWidth(DarkWingsSize.xDark);
				} else if (getApp().getIssac().getViewDirect() == ViewDirect.LEFT) {
					getSsCollection().setXPos(DarkWingsSize.xDark * i + DarkWingsSize.xDark / 2);
					getSsCollection().setWidth(DarkWingsSize.xDark / 2);
				} else if (getApp().getIssac().getViewDirect() == ViewDirect.RIGHT) {
					getSsCollection().setXPos(DarkWingsSize.xDark * i);
					getSsCollection().setWidth(DarkWingsSize.xDark / 2);
				}
				i++;
				try {
					Thread.sleep(80);
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
					getApp().add(getSsCollection(), 0);
					setCollectionX(getApp().getIssac().getXPlayerCenter() - 30);
					setCollectionY(getApp().getIssac().getYPlayerCenter() - 11);
					getSsCollection().drawObj(getCollectionX(), getCollectionY());
				} else if (getApp().getIssac().getViewDirect() == ViewDirect.DOWN) {
					getApp().remove(getSsCollection());
					getApp().add(getSsCollection());
					setCollectionX(getApp().getIssac().getXPlayerCenter() - 30);
					setCollectionY(getApp().getIssac().getYPlayerCenter() - 11);
					getSsCollection().drawObj(getCollectionX(), getCollectionY());
				} else if (getApp().getIssac().getViewDirect() == ViewDirect.LEFT) {
					getApp().remove(getSsCollection());
					getApp().add(getSsCollection(), 0);
					setCollectionX(getApp().getIssac().getXPlayerCenter());
					setCollectionY(getApp().getIssac().getYPlayerCenter() - 11);
					getSsCollection().drawObj(getCollectionX(), getCollectionY());
				} else if (getApp().getIssac().getViewDirect() == ViewDirect.RIGHT) {
					getApp().remove(getSsCollection());
					getApp().add(getSsCollection(), 0);
					setCollectionX(getApp().getIssac().getXPlayerCenter() - 30);
					setCollectionY(getApp().getIssac().getYPlayerCenter() - 11);
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
