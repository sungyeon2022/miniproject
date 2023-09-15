//package monster;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import javax.swing.JFrame;
//
//import SpriteSheet.SpriteSheet;
//import imgSize.Gap;
//import imgSize.ViewDirect;
//import imgSize.WormSize;
//import player.issac;
//
//public class Worm extends Monster {
//	private final static String GUBUN = "Worm : ";
//
//	public Worm(JFrame app, issac Issac, String url, int imgWidth, int imgHeight) {
//		super(app, Issac, url, imgWidth, imgHeight );
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				TimerTask task = new TimerTask() { //3초마다 반복하는 구
//					public void run() {
//						
//						
//					}
//				};
//				Timer timer = new Timer("Timer");
//				long delay = 1000L;
//				long period = 1000L;
//				timer.schedule(task, delay, period);
//
//				while(!isDead()) {
//					if(getLife() <= 0) {
//						setDead(true);
//						break;
//					}
//					moveDirectCheck();
//					moveUp();					
//					moveDown();
//					moveRight();
//					moveLeft();
//					moveMotion();
//					getSsMonster().drawObj(getXPlayer(), getYPlayer());
//					try {
//						Thread.sleep(30);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					if(isPlayerAttacking() == false) {
//						attack();
//					}
//
//				}
//				if(isDead()) {
//					timer.cancel();
//					dead();
//				}
//			}
//		}).start();
//	}
//	
//	public void init() {
//		setSsMonster(new SpriteSheet(getUrl(), "monster", 0, 0, getImgWidth(), getImgHeight())); 
//	}
//	public void setting() {
//		setViewDirect(ViewDirect.RIGHT);
//		setXPlayer(180);
//		setYPlayer(130);
//		setXPlayerCenter(getXPlayer() + WormSize.WIDTH / 2); 
//		setYPlayerCenter(getYPlayer() + WormSize.HEIGHT / 2); 
//		setLife(20);
//	}
//	public void batch() {
//		getSsMonster().drawObj(getXPlayer(), getYPlayer());
//		getApp().add(getSsMonster(), 0);
//	}
//	
//	public void attckCheck(int direct, int range) {
//		setPlayerAttacking(true);
//		int xDistance = getIssac().getXPlayerCenter() - getXPlayerCenter();
//		int yDistance = getIssac().getYPlayerCenter() - getYPlayerCenter();
//		if(xDistance < range || yDistance < range) {
//			attackMotion(direct);
//			System.out.println("공격");
//		}
//	}
//	@Override
//	public void attack() {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				
//				switch (getViewDirect()) {
//					case ViewDirect.DOWN:
//						attckCheck(ViewDirect.DOWN, 20);
//						break;	
//					case ViewDirect.LEFT:
//						attckCheck(ViewDirect.LEFT, 20);
//						break;
//					case ViewDirect.UP:
//						attckCheck(ViewDirect.UP, 20);
//						break;
//					case ViewDirect.RIGHT:
//						attckCheck(ViewDirect.RIGHT, 20);
//						break;
//				}
//				setPlayerAttacking(false);
//			} 
//			
//		}).start();
//	}
//	public void attackMotion(int direct) {
//		monsterSpeed = 2;
//		switch (getViewDirect()) {
//		case ViewDirect.DOWN:
//			moveDown();
//			break;	
//		case ViewDirect.LEFT:
//			moveLeft();
//			break;
//		case ViewDirect.UP:
//			moveUp();
//			break;
//		case ViewDirect.RIGHT:
//			moveRight();
//			break;
//		}
//
//		getSsMonster().setXPos((WormSize.WIDTH * direct) + (Gap.COLUMGAP * direct));
//		getSsMonster().setYPos(WormSize.HEIGHT * 4 + Gap.ROWGAP * 4);
//		getSsMonster().drawObj(getXPlayer(), getYPlayer());
//		
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
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
//	}
//}