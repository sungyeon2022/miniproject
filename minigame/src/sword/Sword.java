package sword;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;

import javax.swing.JFrame;
import javax.swing.JLabel;

import SpriteSheet.SpriteSheet;
import imgSize.SwordSize;
import lombok.Data;
import lombok.Lombok;
import main.miniApp;

@Data // sword가 사용할 데이터의 집합

public class Sword {
	private miniApp app;
	private final static String TAG = "sword";
	private int xSword, ySword;
	private boolean isAttackKeyPress;
	private boolean isSwordAttacking;
	private int dotAttackDelay;
	private boolean isEnemyAttackKeyPress;

	public Sword(miniApp app) {
		this.app = app;
		this.isAttackKeyPress = false;
		this.isSwordAttacking = false;
		this.dotAttackDelay = 2000;
		this.isEnemyAttackKeyPress = false;
	}
	public synchronized void swordNomalForm() {}
	public void swordAttackForm() {}
	
}