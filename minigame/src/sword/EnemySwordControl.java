package sword;

import java.util.Vector;
import javax.swing.JFrame;
import SpriteSheet.SpriteSheet;
import enemy.EnemyIssac;
import imgSize.SwordMotionSize;
import imgSize.SwordSize;
import objectSetting.*;
import lombok.Data;
import monster.Monster;
import player.issac;

@Data
public class EnemySwordControl extends Sword {
	private final static String TAG = "SwordControl : ";
	private EnemySwordControl enemySwordControl = this;
	private SpriteSheet ssSword;
	private Sword sword;
	private issac issac;
	private EnemyIssac enemyIssac;

	public EnemySwordControl(JFrame app, issac issac, EnemyIssac enemyIssac) {
		super(app);
		System.out.println(TAG + "makeSword");
		init(issac, enemyIssac);
		setting();
		batch();
		swordNomalForm();
	}

	public void init(issac issac, EnemyIssac enemyIssac) {
		this.issac = issac;
		this.enemyIssac = enemyIssac;
		ssSword = new SpriteSheet("sword/sword_down.png", "issac_sword",
				SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH, SwordSize.SWORDYGAP,
				SwordSize.SWORDWIDTH, SwordSize.SWORDYHEIGHT - 2);
	}

	public void setting() {
		setXSword(492);
		setYSword(100);
		ssSword.drawObj(getXSword(), getYSword());
	}

	public void batch() {
		getApp().add(ssSword);

	}

	@Override
	public synchronized void swordNomalForm() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (!isSwordAttacking()) {
						if (enemyIssac.getViewDirect() == ViewDirect.LEFT) {
							if (!enemyIssac.isEnemyAttacking()) {
								enemySwordControl.getSsSword().setUrl("sword/sword_left.png");
								enemySwordControl.getSsSword().setXPos(
										SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP - SwordSize.SWORDYHEIGHT + 3);
								enemySwordControl.getSsSword().setYPos(
										SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH - 1);
								enemySwordControl.getSsSword().setWidth(SwordSize.SWORDYHEIGHT - 1);
								enemySwordControl.getSsSword().setHeight(SwordSize.SWORDWIDTH);
								if (enemySwordControl.getSsSword().getUrl().equals("sword/sword_left.png")) {
									enemySwordControl.getSsSword().drawObj(enemyIssac.getXEnemy() - 36,
											enemyIssac.getYEnemy() + 29);
								}
							}
						} else if (enemyIssac.getViewDirect() == ViewDirect.UP) {
							if (!enemyIssac.isEnemyAttacking()) {
								enemySwordControl.getSsSword().setUrl("sword/sword_up.png");
								enemySwordControl.getSsSword().setXPos(SwordSize.SWORDXGAP + 2);
								enemySwordControl.getSsSword().setYPos(
										SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP - SwordSize.SWORDYHEIGHT + 2);
								enemySwordControl.getSsSword().setWidth(SwordSize.SWORDWIDTH - 1);
								enemySwordControl.getSsSword().setHeight(SwordSize.SWORDYHEIGHT);
								if (enemySwordControl.getSsSword().getUrl().equals("sword/sword_up.png")) {
									enemySwordControl.getSsSword().drawObj(enemyIssac.getXEnemy() + 12,
											enemyIssac.getYEnemy() - 25);
								}
							}
						} else if (enemyIssac.getViewDirect() == ViewDirect.RIGHT) {
							if (!enemyIssac.isEnemyAttacking()) {
								enemySwordControl.getSsSword().setUrl("sword/sword_right.png");
								enemySwordControl.getSsSword().setXPos(SwordSize.SWORDYGAP);
								enemySwordControl.getSsSword().setYPos(SwordSize.SWORDXGAP + 1);
								enemySwordControl.getSsSword().setWidth(SwordSize.SWORDYHEIGHT - 1);
								enemySwordControl.getSsSword().setHeight(SwordSize.SWORDWIDTH);
								if (enemySwordControl.getSsSword().getUrl().equals("sword/sword_right.png")) {
									enemySwordControl.getSsSword().drawObj(enemyIssac.getXEnemy() + 34,
											enemyIssac.getYEnemy() + 28);
								}
							}
						} else if (enemyIssac.getViewDirect() == ViewDirect.DOWN) {
							if (!enemyIssac.isEnemyAttacking()) {
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
					}
					if(enemyIssac.isDead()) getApp().remove(ssSword);
					try {
						Thread.sleep(issac.getMoveSpeed());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public synchronized void swordAttackForm() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (!enemyIssac.isEnemyAttack()) {
					enemyIssac.setEnemyAttack(true);
					setSwordAttacking(true);
					if (isAttackKeyPress()) {
						int imgxlocation = 1;
						int imgylocation = 0;
						swingAttack();
						while (true) {
							enemyIssac.setEnemyAttacking(true);// 어택 모션중 모션 검 생성 제한
							if (enemyIssac.getViewDirect() == ViewDirect.DOWN) {
								if (imgxlocation == 1 && imgylocation > 3) {
									imgylocation = 0;
									imgxlocation--;
								}
								if (imgxlocation == 0 && imgylocation > 2) {
									imgxlocation = 1;
									imgylocation = 0;
									enemyIssac.setEnemyAttacking(false);
									setSwordAttacking(false);
									break;
								}
								enemySwordControl.getSsSword().setUrl("sword/sword_down.png");
								enemySwordControl.getSsSword().setWidth(SwordMotionSize.WIDTH);
								enemySwordControl.getSsSword().setHeight(SwordMotionSize.HEIGHT);
								enemySwordControl.getSsSword().setXPos(SwordMotionSize.WIDTH * imgxlocation);
								enemySwordControl.getSsSword().setYPos(SwordSize.SWORDIMGHEIGHT
										- (SwordMotionSize.IMGHEIGHT) + (SwordMotionSize.HEIGHT * imgylocation));
								enemySwordControl.getSsSword().drawObj(enemyIssac.getXEnemy() - 28,
										enemyIssac.getYEnemy() + 25);
								imgylocation++;
							} else if (enemyIssac.getViewDirect() == ViewDirect.UP) {
								if (imgxlocation == 1 && imgylocation > 3) {
									imgylocation = 0;
									imgxlocation--;
								}
								if (imgxlocation == 0 && imgylocation > 2) {
									imgxlocation = 1;
									imgylocation = 0;
									enemyIssac.setEnemyAttacking(false);
									setSwordAttacking(false);
									break;
								}
								enemySwordControl.getSsSword().setUrl("sword/sword_up.png");
								enemySwordControl.getSsSword().setWidth(SwordMotionSize.WIDTH);
								enemySwordControl.getSsSword().setHeight(SwordMotionSize.HEIGHT);
								enemySwordControl.getSsSword()
										.setXPos(SwordMotionSize.WIDTH - (SwordMotionSize.WIDTH * imgxlocation));
								enemySwordControl.getSsSword().setYPos(SwordMotionSize.IMGHEIGHT
										- SwordMotionSize.HEIGHT - (SwordMotionSize.HEIGHT * imgylocation));
								enemySwordControl.getSsSword().drawObj(enemyIssac.getXEnemy() - 28,
										enemyIssac.getYEnemy() - 50);
								imgylocation++;
							} else if (enemyIssac.getViewDirect() == ViewDirect.LEFT) {
								if (imgxlocation == 1 && imgylocation > 3) {
									imgylocation = 0;
									imgxlocation--;
								}
								if (imgxlocation == 0 && imgylocation > 2) {
									imgxlocation = 1;
									imgylocation = 0;
									enemyIssac.setEnemyAttacking(false);
									setSwordAttacking(false);
									break;
								}
								enemySwordControl.getSsSword().setUrl("sword/sword_left.png");
								enemySwordControl.getSsSword().setWidth(SwordMotionSize.HEIGHT);
								enemySwordControl.getSsSword().setHeight(SwordMotionSize.WIDTH);
								enemySwordControl.getSsSword().setXPos((SwordMotionSize.IMGHEIGHT)
										- SwordMotionSize.HEIGHT - (SwordMotionSize.HEIGHT * imgylocation));
								enemySwordControl.getSsSword().setYPos(SwordMotionSize.WIDTH * imgxlocation);
								enemySwordControl.getSsSword().drawObj(enemyIssac.getXEnemy() - 60,
										enemyIssac.getYEnemy() - 10);

								imgylocation++;
							} else if (enemyIssac.getViewDirect() == ViewDirect.RIGHT) {
								if (imgxlocation == 1 && imgylocation > 3) {
									imgylocation = 0;
									imgxlocation--;
								}
								if (imgxlocation == 0 && imgylocation > 2) {
									imgxlocation = 1;
									imgylocation = 0;
									enemyIssac.setEnemyAttacking(false);
									setSwordAttacking(false);
									break;
								}
								enemySwordControl.getSsSword().setUrl("sword/sword_right.png");
								enemySwordControl.getSsSword().setWidth(SwordMotionSize.HEIGHT);
								enemySwordControl.getSsSword().setHeight(SwordMotionSize.WIDTH);
								enemySwordControl.getSsSword().setXPos(SwordSize.SWORDIMGHEIGHT
										- SwordMotionSize.IMGHEIGHT + (SwordMotionSize.HEIGHT * imgylocation));
								enemySwordControl.getSsSword()
										.setYPos(SwordMotionSize.WIDTH - (SwordMotionSize.WIDTH * imgxlocation));
								enemySwordControl.getSsSword().drawObj(enemyIssac.getXEnemy() + 20,
										enemyIssac.getYEnemy() - 10);
								imgylocation++;
							}
							try {
								Thread.sleep(30);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}).start();
	}

	public void swingAttack() {
		if (isSwordAttacking()) {
			if(ssSword.getBounds().intersects(issac.getSsBody().getBounds())||ssSword.getBounds().intersects(issac.getSsHead().getBounds())) {
				issac.setLife(issac.getLife()-enemyIssac.getAttackDamage());
			}
		}
	}
}
