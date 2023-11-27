package monster;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.Gap;
import imgSize.HeadSize;
import objectSetting.*;
import lombok.Data;
import main.miniApp;
import player.issac;

@Data

public class Head extends Monster{
	private String GUBUN = "Head";
	private int defaultX = 300;
	private int defaultY = 200;
	
	public Head(miniApp app, int xLocation, int yLocation) {
		super(app, xLocation, yLocation);
		setGUBUN("Head");
		init();
		setting();
		batch();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(!isDead()) {
					if(getLife() <= 0) {
						setDead(true);
						break;
					}
					moveHead();
					moveUp();					
					moveDown();
					moveRight();
					moveLeft();
					moveMotion();
					getSsMonster().drawObj(getXPlayer(), getYPlayer());
						try {
						Thread.sleep(50);
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
		setSsMonster(new SpriteSheet("monster/head.png", "monster", 0, 0, HeadSize.WIDTH, HeadSize.HEIGHT)); 
	}
	
	//기본 세팅
	public void setting() {
		setViewDirect(ViewDirect.RIGHT);
		setXPlayer(getXLocation());
		setYPlayer(getYLocation());
		setXPlayerCenter(getXPlayer() + HeadSize.WIDTH / 2); 
		setYPlayerCenter(getYPlayer() + HeadSize.HEIGHT / 2); 
		setLife(20);
		setRight(true);
		setUp(true);
	}
	
	public void batch() {
		getSsMonster().drawObj(getXPlayer(), getYPlayer());
		getApp().add(getSsMonster(),1);
	}
	
	@Override
	public void moveRight() {
			if(isRight()) {
				setViewDirect(ViewDirect.RIGHT);
				setXPlayer(getXPlayer() + monsterSpeed);
				setXPlayerCenter(getXPlayerCenter() + monsterSpeed);
				setRight(true);
			}else {
				setRight(false);
			}
	}
	@Override
	public void moveLeft() {
			if(isLeft()) {
				setViewDirect(ViewDirect.LEFT);
				setXPlayer(getXPlayer() - monsterSpeed);
				setXPlayerCenter(getXPlayerCenter() - monsterSpeed);
				setLeft(true);
			}else {
				setLeft(false);
			}
	}
	@Override
	public void moveUp() {
			if(isUp()) {
				setViewDirect(ViewDirect.UP);
				setYPlayer(getYPlayer() - monsterSpeed);
				setYPlayerCenter(getYPlayerCenter() - monsterSpeed);
				setUp(true);
			}else {
				setUp(false);
			}
		
	}
	@Override
	public void moveDown() {
			if(isDown()) {
				setViewDirect(ViewDirect.DOWN);
				setYPlayer(getYPlayer() + monsterSpeed);
				setYPlayerCenter(getYPlayerCenter() + monsterSpeed);
				setDown(true);
			}else {
				setDown(false);
			}
	}
	
	public void moveHead() {
		if((getXPlayer()>790) ) {
			setRight(false);
			setLeft(true);
		}
		// 왼쪽
		if((getXPlayer()<130)) {
			setLeft(false);
			setRight(true);
		}
		// 아래쪽
		if((getYPlayer()>440)) {
			setDown(false);
			setUp(true);
		}
		// 위쪽
		if((getYPlayer()<100)) {
			setUp(false);
			setDown(true);
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
						if(motion > 2)
							motion =0;
						getSsMonster().setXPos((HeadSize.WIDTH * motion) + (7 * motion) + 4);
						getSsMonster().setYPos(HeadSize.HEIGHT * 0 + Gap.ROWGAP * 0);	// 몸 이미지 y좌표
						getSsMonster().drawObj(getXPlayer(), getYPlayer());
						
						try {
							Thread.sleep(200);
						} catch (Exception e) {
							e.printStackTrace();
						}
						motion += 1;
					}
			}
			}
		}).start();
	}
	
	
}
