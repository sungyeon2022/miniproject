package player;

import javax.swing.JFrame;

<<<<<<< HEAD
import imgSize.*;
import lombok.Data;
import lombok.Lombok;
import sword.SwordControl;
=======
import lombok.Data;
import lombok.Lombok;
import objectSetting.*;
>>>>>>> origin/최낙연

@Data
public class Player extends Lombok {
	private JFrame app;
	private final static String GUBUN="player";
<<<<<<< HEAD
	private double life=5;//생명,최대 생명
	private double maxlife=8;
	private boolean isUp, isDown, isRight, isLeft;
	private boolean iskeyPress;
=======
	private double life,maxlife; //생명,최대 생명
	private double attackDamage; //공격 데미지
	private boolean isUp, isDown, isRight, isLeft;
>>>>>>> origin/최낙연
	private int viewDirect;
	private int xPlayer, yPlayer;
	private int xPlayerCenter, yPlayerCenter;//플레이어 중심값
	private boolean dead;
<<<<<<< HEAD
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
		this.iskeyPress = false;
		this.PlayerAttack = false;
=======
	private boolean PlayerMoveStart;
	private boolean PlayerAttacking;
	
	public Player(JFrame app) {
		this.app = app;
		isUp = false;
		isDown = false;
		isRight = false;
		isLeft = false;
		dead = false;
>>>>>>> origin/최낙연
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
