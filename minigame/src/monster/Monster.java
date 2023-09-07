package monster;

import java.util.Vector;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import player.Player;
import player.issac;
import lombok.Data;
import imgSize.*;//objectsetting에서 옮겨옴

@Data

public class Monster extends Player{
	private final static String TAG = "Monster : ";
	public static int monsterSpeed = 1;
	private issac issac;
	private SpriteSheet ssMonster;
	private SpriteSheet ssDead;
	private String url;
	private int imgWidth, imgHeight;

	public Monster(JFrame app, issac issac, String url, int imgWidth, int imgHeight) {
		super(app);
		this.issac = issac;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.url = url;
		init();
		setting();
		batch();
	}
	public void init() {
	}
	public void setting() {
		
	}
	public void batch() {
		
	}
	public void moveDirectCheck() {
		// 오른쪽 
		if((getXPlayerCenter() + getImgWidth() / 2 < getIssac().getXPlayerCenter()) &&(getXPlayer()>810) ) {
			setLeft(false);
			setUp(false);
			setDown(false);
			setRight(true);
//			System.out.println("벌레 오른쪽");
		} else {
			setRight(false);
		}
		// 왼쪽
		if((getXPlayerCenter() - getImgWidth() / 2 > getIssac().getXPlayerCenter()) &&(getXPlayer()>130)) {
			setLeft(true);
			setUp(false);
			setDown(false);
			setRight(false);
//			System.out.println("벌레 왼쪽");
		} else {
			setLeft(false);
		}
		// 아래쪽
		if((getXPlayerCenter() + getImgHeight() / 2 < getIssac().getXPlayerCenter()) &&(getYPlayer()<440)) {
			setLeft(false);
			setUp(false);
			setDown(true);
			setRight(false);
//			System.out.println("벌레 아래쪽");
		}else {
			setDown(false);
		}
		// 위쪽
		if((getXPlayerCenter() - getImgHeight() / 2 > getIssac().getXPlayerCenter()) &&(getYPlayer()>100)) {
			setLeft(false);
			setUp(true);
			setDown(false);
			setRight(false);
//			System.out.println("벌레 위쪽");
		}else {
			setUp(false);
		}
		

	}

	@Override
	public void moveRight() {
		if(isRight()) {
			setViewDirect(ViewDirect.RIGHT);
			if(getXPlayerCenter() < issac.getXPlayerCenter()) {
				setXPlayer(getXPlayer() + monsterSpeed);
				setXPlayerCenter(getXPlayerCenter() + monsterSpeed);
			}
		}
	}
	@Override
	public void moveLeft() {
		if(isLeft()) {
			setViewDirect(ViewDirect.LEFT);
			if(getXPlayerCenter() > issac.getXPlayerCenter()) {
				setXPlayer(getXPlayer() - monsterSpeed);
				setXPlayerCenter(getXPlayerCenter() - monsterSpeed);
			}
		}
	}
	@Override
	public void moveUp() {
		if(isUp()) {
			setViewDirect(ViewDirect.UP);
			if(getXPlayerCenter() > issac.getXPlayerCenter()) {
				setYPlayer(getYPlayer() - monsterSpeed);
				setXPlayerCenter(getYPlayerCenter() - monsterSpeed);
			}
		}
		
	}
	@Override
	public void moveDown() {
		if(isDown()) {
			setViewDirect(ViewDirect.DOWN);
			if(getXPlayerCenter() < issac.getXPlayerCenter()) {
				setYPlayer(getYPlayer() + monsterSpeed);
				setXPlayerCenter(getYPlayerCenter() + monsterSpeed);
			}
		}
	}
	public void moveMotion() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int motion = 0;
				if(isPlayerMoveStart() == false) {
					setPlayerMoveStart(true);
					while(!isDead()) {
						if(isDown() && getViewDirect() == ViewDirect.DOWN) {
							if(motion > 3)
								motion = 0;
							ssMonster.setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if(getViewDirect() == ViewDirect.DOWN) {
								ssMonster.setYPos(getImgHeight() * 0 + Gap.ROWGAP * 0);	// 몸 이미지 y좌표
								ssMonster.drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
						else if(isLeft() && getViewDirect() == ViewDirect.LEFT) {
							if(motion > 3)
								motion = 0;
							ssMonster.setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if(getViewDirect() == ViewDirect.LEFT) {
								ssMonster.setYPos(getImgHeight() * 1 + Gap.ROWGAP * 1);	// 몸 이미지 y좌표
								ssMonster.drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
						else if(isUp() && getViewDirect() == ViewDirect.UP) {
							if(motion > 3)
								motion = 0;
							ssMonster.setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if(getViewDirect() == ViewDirect.UP) {
								ssMonster.setYPos(getImgHeight() * 2 + Gap.ROWGAP * 2);	// 몸 이미지 y좌표
								ssMonster.drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
						else if(isRight() && getViewDirect() == ViewDirect.RIGHT) {
							if(motion > 3)
								motion = 0;
							ssMonster.setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if(getViewDirect() == ViewDirect.RIGHT) {
								ssMonster.setYPos(getImgHeight() * 3 + Gap.ROWGAP * 3);	// 몸 이미지 y좌표
								ssMonster.drawObj(getXPlayer(), getYPlayer());
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