package enemy;

import java.util.Vector;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import connect.ConnectControl;
import imgSize.*;
import lombok.Data;
import main.miniApp;
import objectSetting.ViewDirect;
import wall.*;
import item.*;
import player.*;

@Data

public class EnemyIssac extends Enemy {
	private final static String TAG = "issac: ";
	private ConnectControl connectControl;
	private SpriteSheet ssHead, ssBody, ssDead;
	private SpriteSheet ssTotal;
	private Vector<SpriteSheet> ssLife;
	private int xPlusBody = 8, yPlusBody = 30;
	private issac issac;
	private int movespeed = 5;
	// 상하 좌우 이동 모션
	// 개수 및 수치 표시용 레이블 + 변수 선언 및 초기화
	private int bombCount = 0;
	private int speedNum = 11;
	private int powerNum = 1;
	private int attackspeedNum = 1;

	public EnemyIssac(miniApp app, Vector<structure> walls, Vector<Item> items, issac issac, ConnectControl connectControl) {
		super(app);
		System.out.println(TAG + "make issac");
		init(walls, items, issac, connectControl);
		setting();
	}

	public void init(Vector<structure> walls, Vector<Item> items, issac issac, ConnectControl connectControl) {
		this.issac = issac;
		this.connectControl = connectControl;
		ssHead = new SpriteSheet("issac/issac.png", "issacssHead", 0, 0, issacSize.issacHEADWIDTH,
				issacSize.issacHEADHEIGHT);
		ssBody = new SpriteSheet("issac/issac.png", "issacBody", 0, (issacSize.issacHEADHEIGHT + Gap.ROWGAP),
				issacSize.issacBODYWIDTH, issacSize.issacBODYHEIGHT);
		ssDead = new SpriteSheet("issac/issac.png", "issacDead", 63, 215, 42, 48);
		ssLife = new Vector<SpriteSheet>();
		for (int i = 0; i < getLife(); i++) {
			this.ssLife.add(i,
					new SpriteSheet("issac/life.png", "life", 0, 0, Lifesize.LIFEWIDTH, Lifesize.LIFEHEIGHT));
		}
		for (int i = (int) getLife(); i < getMaxlife(); i++) {
			this.ssLife.add(new SpriteSheet("issac/life.png", "life", Lifesize.LIFEWIDTH * 2 + Gap.COLUMGAP * 2, 0,
					Lifesize.LIFEWIDTH, Lifesize.LIFEHEIGHT));
		}
	}

	public synchronized void setting() {
		setViewDirect(ViewDirect.DOWN);
		setXEnemy(449);
		setYEnemy(110);
		setAttackDamage(1);
		setXEnemyCenter(getXEnemy() + issacSize.issacHEADWIDTH / 2);
		setYEnemyCenter(getYEnemy() + issacSize.issacHEADHEIGHT);

		for (int i = 0; i < getMaxlife(); i++) {
			if (i <= getLife()) {
				ssLife.get(i).drawObj(680 + (i * 30), 10);
			} else {
				ssLife.get(i).drawObj(680 + (i * 30), 10);
			}
		}
	}

	public void batch() {
		ssHead.drawObj(getXEnemy(), getYEnemy());
		ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
		getApp().add(ssHead, 0);
		getApp().add(ssBody, 1);
		getApp().add(ssDead, 2);
		moveMotion();
		// 폭탄 파워 속도 레이블 추가
		for (int i = 0; i < getMaxlife(); i++) {
			getApp().add(ssLife.get(i), 1);
			ssLife.get(i).setVisible(false);
			ssLife.get(i).setVisible(true);
		}
	}

	@Override
	public synchronized void moveMotion() { // 움직이는 동작중 이미지 갱신
		// Down을 기준으로 설명하겠습니다 나머지 내용은 ColumGap과 RowGap, HEIGHT, WIDTH로 상하 좌우가 구분됩니다
		new Thread(new Runnable() {
			@Override
			public void run() {
				int motion = 0;
				if (!isEnemyMoveStart()) {
					setEnemyMoveStart(true);
					while (!isDead()) {
						receiveData();
						if (getViewDirectInfo()[ViewDirect.DOWN] && getViewDirect() == ViewDirect.DOWN) {
							if (motion > 9 * 5) // 상하좌우 방향 모션 개수와 동일 0~9 10개
								motion = 0;// 마지막사진 도착후 처음으로 순환을 위한 if문 종료
							ssBody.setXPos((issacSize.issacBODYWIDTH * (motion / 5)) + (Gap.COLUMGAP * (motion / 5))); // XPos는
																														// 사진에서
							if (getViewDirect() == ViewDirect.DOWN) {
								ssHead.setXPos(0); // 첫번째 사진이므로 0 다른 내용은 images/issac/issac.img에서 순서 확인하시면 됩니다.
								ssBody.setYPos(issacSize.issacHEADHEIGHT + Gap.ROWGAP);// X좌표로 순서를 정하고 Y좌표는 사진사이의 간격과
																						// 머리 이미지를 무시해야 하기에 머리 이미지의 크기만큼
																						// 더해서 좌표값을 내려줍니다
								receiveData();
								ssHead.drawObj(getXEnemy(), getYEnemy()); // 그려지는 기준점이 되는 캐릭터(몬스터의) 좌표값을 설정합니다.
								ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);// X와Y좌표를 기준으로 머리를
																									// 생성하고 머리와 몸이 겹치지
																									// // 않게하기위해 사용합니다.
								motion += 1;
							}

						} else if (getViewDirectInfo()[ViewDirect.LEFT] && getViewDirect() == ViewDirect.LEFT) {
							if (motion > 9 * 5)
								motion = 0;
							ssBody.setXPos((issacSize.issacBODYWIDTH * (motion / 5)) + (Gap.COLUMGAP * (motion / 5)));
							if (getViewDirect() == ViewDirect.LEFT) {
								ssHead.setXPos(issacSize.issacHEADWIDTH * 6 + Gap.HEADCOLUMGAP * 6);
								ssBody.setYPos(
										issacSize.issacHEADHEIGHT + issacSize.issacBODYHEIGHT * 2 + Gap.ROWGAP * 3);
								receiveData();
								ssHead.drawObj(getXEnemy(), getYEnemy());
								ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);

								motion += 1;
							}

						} else if (getViewDirectInfo()[ViewDirect.UP] && getViewDirect() == ViewDirect.UP) {
							if (motion > 9 * 4)
								motion = 0;
							ssBody.setXPos((issacSize.issacBODYWIDTH * (motion / 5)) + (Gap.COLUMGAP * (motion / 5)));
							if (getViewDirect() == ViewDirect.UP) {
								ssHead.setXPos(issacSize.issacHEADWIDTH * 4 + Gap.HEADCOLUMGAP * 4);
								ssBody.setYPos(issacSize.issacHEADHEIGHT + Gap.ROWGAP);
								receiveData();
								ssHead.drawObj(getXEnemy(), getYEnemy());
								ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
								motion += 1;
							}

						} else if (getViewDirectInfo()[ViewDirect.RIGHT] && getViewDirect() == ViewDirect.RIGHT) {
							if (motion > 9 * 4)
								motion = 0;
							ssBody.setXPos((issacSize.issacBODYWIDTH * (motion / 5)) + (Gap.COLUMGAP * (motion / 5)));
							if (getViewDirect() == ViewDirect.RIGHT) {
								ssHead.setXPos(issacSize.issacHEADWIDTH * 2 + Gap.HEADCOLUMGAP * 2);
								ssBody.setYPos(issacSize.issacHEADHEIGHT + issacSize.issacBODYHEIGHT + Gap.ROWGAP * 2);
								receiveData();
								ssHead.drawObj(getXEnemy(), getYEnemy());
								ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
								motion += 1;
							}

						} else {
							ssBody.setXPos(0);
							ssHead.drawObj(getXEnemy(), getYEnemy());
							ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
						}
						receiveData();
						if (isSendInvincible()&&!isDead()) {
							hitCheck();
						}
						if (getLife() == 0) {
							setDead(true);
							break;
						}
						try {
							Thread.sleep(movespeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					deadMotion();
				}
			}
		}).start();
	}

	public void refreshDirect() {
		if (getViewDirectInfo()[ViewDirect.DOWN]) {
			setViewDirect(ViewDirect.DOWN);
		}
		if (getViewDirectInfo()[ViewDirect.LEFT]) {
			setViewDirect(ViewDirect.LEFT);
		}
		if (getViewDirectInfo()[ViewDirect.UP]) {
			setViewDirect(ViewDirect.UP);
		}
		if (getViewDirectInfo()[ViewDirect.RIGHT]) {
			setViewDirect(ViewDirect.RIGHT);
		}
	}

	public void reDrawLife() {
		double currentLife = getLife();
		for (int i = 0; i < getMaxlife(); i++) {
			if (currentLife >= 1) {
				ssLife.get(i).setXPos(0);
				ssLife.get(i).drawObj(680 + (i * 30), 10);
				currentLife -= 1;
			} else if (currentLife > 0 && currentLife < 1) {
				ssLife.get(i).setXPos(Lifesize.LIFEWIDTH + Gap.COLUMGAP);
				ssLife.get(i).drawObj(680 + (i * 30), 10);
				currentLife -= 0.5;
			} else {
				ssLife.get(i).setXPos(Lifesize.LIFEWIDTH * 2 + Gap.COLUMGAP * 2);
				ssLife.get(i).drawObj(680 + (i * 30), 10);
			}

		}
	}

	public void hitCheck() {
		new Thread(() -> {
			if (!isInvincible()) {
				setInvincible(true);
				ssBody.setVisible(false);
				ssHead.setVisible(false);
				ssDead.drawObj(getXEnemy(), getYEnemy());
				ssDead.setVisible(true);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ssDead.setVisible(false);
				ssBody.setVisible(true);
				ssHead.setVisible(true);
				for (int i = 300; i > 0; i -= 50) {
					ssBody.setVisible(false);
					ssHead.setVisible(false);
					try {
						Thread.sleep(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ssBody.setVisible(true);
					ssHead.setVisible(true);
					try {
						Thread.sleep(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				setInvincible(false);
			}
		}).start();
	}

	public void deadMotion() {
		ssBody.setVisible(false);
		ssHead.setVisible(false);
		ssDead.setVisible(true);
		ssDead.drawObj(getXEnemy(), getYEnemy());
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int gap = 51;
		for (int i = 0; i < 2; i++) {
			ssDead.setXPos(ssDead.getXPos() + gap * i);
			ssDead.setWidth(55);
			ssDead.drawObj(getXEnemy(), getYEnemy());
		}
	}

	public void receiveData() {
		if (connectControl.isIsconnect() && connectControl.isReciveMulti()) {
			setXEnemy(connectControl.getReciveDataClass().getXPlayer());
			setYEnemy(connectControl.getReciveDataClass().getYPlayer());
			setViewDirect(connectControl.getReciveDataClass().getIntView());
			setViewDirectInfo(connectControl.getReciveDataClass().getBooleanView());
			setKeyPress(connectControl.getReciveDataClass().isAttack());
			setLife(connectControl.getReciveDataClass().getLife());
			reDrawLife();
			setSendInvincible(connectControl.getReciveDataClass().isInvincible());
			setMovespeed(connectControl.getReciveDataClass().getMoveSpeed());
			setAttackDamage(connectControl.getReciveDataClass().getAttackDamage());
			setDead(connectControl.getReciveDataClass().isDead());
		}
	}
}