package sword;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import lombok.Data;
import lombok.Lombok;
import objectSetting.SwordSize;

@Data //sword가 사용할 데이터의 집합

public class Sword{
	private final static String TAG="sword";
	private SpriteSheet ssSword;
	private int armleng = 10;
	private int xSword, ySword;
	private int direction;
	private double attackDamage;
	
	
	public Sword(double attackDamage, int direction, int xSword, int ySword){ 
		ssSword = new SpriteSheet("sword/sword.png","sword",0,0,SwordSize.SWORDWIDTH,SwordSize.SWORDYHEIGHT);
		this.attackDamage = attackDamage;
		this.xSword = xSword;
		this.ySword = ySword;
		this.direction = direction;
	}
	
}
