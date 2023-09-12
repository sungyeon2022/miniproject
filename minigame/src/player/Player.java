package player;

import javax.swing.JFrame;

import imgSize.*;
import lombok.Data;
import lombok.Lombok;
import sword.SwordControl;

@Data
public class Player extends Lombok {
	private JFrame app;
	private final static String GUBUN="player";
	private double life=5;//생명,최대 생명
	private double maxlife=8;
	private boolean isUp, isDown, isRight, isLeft;
	private int viewDirect;
	private int xPlayer, yPlayer;
	private int xPlayerCenter, yPlayerCenter;//플레이어 중심값
	private boolean dead;
	private SwordControl swordControl;
	private double attackDamge;
	private boolean PlayerMoveStart;
	private boolean PlayerAttacking;
	private boolean PlayerAttack;
	
	public Player(JFrame app) {
		this.app = app;
		this.isUp = false;
		this.isDown = false;
		this.isRight = false;
		this.isLeft = false;
		this.dead = false;
		this.PlayerAttack = false;
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
