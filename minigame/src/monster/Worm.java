package monster;

import java.util.Vector;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import player.issac;
import objectSetting.Gap;
import objectSetting.ViewDirect;
import objectSetting.WormSize;
import objectSetting.issacSize;

public class Worm extends Monster {
	private final static String GUBUN = "Worm : ";

	public Worm(JFrame app, issac Issac, String url, int imgWidth, int imgHeight) {
		super(app, Issac, url, imgWidth, imgHeight);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(!isDead()) {
					if(getLife() <= 0) {
						setDead(true);
						break;
					}
					moveDirectCheck();
					moveUp();					
					moveDown();
					moveRight();
					moveLeft();
					moveMotion();
					getSsMonster().drawObj(getXPlayer(), getYPlayer());
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				if(isDead()) {
					dead();
				}
			}
		}).start();
	}
	
	public void init() {
		setSsMonster(new SpriteSheet(getUrl(), "monster", 0, 0, getImgWidth(), getImgHeight())); 
	}
	public void setting() {
		setViewDirect(ViewDirect.RIGHT);
		setXPlayer(180);
		setYPlayer(130);
		setXPlayerCenter(getXPlayer() + WormSize.WIDTH / 2); 
		setYPlayerCenter(getYPlayer() + WormSize.HEIGHT / 2); 
		setLife(20);
	}
	public void batch() {
		getSsMonster().drawObj(getXPlayer(), getYPlayer());
		getApp().add(getSsMonster(), 0);
	}
	
	public void attckCheck(int direct, int range) {
		int xDistance = getIssac().getXPlayerCenter() - getXPlayerCenter();
		int yDistance = getIssac().getYPlayerCenter() - getYPlayerCenter();
		System.out.println("attack");
		double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
		if(distance < range) {
			attackMotion(direct - 1);
		}
	}
	@Override
	public void attack() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				switch (getViewDirect()) {
					case ViewDirect.DOWN:
						attckCheck(ViewDirect.DOWN, 53);
						break;	
					case ViewDirect.LEFT:
						attckCheck(ViewDirect.LEFT, 35);
						break;
					case ViewDirect.UP:
						attckCheck(ViewDirect.UP, 30);
						break;
					case ViewDirect.RIGHT:
						attckCheck(ViewDirect.RIGHT, 35);
						break;
				}
			} 
			
		}).start();
	}
	public void attackMotion(int direct) {
		getSsMonster().setXPos((WormSize.WIDTH * direct) + (Gap.COLUMNGAP * direct));
		getSsMonster().setYPos(WormSize.HEIGHT * 4 + Gap.ROWGAP * 4);
		getSsMonster().drawObj(getXPlayer(), getYPlayer());
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getSsMonster().setXPos(0);
		getSsMonster().setYPos(WormSize.HEIGHT * direct + Gap.ROWGAP * direct);
		getSsMonster().drawObj(getXPlayer(), getYPlayer());
		getIssac().setLife(getIssac().getLife() - 1);	// 플레이어 생명력 1감소
		getIssac().dead();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}