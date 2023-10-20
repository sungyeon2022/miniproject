package data;

import java.io.Serializable;
import java.util.Arrays;

import lombok.Data;

@Data
public class DataClass implements Serializable {
	private static final long serialVersionUID = 1L; // 중요

	/* 사용자 정보 */
	private String clientName;
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

	public DataClass() {
	}
}
