package sword;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.SwordSize;
import lombok.Data;
import lombok.Lombok;

@Data // sword가 사용할 데이터의 집합

public class Sword extends Lombok {
	private JFrame app;
	private final static String TAG = "sword";
	private int xSword, ySword;
	private boolean isAttack;
	private boolean isAttackKeyPress;
	private boolean isSwordAttacking;
	private int dotAttackDelay;
	private boolean isEnemyAttackPress;

	public Sword(JFrame app) {
		this.app = app;
		this.isAttack = false;
		this.isAttackKeyPress = false;
		this.isSwordAttacking = false;
		this.dotAttackDelay = 2000;
		this.isEnemyAttackPress = false;
	}
	public synchronized void swordNomalForm() {}
	public synchronized void swordAttackForm() {}
	
}