package player;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import player.Player;
import SpriteSheet.SpriteSheet;
import lombok.Data;
import objectSetting.*;

@Data

public class issac extends Player{
	Player player;
	private final static String TAG = "issac: ";
	private issac issac = this;
	
	private SpriteSheet ssHead,ssBody;
	private SpriteSheet ssTotal;
	
	private int xPlusBody = 7, yPlusBody = 30;
	private int yTotalSize;
	private int item1Count = 0;
	private int item2Count = 0;
	private int item3Count = 0;
	private int item4Count = 0;
	private int moveSpeed = 12;
	
	public issac(JFrame app) {
		super(app);
		System.out.println(TAG + "make issac");
		init();
		setting();
		batch();
		
	}
	public void init() {

		ssHead = new SpriteSheet("issac/issac.png","issacssHead",0,0,issacSize.issacHEADWIDTH,issacSize.issacHEADHEIGHT);
		ssBody = new SpriteSheet("issac/issac.png", "issacBody", 0, (issacSize.issacHEADHEIGHT + Gap.ROWGAP), issacSize.issacBODYWIDTH, issacSize.issacBODYHEIGHT);
		ssTotal = new SpriteSheet("issac/issac.png", "issacsBody", 0, yTotalSize, issacSize.issacTOTALWIDTH, issacSize.issacTOTALHEIGHT);
		
		ssHead = new SpriteSheet("issac/issac.png","issacssHead",0,0,issacSize.issacHEADWIDTH,issacSize.issacHEADHEIGHT);
		ssBody = new SpriteSheet("issac/issac.png", "issacBody", 0, (issacSize.issacHEADHEIGHT + Gap.ROWGAP), issacSize.issacBODYWIDTH, issacSize.issacBODYHEIGHT);
		ssTotal = new SpriteSheet("issac/issac.png", "issacsBody", 0, yTotalSize, issacSize.issacTOTALWIDTH, issacSize.issacTOTALHEIGHT);
		yTotalSize = issacSize.issacHEADHEIGHT + issacSize.issacBODYHEIGHT * 4 + Gap.ROWGAP * 5;
	}
	
	public void setting() {
		setViewDirect(ViewDirect.DOWN);
		setXPlayer(480);
		setYPlayer(430);
		setXPlayerCenter(getXPlayer()+issacSize.issacHEADWIDTH/2);
		setYPlayerCenter(getXPlayer()+issacSize.issacHEADHEIGHT);
		ssHead.drawObj(getXPlayer(), getYPlayer());
		ssBody.drawObj(getXPlayer()+xPlusBody, getYPlayer()+yPlusBody);
	}
	public void batch() {
		getApp().add(ssHead,0);
		getApp().add(ssBody,1);
	}
	//상하 좌우 이동 모션
	@Override
	public void moveRight() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(isRight()==false) {
					setRight(true);
					setViewDirect(ViewDirect.RIGHT);
					while (isRight()) {
						if(getXPlayer()+issacSize.issacBODYWIDTH>810) { //벽이상 움직임 제한 
							setRight(false);
							refreshDirect();
							break;
						}
						setXPlayer(getXPlayer()+1);
						setXPlayerCenter(getXPlayerCenter()+1);
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}	
					}
					System.out.println("캐릭터생성");
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer()+xPlusBody, getYPlayer()+yPlusBody);
				}
			}
		}).start(); 
	}
	@Override
	public void moveLeft() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(isLeft()==false) {
					setLeft(true);
					setViewDirect(ViewDirect.LEFT);
					while (isLeft()) {
						if(getXPlayer()<130) {
							setLeft(false);
							refreshDirect();
							break;
						}
						setXPlayer(getXPlayer()-1);
						setXPlayerCenter(getXPlayerCenter()-1);
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}	
					}
					System.out.println("캐릭터생성");
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer()+xPlusBody, getYPlayer()+yPlusBody);
				}
			}
		}).start(); 
	}
	@Override
	public void moveDown() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(isDown()==false) {
					setDown(true);
					setViewDirect(ViewDirect.DOWN);
					while (isDown()) {
						if(getYPlayer()>440) {
							setDown(false);
							refreshDirect();
							break;
						}
						setYPlayer(getYPlayer()+1);//플레이어 이동시 좌표값 변경
						setYPlayerCenter(getYPlayerCenter()+1);//중앙
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}	
					}
					System.out.println("캐릭터생성");
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer()+xPlusBody, getYPlayer()+yPlusBody);
				}
			}
		}).start(); 
	}
	@Override
	public void moveUp() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(isUp()==false) {
					setUp(true);
					setViewDirect(ViewDirect.UP);
					while (isUp()) {
						if(getYPlayer()<100) {
							setUp(false);
							refreshDirect();
							break;
						}
						setYPlayer(getYPlayer()-1);
						setXPlayerCenter(getXPlayerCenter()-1);
						moveMotion();
						try {
							Thread.sleep(moveSpeed);
						} catch (Exception e) {
							e.printStackTrace();
						}	
					}
					System.out.println("캐릭터생성");
					ssBody.setXPos(0);
					ssHead.drawObj(getXPlayer(), getYPlayer());
					ssBody.drawObj(getXPlayer()+xPlusBody, getYPlayer()+yPlusBody);
				}
			}
		}).start(); 
	}
	@Override //Override
	public void moveMotion() { //움직이는 동작중 이미지 갱신
		//Down을 기준으로 설명하겠습니다 나머지 내용은 ColumGap과 RowGap, HEIGHT, WIDTH로 상하 좌우가 구분됩니다
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int motion = 0;
				if(isPlayerMoveStart()==false){
					setPlayerMoveStart(true);
					while(true) {
						if(isDown()&&getViewDirect()==ViewDirect.DOWN) {
							if(motion>9) //상하좌우 방향 모션 개수와 동일 0~9 10개
								motion=0;//마지막사진 도착후 처음으로 순환을 위한 if문 종료
							ssBody.setXPos((issacSize.issacBODYWIDTH*motion)+(Gap.COLUMGAP*motion)); //XPos는 사진에서 가져올 기준이 되는 X좌표가 됩니다
							if(getViewDirect()==ViewDirect.DOWN) {
								ssHead.setXPos(0); // 첫번째 사진이므로 0 다른 내용은 images/issac/issac.img에서 순서 확인하시면 됩니다.
								ssBody.setYPos(issacSize.issacHEADWIDTH+Gap.COLUMGAP);//X좌표로 순서를 정하고 Y좌표는 사진사이의 간격과 머리 이미지를 무시해야 하기에 머리 이미지의 크기만큼 더해서 좌표값을 내려줍니다
								ssHead.drawObj(getXPlayer(), getYPlayer()); //그려지는 기준점이 되는 캐릭터(몬스터의) 좌표값을 설정합니다.
								ssBody.drawObj(getXPlayer()+xPlusBody, getYPlayer()+yPlusBody);//X와Y좌표를 기준으로 머리를 생성하고 머리와 몸이 겹치지 않게하기위해 사용합니다.
								motion += 1;
							}
						}
						else if(isLeft()&&getViewDirect()==ViewDirect.LEFT) {
							if(motion>9) 
								motion=0;
							ssBody.setXPos((issacSize.issacBODYWIDTH*motion)+(Gap.COLUMGAP*motion));
							if(getViewDirect()==ViewDirect.LEFT) {
								ssHead.setXPos(issacSize.issacHEADWIDTH*6+Gap.COLUMGAP*6);
								ssBody.setYPos(issacSize.issacHEADHEIGHT+issacSize.issacBODYHEIGHT*2+Gap.ROWGAP*3);
								ssHead.drawObj(getXPlayer(), getYPlayer());
								ssBody.drawObj(getXPlayer()+xPlusBody, getYPlayer()+yPlusBody);
								motion += 1;
							}
						}
						else if(isUp()&&getViewDirect()==ViewDirect.UP) {
							if(motion>9) 
								motion=0;
							ssBody.setXPos((issacSize.issacBODYWIDTH*motion)+(Gap.COLUMGAP*motion));
							if(getViewDirect()==ViewDirect.UP) {
								ssHead.setXPos(issacSize.issacHEADWIDTH*4+Gap.COLUMGAP*4);
								ssBody.setYPos(issacSize.issacHEADWIDTH+Gap.COLUMGAP);
								ssHead.drawObj(getXPlayer(), getYPlayer());
								ssBody.drawObj(getXPlayer()+xPlusBody, getYPlayer()+yPlusBody);
								motion += 1;
							}
						}
						else if(isRight()&&getViewDirect()==ViewDirect.RIGHT) {
							if(motion>9) 
								motion=0;
							ssBody.setXPos((issacSize.issacBODYWIDTH*motion)+(Gap.COLUMGAP*motion));
							if(getViewDirect()==ViewDirect.RIGHT) {
								ssHead.setXPos(issacSize.issacHEADWIDTH*2+Gap.COLUMGAP*2);
								ssBody.setYPos(issacSize.issacHEADHEIGHT+issacSize.issacBODYHEIGHT+Gap.ROWGAP*2);
								ssHead.drawObj(getXPlayer(), getYPlayer());
								ssBody.drawObj(getXPlayer()+xPlusBody, getYPlayer()+yPlusBody);
								motion += 1;
							}
						}
						try {
							Thread.sleep(40);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
			}
		}).start();
	}
	public void refreshDirect() {
		if(issac.isDown()) {
			issac.setViewDirect(ViewDirect.DOWN);
		}
		if(issac.isLeft()) {
			issac.setViewDirect(ViewDirect.LEFT);
		}
		if(issac.isUp()) {
			issac.setViewDirect(ViewDirect.UP);
		}
		if(issac.isRight()) {
			issac.setViewDirect(ViewDirect.RIGHT);
		}
	}
}
