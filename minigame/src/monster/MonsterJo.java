package monster;

import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.Gap;
import imgSize.ViewDirect;
import lombok.Data;
import player.Player;
import player.issac;//objectsetting에서 옮겨옴

@Data

public class MonsterJo extends Player{
	
	private final static String TAG = "Monster : ";
	public static int monsterSpeed = 2;
	private issac issac;
	private SpriteSheet ssMonster;
	private SpriteSheet ssDead;
	private String url;
	private int imgWidth, imgHeight;

	public MonsterJo(JFrame app, issac issac, String url, int imgWidth, int imgHeight) {
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
	public void attack() {
		
	}
	public void moveRangeCheck() { //맵 밖으로 나가려하면 강제 방향전환
		// 오른쪽
		if((getXPlayer()>790) ) {
			setRight(false);
			setLeft(true);
			setDown(false);
			setUp(false);
			System.out.println("오른쪽 벽 충돌");
		}
//		// 왼쪽
		if((getXPlayer()<130)) {
			setLeft(false);
			setRight(true);
			setDown(false);
			setUp(false);
			System.out.println("왼쪽 벽 충돌");
		}
//		// 아래쪽
		if((getYPlayer()>440)) {
			setLeft(false);
			setRight(false);
			setDown(false);
			setUp(true);
			System.out.println("아래쪽 벽 충돌");
		}
//		// 위쪽
		if((getYPlayer()<100)) {
			setLeft(false);
			setRight(false);
			setUp(false);
			setDown(true);
			System.out.println("위쪽 벽 충돌");
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
	public void moveMotion() {}
		
}