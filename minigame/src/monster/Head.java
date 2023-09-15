package monster;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.Gap;
import imgSize.HeadSize;
import imgSize.ViewDirect;
import lombok.Data;

@Data

public class Head extends Monster{
	private final static String GUBUN = "Head : ";
	
	public Head(JFrame app, player.issac issac, String url, int imgWidth, int imgHeight) {
		super(app, issac, url, imgWidth, imgHeight);
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
		setSsMonster(new SpriteSheet(getUrl(), "monster", 0, 0, getImgWidth(), getImgHeight())); 
	}
	
	//기본 세팅
	public void setting() {
		setViewDirect(ViewDirect.RIGHT);
		setXPlayer(300);
		setYPlayer(200);
		setXPlayerCenter(getXPlayer() + HeadSize.WIDTH / 2); 
		setYPlayerCenter(getYPlayer() + HeadSize.HEIGHT / 2); 
		setLife(20);
		setRight(true);
		setUp(true);
	}
	
	public void batch() {
		getSsMonster().drawObj(getXPlayer(), getYPlayer());
		getApp().add(getSsMonster(), 0);
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
//			System.out.println("오른쪽 벽 충돌");
		}
		// 왼쪽
		if((getXPlayer()<130)) {
			setLeft(false);
			setRight(true);
//			System.out.println("왼쪽 벽 충돌");
		}
		// 아래쪽
		if((getYPlayer()>440)) {
			setDown(false);
			setUp(true);
//			System.out.println("아래쪽 벽 충돌");
		}
		// 위쪽
		if((getYPlayer()<100)) {
			setUp(false);
			setDown(true);
//			System.out.println("위쪽 벽 충돌");
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
						getSsMonster().setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion) + 2);
						getSsMonster().setYPos(getImgHeight() * 0 + Gap.ROWGAP * 0);	// 몸 이미지 y좌표
						getSsMonster().drawObj(getXPlayer(), getYPlayer());
						
						try {
							Thread.sleep(300);
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
