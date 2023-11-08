package player;

import javax.swing.JFrame;
import javax.swing.JLabel;

import imgSize.issacSize;
import lombok.Data;
import lombok.Lombok;
import main.miniApp;
import sword.SwordControl;

@Data
public class Player {
	private miniApp app;
	private final static String GUBUN="player";
	private double life=1;//생명,최대 생명
	private double maxlife=8;
	private boolean isUp, isDown, isRight, isLeft;
	private boolean isKeyPress;
	private double attackDamage; //공격 데미지
	private int viewDirect;
	private int xPlayer, yPlayer;
	private int xPlayerCenter, yPlayerCenter;//플레이어 중심값
	private boolean dead;
	private SwordControl swordControl;
	private boolean PlayerMoveStart;
	private boolean isRock;
	private double[] PlayerData;
	private int[] PlayerXY = {xPlayer,yPlayer};
	private int sendViewDirect;
	private boolean[] viewDirectInfo;
	private int defaultX;
	private int defaultY;
	private boolean isInvincible;
	
	public Player(miniApp app) {
		this.app = app;
		this.isUp = false;
		this.isDown = false;
		this.isRight = false;
		this.isLeft = false;
		this.isRock = false;
		this.dead = false;
		this.isKeyPress = false;
		this.viewDirectInfo = new boolean[4];
		this.defaultX = 960/2-issacSize.issacHEADWIDTH/2;
		this.defaultY = 500-(issacSize.issacHEADHEIGHT+issacSize.issacBODYHEIGHT);
	}
	public void moveRight() {}
	public void moveLeft() {}
	public void moveUp() {}
	public void moveDown() {}
	public void moveMotion() {}
	public void isDeadCheck() {}
	public void dead() {}
	public void attack() {}

}
