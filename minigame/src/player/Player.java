package player;

import javax.swing.JFrame;

import lombok.Data;
import lombok.Lombok;
import objectSetting.*;

@Data
public class Player extends Lombok {
	private JFrame app;
	private final static String GUBUN="player";
	private double life,maxlife; //생명,최대 생명
	private double attackDamage; //공격 데미지
	private boolean isUp, isDown, isRight, isLeft;
	private int viewDirect;
	private int xPlayer, yPlayer;
	private int xPlayerCenter, yPlayerCenter;//플레이어 중심값
	private boolean dead;
	private boolean PlayerMoveStart = false;
	private boolean PlayerAttacking = false;

	
	public Player(JFrame app) {
		this.app = app;
		isUp = false;
		isDown = false;
		isRight = false;
		isLeft = false;
		dead = false;
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
