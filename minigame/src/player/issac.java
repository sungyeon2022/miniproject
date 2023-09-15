package player;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;

import SpriteSheet.SpriteSheet;
import item.Item;
import lombok.Data;
import objectSetting.Gap;
import objectSetting.RockSize;
import objectSetting.ViewDirect;
import objectSetting.issacSize;
import wall.wall;

@Data

public class issac extends Player {
	Player player;
	private final static String TAG = "issac: ";
	private issac issac = this;

	private Vector<SpriteSheet> ssLifes;
	private SpriteSheet ssHead, ssBody;
	private SpriteSheet ssTotal;

	private Vector<wall> walls;
	private Vector<Item> items;

	private int xPlusBody = 7, yPlusBody = 30;
	private int yTotalSize;
	private int item1Count = 0;
	private int item2Count = 0;
	private int item3Count = 0;
	private int item4Count = 0;
	private int moveSpeed = 10;
	// 개수 및 수치 표시용 레이블 + 변수 선언 및 초기화
	private JLabel labomb;
	private JLabel laspeed;
	private JLabel lapower;
	private JLabel laattackspeed;
	private int bombCount = 0;
	private int speedNum = 11;
	private int powerNum = 1;
	private int attackspeedNum = 1;

	public issac(JFrame app, Vector<wall> walls, Vector<Item> items) {
		super(app);
		System.out.println(TAG + "make issac");
		init(walls, items);
		setting();
		batch();

	}

	public void init(Vector<wall> walls, Vector<Item> items) {
		this.walls = walls;
		this.items = items;
		ssLifes = new Vector<SpriteSheet>();
		for (int i = 0; i < 5; i++) {
			this.ssLifes.add(new SpriteSheet("issac/life.png", "life", 0, 0, 30, 26));
		}
		ssHead = new SpriteSheet("issac/issac.png", "issacssHead", 0, 0, issacSize.issacHEADWIDTH,
				issacSize.issacHEADHEIGHT);
		ssBody = new SpriteSheet("issac/issac.png", "issacBody", 0, (issacSize.issacHEADHEIGHT + Gap.ROWGAP),
				issacSize.issacBODYWIDTH, issacSize.issacBODYHEIGHT);
		ssTotal = new SpriteSheet("issac/issac.png", "issacsBody", 0, yTotalSize, issacSize.issacTOTALWIDTH,
				issacSize.issacTOTALHEIGHT);

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
	}

	public void setting() {
		setViewDirect(ViewDirect.DOWN);
		setXPlayer(510);
		setYPlayer(300);
		setLife(5);
		setMaxlife(5);
		setXPlayerCenter(getXPlayer() + issacSize.issacHEADWIDTH / 2);
		setYPlayerCenter(getYPlayer() + issacSize.issacHEADHEIGHT);
		ssHead.drawObj(getXPlayer(), getYPlayer());
		ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
		// 체력 그리기
		for (int i = 0; i < 5; i++) {
			ssLifes.get(i).drawObj(10 + (i * 30), 10);
		}

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

	}

	public void batch() {
		getApp().add(ssHead, 0);
		getApp().add(ssBody, 1);
		// 체력추가
		for (int i = 0; i < 5; i++) {
			getApp().add(ssLifes.get(i), 1);
		}

		// 폭탄 파워 속도 레이블 추가
		getApp().add(labomb);
		getApp().add(lapower);
		getApp().add(laspeed);
		getApp().add(laattackspeed);
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
						// 돌 충돌 체크 시작
						boolean isRockCollision = false;
						for (int i = 0; i < walls.size(); i++) {
							if (!walls.get(i).isBroken() && walls.get(i).getSswall().getGubun() == "rock") {
								if (getXPlayerCenter() + (issacSize.issacHEADWIDTH / 2) > walls.get(i).getXwall()
										&& getXPlayerCenter() < walls.get(i).getXwall() + RockSize.WIDTH
										&& getYPlayerCenter() + issacSize.issacHEADHEIGHT - yPlusBody > walls.get(i)
												.getYwall()
										&& getYPlayerCenter() < walls.get(i).getYwall() + RockSize.HEIGHT) {
									isRockCollision = true;
									break;
								}
							}
						}
						if (isRockCollision) {
							setRight(false);
							refreshDirect();
							break;
						}
						getItem();
						// 돌 충돌 체크 끝
						setXPlayer(getXPlayer() + 1);
						setXPlayerCenter(getXPlayerCenter() + 1);
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					System.out.println("캐릭터생성");
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
					while (isLeft()) {
						if (getXPlayer() < 130) {
							setLeft(false);
							refreshDirect();
							break;
						}
						boolean isRockCollision = false;
						// 돌 충돌 체크 시작
						for (int i = 0; i < walls.size(); i++) {
							if (!walls.get(i).isBroken() && walls.get(i).getSswall().getGubun() == "rock") {

								if (getXPlayerCenter() > walls.get(i).getXwall()
										&& getXPlayerCenter() - (issacSize.issacHEADWIDTH / 2) < walls.get(i).getXwall()
												+ RockSize.WIDTH
										&& getYPlayerCenter() + issacSize.issacHEADHEIGHT - yPlusBody > walls.get(i)
												.getYwall()
										&& getYPlayerCenter() < walls.get(i).getYwall() + RockSize.HEIGHT) {
									isRockCollision = true;
									break;
								}
							}
						}
						if (isRockCollision) {
							setLeft(false);
							refreshDirect();
							break;
						}
						// 돌 충돌 체크 끝
						getItem();
						setXPlayer(getXPlayer() - 1);
						setXPlayerCenter(getXPlayerCenter() - 1);
						moveMotion();
						try {

							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					System.out.println("캐릭터생성");
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
					while (isDown()) {
						if (getYPlayer() > 440) {
							setDown(false);
							refreshDirect();
							break;
						}
						// 돌 충돌 체크 시작
						boolean isRockCollision = false;
						for (int i = 0; i < walls.size(); i++) {
							if (!walls.get(i).isBroken() && walls.get(i).getSswall().getGubun() == "rock") {
								if (getXPlayerCenter() + (issacSize.issacHEADWIDTH / 2) > walls.get(i).getXwall() + 5
										&& getXPlayerCenter() - (issacSize.issacHEADWIDTH / 2) < walls.get(i).getXwall()
												+ RockSize.WIDTH - 5
										&& getYPlayerCenter() + (issacSize.issacHEADHEIGHT - yPlusBody) + 5 > walls
												.get(i).getYwall()
										&& getYPlayerCenter() < walls.get(i).getYwall() + RockSize.HEIGHT) {
									isRockCollision = true;
									break;
								}
							}
						}
						if (isRockCollision) {
							setUp(false);
							refreshDirect();
							break;
						}
						getItem();
						// 돌 충돌 체크 끝
						setYPlayer(getYPlayer() + 1);// 플레이어 이동시 좌표값 변경
						setYPlayerCenter(getYPlayerCenter() + 1);// 중앙
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					System.out.println("캐릭터생성");
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
					while (isUp()) {
						if (getYPlayer() < 100) {
							setUp(false);
							refreshDirect();
							break;
						}
						boolean isRockCollision = false;
						// 돌 충돌 체크 시작
						for (int i = 0; i < walls.size(); i++) {
							if (!walls.get(i).isBroken() && walls.get(i).getSswall().getGubun() == "rock") {
								if (getXPlayerCenter() + (issacSize.issacHEADWIDTH / 2) > walls.get(i).getXwall() + 5
										&& getXPlayerCenter() - (issacSize.issacHEADWIDTH / 2) < walls.get(i).getXwall()
												+ RockSize.WIDTH - 5
										&& getYPlayerCenter() > walls.get(i).getYwall() && getYPlayerCenter()
												+ (issacSize.issacBODYHEIGHT - yPlusBody) < walls.get(i).getYwall()
														+ RockSize.HEIGHT) {
									isRockCollision = true;
									break;
								}
							}
						}
						if (isRockCollision) {
							setUp(false);
							refreshDirect();
							break;
						}
						getItem();
						// 돌 충돌 체크 끝
						setYPlayer(getYPlayer() - 1);
						setYPlayerCenter(getYPlayerCenter() - 1);
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					System.out.println("캐릭터생성");
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer() + xPlusBody, getYPlayer() + yPlusBody);
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
																									// 않게하기위해 사용합니다.
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
	//체력바 갱신
	public void reDrawLife() {
		double currentLife = getLife();
		for(int i = 0; i < 5; i++) {
			if(currentLife >= 1) {
				ssLifes.get(i).setXPos(0);
				currentLife -= 1;
			} else if(currentLife > 0 && currentLife < 1) {
				ssLifes.get(i).setXPos(30 + Gap.COLUMGAP);
				currentLife -= 0.5;
			} else {
				ssLifes.get(i).setXPos(30 * 2 + Gap.COLUMGAP * 2);
			}
		}
		for(int i = 0; i < 5; i++) {
			ssLifes.get(i).drawObj(10 + (i * 30), 10);
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
					else if (items.get(i).getSsItem().getGubun() == "FullHp") {
						setLife(getMaxlife());
						reDrawLife();
					}

					else if (items.get(i).getSsItem().getGubun() == "heart") {
						if (getLife() != getMaxlife()) {
							setLife(getLife() + 1);
							reDrawLife();
						}

					}

					System.out.println(items.get(i).getSsItem().getGubun() + " 발견");
					getItemMotion(items.get(i));

				}
			}

		}
	}
	//아이템 습득 모션
	public void getItemMotion(Item item) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				getApp().remove(ssHead);
				getApp().remove(ssBody);
				int col = 2;
				for(int i = 0; i < 2; i++) {
					col += i;
					for (int j = 0; j<200; j++) {
						try {
							Thread.sleep(1);
							item.getSsItem().drawObj(getXPlayer() + 10, getYPlayer() - 21);
							ssTotal.setXPos(issacSize.issacTOTALWIDTH * col + Gap.COLUMGAP * col);
							ssTotal.setYPos(yTotalSize);
							ssTotal.drawObj(getXPlayer()-5, getYPlayer()-6);
							getApp().add(ssTotal);
							getApp().repaint();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					}
				}
				getApp().remove(item.getSsItem());
				getApp().remove(ssTotal);
				getApp().repaint();
				getApp().add(ssHead, 0);
				getApp().add(ssBody, 1);
			}
		}).start();
		
	}
	
	
}
