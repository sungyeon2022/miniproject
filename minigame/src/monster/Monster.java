package monster;

import java.util.Vector;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import player.Player;
import player.issac;
import lombok.Data;
import objectSetting.Gap;
import objectSetting.ViewDirect;
import objectSetting.issacSize;

@Data

public class Monster extends Player{
	private final static String TAG = "Monster : ";
	
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
			setRight(true);
		} else {
			setRight(false);
		}
		// 왼쪽
		if((getXPlayerCenter() - getImgWidth() / 2 > getIssac().getXPlayerCenter()) &&(getXPlayer()>130)) {
			setLeft(true);
		} else {
			setLeft(false);
		}
		// 아래쪽
		if((getXPlayerCenter() + getImgHeight() < getIssac().getXPlayerCenter()) &&(getYPlayer()<440)) {
			setDown(true);
		}else {
			setDown(false);
		}
		// 위쪽
		if((getXPlayerCenter() - getImgHeight() / 2 > getIssac().getXPlayerCenter()) &&(getYPlayer()>100)) {
			setUp(true);
		}else {
			setUp(false);
		}
		
		// 상하 좌우 중 하나만 활성화 벽에 부딪히면 하나 꺼지고 하나랜덤 활성화

	}

	@Override
	public void moveRight() {
		if(isRight()) {
			setViewDirect(ViewDirect.RIGHT);
			if(getXPlayerCenter() < issac.getXPlayerCenter()) {
				setXPlayer(getXPlayer() + 1);
				setXPlayerCenter(getXPlayerCenter() + 1);
			}
		}
	}
	@Override
	public void moveLeft() {
		if(isLeft()) {
			setViewDirect(ViewDirect.LEFT);
			if(getXPlayerCenter() > issac.getXPlayerCenter()) {
				setXPlayer(getXPlayer() - 1);
				setXPlayerCenter(getXPlayerCenter() - 1);
			}
		}
	}
	@Override
	public void moveUp() {
		if(isUp()) {
			setViewDirect(ViewDirect.UP);
			if(getXPlayerCenter() > issac.getXPlayerCenter()) {
				setYPlayer(getYPlayer() - 1);
				setXPlayerCenter(getYPlayerCenter() - 1);
			}
		}
		
	}
	@Override
	public void moveDown() {
		if(isDown()) {
			setViewDirect(ViewDirect.DOWN);
			if(getXPlayerCenter() < issac.getXPlayerCenter()) {
				setYPlayer(getYPlayer() + 1);
				setXPlayerCenter(getYPlayerCenter() + 1);
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
							ssMonster.setXPos((getImgWidth() * motion) + (Gap.COLUMNGAP * motion));
							if(getViewDirect() == ViewDirect.DOWN) {
								ssMonster.setYPos(getImgHeight() * 0 + Gap.ROWGAP * 0);	// 몸 이미지 y좌표
								ssMonster.drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
						else if(isLeft() && getViewDirect() == ViewDirect.LEFT) {
							if(motion > 3)
								motion = 0;
							ssMonster.setXPos((getImgWidth() * motion) + (Gap.COLUMNGAP * motion));
							if(getViewDirect() == ViewDirect.LEFT) {
								ssMonster.setYPos(getImgHeight() * 1 + Gap.ROWGAP * 1);	// 몸 이미지 y좌표
								ssMonster.drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
						else if(isUp() && getViewDirect() == ViewDirect.UP) {
							if(motion > 3)
								motion = 0;
							ssMonster.setXPos((getImgWidth() * motion) + (Gap.COLUMNGAP * motion));
							if(getViewDirect() == ViewDirect.UP) {
								ssMonster.setYPos(getImgHeight() * 2 + Gap.ROWGAP * 2);	// 몸 이미지 y좌표
								ssMonster.drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
						else if(isRight() && getViewDirect() == ViewDirect.RIGHT) {
							if(motion > 3)
								motion = 0;
							ssMonster.setXPos((getImgWidth() * motion) + (Gap.COLUMNGAP * motion));
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