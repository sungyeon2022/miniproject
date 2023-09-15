package monster;

import java.util.Random;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.DeadSize;
import imgSize.Gap;
import lombok.Data;
import player.Player;
import player.issac;//objectsetting에서 옮겨옴

@Data

public class Monster extends Player{
	
	private final static String TAG = "Monster : ";
	public int monsterSpeed = 2;
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
		ssDead = new SpriteSheet("monster/deadMonster.png", "dead", 0, 0, DeadSize.WIDTH, DeadSize.HEIGHT);
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
	public void attack() {
		
	}

	@Override
	public void dead() {
		int rowCount = 0;
		getApp().remove(ssMonster);
		getApp().repaint();
		for(int i = 0; i < 11; i ++) {
			if(i % 4 == 0 && i > 0) {
				rowCount += 1;
			}
			int x = DeadSize.WIDTH * (i % 4) + Gap.COLUMGAP * (i % 4);
			int y = DeadSize.HEIGHT * rowCount  + (Gap.ROWGAP * rowCount);
			ssDead.setXPos(x);
			ssDead.setYPos(y);
			ssDead.drawObj(getXPlayer(), getYPlayer());
			if(i == 0) {
				getApp().add(ssDead, 2);
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		getApp().remove(ssDead);
		
	}
	public void moveRangeCheck() { //맵 밖으로 나가려하면 강제 방향전환
		// 오른쪽
		if((getXPlayer()>790) ) {
			setRight(false);
			setLeft(true);
			setDown(false);
			setUp(false);
//			System.out.println("오른쪽 벽 충돌");
		}
		// 왼쪽
		if((getXPlayer()<130)) {
			setLeft(false);
			setRight(true);
			setDown(false);
			setUp(false);
//			System.out.println("왼쪽 벽 충돌");
		}
		// 아래쪽
		if((getYPlayer()>440)) {
			setLeft(false);
			setRight(false);
			setDown(false);
			setUp(true);
//			System.out.println("아래쪽 벽 충돌");
		}
		// 위쪽
		if((getYPlayer()<100)) {
			setLeft(false);
			setRight(false);
			setUp(false);
			setDown(true);
//			System.out.println("위쪽 벽 충돌");
		}	
	}
	public void moveDirectCheck() {
		 Random rd = new Random();
		 int directionSwitch = rd.nextInt(4);
		 switch(directionSwitch) {
		 case 0:
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
		 case 3:
			 setUp(false);
			 setDown(false);
			 setLeft(false);
			 setRight(true);
			 break;
		}
		

	}

}