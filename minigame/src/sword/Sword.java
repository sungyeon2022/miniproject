package sword;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.SwordSize;
import lombok.Data;
import lombok.Lombok;

@Data //sword가 사용할 데이터의 집합

public class Sword extends Lombok{
	private JFrame app;
	private final static String TAG="sword";
	private int armleng = 10;
	private int xSword, ySword;
	private int direction;
	private double attackDamage;
	
	
	public Sword(JFrame app){ 
		this.app = app;
	}
	
}
