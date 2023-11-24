package monster;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.plaf.IconUIResource;

import SpriteSheet.SpriteSheet;
import imgSize.BodySize;
import imgSize.Gap;
import imgSize.RockSize;
import objectSetting.*;
import lombok.Data;
import main.miniApp;
import player.issac;
import wall.structure;

@Data

public class body extends Monster {
	private String GUBUN = "Body";
	public int monsterSpeed = 2;
	private int defaultX = 400;
	private int defaultY = 200;
	private Vector<structure> structures;

	public body(miniApp app, issac Issac, String url, int imgWidth, int imgHeight) {
		super(app, Issac, url, imgWidth, imgHeight);
		this.structures = getApp().getStructures();
		setGUBUN("Body");
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!isDead()) {
					if (getLife() <= 0) {
						setDead(true);
						break;
					}
					
					moveUp(); moveDown(); moveRight(); moveLeft();
					 
					
					moveMotion();
					getSsMonster().drawObj(getXPlayer(), getYPlayer());
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 죽으면 루프멈추고끝
				if (isDead()) {
					dead();
				}
			}
		}).start();
	}

	// 시작할 때 이미지 불러오기
	public void init() {
		setSsMonster(new SpriteSheet(getUrl(), "monster", 0, 0, getImgWidth(), getImgHeight()));
	}

	// 기본 세팅
	public void setting() {
		setViewDirect(ViewDirect.RIGHT);
		setXPlayer(100);
		setYPlayer(100);
		setXPlayerCenter(getXPlayer() + BodySize.WIDTH / 2);
		setYPlayerCenter(getYPlayer() + BodySize.HEIGHT / 2);
		setLife(20);
	}

	public void batch() {
		getSsMonster().drawObj(getXPlayer(), getYPlayer());
		getApp().add(getSsMonster(), 0);
	}

	@Override
	public void moveRight() {
		if (getXPlayer() < getIssac().getXPlayerCenter() - getSsMonster().getWidth() / 2) {
			
			setViewDirect(ViewDirect.RIGHT);
			setXPlayer(getXPlayer() + monsterSpeed);
			setXPlayerCenter(getXPlayerCenter() + monsterSpeed);
			if (isblocked()) {
				setXPlayer(getXPlayer() - monsterSpeed);
				setXPlayerCenter(getXPlayerCenter() - monsterSpeed);
			}
			setRight(true);
		} else {
			setRight(false);
		}
	}

	@Override
	public void moveLeft() {
		if (getXPlayer() > getIssac().getXPlayerCenter() - getSsMonster().getWidth() / 2) {
			setViewDirect(ViewDirect.LEFT);
			setXPlayer(getXPlayer() - monsterSpeed);
			setXPlayerCenter(getXPlayerCenter() - monsterSpeed);
			if (isblocked()) {
				setXPlayer(getXPlayer() + monsterSpeed);
				setXPlayerCenter(getXPlayerCenter() + monsterSpeed);
			}
			setLeft(true);
		} else {
			setLeft(false);
		}
	}

	@Override
	public void moveUp() {
		if (getYPlayer() > getIssac().getYPlayerCenter() - getSsMonster().getHeight() / 2) {
			setViewDirect(ViewDirect.UP);
			setYPlayer(getYPlayer() - monsterSpeed);
			setYPlayerCenter(getYPlayerCenter() - monsterSpeed);

			
			  if (isblockedy()) { setYPlayer(getYPlayer() + monsterSpeed*2);
			  setYPlayerCenter(getYPlayerCenter() + monsterSpeed*2); }
			 

			setUp(true);
		} else {
			setUp(false);
		}

	}

	@Override
	public void moveDown() {
		if (getYPlayer() < getIssac().getYPlayerCenter() - getSsMonster().getHeight() / 2) {
			setViewDirect(ViewDirect.DOWN);
			setYPlayer(getYPlayer() + monsterSpeed);
			setYPlayerCenter(getYPlayerCenter() + monsterSpeed);

			
			  if (isblockedy()) { setYPlayer(getYPlayer() - monsterSpeed*2);
			  setYPlayerCenter(getYPlayerCenter() - monsterSpeed*2); }
			 

			setDown(true);
		} else {
			setDown(false);
		}
	}
	
	
	public void moveMotion() {
		if (isPlayerMoveStart())
			return;
		new Thread(new Runnable() {
			@Override
			public void run() {
				int motion = 0;
				if (isPlayerMoveStart() == false) {
					setPlayerMoveStart(true);
					while (!isDead()) {
						if (isDown() && getViewDirect() == ViewDirect.DOWN) {
							if (motion > 9)
								motion = 0;
							getSsMonster().setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if (getViewDirect() == ViewDirect.DOWN) {
								getSsMonster().setYPos(getImgHeight() * 0 + Gap.ROWGAP * 0); // 몸 이미지 y좌표
								getSsMonster().drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						} else if (isLeft() && getViewDirect() == ViewDirect.LEFT) {
							if (motion > 9)
								motion = 0;
							getSsMonster().setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if (getViewDirect() == ViewDirect.LEFT) {
								getSsMonster().setYPos(getImgHeight() * 2 + Gap.ROWGAP * 2); // 몸 이미지 y좌표
								getSsMonster().drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						} else if (isUp() && getViewDirect() == ViewDirect.UP) {
							if (motion > 9)
								motion = 0;
							getSsMonster().setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if (getViewDirect() == ViewDirect.UP) {
								getSsMonster().setYPos(getImgHeight() * 3 + Gap.ROWGAP * 3); // 몸 이미지 y좌표
								getSsMonster().drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						} else if (isRight() && getViewDirect() == ViewDirect.RIGHT) {
							if (motion > 9)
								motion = 0;
							getSsMonster().setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if (getViewDirect() == ViewDirect.RIGHT) {
								getSsMonster().setYPos(getImgHeight() * 1 + Gap.ROWGAP * 1); // 몸 이미지 y좌표
								getSsMonster().drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
						try {
							Thread.sleep(70);
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
			}
		}).start();
	}

	public void collisionRock() {
		// new Thread(()->{
		if (structures != null) {
			for (int i = 0; i < structures.size(); i++) {
				if (!structures.get(i).isBroken() && structures.get(i).getSsStructure().getGubun() == "rock") {
					if (getSsMonster().getBounds().intersects(structures.get(i).getSsStructure().getBounds())) {
						// 돌 오른쪽면에 닿음
						if ((getYPlayer() <= structures.get(i).getYwall() + RockSize.HEIGHT
								&& getYPlayer() + getImgHeight() >= structures.get(i).getYwall())
								&& getXPlayer() + getImgWidth() >= structures.get(i).getXwall()
								&& getXPlayer() + getImgWidth() < structures.get(i).getXwall() + RockSize.WIDTH) {
							setXPlayer(getXPlayer() - monsterSpeed);
							setXPlayerCenter(getXPlayerCenter() - monsterSpeed);
							System.out.println("you hit left side of wall");
						}
						// 돌 왼쪽면에 닿음

						if ((getYPlayer() <= structures.get(i).getYwall() + RockSize.HEIGHT
								&& getYPlayer() + getImgHeight() >= structures.get(i).getYwall())
								&& getXPlayer() <= structures.get(i).getXwall() + RockSize.WIDTH
								&& getXPlayer() >= structures.get(i).getXwall()) {
							setXPlayer(getXPlayer() + monsterSpeed);
							setXPlayerCenter(getXPlayerCenter() + monsterSpeed);
							System.out.println("you hit right side of wall");
						}
						// 돌 아랫면에 닿음
						if ((getXPlayer() <= structures.get(i).getXwall() + RockSize.WIDTH
								&& getXPlayer() + getImgWidth() >= structures.get(i).getXwall())
								&& getYPlayer() <= structures.get(i).getYwall() + RockSize.HEIGHT
								&& getYPlayer() >= structures.get(i).getYwall()) {
							setYPlayer(getYPlayer() + monsterSpeed);
							setYPlayerCenter(getYPlayerCenter() + monsterSpeed);
							System.out.println("you hit bottom side of wall");
						}
						// 돌 윗면에 닿음
						if ((getXPlayer() <= structures.get(i).getXwall() + RockSize.WIDTH
								&& getXPlayer() + getImgWidth() >= structures.get(i).getXwall())
								&& getYPlayer() + getImgHeight() >= structures.get(i).getYwall()
								&& getYPlayer() + getImgHeight() <= structures.get(i).getYwall() + RockSize.HEIGHT) {
							setYPlayer(getYPlayer() - monsterSpeed);
							setYPlayerCenter(getYPlayerCenter() - monsterSpeed);
							System.out.println("you hit top side of wall");
						}

					}
				}
			}
		}
		// }).start();

	}

	public boolean isblocked() {
		if (structures != null) {
			for (int i = 0; i < structures.size(); i++) {
				if (!structures.get(i).isBroken() && structures.get(i).getSsStructure().getGubun() == "rock") {
					if (getSsMonster().getBounds().intersects(structures.get(i).getSsStructure().getBounds())) {
						System.out.println("im stuck");
						return true;

					}
				}
			}
		}
		return false;
	}
	
	public boolean isblockedy() {
		if (structures != null) {
			for (int i = 0; i < structures.size(); i++) {
				if (!structures.get(i).isBroken() && structures.get(i).getSsStructure().getGubun() == "rock") {
					if (getSsMonster().getBounds().intersects(structures.get(i).getSsStructure().getBounds())) {
						System.out.println("im stucky");
						return true;

					}
				}
			}
		}
		return false;
	}

}
