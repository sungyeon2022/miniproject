package player;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;

import sword.SwordControl;
import SpriteSheet.SpriteSheet;
import connect.ConnectControl;
import data.DataClass;
import imgSize.*;
import lombok.Data;
import monster.Monster;
import monster.Worm;
import objectSetting.ViewDirect;
import startButton.StartButtonControl;
import wall.*;
import item.*;

@Data

public class issac extends Player {
	private final static String TAG = "issac: ";
	private issac issac = this;
//	private EnemyIssac enemyIssac;
	private StartButtonControl startButtonControl;
	private SpriteSheet ssHead, ssBody;
	private SpriteSheet ssDead, ssHit;
	private Vector<SpriteSheet> ssLife;
	private SwordControl swordControl;
	private Worm worm;
	private Vector<Monster> monsters;
	private Vector<wall> walls;
	private Vector<Item> items;
	private int xPlusBody = 8, yPlusBody = 30;
	private int item1Count = 0;
	private int item2Count = 0;
	private int item3Count = 0;
	private int item4Count = 0;
	private int moveSpeed = 5;
	// 상하 좌우 이동 모션
	// 개수 및 수치 표시용 레이블 + 변수 선언 및 초기화
	private JLabel labomb;
	private JLabel laspeed;
	private JLabel lapower;
	private JLabel laattackspeed;
	private int bombCount = 0;
	private int speedNum = 11;
	private int powerNum = 1;
	private int attackspeedNum = 1;
	private ConnectControl connectControl;

	public issac(JFrame app, Vector<Monster> monsters, Vector<wall> walls, Vector<Item> items,
			StartButtonControl startButtonControl, ConnectControl connectControl) {
		super(app);
		System.out.println(TAG + "make issac");
		init(monsters, walls, items, startButtonControl, connectControl);
		setting();
		batch();
		moveMotion();
	}

	public void init(Vector<Monster> monsters, Vector<wall> walls, Vector<Item> items,
			StartButtonControl startButtonControl, ConnectControl connectControl) {
		this.connectControl = connectControl;
		this.startButtonControl = startButtonControl;
		this.walls = walls;
		this.items = items;
		this.monsters = monsters;
		ssHead = new SpriteSheet("issac/issac.png", "issacssHead", issacSize.issacHEADWIDTH * 4 + Gap.HEADCOLUMGAP * 4,
				0, issacSize.issacHEADWIDTH, issacSize.issacHEADHEIGHT);
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

	public void setting() {
		setViewDirect(ViewDirect.UP);
		setXPlayer(getDefaultX());
		setYPlayer(getDefaultY());
		setAttackDamage(1);
		setXPlayerCenter(getXPlayer() + issacSize.issacHEADWIDTH / 2);
		setYPlayerCenter(getYPlayer() + (issacSize.issacHEADHEIGHT+issacSize.issacBODYHEIGHT)/2);
		ssHead.drawObj(getXPlayer(), getYPlayer());
		ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
		for (int i = 0; i < getMaxlife(); i++) {
			if (i <= getLife()) {
				ssLife.get(i).drawObj(10 + (i * 30), 10);
			} else {
				ssLife.get(i).drawObj(10 + (i * 30), 10);
			}

		}
	}

	public void batch() {
		getApp().add(ssHead, 0);
		getApp().add(ssBody, 1);
		getApp().add(ssDead, 2);
		for (int i = 0; i < getMaxlife(); i++) {
			getApp().add(ssLife.get(i), 1);
		}
	}

	// 상하 좌우 이동 모션
	@Override
	public void moveRight() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (!isRight()) {
					setRight(true);
					setViewDirect(ViewDirect.RIGHT);
					issac.setSendViewDirect(ViewDirect.LEFT);
					issac.getViewDirectInfo()[ViewDirect.LEFT] = true;
					issacInfoRefresh();
					while (isRight()) {
						if (isDead())
							break;
						if (getXPlayer() + issacSize.issacBODYWIDTH > 810) { // 벽이상 움직임 제한
							setRight(false);
							refreshDirect();
							break;
						}
						boolean isrock = false;
						// 돌 충돌 체크 시작
						if (!walls.isEmpty()) {
							for (int i = 0; i < walls.size(); i++) {
								if (!walls.get(i).isBroken() && walls.get(i).getSswall().getGubun() == "rock") {
									if (issac.getSsBody().getBounds()
											.intersects(walls.get(i).getSswall().getBounds())) {
										setXPlayer(getXPlayer() - 2);
										issacInfoRefresh();
										isrock = true;
									}
								}
							}
						}
						if (isrock)
							break;
						// 돌 충돌 체크 끝
						setXPlayer(getXPlayer() + 1);
						setXPlayerCenter(getXPlayerCenter() + 1);
						issacInfoRefresh();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
				}
			}
		}).start();
	}

	@Override
	public void moveLeft() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (isLeft() == false) {
					setLeft(true);
					setViewDirect(ViewDirect.LEFT);
					issac.setSendViewDirect(ViewDirect.RIGHT);
					issac.getViewDirectInfo()[ViewDirect.RIGHT] = true;
					issacInfoRefresh();
					while (isLeft()) {
						if (isDead())
							break;
						if (getXPlayer() < 110) {
							setLeft(false);
							refreshDirect();
							break;
						}
						boolean isrock = false;
						if (!walls.isEmpty()) {
							for (int i = 0; i < walls.size(); i++) {
								if (!walls.get(i).isBroken() && walls.get(i).getSswall().getGubun() == "rock") {
									if (issac.getSsBody().getBounds()
											.intersects(walls.get(i).getSswall().getBounds())) {
										setXPlayer(getXPlayer() + 2);
										issacInfoRefresh();
										isrock = true;
									}
								}
							}
						}
						// 돌 충돌 체크 끝
						if (isrock)
							break;
						setXPlayer(getXPlayer() - 1);
						setXPlayerCenter(getXPlayerCenter() - 1);
						issacInfoRefresh();
						try {

							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
				}
			}
		}).start();
	}

	@Override
	public void moveDown() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (isDown() == false) {
					setDown(true);
					setViewDirect(ViewDirect.DOWN);
					issac.setSendViewDirect(ViewDirect.UP);
					issac.getViewDirectInfo()[ViewDirect.UP] = true;
					issacInfoRefresh();
					while (isDown()) {
						if (isDead())
							break;
						if (getYPlayer() > 440) {
							setDown(false);
							refreshDirect();
							break;
						}
						// 돌 충돌 체크 시작
						boolean isrock = false;
						if (!walls.isEmpty()) {
							for (int i = 0; i < walls.size(); i++) {
								if (!walls.get(i).isBroken() && walls.get(i).getSswall().getGubun() == "rock") {
									if (issac.getSsBody().getBounds()
											.intersects(walls.get(i).getSswall().getBounds())) {
										setYPlayer(getYPlayer() - 2);
										issacInfoRefresh();
										isrock = true;
									}
								}
							}
						}
						if (isrock)
							break;
						// 돌 충돌 체크 끝
						setYPlayer(getYPlayer() + 1);// 플레이어 이동시 좌표값 변경
						setYPlayerCenter(getYPlayerCenter() + 1);// 중앙
						issacInfoRefresh();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
				}
			}
		}).start();
	}

	@Override
	public void moveUp() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (isUp() == false) {
					setUp(true);
					setViewDirect(ViewDirect.UP);
					issac.setSendViewDirect(ViewDirect.DOWN);
					issac.getViewDirectInfo()[ViewDirect.DOWN] = true;
					issacInfoRefresh();
					while (isUp()) {
						if (isDead())
							break;
						if (getYPlayer() < 80) {
							setUp(false);
							refreshDirect();
							break;
						}
						boolean isrock = false;
						if (!walls.isEmpty()) {
							for (int i = 0; i < walls.size(); i++) {
								if (!walls.get(i).isBroken() && walls.get(i).getSswall().getGubun() == "rock") {
									if (issac.getSsBody().getBounds()
											.intersects(walls.get(i).getSswall().getBounds())) {
										setYPlayer(getYPlayer() + 2);
										issacInfoRefresh();
										isrock = true;
									}
								}
							}
						}
						// 돌 충돌 체크 끝
						if (isrock)
							break;
						setYPlayer(getYPlayer() - 1);
						setYPlayerCenter(getYPlayerCenter() - 1);
						issacInfoRefresh();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
				}
			}
		}).start();
	}

	@Override
	public synchronized void moveMotion() { // 움직이는 동작중 이미지 갱신
		// Down을 기준으로 설명하겠습니다 나머지 내용은 ColumGap과 RowGap, HEIGHT, WIDTH로 상하 좌우가 구분됩니다
		// 상시작동 & 상태 검사
		new Thread(new Runnable() {
			@Override
			public void run() {
				int motion = 0;
				if (isPlayerMoveStart() == false) {
					setPlayerMoveStart(true);
					while (!isDead()) {
						if (isDown() && getViewDirect() == ViewDirect.DOWN) {
							if (motion > 9 * 5) // 상하좌우 방향 모션 개수와 동일 0~9 10개
								motion = 0;// 마지막사진 도착후 처음으로 순환을 위한 if문 종료
							ssBody.setXPos((issacSize.issacBODYWIDTH * (motion / 5)) + (Gap.COLUMGAP * (motion / 5))); // XPos는
																														// 사진에서
							if (getViewDirect() == ViewDirect.DOWN) {
								ssHead.setXPos(0); // 첫번째 사진이므로 0 다른 내용은 images/issac/issac.img에서 순서 확인하시면 됩니다.
								ssBody.setYPos(issacSize.issacHEADHEIGHT + Gap.ROWGAP);// X좌표로 순서를 정하고 Y좌표는 사진사이의 간격과
																						// 머리 이미지를 무시해야 하기에 머리 이미지의 크기만큼
																						// 더해서 좌표값을 내려줍니다
								ssHead.drawObj(getXPlayer(), getYPlayer()); // 그려지는 기준점이 되는 캐릭터(몬스터의) 좌표값을 설정합니다.
								ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
								// X와Y좌표를 기준으로 머리를
								// 생성하고 머리와 몸이 겹치지
								// // 않게하기위해 사용합니다.
								motion += 1;
							}
						} else if (isLeft() && getViewDirect() == ViewDirect.LEFT) {
							if (motion > 9 * 5)
								motion = 0;
							ssBody.setXPos((issacSize.issacBODYWIDTH * (motion / 5)) + (Gap.COLUMGAP * (motion / 5)));
							if (getViewDirect() == ViewDirect.LEFT) {
								ssHead.setXPos(issacSize.issacHEADWIDTH * 6 + Gap.HEADCOLUMGAP * 6);
								ssBody.setYPos(
										issacSize.issacHEADHEIGHT + issacSize.issacBODYHEIGHT * 2 + Gap.ROWGAP * 3);
								ssHead.drawObj(getXPlayer(), getYPlayer());
								ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
								motion += 1;
							}

						} else if (isUp() && getViewDirect() == ViewDirect.UP) {
							if (motion > 9 * 5)
								motion = 0;
							ssBody.setXPos((issacSize.issacBODYWIDTH * (motion / 5)) + (Gap.COLUMGAP * (motion / 5)));
							if (getViewDirect() == ViewDirect.UP) {
								ssHead.setXPos(issacSize.issacHEADWIDTH * 4 + Gap.HEADCOLUMGAP * 4);
								ssBody.setYPos(issacSize.issacHEADHEIGHT + Gap.ROWGAP);
								ssHead.drawObj(getXPlayer(), getYPlayer());
								ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
								motion += 1;
							}

						} else if (isRight() && getViewDirect() == ViewDirect.RIGHT) {
							if (motion > 9 * 5)
								motion = 0;
							ssBody.setXPos((issacSize.issacBODYWIDTH * (motion / 5)) + (Gap.COLUMGAP * (motion / 5)));
							if (getViewDirect() == ViewDirect.RIGHT) {
								ssHead.setXPos(issacSize.issacHEADWIDTH * 2 + Gap.HEADCOLUMGAP * 2);
								ssBody.setYPos(issacSize.issacHEADHEIGHT + issacSize.issacBODYHEIGHT + Gap.ROWGAP * 2);
								ssHead.drawObj(getXPlayer(), getYPlayer());
								ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
								motion += 1;
							}
						}
						if (!connectControl.isMulti()) {
							startCheck();
						}
						if(getLife()==0) {
							setDead(true);
						}
//						MonsterCheck();
						try {
							Thread.sleep(moveSpeed);
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
		if (issac.isDown()) {
			issac.setViewDirect(ViewDirect.DOWN);
			issac.setSendViewDirect(ViewDirect.UP);
			issacInfoRefresh();
		}
		if (issac.isLeft()) {
			issac.setViewDirect(ViewDirect.LEFT);
			issac.setSendViewDirect(ViewDirect.RIGHT);
			issacInfoRefresh();
		}
		if (issac.isUp()) {
			issac.setViewDirect(ViewDirect.UP);
			issac.setSendViewDirect(ViewDirect.DOWN);
			issacInfoRefresh();
		}
		if (issac.isRight()) {
			issac.setViewDirect(ViewDirect.RIGHT);
			issac.setSendViewDirect(ViewDirect.LEFT);
			issacInfoRefresh();
		}
	}

	public void reDrawLife() {
		double currentLife = getLife();
		for (int i = 0; i < getMaxlife(); i++) {
			if (currentLife >= 1) {
				ssLife.get(i).setXPos(0);
				ssLife.get(i).drawObj(10 + (i * 30), 10);
				currentLife -= 1;
			} else if (currentLife > 0 && currentLife < 1) {
				ssLife.get(i).setXPos(Lifesize.LIFEWIDTH + Gap.COLUMGAP);
				ssLife.get(i).drawObj(10 + (i * 30), 10);
				currentLife -= 0.5;
			} else {
				ssLife.get(i).setXPos(Lifesize.LIFEWIDTH * 2 + Gap.COLUMGAP * 2);
				ssLife.get(i).drawObj(10 + (i * 30), 10);
			}
		}
	}

	// 주변 아이템 여부 체크
	public boolean nearItemCheck(Item item) {
		if (getXPlayerCenter() > item.getXItem() && getXPlayerCenter() < item.getXItem() + item.getSsItem().getWidth()
				&& getYPlayerCenter() > item.getYItem()
				&& getYPlayerCenter() < item.getYItem() + item.getSsItem().getHeight()) {
			return true;
		} else {
			return false;
		}
	}

	public void deadMotion() {
		ssBody.setVisible(false);
		ssHead.setVisible(false);
		ssDead.setVisible(true);
		ssDead.drawObj(getXPlayer(), getYPlayer());
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int gap = 51;
		for (int i = 0; i < 2; i++) {
			ssDead.setXPos(ssDead.getXPos() + gap * i);
			ssDead.setWidth(55);
			ssDead.drawObj(getXPlayer(), getYPlayer());
		}
	}

	public void MonsterCheck() {
		for (int i = 0; i < monsters.size(); i++) {
			if (ssBody.getBounds().intersects(monsters.get(i).getSsMonster().getBounds()) && !isInvincible()) {
				setLife(getLife() - 1);
				reDrawLife();
				if (getLife() == 0) {
					setDead(true);
				}
				if (!isDead())
					hitDelayMotion();
				System.out.println(getLife());
			}
		}
	}

	public synchronized void hitDelayMotion() {
		new Thread(() -> {
			setInvincible(true);
			issacInfoRefresh();
			ssBody.setVisible(false);
			ssHead.setVisible(false);
			ssDead.drawObj(getXPlayer(), getYPlayer());
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
			setInvincible(false);
			issacInfoRefresh();
		}).start();
	}

	public void startCheck() {
		if (startButtonControl.getSsStartButton().getBounds().intersects(issac.getSsBody().getBounds())
				&& connectControl.isIsconnect()) {
			startButtonControl.getSsStartButton().setXPos(ButtonSize.XPos + ButtonSize.Width + ButtonSize.Gap);
			startButtonControl.getSsStartButton().drawObj(startButtonControl.getXButton(),
					startButtonControl.getYButton());
			connectControl.setMulti(true);
		}
	}

	public void issacInfoRefresh() {
		if (connectControl.isMulti()&&connectControl.getSendDataClass()!=null){
			connectControl.getSendDataClass().setXPlayer(945 - getXPlayer()-issacSize.issacHEADWIDTH);
			connectControl.getSendDataClass().setYPlayer(520 - getYPlayer());
			connectControl.getSendDataClass().setBooleanView(getViewDirectInfo());
			connectControl.getSendDataClass().setIntView(getSendViewDirect());
			connectControl.getSendDataClass().setAttack(isKeyPress());
			connectControl.getSendDataClass().setAttackDamage(getAttackDamage());
			connectControl.getSendDataClass().setLife(getLife());
			connectControl.getSendDataClass().setMoveSpeed(getMoveSpeed());
			connectControl.getSendDataClass().setInvincible(isInvincible());
			connectControl.getSendDataClass().setDead(isDead());
			connectControl.SendData();
		}
	}
}