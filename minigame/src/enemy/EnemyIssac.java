package enemy;

import java.util.Vector;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import connect.ConnectControl;
import imgSize.*;
import lombok.Data;
import objectSetting.ViewDirect;
import wall.*;
import item.*;
import player.*;

@Data

public class EnemyIssac extends Enemy {
	private final static String TAG = "issac: ";
	private ConnectControl connectControl;
	private SpriteSheet ssHead, ssBody;
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

	public EnemyIssac(JFrame app, Vector<wall> walls, Vector<Item> items, issac issac, ConnectControl connectControl) {
		super(app);
		System.out.println(TAG + "make issac");
		init(walls, items, issac, connectControl);
		setting();
		batch();
		ReceiveThread();
		moveMotion();
	}

	public void init(Vector<wall> walls, Vector<Item> items, issac issac, ConnectControl connectControl) {
		this.issac = issac;
		this.connectControl = connectControl;
		ssHead = new SpriteSheet("issac/issac.png", "issacssHead", 0, 0, issacSize.issacHEADWIDTH,
				issacSize.issacHEADHEIGHT);
		ssBody = new SpriteSheet("issac/issac.png", "issacBody", 0, (issacSize.issacHEADHEIGHT + Gap.ROWGAP),
				issacSize.issacBODYWIDTH, issacSize.issacBODYHEIGHT);
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

	public void setting() {
		setViewDirect(ViewDirect.DOWN);
		setXEnemy(449);
		setYEnemy(110);
		setAttackDamage(1);
		setLife(3);
		setXEnemyCenter(getXEnemy() + issacSize.issacHEADWIDTH / 2);
		setYEnemyCenter(getYEnemy() + issacSize.issacHEADHEIGHT);
		ssHead.drawObj(getXEnemy(), getYEnemy());
		ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
		for (int i = 0; i < getMaxlife(); i++) {
			if (i <= getLife()) {
				ssLife.get(i).drawObj(680 + (i * 30), 10);
			} else {
				ssLife.get(i).drawObj(680 + (i * 30), 10);
			}

		}
	}

	public void batch() {
		getApp().add(ssHead, 0);
		getApp().add(ssBody, 1);
		// 폭탄 파워 속도 레이블 추가
		for (int i = 0; i < getMaxlife(); i++) {
			getApp().add(ssLife.get(i), 1);
		}
	}

	@Override
	public void moveMotion() { // 움직이는 동작중 이미지 갱신
		// Down을 기준으로 설명하겠습니다 나머지 내용은 ColumGap과 RowGap, HEIGHT, WIDTH로 상하 좌우가 구분됩니다
		new Thread(new Runnable() {
			@Override
			public void run() {
				int motion = 0;
				if (!isEnemyMoveStart()) {
					setEnemyMoveStart(true);
					while (true) {
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
								ssHead.drawObj(getXEnemy(), getYEnemy());
								ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
								motion += 1;
							}

						} else {
							ssBody.setXPos(0);
							ssHead.drawObj(getXEnemy(), getYEnemy());
							ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
						}
						try {
							Thread.sleep(movespeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

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
				currentLife -= 1;
			} else if (currentLife > 0 && currentLife < 1) {
				ssLife.get(i).setXPos(Lifesize.LIFEWIDTH + Gap.COLUMGAP);
				currentLife -= 0.5;
			} else {
				ssLife.get(i).setXPos(Lifesize.LIFEWIDTH * 2 + Gap.COLUMGAP * 2);
			}

		}
	}

	public void ReceiveThread() {
		new Thread(() -> {
			while (!isDead()) {
				System.out.println(connectControl.getReciveMap());
				if (!connectControl.getReciveMap().isEmpty()) {
					setXEnemy((int) connectControl.getReciveMap().get("PlayerX") - issacSize.issacHEADWIDTH
							- issacSize.issacBODYWIDTH + xPlusBody + 1);
					setYEnemy((int) connectControl.getReciveMap().get("PlayerY")
							- (issacSize.issacBODYHEIGHT + issacSize.issacHEADHEIGHT) * 2 + issacSize.issacBODYHEIGHT);
					setViewDirect((int) connectControl.getReciveMap().get("intView"));
					setViewDirectInfo(((boolean[]) connectControl.getReciveMap().get("booleanView")));
				} else {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}).start();
	}
	// 주변 아이템 여부 체크

}