package monster;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import SpriteSheet.SpriteSheet;
import main.miniApp;
import objectSetting.ViewDirect;
import player.issac;

public class fly extends Monster {
	private issac issac;
	private boolean[] view = { false, false, false, false };

	public fly(miniApp app, issac issac, String url, int imgWidth, int imgHeight) {
		super(app, issac, url, imgWidth, imgHeight);
		this.issac = issac;
		init();
		setting();
		batch();
		motion();
	}

	@Override
	public void init() {
		setGUBUN("fly");
		setSsMonster(new SpriteSheet("monster/fly.png", getGUBUN(), 7, 37, 20, 16));
	}

	@Override
	public void setting() {
		setLife(6);
		setXPlayer(300);
		setYPlayer(200);
		setXPlayerCenter(getXPlayer() + 10);
		setYPlayerCenter(getYPlayer() + 8);
	}

	@Override
	public void batch() {
		getApp().add(getSsMonster());
	}

	public void motion() {
		new Thread(() -> {
			int i = 0;
			int direction = 1;
			int distance;
			int xGap;
			int yGap;
			int attackSpeed = 1;
			while (!isDead()) {
				if(getLife()<0) {
					setDead(true);
				}
				distance = (int) (Math.random() * 15) + 1;
				direction = (int) (Math.random() * 4) + 1;
				view[direction - 1] = true;
				if (getSsMonster().getLocation().distance(issac.getSsBody().getLocation()) > 200) {
					if (view[ViewDirect.DOWN] && i == 1) {
						setYPlayer(getYPlayer() + distance);
						view[ViewDirect.DOWN] = false;
					}
					if (view[ViewDirect.UP] && i == 1) {
						setYPlayer(getYPlayer() - distance);
						view[ViewDirect.UP] = false;
					}
					if (view[ViewDirect.LEFT] && i == 1) {
						setXPlayer(getXPlayer() - distance);
						view[ViewDirect.LEFT] = false;
					}
					if (view[ViewDirect.RIGHT] && i == 1) {
						setXPlayer(getXPlayer() + distance);
						view[ViewDirect.RIGHT] = false;
					}
				} else {
					xGap = getXPlayerCenter() - issac.getXPlayerCenter();
					yGap = getYPlayerCenter() - issac.getYPlayerCenter();
					if (xGap > 0) {
						if (xGap < attackSpeed) {
							setXPlayer(getXPlayer() - xGap);
						} else {
							setXPlayer(getXPlayer() - attackSpeed);
						}
					} else if (xGap < 0) {
						if (xGap > -attackSpeed) {
							setXPlayer(getXPlayer() - xGap);
						} else {
							setXPlayer(getXPlayer() + attackSpeed);
						}
					}
					if (yGap > 0) {
						if (yGap < attackSpeed) {
							setYPlayer(getYPlayer() - yGap);
						} else {
							setYPlayer(getYPlayer() - attackSpeed);
						}
					} else if (yGap < 0) {
						if (yGap > -attackSpeed) {
							setYPlayer(getYPlayer() - yGap);
						} else {
							setYPlayer(getYPlayer() + attackSpeed);
						}
					}
				}
				if (getXPlayer() > 810)
					setXPlayer(785);
				if (getXPlayer() < 110)
					setXPlayer(110);
				if (getYPlayer() > 440)
					setYPlayer(440);
				if (getYPlayer() < 80)
					setYPlayer(80);

				setXPlayerCenter(getXPlayer() + 10);
				setYPlayerCenter(getYPlayer() + 8);

				getSsMonster().setXPos(7 + 31 * (i / 2));
				getSsMonster().setYPos(7 + 31 * (i / 2));
				getSsMonster().drawObj(getXPlayer(), getYPlayer());
				i++;
				if (i == 4) {
					i = 0;
				}
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			dead();
		}).start();
	}
}
