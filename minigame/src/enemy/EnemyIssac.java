package enemy;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.imageio.ImageTranscoder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.stream.events.StartDocument;

import sword.SwordControl;
import SpriteSheet.SpriteSheet;
import imgSize.*;
import lombok.Data;
import monster.Monster;
import monster.Worm;
import objectSetting.RockSize;
import wall.*;
import item.*;

@Data

public class EnemyIssac extends Enemy {
	private final static String TAG = "issac: ";
	private EnemyIssac issac = this;
	private SpriteSheet ssHead, ssBody;
	private SpriteSheet ssTotal;
	private Vector<SpriteSheet> ssLife;
	private SwordControl swordControl;
	private Worm worm;
	private Vector<Monster> monsters;
	private Vector<wall> walls;
	private Vector<Item> items;
	private int xPlusBody = 7, yPlusBody = 30;
	private int yTotalSize;
	private int item1Count = 0;
	private int item2Count = 0;
	private int item3Count = 0;
	private int item4Count = 0;
	private int moveSpeed = 10;
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

	public EnemyIssac(JFrame app, Vector<Monster> monsters, Vector<wall> walls, Vector<Item> items) {
		super(app);
		System.out.println(TAG + "make issac");
		init(monsters, walls, items);
		setting();
		batch();

	}

	public void init(Vector<Monster> monsters, Vector<wall> walls, Vector<Item> items) {
		this.walls = walls;
		this.items = items;
		this.monsters = monsters;
		ssHead = new SpriteSheet("issac/issac.png", "issacssHead", 0, 0, issacSize.issacHEADWIDTH,
				issacSize.issacHEADHEIGHT);
		ssBody = new SpriteSheet("issac/issac.png", "issacBody", 0, (issacSize.issacHEADHEIGHT + Gap.ROWGAP),
				issacSize.issacBODYWIDTH, issacSize.issacBODYHEIGHT);
		ssTotal = new SpriteSheet("issac/issac.png", "issacsBody", 0, yTotalSize, issacSize.issacTOTALWIDTH,
				issacSize.issacTOTALHEIGHT);
		yTotalSize = issacSize.issacHEADHEIGHT + issacSize.issacBODYHEIGHT * 4 + Gap.ROWGAP * 5;
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
			this.ssLife.add(new SpriteSheet("issac/life.png", "life", Lifesize.LIFEWIDTH * 2, 0, Lifesize.LIFEWIDTH,
					Lifesize.LIFEHEIGHT));
		}
	}

	public void setting() {
		setViewDirect(ViewDirect.DOWN);
		setXEnemy(480);
		setYEnemy(110);
		setAttackDamage(1);
		setLife(3);
		setXEnemyCenter(getXEnemy() + issacSize.issacHEADWIDTH / 2);
		setYEnemyCenter(getYEnemy() + issacSize.issacHEADHEIGHT);
		ssHead.drawObj(getXEnemy(), getYEnemy());
		ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
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
		// 폭탄 파워 속도 레이블 추가
		getApp().add(labomb);
		getApp().add(lapower);
		getApp().add(laspeed);
		getApp().add(laattackspeed);
		swordControl = new SwordControl(getApp());
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
				if (isRight() == false) {
					setRight(true);
					setViewDirect(ViewDirect.RIGHT);
					while (isRight()) {
						if (getXEnemy() + issacSize.issacBODYWIDTH > 810) { // 벽이상 움직임 제한
							setRight(false);
							refreshDirect();
							break;
						}
						// 돌 충돌 체크 시작
						boolean isRockCollision = false;
						for (int i = 0; i < walls.size(); i++) {
							if (!walls.get(i).isBroken() && walls.get(i).getSswall().getGubun() == "rock") {
								if (getXEnemyCenter() + (issacSize.issacHEADWIDTH / 2) > walls.get(i).getXwall()
										&& getXEnemyCenter() < walls.get(i).getXwall() + RockSize.WIDTH
										&& getYEnemyCenter() + issacSize.issacHEADHEIGHT - yPlusBody > walls.get(i)
												.getYwall()
										&& getYEnemyCenter() < walls.get(i).getYwall() + RockSize.HEIGHT) {
									isRockCollision = true;
									break;
								}
							}
						}
						if (isRockCollision) {
							refreshDirect();
							break;
						}
						getItem();
						// 돌 충돌 체크 끝
						setXEnemy(getXEnemy() + 1);
						setXEnemyCenter(getXEnemyCenter() + 1);
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ssBody.setXPos(0);
					ssHead.drawObj(getXEnemy(), getYEnemy());
					ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
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
					while (isLeft()) {
						if (getXEnemy() < 130) {
							setLeft(false);
							refreshDirect();
							break;
						}
						boolean isRockCollision = false;
						// 돌 충돌 체크 시작
						for (int i = 0; i < walls.size(); i++) {
							if (!walls.get(i).isBroken() && walls.get(i).getSswall().getGubun() == "rock") {

								if (getXEnemyCenter() > walls.get(i).getXwall()
										&& getXEnemyCenter() - (issacSize.issacHEADWIDTH / 2) < walls.get(i).getXwall()
												+ RockSize.WIDTH
										&& getYEnemyCenter() + issacSize.issacHEADHEIGHT - yPlusBody > walls.get(i)
												.getYwall()
										&& getYEnemyCenter() < walls.get(i).getYwall() + RockSize.HEIGHT) {
									isRockCollision = true;
									break;
								}
							}
						}
						if (isRockCollision) {
							refreshDirect();
							break;
						}
						// 돌 충돌 체크 끝
						getItem();
						setXEnemy(getXEnemy() - 1);
						setXEnemyCenter(getXEnemyCenter() - 1);
						moveMotion();
						try {

							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ssBody.setXPos(0);
					ssHead.drawObj(getXEnemy(), getYEnemy());
					ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
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
					while (isDown()) {
						if (getYEnemy() > 440) {
							setDown(false);
							refreshDirect();
							break;
						}
						// 돌 충돌 체크 시작
						boolean isRockCollision = false;
						for (int i = 0; i < walls.size(); i++) {
							if (!walls.get(i).isBroken() && walls.get(i).getSswall().getGubun() == "rock") {
								if (getXEnemyCenter() + (issacSize.issacHEADWIDTH / 2) > walls.get(i).getXwall() + 5
										&& getXEnemyCenter() - (issacSize.issacHEADWIDTH / 2) < walls.get(i).getXwall()
												+ RockSize.WIDTH - 5
										&& getYEnemyCenter() + (issacSize.issacHEADHEIGHT - yPlusBody) + 5 > walls
												.get(i).getYwall()
										&& getYEnemyCenter() < walls.get(i).getYwall() + RockSize.HEIGHT) {
									isRockCollision = true;
									break;
								}
							}
						}
						if (isRockCollision) {
							refreshDirect();
							break;
						}
						getItem();
						// 돌 충돌 체크 끝
						setYEnemy(getYEnemy() + 1);// 플레이어 이동시 좌표값 변경
						setYEnemyCenter(getYEnemyCenter() + 1);// 중앙
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ssBody.setXPos(0);
					ssHead.drawObj(getXEnemy(), getYEnemy());
					ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
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
					while (isUp()) {
						if (getYEnemy() < 100) {
							refreshDirect();
							break;
						}
						boolean isRockCollision = false;
						// 돌 충돌 체크 시작
						for (int i = 0; i < walls.size(); i++) {
							if (!walls.get(i).isBroken() && walls.get(i).getSswall().getGubun() == "rock") {
								if (getXEnemyCenter() + (issacSize.issacHEADWIDTH / 2) > walls.get(i).getXwall() + 5
										&& getXEnemyCenter() - (issacSize.issacHEADWIDTH / 2) < walls.get(i).getXwall()
												+ RockSize.WIDTH - 5
										&& getYEnemyCenter() > walls.get(i).getYwall() && getYEnemyCenter()
												+ (issacSize.issacBODYHEIGHT - yPlusBody) < walls.get(i).getYwall()
														+ RockSize.HEIGHT) {
									isRockCollision = true;
									break;
								}
							}
						}
						if (isRockCollision) {
							refreshDirect();
							break;
						}
						
						getItem();
						// 돌 충돌 체크 끝
						setYEnemy(getYEnemy() - 1);
						setYEnemyCenter(getYEnemyCenter() - 1);
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ssBody.setXPos(0);
					ssHead.drawObj(getXEnemy(), getYEnemy());
					ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
				}
			}
		}).start();
	}

	@Override
	public void moveMotion() { // 움직이는 동작중 이미지 갱신
		// Down을 기준으로 설명하겠습니다 나머지 내용은 ColumGap과 RowGap, HEIGHT, WIDTH로 상하 좌우가 구분됩니다
		new Thread(new Runnable() {
			@Override
			public void run() {
				int motion = 0;
				if (isEnemyMoveStart() == false) {
					setEnemyMoveStart(true);
					while (true) {
						if (isDown() && getViewDirect() == ViewDirect.DOWN) {
							if (motion > 9) // 상하좌우 방향 모션 개수와 동일 0~9 10개
								motion = 0;// 마지막사진 도착후 처음으로 순환을 위한 if문 종료
							ssBody.setXPos((issacSize.issacBODYWIDTH * motion) + (Gap.COLUMGAP * motion)); // XPos는 사진에서
							if (getViewDirect() == ViewDirect.DOWN) {
								ssHead.setXPos(0); // 첫번째 사진이므로 0 다른 내용은 images/issac/issac.img에서 순서 확인하시면 됩니다.
								ssBody.setYPos(issacSize.issacHEADWIDTH + Gap.COLUMGAP);// X좌표로 순서를 정하고 Y좌표는 사진사이의 간격과
																						// 머리 이미지를 무시해야 하기에 머리 이미지의 크기만큼
																						// 더해서 좌표값을 내려줍니다
								ssHead.drawObj(getXEnemy(), getYEnemy()); // 그려지는 기준점이 되는 캐릭터(몬스터의) 좌표값을 설정합니다.
								ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);// X와Y좌표를 기준으로 머리를
																									// 생성하고 머리와 몸이 겹치지
																									// // 않게하기위해 사용합니다.
								if (!isEnemyAttacking()) {
									swordControl.getSsSword().setXPos(
											SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH);
									swordControl.getSsSword().setYPos(SwordSize.SWORDYGAP);
									swordControl.getSsSword().setWidth(SwordSize.SWORDWIDTH);
									swordControl.getSsSword().setHeight(SwordSize.SWORDYHEIGHT - 2);
									swordControl.getSsSword().setUrl("sword/sword_down.png");
									if (swordControl.getSsSword().getUrl().equals("sword/sword_down.png"))
										swordControl.getSsSword().drawObj(getXEnemy() + 12, getYEnemy() + 50);
								}
								motion += 1;
							}
							
						} else if (isLeft() && getViewDirect() == ViewDirect.LEFT) {
							if (motion > 9)
								motion = 0;
							ssBody.setXPos((issacSize.issacBODYWIDTH * motion) + (Gap.COLUMGAP * motion));
							if (getViewDirect() == ViewDirect.LEFT) {
								ssHead.setXPos(issacSize.issacHEADWIDTH * 6 + Gap.COLUMGAP * 6);
								ssBody.setYPos(
										issacSize.issacHEADHEIGHT + issacSize.issacBODYHEIGHT * 2 + Gap.ROWGAP * 3);
								ssHead.drawObj(getXEnemy(), getYEnemy());
								ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
								if (!isEnemyAttacking()) {
									swordControl.getSsSword().setXPos(SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP
											- SwordSize.SWORDYHEIGHT + 3);
									swordControl.getSsSword().setYPos(
											SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH - 1);
									swordControl.getSsSword().setWidth(SwordSize.SWORDYHEIGHT - 1);
									swordControl.getSsSword().setHeight(SwordSize.SWORDWIDTH);
									swordControl.getSsSword().setUrl("sword/sword_left.png");
									if (swordControl.getSsSword().getUrl().equals("sword/sword_left.png"))
										swordControl.getSsSword().drawObj(getXEnemy() - 36, getYEnemy() + 29);
								}
								motion += 1;
							}
							
						} else if (isUp() && getViewDirect() == ViewDirect.UP) {
							if (motion > 9)
								motion = 0;
							ssBody.setXPos((issacSize.issacBODYWIDTH * motion) + (Gap.COLUMGAP * motion));
							if (getViewDirect() == ViewDirect.UP) {
								ssHead.setXPos(issacSize.issacHEADWIDTH * 4 + Gap.COLUMGAP * 4);
								ssBody.setYPos(issacSize.issacHEADWIDTH + Gap.COLUMGAP);
								ssHead.drawObj(getXEnemy(), getYEnemy());
								ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
								if (!isEnemyAttacking()) {
									swordControl.getSsSword().setXPos(SwordSize.SWORDXGAP + 2);
									swordControl.getSsSword().setYPos(SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP
											- SwordSize.SWORDYHEIGHT + 2);
									swordControl.getSsSword().setWidth(SwordSize.SWORDWIDTH - 1);
									swordControl.getSsSword().setHeight(SwordSize.SWORDYHEIGHT);
									swordControl.getSsSword().setUrl("sword/sword_up.png");
									if (swordControl.getSsSword().getUrl().equals("sword/sword_up.png"))
										swordControl.getSsSword().drawObj(getXEnemy() + 12, getYEnemy() - 25);
								}
								motion += 1;
							}
							
						} else if (isRight() && getViewDirect() == ViewDirect.RIGHT) {
							if (motion > 9)
								motion = 0;
							ssBody.setXPos((issacSize.issacBODYWIDTH * motion) + (Gap.COLUMGAP * motion));
							if (getViewDirect() == ViewDirect.RIGHT) {
								ssHead.setXPos(issacSize.issacHEADWIDTH * 2 + Gap.COLUMGAP * 2);
								ssBody.setYPos(issacSize.issacHEADHEIGHT + issacSize.issacBODYHEIGHT + Gap.ROWGAP * 2);
								ssHead.drawObj(getXEnemy(), getYEnemy());
								ssBody.drawObj(getXEnemy() + xPlusBody, getYEnemy() + yPlusBody);
								if (!isEnemyAttacking()) {
									swordControl.getSsSword().setXPos(SwordSize.SWORDYGAP);
									swordControl.getSsSword().setYPos(SwordSize.SWORDXGAP + 1);
									swordControl.getSsSword().setWidth(SwordSize.SWORDYHEIGHT - 1);
									swordControl.getSsSword().setHeight(SwordSize.SWORDWIDTH);
									swordControl.getSsSword().setUrl("sword/sword_right.png");
									if (swordControl.getSsSword().getUrl().equals("sword/sword_right.png"))
										swordControl.getSsSword().drawObj(getXEnemy() + 34, getYEnemy() + 28);
								}
								motion += 1;
							}
							
						}
						try {
							Thread.sleep(15);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
			}
		}).start();
	}

	public synchronized void attackMotion() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (isEnemyAttack() == false) {
					setEnemyAttack(true);
					if (isEnemyAttack()) {
						int imgxlocation = 1;
						int imgylocation = 0;
						while (true) {
							setEnemyAttacking(true);
							if (getViewDirect() == ViewDirect.DOWN) {
								if (imgxlocation == 1 && imgylocation > 3) {
									imgylocation = 0;
									imgxlocation--;
								}
								if (imgxlocation == 0 && imgylocation > 2) {
									imgxlocation = 1;
									imgylocation = 0;
									setEnemyAttacking(false);
									swordControl.getSsSword().setXPos(
											SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH);
									swordControl.getSsSword().setYPos(SwordSize.SWORDYGAP);
									swordControl.getSsSword().setWidth(SwordSize.SWORDWIDTH);
									swordControl.getSsSword().setHeight(SwordSize.SWORDYHEIGHT - 2);
									swordControl.getSsSword().setUrl("sword/sword_down.png");
									swordControl.getSsSword().drawObj(getXEnemy() + 12, getYEnemy() + 50);
									break;
								}
								swordControl.getSsSword().setWidth(SwordMotionSize.WIDTH);
								swordControl.getSsSword().setHeight(SwordMotionSize.HEIGHT);
								swordControl.getSsSword().setXPos(SwordMotionSize.WIDTH * imgxlocation);
								swordControl.getSsSword().setYPos(SwordSize.SWORDIMGHEIGHT - (SwordMotionSize.IMGHEIGHT)
										+ (SwordMotionSize.HEIGHT * imgylocation));
								swordControl.getSsSword().setUrl("sword/sword_down.png");
								swordControl.getSsSword().drawObj(getXEnemy() - 28, getYEnemy() + 25);
								imgylocation++;
							} else if (getViewDirect() == ViewDirect.UP) {
								if (imgxlocation == 1 && imgylocation > 3) {
									imgylocation = 0;
									imgxlocation--;
								}
								if (imgxlocation == 0 && imgylocation > 2) {
									imgxlocation = 1;
									imgylocation = 0;
									setEnemyAttacking(false);
									swordControl.getSsSword().setXPos(SwordSize.SWORDXGAP + 2);
									swordControl.getSsSword().setYPos(SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP
											- SwordSize.SWORDYHEIGHT + 2);
									swordControl.getSsSword().setWidth(SwordSize.SWORDWIDTH - 1);
									swordControl.getSsSword().setHeight(SwordSize.SWORDYHEIGHT);
									swordControl.getSsSword().setUrl("sword/sword_up.png");
									swordControl.getSsSword().drawObj(getXEnemy() + 12, getYEnemy() - 25);
									break;
								}
								swordControl.getSsSword().setWidth(SwordMotionSize.WIDTH);
								swordControl.getSsSword().setHeight(SwordMotionSize.HEIGHT);
								swordControl.getSsSword()
										.setXPos(SwordMotionSize.WIDTH - (SwordMotionSize.WIDTH * imgxlocation));
								swordControl.getSsSword().setYPos(SwordMotionSize.IMGHEIGHT - SwordMotionSize.HEIGHT
										- (SwordMotionSize.HEIGHT * imgylocation));
								swordControl.getSsSword().setUrl("sword/sword_up.png");
								swordControl.getSsSword().drawObj(getXEnemy() - 28, getYEnemy() - 50);
								imgylocation++;
							} else if (getViewDirect() == ViewDirect.LEFT) {
								if (imgxlocation == 1 && imgylocation > 3) {
									imgylocation = 0;
									imgxlocation--;
								}
								if (imgxlocation == 0 && imgylocation > 2) {
									imgxlocation = 1;
									imgylocation = 0;
									setEnemyAttacking(false);
									swordControl.getSsSword().setXPos(SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP
											- SwordSize.SWORDYHEIGHT + 3);
									swordControl.getSsSword().setYPos(
											SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH - 1);
									swordControl.getSsSword().setWidth(SwordSize.SWORDYHEIGHT - 1);
									swordControl.getSsSword().setHeight(SwordSize.SWORDWIDTH);
									swordControl.getSsSword().setUrl("sword/sword_left.png");
									swordControl.getSsSword().drawObj(getXEnemy() - 36, getYEnemy() + 29);
									break;
								}
								swordControl.getSsSword().setWidth(SwordMotionSize.HEIGHT);
								swordControl.getSsSword().setHeight(SwordMotionSize.WIDTH);
								swordControl.getSsSword().setXPos((SwordMotionSize.IMGHEIGHT) - SwordMotionSize.HEIGHT
										- (SwordMotionSize.HEIGHT * imgylocation));
								swordControl.getSsSword().setYPos(SwordMotionSize.WIDTH * imgxlocation);
								swordControl.getSsSword().setUrl("sword/sword_left.png");
								swordControl.getSsSword().drawObj(getXEnemy() - 60, getYEnemy() - 10);
								imgylocation++;
							} else if (getViewDirect() == ViewDirect.RIGHT) {
								if (imgxlocation == 1 && imgylocation > 3) {
									imgylocation = 0;
									imgxlocation--;
								}
								if (imgxlocation == 0 && imgylocation > 2) {
									imgxlocation = 1;
									imgylocation = 0;
									setEnemyAttacking(false);
									swordControl.getSsSword().setXPos(SwordSize.SWORDYGAP);
									swordControl.getSsSword().setYPos(SwordSize.SWORDXGAP + 1);
									swordControl.getSsSword().setWidth(SwordSize.SWORDYHEIGHT - 1);
									swordControl.getSsSword().setHeight(SwordSize.SWORDWIDTH);
									swordControl.getSsSword().setUrl("sword/sword_right.png");
									swordControl.getSsSword().drawObj(getXEnemy() + 34, getYEnemy() + 28);
									break;
								}
								swordControl.getSsSword().setWidth(SwordMotionSize.HEIGHT);
								swordControl.getSsSword().setHeight(SwordMotionSize.WIDTH);
								swordControl.getSsSword().setXPos(SwordSize.SWORDIMGHEIGHT - SwordMotionSize.IMGHEIGHT
										+ (SwordMotionSize.HEIGHT * imgylocation));
								swordControl.getSsSword()
										.setYPos(SwordMotionSize.WIDTH - (SwordMotionSize.WIDTH * imgxlocation));
								swordControl.getSsSword().setUrl("sword/sword_right.png");
								swordControl.getSsSword().drawObj(getXEnemy() + 20, getYEnemy() - 10);
								imgylocation++;
							}
							try {
								Thread.sleep(20);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}).start();
	}

	public void refreshDirect() {
		if (issac.isDown()) {
			issac.setViewDirect(ViewDirect.DOWN);
		}
		if (issac.isLeft()) {
			issac.setViewDirect(ViewDirect.LEFT);
		}
		if (issac.isUp()) {
			issac.setViewDirect(ViewDirect.UP);
		}
		if (issac.isRight()) {
			issac.setViewDirect(ViewDirect.RIGHT);
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
		if (getXEnemyCenter() > item.getXItem() && getXEnemyCenter() < item.getXItem() + item.getSsItem().getWidth()
				&& getYEnemyCenter() > item.getYItem()
				&& getYEnemyCenter() < item.getYItem() + item.getSsItem().getHeight()) {
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
}