package player;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.imageio.ImageTranscoder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.stream.events.StartDocument;

import player.Player;
import sword.SwordControl;
import SpriteSheet.SpriteSheet;
import connect.Connect;
import connect.ConnectControl;
//import enemy.EnemyIssac;
import imgSize.*;
import lombok.Data;
import monster.Monster;
import monster.Worm;
import objectSetting.ViewDirect;
import wall.*;
import item.*;

@Data

public class issac extends Player {
	private final static String TAG = "issac: ";
	private issac issac = this;
//	private EnemyIssac enemyIssac;
	private SpriteSheet ssHead, ssBody;
	private SpriteSheet ssDead;
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
			ConnectControl connectControl) {
		super(app);
		System.out.println(TAG + "make issac");
		init(monsters, walls, items, connectControl);
		setting();
		batch();
		issacInfoRefresh();
		moveMotion();
	}

	public void init(Vector<Monster> monsters, Vector<wall> walls, Vector<Item> items, ConnectControl connectControl) {
		this.connectControl = connectControl;
		this.walls = walls;
		this.items = items;
		this.monsters = monsters;
		ssHead = new SpriteSheet("issac/issac.png", "issacssHead", issacSize.issacHEADWIDTH * 4 + Gap.HEADCOLUMGAP * 4,
				0, issacSize.issacHEADWIDTH, issacSize.issacHEADHEIGHT);
		ssBody = new SpriteSheet("issac/issac.png", "issacBody", 0, (issacSize.issacHEADHEIGHT + Gap.ROWGAP),
				issacSize.issacBODYWIDTH, issacSize.issacBODYHEIGHT);
		ssDead = new SpriteSheet("issac/issac.png", "issacDead", 63, 215, 42, 48);
		// 레이블 초기화
		labomb = new JLabel(Integer.toString(bombCount));
		laspeed = new JLabel(Integer.toString(speedNum - moveSpeed));
		lapower = new JLabel(Integer.toString(powerNum));
		laattackspeed = new JLabel(Integer.toString(attackspeedNum));
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
		setXPlayer(449);
		setYPlayer(430);
		setAttackDamage(1);
		setLife(5);
		setXPlayerCenter(getXPlayer() + issacSize.issacHEADWIDTH / 2);
		setYPlayerCenter(getYPlayer() + issacSize.issacHEADHEIGHT);
		ssHead.drawObj(getXPlayer(), getYPlayer());
		ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
		// 폭탄 레이블 설정
		labomb.setSize(30, 30);
		labomb.setLocation(65, 50);
		labomb.setFont(new Font("바탕", Font.ITALIC, 25));
		labomb.setForeground(Color.WHITE);
		// 속도 레이블 설정
		lapower.setSize(30, 30);
		lapower.setLocation(65, 85);
		lapower.setFont(new Font("바탕", Font.ITALIC, 25));
		lapower.setForeground(Color.WHITE);
		// 속도 레이블 설정
		laspeed.setSize(40, 30);
		laspeed.setLocation(65, 120);
		laspeed.setFont(new Font("바탕", Font.ITALIC, 25));
		laspeed.setForeground(Color.WHITE);
		// 공속 레이블 설정
		laattackspeed.setSize(40, 30);
		laattackspeed.setLocation(65, 155);
		laattackspeed.setFont(new Font("바탕", Font.ITALIC, 25));
		laattackspeed.setForeground(Color.WHITE);

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
		getApp().add(ssDead, 1);
		// 폭탄 파워 속도 레이블 추가
		getApp().add(labomb);
		getApp().add(lapower);
		getApp().add(laspeed);
		getApp().add(laattackspeed);
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
					while (isRight()) {

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
										isrock = true;
									}
								}
							}
						}
						getItem();
						if (isrock)
							break;
						// 돌 충돌 체크 끝
						setXPlayer(getXPlayer() + 1);
						setXPlayerCenter(getXPlayerCenter() + 1);

						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
//					int cnt = 0;
//					while (true) { //생성이유 특정조건 벽이나 구석
//						cnt++;
//						System.out.println("moveRight Thread 유지중"+cnt);
//						if(!isIskeyPress()) break;
//						try {
//							Thread.sleep(100);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
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
					while (isLeft()) {
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
										isrock = true;
									}
								}
							}
						}
						// 돌 충돌 체크 끝
						getItem();
						if (isrock)
							break;
						setXPlayer(getXPlayer() - 1);
						setXPlayerCenter(getXPlayerCenter() - 1);
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
					while (isDown()) {
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
										isrock = true;
									}
								}
							}
						}
						getItem();
						if (isrock)
							break;
						// 돌 충돌 체크 끝
						setYPlayer(getYPlayer() + 1);// 플레이어 이동시 좌표값 변경
						setYPlayerCenter(getYPlayerCenter() + 1);// 중앙
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
					while (isUp()) {
						if (getYPlayer() < 100) {
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
										isrock = true;
									}
								}
							}
						}
						getItem();
						// 돌 충돌 체크 끝
						if (isrock)
							break;
						setYPlayer(getYPlayer() - 1);
						setYPlayerCenter(getYPlayerCenter() - 1);
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
							if (motion > 9 * 4) // 상하좌우 방향 모션 개수와 동일 0~9 10개
								motion = 0;// 마지막사진 도착후 처음으로 순환을 위한 if문 종료
							ssBody.setXPos((issacSize.issacBODYWIDTH * (motion / 4)) + (Gap.COLUMGAP * (motion / 4))); // XPos는
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
							if (motion > 9 * 4)
								motion = 0;
							ssBody.setXPos((issacSize.issacBODYWIDTH * (motion / 4)) + (Gap.COLUMGAP * (motion / 4)));
							if (getViewDirect() == ViewDirect.LEFT) {
								ssHead.setXPos(issacSize.issacHEADWIDTH * 6 + Gap.HEADCOLUMGAP * 6);
								ssBody.setYPos(
										issacSize.issacHEADHEIGHT + issacSize.issacBODYHEIGHT * 2 + Gap.ROWGAP * 3);
								ssHead.drawObj(getXPlayer(), getYPlayer());
								ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
								motion += 1;
							}

						} else if (isUp() && getViewDirect() == ViewDirect.UP) {
							if (motion > 9 * 4)
								motion = 0;
							ssBody.setXPos((issacSize.issacBODYWIDTH * (motion / 4)) + (Gap.COLUMGAP * (motion / 4)));
							if (getViewDirect() == ViewDirect.UP) {
								ssHead.setXPos(issacSize.issacHEADWIDTH * 4 + Gap.HEADCOLUMGAP * 4);
								ssBody.setYPos(issacSize.issacHEADHEIGHT + Gap.ROWGAP);
								ssHead.drawObj(getXPlayer(), getYPlayer());
								ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
								motion += 1;
							}

						} else if (isRight() && getViewDirect() == ViewDirect.RIGHT) {
							if (motion > 9 * 4)
								motion = 0;
							ssBody.setXPos((issacSize.issacBODYWIDTH * (motion / 4)) + (Gap.COLUMGAP * (motion / 4)));
							if (getViewDirect() == ViewDirect.RIGHT) {
								ssHead.setXPos(issacSize.issacHEADWIDTH * 2 + Gap.HEADCOLUMGAP * 2);
								ssBody.setYPos(issacSize.issacHEADHEIGHT + issacSize.issacBODYHEIGHT + Gap.ROWGAP * 2);
								ssHead.drawObj(getXPlayer(), getYPlayer());
								ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
								motion += 1;
							}
						}
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
		}
		if (issac.isLeft()) {
			issac.setViewDirect(ViewDirect.LEFT);
			issac.setSendViewDirect(ViewDirect.RIGHT);
		}
		if (issac.isUp()) {
			issac.setViewDirect(ViewDirect.UP);
			issac.setSendViewDirect(ViewDirect.DOWN);
		}
		if (issac.isRight()) {
			issac.setViewDirect(ViewDirect.RIGHT);
			issac.setSendViewDirect(ViewDirect.LEFT);
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

	// 아이템 획득 종류 확인
	public void getItem() {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).isDrop()) {
				if (nearItemCheck(items.get(i))) {
					items.get(i).setDrop(false);
					// Gubun String이 "bomb" 일때
					int luck = (int) (Math.random() * 2);

					if (items.get(i).getSsItem().getGubun() == "bomb") {
						// System.out.println(items.get(i).getSsItem().getGubun() + " 발견");
						if (luck == 1) {
							bombCount += 1;
							labomb.setText(Integer.toString(bombCount));
						} else if (luck == 0) {
							bombCount -= 1;
							labomb.setText(Integer.toString(bombCount));
						} // Gubun String이 "Power" 일때
					} else if (items.get(i).getSsItem().getGubun() == "Power") {
						// System.out.println(items.get(i).getSsItem().getGubun() + " 발견");
						if (luck == 1) {

							powerNum += 2;
							lapower.setText(Integer.toString(powerNum));

						} else if (luck == 0) {
							powerNum--;
							lapower.setText(Integer.toString(powerNum));

						}
						// Gubun String이 "Speed" 일때
					} else if (items.get(i).getSsItem().getGubun() == "Speed") {
						// System.out.println(items.get(i).getSsItem().getGubun() + " 발견");
						if (luck == 1) {

							moveSpeed += 3;
							laspeed.setText(Integer.toString(speedNum - moveSpeed));

						} else if (luck == 0) {
							moveSpeed--;
							laspeed.setText(Integer.toString(speedNum - moveSpeed));

						}
					} else if (items.get(i).getSsItem().getGubun() == "AttackSpeed") {
						if (luck == 1) {

							attackspeedNum += 2;
							laattackspeed.setText(Integer.toString(attackspeedNum));

						} else if (luck == 0) {
							attackspeedNum--;
							laattackspeed.setText(Integer.toString(attackspeedNum));

						}
					}
					System.out.println(items.get(i).getSsItem().getGubun() + " 발견");
					getApp().remove(items.get(i).getSsItem());
					getApp().repaint();

				}
			}

		}
	}

	public void deadMotion() {
		getApp().remove(ssBody);
		getApp().remove(ssHead);
		ssDead.drawObj(getXPlayer(), getYPlayer());
		getApp().add(ssDead);
		int gap = 51;
		for(int i = 0;i<2;i++) {
			ssDead.setXPos(ssDead.getXPos()+gap*i);
			ssDead.drawObj(getXPlayer(),getYPlayer());
		}
	}

	public void issacInfoRefresh() {
		new Thread(() -> {
			while (connectControl.isIsconnect()) {
				setPlayerData(new double[] { getAttackDamage(), getLife(), getMoveSpeed() });
				connectControl.getSendMap().put("PlayerX", 960 - getXPlayer());
				connectControl.getSendMap().put("PlayerY", 640 - getYPlayer());
				connectControl.getSendMap().put("PlayerStats", getPlayerData());
				connectControl.getSendMap().put("booleanView", getViewDirectInfo());
				connectControl.getSendMap().put("intView", getSendViewDirect());

			}
		}).start();
	}
}