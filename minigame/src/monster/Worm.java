package monster;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.Gap;
import imgSize.ViewDirect;
import imgSize.WormSize;
import lombok.Data;
import player.issac;

@Data

public class Worm extends Monster {
	public static boolean monsterAttacking = false;
	private static int gapX ;
	private static int gapY;
	private boolean checkAttackDirectX = false;
	private boolean checkAttackDirectY = false;
	private int attackDirect;
	private final static String GUBUN = "Worm : ";

	public Worm(JFrame app, issac Issac, String url, int imgWidth, int imgHeight) {
		super(app, Issac, url, imgWidth, imgHeight );
		Timer timer = new Timer("Timer");
		new Thread(new Runnable() {
			@Override
			public void run() {
				TimerTask task = new TimerTask() { //4초마다 방향전환
					public void run() {
						if(monsterAttacking == false) {
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
					gapCheck();
					moveRangeCheck();
					attack(); // 지속적으로 공격 조건 감시
					moveUp();					
					moveDown();
					moveRight();
					moveLeft();
					System.out.println(monsterAttacking);
					if(monsterAttacking == false)
					{
						moveMotion();
					getSsMonster().drawObj(getXPlayer(), getYPlayer());
					}
						try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
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
	public void gapCheck() {
		gapX = getIssac().getXPlayer() - getXPlayer(); //아이작 위치 - 몬스터 위치
		gapY = getIssac().getYPlayer() - getYPlayer();
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
	
	public void attack() { //아이작과 몬스터 겹치면 공격 시작

		if (Math.abs(gapX) < 10 || Math.abs(gapY) < 10 ) {
			if(Math.abs(gapX) < 10 ) {
				checkAttackDirectY = true;
			}else {

				checkAttackDirectX = true;
			}
			attackOn();
		}
	}
	public void attackMotion(int direct) {
//		getSsMonster().setXPos((WormSize.WIDTH * direct) + (Gap.COLUMGAP * direct));
//		getSsMonster().setYPos(WormSize.HEIGHT * 4 + Gap.ROWGAP * 4);
//		getSsMonster().drawObj(getXPlayer(), getYPlayer());
//		
//
//		getSsMonster().setXPos(0);
//		getSsMonster().setYPos(WormSize.HEIGHT * direct + Gap.ROWGAP * direct);
//		getSsMonster().drawObj(getXPlayer(), getYPlayer());
//		
//		
//		getIssac().setLife(getIssac().getLife() - 1);	// 플레이어 생명력 1감소
//		getIssac().dead();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	public void attackOn() {
//		System.out.println(getLife());
//		System.out.println(isDead());
//		System.out.println("공격");
		monsterSpeed = 4;
		monsterAttacking = true;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(getXPlayer() > 130 && getXPlayer() < 790 && getYPlayer() > 100 && getYPlayer() <440) {
			
			if( checkAttackDirectX && (gapX > 0)) { //오른쪽 공격
				setViewDirect(ViewDirect.RIGHT);
				attackMotion(ViewDirect.RIGHT);
				moveRight();
				setLeft(false);
				setRight(true);
				setDown(false);
				setUp(false);
			}else if ( checkAttackDirectX && (gapX <= 0)) { //왼쪽 공격
				setViewDirect(ViewDirect.LEFT);
				attackMotion(ViewDirect.LEFT);
				moveLeft();
				setRight(false);
				setLeft(true);
				setDown(false);
				setUp(false);
			}else if ( checkAttackDirectY && (gapY > 0)) {//아래로 공격
				setViewDirect(ViewDirect.DOWN);
				attackMotion(ViewDirect.DOWN);
				moveDown();
				setLeft(false);
				setRight(false);
				setUp(false);
				setDown(true);
			}else if ( checkAttackDirectY && (gapY <= 0)) { //위로 공격
				setViewDirect(ViewDirect.UP);
				attackMotion(ViewDirect.UP);
				moveUp();
				setLeft(false);
				setRight(false);
				setDown(false);
				setUp(true);
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			attackMotion();
			
		}
		monsterAttacking = false;
		checkAttackDirectY = false;
		checkAttackDirectX = false;
		monsterSpeed = 2;
		
	
		
	}

	@Override
	public void moveRight() {
		if(isRight()) {
			setViewDirect(ViewDirect.RIGHT);
				setXPlayer(getXPlayer() + monsterSpeed);
				setXPlayerCenter(getXPlayerCenter() + monsterSpeed);
		}
	}
	@Override
	public void moveLeft() {
		if(isLeft()) {
			setViewDirect(ViewDirect.LEFT);
				setXPlayer(getXPlayer() - monsterSpeed);
				setXPlayerCenter(getXPlayerCenter() - monsterSpeed);
			
		}
	}
	@Override
	public void moveUp() {
		if(isUp()) {
			setViewDirect(ViewDirect.UP);
				setYPlayer(getYPlayer() - monsterSpeed);
				setYPlayerCenter(getYPlayerCenter() - monsterSpeed);
			
		}
		
	}
	@Override
	public void moveDown() {
		if(isDown()) {
			setViewDirect(ViewDirect.DOWN);
				setYPlayer(getYPlayer() + monsterSpeed);
				setYPlayerCenter(getYPlayerCenter() + monsterSpeed);
			
		}
	}
	public void moveMotion() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int motion = 0;
				if(isPlayerMoveStart() == false ) {
					setPlayerMoveStart(true);
					while(!isDead()) {
						if(isDown() && getViewDirect() == ViewDirect.DOWN) {
							if(motion > 3)
								motion = 0;
							getSsMonster().setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if(getViewDirect() == ViewDirect.DOWN) {
								getSsMonster().setYPos(getImgHeight() * 0 + Gap.ROWGAP * 0);	// 몸 이미지 y좌표
								getSsMonster().drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
						else if(isLeft() && getViewDirect() == ViewDirect.LEFT) {
							if(motion > 3)
								motion = 0;
							getSsMonster().setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if(getViewDirect() == ViewDirect.LEFT) {
								getSsMonster().setYPos(getImgHeight() * 1 + Gap.ROWGAP * 1);	// 몸 이미지 y좌표
								getSsMonster().drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
						else if(isUp() && getViewDirect() == ViewDirect.UP) {
							if(motion > 3)
								motion = 0;
							getSsMonster().setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if(getViewDirect() == ViewDirect.UP) {
								getSsMonster().setYPos(getImgHeight() * 2 + Gap.ROWGAP * 2);	// 몸 이미지 y좌표
								getSsMonster().drawObj(getXPlayer(), getYPlayer());
								motion += 1;
							}
						}
						else if(isRight() && getViewDirect() == ViewDirect.RIGHT) {
							if(motion > 3)
								motion = 0;
							getSsMonster().setXPos((getImgWidth() * motion) + (Gap.COLUMGAP * motion));
							if(getViewDirect() == ViewDirect.RIGHT) {
								getSsMonster().setYPos(getImgHeight() * 3 + Gap.ROWGAP * 3);	// 몸 이미지 y좌표
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
	