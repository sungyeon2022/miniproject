package skins;

import SpriteSheet.SpriteSheet;
import main.miniApp;
import imgSize.*;

public class Wings extends Skins {

	public Wings(miniApp app) {
		super(app);
	}
	@Override
	public void init() {
		setSsSkin(new SpriteSheet("collections/sprite_img/wings.png", "wings", 0, 0, WingsSize.AXWINGS, WingsSize.YWINGS));
		super.init();
	}
	@Override
	public void setting() {
		setSkinX(getApp().getIssac().getXPlayerCenter()); 
		setSkinY(/*getApp().getIssac().getYPlayerCenter()*/300);
		getSsSkin().drawObj(getSkinX(), getSkinY());
		super.setting();
	}
	@Override
	public void batch() {
		getApp().add(getSsSkin());
		super.batch();
	}
	@Override
	public void motion() {
		new Thread(()->{
			while (!getApp().getIssac().isDead()) {
				getSsSkin().setXPos(WingsSize.AXWINGS-2);
				getSsSkin().setWidth(WingsSize.BXWINGS);
				getSsSkin().drawObj(getSkinX()+1, getSkinY());
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
				getSsSkin().setXPos(getSsSkin().getXPos()+WingsSize.BXWINGS-2);
				getSsSkin().setWidth(WingsSize.CXWINGS);
				getSsSkin().drawObj(getSkinX()-2, getSkinY());
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
				getSsSkin().setXPos(getSsSkin().getXPos()+WingsSize.CXWINGS-2);
				getSsSkin().setWidth(WingsSize.DXWINGS);
				getSsSkin().drawObj(getSkinX()-1, getSkinY());
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
				getSsSkin().setXPos(getSsSkin().getXPos()+WingsSize.DXWINGS-2);
				getSsSkin().setWidth(WingsSize.EXWINGS);
				getSsSkin().drawObj(getSkinX(), getSkinY());
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
				getSsSkin().setXPos(getSsSkin().getXPos()+WingsSize.EXWINGS-2);
				getSsSkin().setWidth(WingsSize.FXWINGS);
				getSsSkin().drawObj(getSkinX()-1, getSkinY());
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
				getSsSkin().setXPos(getSsSkin().getXPos()+WingsSize.FXWINGS-2);
				getSsSkin().setWidth(WingsSize.GXWINGS-2);
				getSsSkin().drawObj(getSkinX()+2, getSkinY());
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
				getSsSkin().setXPos(0);
				getSsSkin().setWidth(WingsSize.AXWINGS);
				getSsSkin().drawObj(getSkinX()+1, getSkinY());
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		super.motion();
	}
}
