package itemEffect;

import SpriteSheet.SpriteSheet;
import main.miniApp;
import objectSetting.ViewDirect;
import objectSetting.defaultSetting;
import player.issac;

public class wings implements defaultSetting {
	private miniApp app;
	private SpriteSheet ssWings;
	private issac issac;

	public wings(miniApp app, issac issac) {
		this.app = app;
		init();
		setting();
		batch();
		motion();
	}

	@Override
	public void init() {
		this.issac = app.getIssac();
		ssWings = new SpriteSheet("item/wings.png", "wings", 0, 0, 64, 44);
	}

	@Override
	public void setting() {
		ssWings.drawObj(0, 0);
	}

	@Override
	public void batch() {
		app.add(ssWings);
	}

	public void motion() {
		new Thread(() -> {
			int i = 1;
			while (true) {
				if (issac.getViewDirect() == ViewDirect.DOWN) {
					app.remove(ssWings);
					app.add(ssWings);
					ssWings.setWidth(64);
					ssWings.setXPos(64 * (i / 10));
					ssWings.drawObj(issac.getXPlayerCenter() - 64 / 2 + 1, issac.getYPlayerCenter() - 15);
				} else if (issac.getViewDirect() == ViewDirect.RIGHT) {
					app.remove(ssWings);
					app.add(ssWings,0);
					ssWings.setWidth(32);
					ssWings.setXPos(64 * (i / 10));
					ssWings.drawObj(issac.getXPlayerCenter() - 64 / 2 + 1, issac.getYPlayerCenter() - 15);
				} else if (issac.getViewDirect() == ViewDirect.LEFT) {
					app.remove(ssWings);
					app.add(ssWings,0);
					ssWings.setWidth(32);
					ssWings.setXPos(32 + 64 * (i / 10));
					ssWings.drawObj(issac.getXPlayerCenter() - 64 / 2 + 33, issac.getYPlayerCenter() - 15);
				} else if (issac.getViewDirect() == ViewDirect.UP) {
					app.remove(ssWings);
					app.add(ssWings,0);
					ssWings.setWidth(64);
					ssWings.setXPos(64 * (i / 10));
					ssWings.drawObj(issac.getXPlayerCenter() - 64 / 2 + 1, issac.getYPlayerCenter() - 15);
				}
				
				i++;
				if (i == 70)
					i = 0;
				try {
					Thread.sleep(issac.getMoveSpeed());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
