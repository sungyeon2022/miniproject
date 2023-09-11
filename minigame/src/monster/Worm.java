package monster;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.Gap;
import imgSize.ViewDirect;
import imgSize.WormSize;
import player.issac;

public class Worm extends Monster {
	private final static String GUBUN = "Worm : ";

	public Worm(JFrame app, issac Issac, String url, int imgWidth, int imgHeight) {
		super(app, Issac, url, imgWidth, imgHeight );
		Timer timer = new Timer("Timer");
		new Thread(new Runnable() {
			@Override
			public void run() {
				TimerTask task = new TimerTask() { //4초마다 방향전환
					public void run() {
						if(isPlayerAttacking() == false) {
							System.out.println("방향전환");
							moveDirectCheck();							
						}
					}
				};
				long delay = 500L;
				long period = 4000L;
				timer.schedule(task, delay, period);
				while(!isDead()) {
					if(getLife() <= 0) {
						setDead(true);
						break;
					}
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
						if(isPlayerAttacking() == false) {
							attack();
						}
				}
				//죽으면 루프멈추고끝
				if(isDead()) {
					timer.cancel();
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
		setXPlayer(180);
		setYPlayer(130);
		setXPlayerCenter(getXPlayer() + WormSize.WIDTH / 2); 
		setYPlayerCenter(getYPlayer() + WormSize.HEIGHT / 2); 
		setLife(20);
	}
	public void batch() {
		getSsMonster().drawObj(getXPlayer(), getYPlayer());
		getApp().add(getSsMonster(), 0);
	}
	
	//공격 체크
	public void attackCheck(int direct, int range) {
//		int xDistance = getIssac().getXPlayerCenter() - getXPlayerCenter();
//		int yDistance = getIssac().getYPlayerCenter() - getYPlayerCenter();
//		System.out.println(getIssac().getXPlayerCenter() + "  " + range + " " + getYPlayer());
		if(getIssac().getXPlayerCenter()-range < 30 || getIssac().getYPlayerCenter()-range < 30) {
			System.out.println("공격");
			System.out.println(direct);
			System.out.println(range);
			System.out.println(getIssac().getXPlayerCenter());
			System.out.println(getIssac().getYPlayerCenter());
			System.out.println(getXPlayerCenter());
			System.out.println(getYPlayerCenter());
			setPlayerAttacking(true);
			switch(direct) {
			 case 3:
				 setUp(true);
				 setDown(false);
				 setLeft(false);
				 setRight(false);
				 break;
			 case 1:
				 setUp(false);
				 setDown(true);
				 setLeft(false);
				 setRight(false);
				 break;
			 case 2:
				 setUp(false);
				 setDown(false);
				 setLeft(true);
				 setRight(false);
				 break;
			 case 4:
				 setUp(false);
				 setDown(false);
				 setLeft(false);
				 setRight(true);
				 break;
			}
			monsterSpeed = 4;
			attackMotion(direct - 1);
			if(getXPlayer()>790 || getXPlayer()<130 || getYPlayer()>440 || getYPlayer()<100) {
				monsterSpeed = 2;
				setPlayerAttacking(false);
			}
			
		}
	}
	
	//공격 체크
	@Override
	public void attack() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				switch (getViewDirect()) {
				case ViewDirect.DOWN:
					attackCheck(ViewDirect.DOWN, getXPlayerCenter());
					break;	
				case ViewDirect.LEFT:
					attackCheck(ViewDirect.LEFT, getYPlayerCenter());
					break;
				case ViewDirect.UP:
					attackCheck(ViewDirect.UP, getXPlayerCenter());
					break;
				case ViewDirect.RIGHT:
					attackCheck(ViewDirect.RIGHT, getYPlayerCenter());
					break;
				}
			} 
		}).start();
	}
	public void attackMotion(int direct) {
		getSsMonster().setXPos((WormSize.WIDTH * direct) + (Gap.COLUMGAP * direct));
		getSsMonster().setYPos(WormSize.HEIGHT * 4 + Gap.ROWGAP * 4);
		getSsMonster().drawObj(getXPlayer(), getYPlayer());
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getSsMonster().setXPos(0);
		getSsMonster().setYPos(WormSize.HEIGHT * direct + Gap.ROWGAP * direct);
		getSsMonster().drawObj(getXPlayer(), getYPlayer());
		
		
		getIssac().setLife(getIssac().getLife() - 1);	// 플레이어 생명력 1감소
		getIssac().dead();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}}
