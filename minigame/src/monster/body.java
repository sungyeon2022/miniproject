package monster;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.BodySize;
import imgSize.Gap;
import imgSize.ViewDirect;
import lombok.Data;
import player.issac;

@Data

public class body extends Monster {
	private boolean monsterAttacking = false;
	private static int gapX ;
	private static int gapY;
	private boolean checkAttackDirectX = false;
	private boolean checkAttackDirectY = false;
	private int attackDirect;
	private final static String GUBUN = "Body : ";
	private boolean stop;
	public int monsterSpeed = 3;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public body(JFrame app, issac Issac, String url, int imgWidth, int imgHeight) {
		super(app, Issac, url, imgWidth, imgHeight );
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(!isDead()) {
					if(getLife() <= 0) {
						setDead(true);
						break;
					}
					System.out.println("check");
					moveRangeCheck();
					moveUp();					
					moveDown();
					moveRight();
					moveLeft();
					moveMotion();
					getSsMonster().drawObj(getXPlayer(), getYPlayer());
						try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//죽으면 루프멈추고끝
				if(isDead()) {
					dead();
				}
			}
		}).start();
	}
	
	//시작할 때 이미지 불러오기
	public void init() {
		setSsMonster(new SpriteSheet(getUrl(), "monster", 0, 0, getImgWidth(), getImgHeight())); 
	}
	//기본 세팅
	public void setting() {
		setViewDirect(ViewDirect.RIGHT);
		setXPlayer(300);
		setYPlayer(200);
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
		if(isRight()) {
			setViewDirect(ViewDirect.RIGHT);
			if(getXPlayer() < getIssac().getXPlayer()) {
				setXPlayer(getXPlayer() + monsterSpeed);
				setXPlayerCenter(getXPlayerCenter() + monsterSpeed);
			}
		}
	}
	@Override
	public void moveLeft() {
		if(isLeft()) {
			setViewDirect(ViewDirect.LEFT);
			if(getXPlayer() > getIssac().getXPlayer()) {
				setXPlayer(getXPlayer() - monsterSpeed);
				setXPlayerCenter(getXPlayerCenter() - monsterSpeed);
			}
		}
	}
	@Override
	public void moveUp() {
		if(isUp()) {
			setViewDirect(ViewDirect.UP);
			if(getYPlayer() > getIssac().getYPlayer()) {
				setYPlayer(getYPlayer() - monsterSpeed);
				setYPlayerCenter(getYPlayerCenter() - monsterSpeed);
			}
		}
		
	}
	@Override
	public void moveDown() {
		if(isDown()) {
			setViewDirect(ViewDirect.DOWN);
			if(getYPlayer() < getIssac().getYPlayer()) {
				setYPlayer(getYPlayer() + monsterSpeed);
				setYPlayerCenter(getYPlayerCenter() + monsterSpeed);
			}
		}
	}
	public void moveMotion() {
		if(isPlayerMoveStart()) return;
		new Thread(new Runnable() {
			@Override
			public void run() {		
				int motion = 0;
				if(isPlayerMoveStart() == false ) {
					setPlayerMoveStart(true);
					while(!isDead()) {
						System.out.println("check");
						if(isDown() && getViewDirect() == ViewDirect.DOWN) {
							if(motion > 9)
								motion = 0;
							getSsMonster().setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if(getViewDirect() == ViewDirect.DOWN) {
								getSsMonster().setYPos(getImgHeight() * 0 + Gap.ROWGAP * 0);	// 몸 이미지 y좌표
								getSsMonster().drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
						else if(isLeft() && getViewDirect() == ViewDirect.LEFT) {
							if(motion > 9)
								motion = 0;
							getSsMonster().setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if(getViewDirect() == ViewDirect.LEFT) {
								getSsMonster().setYPos(getImgHeight() * 2 + Gap.ROWGAP * 2);	// 몸 이미지 y좌표
								getSsMonster().drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
						else if(isUp() && getViewDirect() == ViewDirect.UP) {
							if(motion > 9)
								motion = 0;
							getSsMonster().setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if(getViewDirect() == ViewDirect.UP) {
								getSsMonster().setYPos(getImgHeight() * 3 + Gap.ROWGAP * 3);	// 몸 이미지 y좌표
								getSsMonster().drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
						else if(isRight() && getViewDirect() == ViewDirect.RIGHT) {
							if(motion > 9)
								motion = 0;
							getSsMonster().setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if(getViewDirect() == ViewDirect.RIGHT) {
								getSsMonster().setYPos(getImgHeight() * 1 + Gap.ROWGAP * 1);	// 몸 이미지 y좌표
								getSsMonster().drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
							try {
								Thread.sleep(100);
							} catch (Exception e) {
								e.printStackTrace();
							}
						
					}
				}
			}
		}).start();
	}
	
}
	