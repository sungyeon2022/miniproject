//package enemy;
//
//import javax.swing.JFrame;
//
//import lombok.Data;
//import lombok.Lombok;
//import sword.SwordControl;
//
//@Data
//public class Enemy extends Lombok {
//	private JFrame app;
//	private final static String GUBUN="enemy";
//	private double life=5;//생명,최대 생명
//	private double maxlife=8;
//	private boolean isUp, isDown, isRight, isLeft;
//	private boolean iskeyPress;
//	private double attackDamage; //공격 데미지
//	private int viewDirect;
//	private int xEnemy, yEnemy;
//	private int xEnemyCenter, yEnemyCenter;//플레이어 중심값
//	private boolean dead;
//	private SwordControl swordControl;
//	private boolean EnemyMoveStart;
//	private boolean EnemyAttacking;
//	private boolean EnemyAttack;
//	private boolean isRock;
//	
//	public Enemy(JFrame app) {
//		this.app = app;
//		this.isUp = false;
//		this.isDown = false;
//		this.isRight = false;
//		this.isLeft = false;
//		this.isRock = false;
//		this.dead = false;
//		this.iskeyPress = false;
//	}
//	public void moveRight() {}
//	public void moveLeft() {}
//	public void moveUp() {}
//	public void moveDown() {}
//	public void moveMotion() {}
//	public void isDeadCheck() {}
//	public void dead() {}
//	public void attack() {}
//}
