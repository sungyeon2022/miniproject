package sword;

import java.util.Vector;
import javax.swing.JFrame;
import SpriteSheet.SpriteSheet;
import connect.ConnectControl;
import imgSize.SwordMotionSize;
import imgSize.SwordSize;
import objectSetting.*;
import lombok.Data;
import main.miniApp;
import monster.Monster;
import player.issac;

@Data
public class SwordControl extends Sword {
	private final static String TAG = "SwordControl : ";
	private SwordControl swordControl = this;
	private SpriteSheet ssSword;
	private Sword sword;
	private issac issac;
	private Vector<Monster> monsters;
	private ConnectControl connectControl;

	public SwordControl(miniApp app, issac issac, Vector<Monster> monsters, ConnectControl connectControl) {
		super(app);
		System.out.println(TAG + "makeSword");
		init(issac, monsters, connectControl);
		setting();
		batch();
		swordNomalForm();
		dotAttack();
	}

	public void init(issac issac, Vector<Monster> monsters, ConnectControl connectControl) {
		this.issac = issac;
		this.monsters = monsters;
		this.connectControl = connectControl;
		ssSword = new SpriteSheet("sword/sword_up.png", "issac_sword", SwordSize.SWORDXGAP + 2,
				SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP - SwordSize.SWORDYHEIGHT + 2, SwordSize.SWORDWIDTH - 1,
				SwordSize.SWORDYHEIGHT);
	}

	public void setting() {
		setXSword(492);
		setYSword(405);
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
				while (!isSwordAttacking()) {
					if (issac.isKeyPress()) {
						swordAttackForm();
						break;
					} else {
						if (issac.getViewDirect() == ViewDirect.LEFT) {
							swordControl.getSsSword().setUrl("sword/sword_left.png");
							swordControl.getSsSword().setXPos(
									SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP - SwordSize.SWORDYHEIGHT + 3);
							swordControl.getSsSword()
									.setYPos(SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH - 1);
							swordControl.getSsSword().setWidth(SwordSize.SWORDYHEIGHT - 1);
							swordControl.getSsSword().setHeight(SwordSize.SWORDWIDTH);
							if (swordControl.getSsSword().getUrl().equals("sword/sword_left.png")) {
								swordControl.getSsSword().drawObj(issac.getXPlayer() - 36, issac.getYPlayer() + 29);
							}

						} else if (issac.getViewDirect() == ViewDirect.UP) {
							swordControl.getSsSword().setUrl("sword/sword_up.png");
							swordControl.getSsSword().setXPos(SwordSize.SWORDXGAP + 2);
							swordControl.getSsSword().setYPos(
									SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP - SwordSize.SWORDYHEIGHT + 2);
							swordControl.getSsSword().setWidth(SwordSize.SWORDWIDTH - 1);
							swordControl.getSsSword().setHeight(SwordSize.SWORDYHEIGHT);
							if (swordControl.getSsSword().getUrl().equals("sword/sword_up.png")) {
								swordControl.getSsSword().drawObj(issac.getXPlayer() + 12, issac.getYPlayer() - 25);

							}
						} else if (issac.getViewDirect() == ViewDirect.RIGHT) {
							swordControl.getSsSword().setUrl("sword/sword_right.png");
							swordControl.getSsSword().setXPos(SwordSize.SWORDYGAP);
							swordControl.getSsSword().setYPos(SwordSize.SWORDXGAP + 1);
							swordControl.getSsSword().setWidth(SwordSize.SWORDYHEIGHT - 1);
							swordControl.getSsSword().setHeight(SwordSize.SWORDWIDTH);
							if (swordControl.getSsSword().getUrl().equals("sword/sword_right.png")) {
								swordControl.getSsSword().drawObj(issac.getXPlayer() + 34, issac.getYPlayer() + 28);

							}
						} else if (issac.getViewDirect() == ViewDirect.DOWN) {
							swordControl.getSsSword().setUrl("sword/sword_down.png");
							swordControl.getSsSword()
									.setXPos(SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH);
							swordControl.getSsSword().setYPos(SwordSize.SWORDYGAP);
							swordControl.getSsSword().setWidth(SwordSize.SWORDWIDTH);
							swordControl.getSsSword().setHeight(SwordSize.SWORDYHEIGHT - 2);
							if (swordControl.getSsSword().getUrl().equals("sword/sword_down.png")) {
								swordControl.getSsSword().drawObj(issac.getXPlayer() + 12, issac.getYPlayer() + 50);
							}

						}
						if (issac.isDead())
							swordControl.ssSword.setVisible(false);
					}
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
		int imgxlocation = 1;
		int imgylocation = 0;
		swingAttack();
		while (true) {
			setSwordAttacking(true);// 어택 모션중 모션 검 생성 제한
			if (issac.getViewDirect() == ViewDirect.DOWN) {
				if (imgxlocation == 1 && imgylocation > 3) {
					imgylocation = 0;
					imgxlocation--;
				}
				if (imgxlocation == 0 && imgylocation > 2) {
					imgxlocation = 1;
					imgylocation = 0;
					break;
				}
				swordControl.getSsSword().setUrl("sword/sword_down.png");
				swordControl.getSsSword().setWidth(SwordMotionSize.WIDTH);
				swordControl.getSsSword().setHeight(SwordMotionSize.HEIGHT);
				swordControl.getSsSword().setXPos(SwordMotionSize.WIDTH * imgxlocation);
				swordControl.getSsSword().setYPos(SwordSize.SWORDIMGHEIGHT - (SwordMotionSize.IMGHEIGHT)
						+ (SwordMotionSize.HEIGHT * imgylocation));
				swordControl.getSsSword().drawObj(issac.getXPlayer() - 28, issac.getYPlayer() + 25);
				imgylocation++;
			} else if (issac.getViewDirect() == ViewDirect.UP) {
				if (imgxlocation == 1 && imgylocation > 3) {
					imgylocation = 0;
					imgxlocation--;
				}
				if (imgxlocation == 0 && imgylocation > 2) {
					imgxlocation = 1;
					imgylocation = 0;
					break;
				}
				swordControl.getSsSword().setUrl("sword/sword_up.png");
				swordControl.getSsSword().setWidth(SwordMotionSize.WIDTH);
				swordControl.getSsSword().setHeight(SwordMotionSize.HEIGHT);
				swordControl.getSsSword().setXPos(SwordMotionSize.WIDTH - (SwordMotionSize.WIDTH * imgxlocation));
				swordControl.getSsSword().setYPos(
						SwordMotionSize.IMGHEIGHT - SwordMotionSize.HEIGHT - (SwordMotionSize.HEIGHT * imgylocation));
				swordControl.getSsSword().drawObj(issac.getXPlayer() - 28, issac.getYPlayer() - 50);
				imgylocation++;
			} else if (issac.getViewDirect() == ViewDirect.LEFT) {
				if (imgxlocation == 1 && imgylocation > 3) {
					imgylocation = 0;
					imgxlocation--;
				}
				if (imgxlocation == 0 && imgylocation > 2) {
					imgxlocation = 1;
					imgylocation = 0;
					break;
				}
				swordControl.getSsSword().setUrl("sword/sword_left.png");
				swordControl.getSsSword().setWidth(SwordMotionSize.HEIGHT);
				swordControl.getSsSword().setHeight(SwordMotionSize.WIDTH);
				swordControl.getSsSword().setXPos(
						(SwordMotionSize.IMGHEIGHT) - SwordMotionSize.HEIGHT - (SwordMotionSize.HEIGHT * imgylocation));
				swordControl.getSsSword().setYPos(SwordMotionSize.WIDTH * imgxlocation);
				swordControl.getSsSword().drawObj(issac.getXPlayer() - 60, issac.getYPlayer() - 10);

				imgylocation++;
			} else if (issac.getViewDirect() == ViewDirect.RIGHT) {
				if (imgxlocation == 1 && imgylocation > 3) {
					imgylocation = 0;
					imgxlocation--;
				}
				if (imgxlocation == 0 && imgylocation > 2) {
					imgxlocation = 1;
					imgylocation = 0;
					break;
				}
				swordControl.getSsSword().setUrl("sword/sword_right.png");
				swordControl.getSsSword().setWidth(SwordMotionSize.HEIGHT);
				swordControl.getSsSword().setHeight(SwordMotionSize.WIDTH);
				swordControl.getSsSword().setXPos(
						SwordSize.SWORDIMGHEIGHT - SwordMotionSize.IMGHEIGHT + (SwordMotionSize.HEIGHT * imgylocation));
				swordControl.getSsSword().setYPos(SwordMotionSize.WIDTH - (SwordMotionSize.WIDTH * imgxlocation));
				swordControl.getSsSword().drawObj(issac.getXPlayer() + 20, issac.getYPlayer() - 10);
				imgylocation++;
			}
			try {
				Thread.sleep(30);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		setSwordAttacking(false);
		swordNomalForm();
	}

	public void dotAttack() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!Thread.interrupted() && !issac.isDead() && monsters != null) {
					boolean ismonster = false;
					if (!monsters.isEmpty() && !isSwordAttacking()) {
						for (int i = 0; i < monsters.size(); i++) {
							if (swordControl.getSsSword().getBounds()
									.intersects(monsters.get(i).getSsMonster().getBounds()) && !isSwordAttacking()) {
								System.out.println(isSwordAttacking());
								System.out.println("공격 적용");
								monsters.get(i).setLife(monsters.get(i).getLife() - issac.getAttackDamage());
								ismonster = true;
							}
						}
					}
					try {
						if (ismonster && !isSwordAttacking()) {
							Thread.sleep(1000);
						} else {
							Thread.sleep(500);
						}
					} catch (Exception e) {
					}
				}
			}
		}).start();
	}

	public void swingAttack() {
		if (monsters != null && !monsters.isEmpty()) {
			for (int i = 0; i < monsters.size(); i++) {
				if (swordControl.getSsSword().getBounds().intersects(monsters.get(i).getSsMonster().getBounds())) {
					monsters.get(i).setLife(monsters.get(i).getLife() - issac.getAttackDamage());
					System.out.println(monsters.get(i).getLife());
				}
			}
		}
	}
}
