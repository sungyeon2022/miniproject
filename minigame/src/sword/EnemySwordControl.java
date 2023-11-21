package sword;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

import SpriteSheet.SpriteSheet;
import connect.ConnectControl;
import enemy.EnemyIssac;
import imgSize.SwordMotionSize;
import imgSize.SwordSize;
import objectSetting.*;
import lombok.Data;
import main.miniApp;
import player.issac;

@Data

public class EnemySwordControl extends Sword {
	private final static String TAG = "SwordControl : ";
	private EnemySwordControl enemySwordControl = this;
	private SpriteSheet ssSword;
	private Sword sword;
	private issac issac;
	private EnemyIssac enemyIssac;
	private ConnectControl connectControl;

	public EnemySwordControl(miniApp app, issac issac, EnemyIssac enemyIssac, ConnectControl connectControl) {
		super(app);
		System.out.println(TAG + "makeEnemySword");
		init(issac, enemyIssac, connectControl);
		setting();
		batch();
		swordNomalForm();
	}

	public void init(issac issac, EnemyIssac enemyIssac, ConnectControl connectControl) {
		this.issac = issac;
		this.enemyIssac = enemyIssac;
		this.connectControl = connectControl;
		ssSword = new SpriteSheet("sword/sword_down.png", "issac_sword",
				SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH, SwordSize.SWORDYGAP,
				SwordSize.SWORDWIDTH, SwordSize.SWORDYHEIGHT - 2);
	}

	public void setting() {
		setXSword(492);
		setYSword(100);
	}

	public void batch() {
		ssSword.drawObj(getXSword(), getYSword());
		getApp().add(ssSword);

	}

	@Override
	public void swordNomalForm() {
		new Thread(() -> {
			while (!isSwordAttacking() && !enemyIssac.isDead()) {
				dotAttack();
				if (enemyIssac.isKeyPress()) {
					swordAttackForm();
					break;
				} else {
					if (enemyIssac.getViewDirect() == ViewDirect.LEFT) {
						enemySwordControl.getSsSword().setUrl("sword/sword_left.png");
						enemySwordControl.getSsSword()
								.setXPos(SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP - SwordSize.SWORDYHEIGHT + 3);
						enemySwordControl.getSsSword()
								.setYPos(SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH - 1);
						enemySwordControl.getSsSword().setWidth(SwordSize.SWORDYHEIGHT - 1);
						enemySwordControl.getSsSword().setHeight(SwordSize.SWORDWIDTH);
						if (enemySwordControl.getSsSword().getUrl().equals("sword/sword_left.png")) {
							enemySwordControl.getSsSword().drawObj(enemyIssac.getXEnemy() - 36,
									enemyIssac.getYEnemy() + 29);
						}

					} else if (enemyIssac.getViewDirect() == ViewDirect.UP) {
						enemySwordControl.getSsSword().setUrl("sword/sword_up.png");
						enemySwordControl.getSsSword().setXPos(SwordSize.SWORDXGAP + 2);
						enemySwordControl.getSsSword()
								.setYPos(SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP - SwordSize.SWORDYHEIGHT + 2);
						enemySwordControl.getSsSword().setWidth(SwordSize.SWORDWIDTH - 1);
						enemySwordControl.getSsSword().setHeight(SwordSize.SWORDYHEIGHT);
						if (enemySwordControl.getSsSword().getUrl().equals("sword/sword_up.png")) {
							enemySwordControl.getSsSword().drawObj(enemyIssac.getXEnemy() + 12,
									enemyIssac.getYEnemy() - 25);

						}
					} else if (enemyIssac.getViewDirect() == ViewDirect.RIGHT) {
						enemySwordControl.getSsSword().setUrl("sword/sword_right.png");
						enemySwordControl.getSsSword().setXPos(SwordSize.SWORDYGAP);
						enemySwordControl.getSsSword().setYPos(SwordSize.SWORDXGAP + 1);
						enemySwordControl.getSsSword().setWidth(SwordSize.SWORDYHEIGHT - 1);
						enemySwordControl.getSsSword().setHeight(SwordSize.SWORDWIDTH);
						if (enemySwordControl.getSsSword().getUrl().equals("sword/sword_right.png")) {
							enemySwordControl.getSsSword().drawObj(enemyIssac.getXEnemy() + 34,
									enemyIssac.getYEnemy() + 28);

						}
					} else if (enemyIssac.getViewDirect() == ViewDirect.DOWN) {
						enemySwordControl.getSsSword().setUrl("sword/sword_down.png");
						enemySwordControl.getSsSword()
								.setXPos(SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH);
						enemySwordControl.getSsSword().setYPos(SwordSize.SWORDYGAP);
						enemySwordControl.getSsSword().setWidth(SwordSize.SWORDWIDTH);
						enemySwordControl.getSsSword().setHeight(SwordSize.SWORDYHEIGHT - 2);
						if (enemySwordControl.getSsSword().getUrl().equals("sword/sword_down.png")) {
							enemySwordControl.getSsSword().drawObj(enemyIssac.getXEnemy() + 12,
									enemyIssac.getYEnemy() + 50);
						}

					}
				}
				try {
					Thread.sleep(enemyIssac.getMovespeed());
				} catch (Exception e) {
				}
			}
			if (enemyIssac.isDead())
				enemySwordControl.ssSword.setVisible(false);
		}).start();

	}

	@Override
	public void swordAttackForm() {
		int imgxlocation = 1;
		int imgylocation = 0;
		while (!enemyIssac.isDead()) {
			setSwordAttacking(true);
			swingAttack();
			if (enemyIssac.getViewDirect() == ViewDirect.DOWN) {
				if (imgxlocation == 1 && imgylocation > 3) {
					imgylocation = 0;
					imgxlocation--;
				}
				if (imgxlocation == 0 && imgylocation > 2) {
					imgxlocation = 1;
					imgylocation = 0;
					setSwordAttacking(false);
					swordNomalForm();
					break;
				}
				getSsSword().setUrl("sword/sword_down.png");
				getSsSword().setWidth(SwordMotionSize.WIDTH);
				getSsSword().setHeight(SwordMotionSize.HEIGHT);
				getSsSword().setXPos(SwordMotionSize.WIDTH * imgxlocation);
				getSsSword().setYPos(SwordSize.SWORDIMGHEIGHT - (SwordMotionSize.IMGHEIGHT)
						+ (SwordMotionSize.HEIGHT * imgylocation));
				getSsSword().drawObj(enemyIssac.getXEnemy() - 28, enemyIssac.getYEnemy() + 25);
				imgylocation++;
			} else if (enemyIssac.getViewDirect() == ViewDirect.UP) {
				if (imgxlocation == 1 && imgylocation > 3) {
					imgylocation = 0;
					imgxlocation--;
				}
				if (imgxlocation == 0 && imgylocation > 2) {
					imgxlocation = 1;
					imgylocation = 0;
					setSwordAttacking(false);
					swordNomalForm();
					break;
				}
				getSsSword().setUrl("sword/sword_up.png");
				getSsSword().setWidth(SwordMotionSize.WIDTH);
				getSsSword().setHeight(SwordMotionSize.HEIGHT);
				getSsSword().setXPos(SwordMotionSize.WIDTH - (SwordMotionSize.WIDTH * imgxlocation));
				getSsSword().setYPos(
						SwordMotionSize.IMGHEIGHT - SwordMotionSize.HEIGHT - (SwordMotionSize.HEIGHT * imgylocation));
				getSsSword().drawObj(enemyIssac.getXEnemy() - 28, enemyIssac.getYEnemy() - 50);
				imgylocation++;
			} else if (enemyIssac.getViewDirect() == ViewDirect.LEFT) {
				if (imgxlocation == 1 && imgylocation > 3) {
					imgylocation = 0;
					imgxlocation--;
				}
				if (imgxlocation == 0 && imgylocation > 2) {
					imgxlocation = 1;
					imgylocation = 0;
					setSwordAttacking(false);
					swordNomalForm();
					break;
				}
				getSsSword().setUrl("sword/sword_left.png");
				getSsSword().setWidth(SwordMotionSize.HEIGHT);
				getSsSword().setHeight(SwordMotionSize.WIDTH);
				getSsSword().setXPos(
						(SwordMotionSize.IMGHEIGHT) - SwordMotionSize.HEIGHT - (SwordMotionSize.HEIGHT * imgylocation));
				getSsSword().setYPos(SwordMotionSize.WIDTH * imgxlocation);
				getSsSword().drawObj(enemyIssac.getXEnemy() - 60, enemyIssac.getYEnemy() - 10);

				imgylocation++;
			} else if (enemyIssac.getViewDirect() == ViewDirect.RIGHT) {
				if (imgxlocation == 1 && imgylocation > 3) {
					imgylocation = 0;
					imgxlocation--;
				}
				if (imgxlocation == 0 && imgylocation > 2) {
					imgxlocation = 1;
					imgylocation = 0;
					setSwordAttacking(false);
					swordNomalForm();
					break;
				}
				getSsSword().setUrl("sword/sword_right.png");
				getSsSword().setWidth(SwordMotionSize.HEIGHT);
				getSsSword().setHeight(SwordMotionSize.WIDTH);
				getSsSword().setXPos(
						SwordSize.SWORDIMGHEIGHT - SwordMotionSize.IMGHEIGHT + (SwordMotionSize.HEIGHT * imgylocation));
				getSsSword().setYPos(SwordMotionSize.WIDTH - (SwordMotionSize.WIDTH * imgxlocation));
				getSsSword().drawObj(enemyIssac.getXEnemy() + 20, enemyIssac.getYEnemy() - 10);
				imgylocation++;
			}
			try {
				Thread.sleep(30);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void swingAttack() {
		if (ssSword.getBounds().intersects(issac.getSsBody().getBounds()) && !issac.isInvincible()) {
			issac.setLife(issac.getLife() - enemyIssac.getAttackDamage());
			issac.issacInfoRefresh();
			issac.reDrawLife();
			if (issac.getLife() == 0) {
				issac.setDead(true);
			}
			if (!issac.isDead()) {
				issac.hitDelayMotion();
			}
			System.out.println(issac.getLife());
		}
	}

	public void dotAttack() {
		if (!isSwordAttacking()) {
			if (enemySwordControl.ssSword.getBounds().intersects(issac.getSsBody().getBounds()) && !issac.isInvincible()
					&& connectControl!=null && connectControl.getReciveDataClass()!=null && connectControl.getReciveDataClass().isStart()) {
				issac.setLife(issac.getLife() - enemyIssac.getAttackDamage());
				issac.issacInfoRefresh();
				issac.reDrawLife();
				if (issac.getLife() == 0) {
					issac.setDead(true);
				}
				if (!issac.isDead()) {
					issac.hitDelayMotion();
				}
			}
		}
	}
}
