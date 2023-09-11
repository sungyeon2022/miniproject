package player;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.net.Socket;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.stream.events.StartDocument;

import player.Player;
import sword.SwordControl;
import SpriteSheet.SpriteSheet;
import imgSize.*;
import lombok.Data;
import monster.Monster;
import monster.Worm;

@Data

public class issac extends Player {
	private final static String TAG = "issac: ";
	private issac issac = this;
	private SpriteSheet ssHead, ssBody;
	private SpriteSheet ssTotal;
	private Vector<SpriteSheet> ssLife;
	private SwordControl swordControl;

	private int xPlusBody = 7, yPlusBody = 30;
	private int yTotalSize;
	private int item1Count = 0;
	private int item2Count = 0;
	private int item3Count = 0;
	private int item4Count = 0;
	private int moveSpeed = 7;

	public issac(JFrame app) {
		super(app);
		System.out.println(TAG + "make issac");
		init();
		setting();
		batch();
	}

	public void init() {
		ssHead = new SpriteSheet("issac/issac.png", "issacssHead", 0, 0, issacSize.issacHEADWIDTH,
				issacSize.issacHEADHEIGHT);
		ssBody = new SpriteSheet("issac/issac.png", "issacBody", 0, (issacSize.issacHEADHEIGHT + Gap.ROWGAP),
				issacSize.issacBODYWIDTH, issacSize.issacBODYHEIGHT);
		ssTotal = new SpriteSheet("issac/issac.png", "issacsBody", 0, yTotalSize, issacSize.issacTOTALWIDTH,
				issacSize.issacTOTALHEIGHT);
		yTotalSize = issacSize.issacHEADHEIGHT + issacSize.issacBODYHEIGHT * 4 + Gap.ROWGAP * 5;
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
		setXPlayer(480);
		setYPlayer(430);
		setAttackDamge(1);
		setLife(3);
		setXPlayerCenter(getXPlayer() + issacSize.issacHEADWIDTH / 2);
		setYPlayerCenter(getYPlayer() + issacSize.issacHEADHEIGHT);
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
						if (getXPlayer() + issacSize.issacBODYWIDTH > 810) { // 벽이상 움직임 제한
							setRight(false);
							refreshDirect();
							break;
						}
						setXPlayer(getXPlayer() + 1);
						setXPlayerCenter(getXPlayerCenter() + 1);
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
					swordControl.getLeft_ssSword().erase();
					swordControl.getDown_ssSword().erase();
					swordControl.getUp_ssSword().erase();
					swordControl.getRight_ssSword().drawObj(getXPlayer() + 34, getYPlayer() + 32);
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
						if (getXPlayer() < 130) {
							setLeft(false);
							refreshDirect();
							break;
						}
						setXPlayer(getXPlayer() - 1);
						setXPlayerCenter(getXPlayerCenter() - 1);
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
					swordControl.getRight_ssSword().erase();
					swordControl.getDown_ssSword().erase();
					swordControl.getUp_ssSword().erase();
					swordControl.getLeft_ssSword().drawObj(getXPlayer() - 36, getYPlayer() + 27);
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
						if (getYPlayer() > 440) {
							setDown(false);
							refreshDirect();
							break;
						}
						setYPlayer(getYPlayer() + 1);// 플레이어 이동시 좌표값 변경
						setYPlayerCenter(getYPlayerCenter() + 1);// 중앙
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
					swordControl.getRight_ssSword().erase();
					swordControl.getLeft_ssSword().erase();
					swordControl.getUp_ssSword().erase();
					swordControl.getDown_ssSword().drawObj(getXPlayer() + 9, getYPlayer() + 50);
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
						if (getYPlayer() < 100) {
							setUp(false);
							refreshDirect();
							break;
						}
						setYPlayer(getYPlayer() - 1);
						setXPlayerCenter(getXPlayerCenter() - 1);
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
					swordControl.getRight_ssSword().erase();
					swordControl.getDown_ssSword().erase();
					swordControl.getLeft_ssSword().erase();
					swordControl.getUp_ssSword().drawObj(getXPlayer() + 13, getYPlayer() - 25);
				}
			}
		}).start();
	}

	@Override // Override
	public void moveMotion() { // 움직이는 동작중 이미지 갱신
		// Down을 기준으로 설명하겠습니다 나머지 내용은 ColumGap과 RowGap, HEIGHT, WIDTH로 상하 좌우가 구분됩니다
		new Thread(new Runnable() {

			@Override
			public void run() {
				int motion = 0;
				if (isPlayerMoveStart() == false) {
					setPlayerMoveStart(true);
					while (true) {
						if (isDown() && getViewDirect() == ViewDirect.DOWN) {
							if (motion > 9) // 상하좌우 방향 모션 개수와 동일 0~9 10개
								motion = 0;// 마지막사진 도착후 처음으로 순환을 위한 if문 종료
							ssBody.setXPos((issacSize.issacBODYWIDTH * motion) + (Gap.COLUMGAP * motion)); // XPos는 사진에서
																											// 가져올 기준이
																											// 되는 X좌표가
																											// 됩니다
							if (getViewDirect() == ViewDirect.DOWN) {
								ssHead.setXPos(0); // 첫번째 사진이므로 0 다른 내용은 images/issac/issac.img에서 순서 확인하시면 됩니다.
								ssBody.setYPos(issacSize.issacHEADWIDTH + Gap.COLUMGAP);// X좌표로 순서를 정하고 Y좌표는 사진사이의 간격과
																						// 머리 이미지를 무시해야 하기에 머리 이미지의 크기만큼
																						// 더해서 좌표값을 내려줍니다
								ssHead.drawObj(getXPlayer(), getYPlayer()); // 그려지는 기준점이 되는 캐릭터(몬스터의) 좌표값을 설정합니다.
								ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);// X와Y좌표를 기준으로 머리를
																									// 생성하고 머리와 몸이 겹치지
																									// // 않게하기위해 사용합니다.
								swordControl.getLeft_ssSword().erase();
								swordControl.getRight_ssSword().erase();
								swordControl.getUp_ssSword().erase();
								if (!isPlayerAttacking()) {
									swordControl.getDown_ssSword().setXPos(
											SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH);
									swordControl.getDown_ssSword().setYPos(SwordSize.SWORDYGAP);
									swordControl.getDown_ssSword().setWidth(SwordSize.SWORDWIDTH);
									swordControl.getDown_ssSword().setHeight(SwordSize.SWORDYHEIGHT);
									swordControl.getDown_ssSword().drawObj(getXPlayer() + 9, getYPlayer() + 50);
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
								ssHead.drawObj(getXPlayer(), getYPlayer());
								ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
								swordControl.getRight_ssSword().erase();
								swordControl.getDown_ssSword().erase();
								swordControl.getUp_ssSword().erase();
								if (!isPlayerAttacking()) {
									swordControl.getLeft_ssSword().setXPos(
											SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP - SwordSize.SWORDYHEIGHT);
									swordControl.getLeft_ssSword().setYPos(
											SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH);
									swordControl.getLeft_ssSword().setWidth(SwordSize.SWORDYHEIGHT);
									swordControl.getLeft_ssSword().setHeight(SwordSize.SWORDWIDTH);
									swordControl.getLeft_ssSword().drawObj(getXPlayer() - 36, getYPlayer() + 27);
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
								ssHead.drawObj(getXPlayer(), getYPlayer());
								ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
								swordControl.getLeft_ssSword().erase();
								swordControl.getDown_ssSword().erase();
								swordControl.getRight_ssSword().erase();
								if (!isPlayerAttacking()) {
									swordControl.getUp_ssSword().setXPos(SwordSize.SWORDXGAP);
									swordControl.getUp_ssSword().setYPos(
											SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP - SwordSize.SWORDYHEIGHT);
									swordControl.getUp_ssSword().setWidth(SwordSize.SWORDWIDTH);
									swordControl.getUp_ssSword().setHeight(SwordSize.SWORDYHEIGHT);
									swordControl.getUp_ssSword().drawObj(getXPlayer() + 13, getYPlayer() - 25);
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
								ssHead.drawObj(getXPlayer(), getYPlayer());
								ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
								swordControl.getLeft_ssSword().erase();
								swordControl.getDown_ssSword().erase();
								swordControl.getUp_ssSword().erase();
								if (!isPlayerAttacking()) {
									swordControl.getRight_ssSword().setXPos(SwordSize.SWORDYGAP);
									swordControl.getRight_ssSword().setYPos(SwordSize.SWORDXGAP);
									swordControl.getRight_ssSword().setWidth(SwordSize.SWORDYHEIGHT);
									swordControl.getRight_ssSword().setHeight(SwordSize.SWORDWIDTH);
									swordControl.getRight_ssSword().drawObj(getXPlayer() + 34, getYPlayer() + 32);
								}
								motion += 1;
							}
						}
						try {
							Thread.sleep(40);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}
		}).start();
	}

	public void attackMotion() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (isPlayerAttack() == false) {
					setPlayerAttack(true);
					if (isPlayerAttack()) {
						int imgxlocation = 1;
						int imgylocation = 0;
						while (true) {
							setPlayerAttacking(true);
							if (getViewDirect() == ViewDirect.DOWN) {
								if (imgxlocation == 1 && imgylocation > 3) {
									imgylocation = 0;
									imgxlocation--;
								}
								if (imgxlocation == 0 && imgylocation > 2) {
									imgxlocation = 1;
									imgylocation = 0;
									setPlayerAttacking(false);
									swordControl.getDown_ssSword().setWidth(SwordSize.SWORDWIDTH);
									swordControl.getDown_ssSword().setHeight(SwordSize.SWORDYHEIGHT);
									swordControl.getDown_ssSword().setXPos(
											SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH);
									swordControl.getDown_ssSword().setYPos(SwordSize.SWORDYGAP);
									swordControl.getDown_ssSword().drawObj(getXPlayer() + 9, getYPlayer() + 50);
									break;
								}
								swordControl.getDown_ssSword().setWidth(SwordMotionSize.WIDTH);
								swordControl.getDown_ssSword().setHeight(SwordMotionSize.HEIGHT);
								swordControl.getDown_ssSword().setXPos(SwordMotionSize.WIDTH * imgxlocation);
								swordControl.getDown_ssSword().setYPos(SwordSize.SWORDIMGHEIGHT
										- (SwordMotionSize.IMGHEIGHT) + (SwordMotionSize.HEIGHT * imgylocation));
								swordControl.getDown_ssSword().drawObj(getXPlayer() - 30, getYPlayer() + 25);
								imgylocation++;
							} else if (getViewDirect() == ViewDirect.UP) {
								if (imgxlocation == 1 && imgylocation > 3) {
									imgylocation = 0;
									imgxlocation--;
								}
								if (imgxlocation == 0 && imgylocation > 2) {
									imgxlocation = 1;
									imgylocation = 0;
									setPlayerAttacking(false);
									swordControl.getUp_ssSword().setWidth(SwordSize.SWORDWIDTH);
									swordControl.getUp_ssSword().setHeight(SwordSize.SWORDYHEIGHT);
									swordControl.getUp_ssSword().setXPos(SwordSize.SWORDXGAP);
									swordControl.getUp_ssSword().setYPos(
											SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP - SwordSize.SWORDYHEIGHT);
									swordControl.getUp_ssSword().drawObj(getXPlayer() + 13, getYPlayer() - 25);
									break;
								}
								swordControl.getUp_ssSword().setWidth(SwordMotionSize.WIDTH);
								swordControl.getUp_ssSword().setHeight(SwordMotionSize.HEIGHT);
								swordControl.getUp_ssSword()
										.setXPos(SwordMotionSize.WIDTH - (SwordMotionSize.WIDTH * imgxlocation));
								swordControl.getUp_ssSword().setYPos(SwordMotionSize.IMGHEIGHT - SwordMotionSize.HEIGHT
										- (SwordMotionSize.HEIGHT * imgylocation));
								swordControl.getUp_ssSword().drawObj(getXPlayer() - 25, getYPlayer() - 50);
								imgylocation++;
							} else if (getViewDirect() == ViewDirect.LEFT) {
								if (imgxlocation == 1 && imgylocation > 3) {
									imgylocation = 0;
									imgxlocation--;
								}
								if (imgxlocation == 0 && imgylocation > 2) {
									imgxlocation = 1;
									imgylocation = 0;
									setPlayerAttacking(false);
									swordControl.getLeft_ssSword().setWidth(SwordSize.SWORDYHEIGHT);
									swordControl.getLeft_ssSword().setHeight(SwordSize.SWORDWIDTH);
									swordControl.getLeft_ssSword().setXPos(
											SwordSize.SWORDIMGHEIGHT - SwordSize.SWORDYGAP - SwordSize.SWORDYHEIGHT);
									swordControl.getLeft_ssSword().setYPos(
											SwordSize.SWORDIMGWIDTH - SwordSize.SWORDXGAP - SwordSize.SWORDWIDTH);
									swordControl.getLeft_ssSword().drawObj(getXPlayer() - 36, getYPlayer() + 27);
									break;
								}
								swordControl.getLeft_ssSword().setWidth(SwordMotionSize.HEIGHT);
								swordControl.getLeft_ssSword().setHeight(SwordMotionSize.WIDTH);
								swordControl.getLeft_ssSword().setXPos((SwordMotionSize.IMGHEIGHT)
										- SwordMotionSize.HEIGHT - (SwordMotionSize.HEIGHT * imgylocation));
								swordControl.getLeft_ssSword().setYPos(SwordMotionSize.WIDTH * imgxlocation);
								swordControl.getLeft_ssSword().drawObj(getXPlayer() - 60, getYPlayer() - 10);
								imgylocation++;
							} else if (getViewDirect() == ViewDirect.RIGHT) {
								if (imgxlocation == 1 && imgylocation > 3) {
									imgylocation = 0;
									imgxlocation--;
								}
								if (imgxlocation == 0 && imgylocation > 2) {
									imgxlocation = 1;
									imgylocation = 0;
									setPlayerAttacking(false);
									swordControl.getRight_ssSword().setWidth(SwordSize.SWORDYHEIGHT);
									swordControl.getRight_ssSword().setHeight(SwordSize.SWORDWIDTH);
									swordControl.getRight_ssSword().setXPos(SwordSize.SWORDYGAP);
									swordControl.getRight_ssSword().setYPos(SwordSize.SWORDXGAP);
									swordControl.getRight_ssSword().drawObj(getXPlayer() + 34, getYPlayer() + 32);
									break;
								}
								swordControl.getRight_ssSword().setWidth(SwordMotionSize.HEIGHT);
								swordControl.getRight_ssSword().setHeight(SwordMotionSize.WIDTH);
								swordControl.getRight_ssSword().setXPos(SwordSize.SWORDIMGHEIGHT
										- SwordMotionSize.IMGHEIGHT + (SwordMotionSize.HEIGHT * imgylocation));
								swordControl.getRight_ssSword()
										.setYPos(SwordMotionSize.WIDTH - (SwordMotionSize.WIDTH * imgxlocation));
								swordControl.getRight_ssSword().drawObj(getXPlayer() + 20, getYPlayer() - 5);
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

	@Override
	public void attack() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (isPlayerAttacking()) {
					
				} else {
					if(issac.getViewDirect()==ViewDirect.DOWN) {
						if(-swordControl.getXSword())
					}else if (issac.getViewDirect()==ViewDirect.UP) {
						
					}else if (issac.getViewDirect()==ViewDirect.LEFT) {
						
					}else if (issac.getViewDirect()==ViewDirect.RIGHT) {
						
					}
					
				}
			}
		}).start();
	}
}
