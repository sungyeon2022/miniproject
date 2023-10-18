package data;

import java.io.Serializable;
import java.util.Arrays;

import lombok.Data;

@Data
public class DataClass implements Serializable {
	private static final long serialVersionUID = 1L; // 중요
	
	/* 사용자 정보 */
	private String clientName = "Beta1";
	private int xPlayer;
	private int yPlayer;
	private int moveSpeed;
	private double attackDamage;
	private double life;
	private int intView;
	private boolean[] booleanView = new boolean[4];
	private boolean isAttack;
	private boolean isInvincible;
	
	/* 시스템 정보 */
	private String Timer;
	private boolean isStart;
	private boolean isReady;
	
	public DataClass() {}
	
	@Override
	public String toString() {
		return "clientName : " + clientName + ", xPlayer : " + xPlayer + ", yPlayer : " + yPlayer 
		+ ", moveSpeed : " + moveSpeed + ", attackDamage : " + attackDamage + ", life : " + life
		+ ", intView : " + intView + ", booleanView : " + Arrays.toString(booleanView) + ", isAttack : "
		+ isAttack + ", isInvincible : " + isInvincible + ", Timer : " + Timer + ", isStart : " + isStart
		+ ", isReady : " + isReady;
	}
	public void recivePlayerData(DataClass dataClass) {
		this.clientName = dataClass.clientName;
		this.xPlayer = dataClass.xPlayer;
		this.yPlayer = dataClass.yPlayer;
		this.moveSpeed = dataClass.moveSpeed;
		this.attackDamage = dataClass.attackDamage;
		this.life = dataClass.life;
		this.intView = dataClass.intView;
		this.booleanView = dataClass.booleanView;
		this.isAttack = dataClass.isAttack;
		this.isInvincible = dataClass.isInvincible;
	}
	public void reciveSystemData(DataClass dataClass) {
		this.Timer = dataClass.Timer;
		this.isStart = dataClass.isStart;
		this.isReady = dataClass.isReady;
	}
}
