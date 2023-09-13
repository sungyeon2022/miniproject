package monster;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.ViewDirect;
import imgSize.WormSize;
import lombok.Data;
import player.issac;

@Data

public class Worm extends Monster {
	private boolean monsterAttacking = false;
	private int gapX = getIssac().getXPlayerCenter() - getXPlayerCenter(); //아이작 위치 - 몬스터 위치
	private int gapY = getIssac().getYPlayerCenter() - getYPlayerCenter();
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
							attack(); // 지속적으로 공격 조건 감시
						}
					}
				};
				long delay = 500L;
				long period = 4000L;
				timer.schedule(task, delay, period);
				while(!isDead() && monsterAttacking == false) {
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
	
	public void attack() { //아이작과 몬스터 겹치면 공격 시작

		if (Math.abs(gapX) < 20 || Math.abs(gapY) < 20 ) {
			if(gapX < 20 ) {
				checkAttackDirectX = true;
			}else {
				checkAttackDirectY = true;
			}
			attackOn();
		}
	}
	public void attackOn() {
		while(getXPlayer() > 130 && getXPlayer() < 790 && getYPlayer() > 100 && getYPlayer() <440) {
			monsterAttacking = true;
			monsterSpeed = 4;
			if( checkAttackDirectX && (gapX > 0)) { //오른쪽 공격
				setViewDirect(ViewDirect.RIGHT);
				setLeft(false);
				setRight(true);
				setDown(false);
				setUp(false);
			}else if ( checkAttackDirectX && (gapX <= 0)) { //왼쪽 공격
				setViewDirect(ViewDirect.LEFT);
				setRight(false);
				setLeft(true);
				setDown(false);
				setUp(false);
			}else if ( checkAttackDirectY && (gapY > 0)) {//아래로 공격
				setViewDirect(ViewDirect.DOWN);
				setLeft(false);
				setRight(false);
				setUp(false);
				setDown(true);
			}else if ( checkAttackDirectY && (gapY <= 0)) { //위로 공격
				setViewDirect(ViewDirect.UP);
				setLeft(false);
				setRight(false);
				setDown(false);
				setUp(true);//check
			}
//			attackMotion();
			
		}
		
	
		
	}
}
	