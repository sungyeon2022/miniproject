package sword;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

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
//public void getrotateImage(double angle) {
//	BufferedImage img = getObjimg();
//	double isangle = Math.toRadians(angle);
//	double sin = Math.abs(Math.sin(isangle));
//	double cos = Math.abs(Math.cos(isangle));
//	double w = img.getWidth();
//	double h = img.getHeight();
//	int newW = (int)Math.floor(w*cos + h*sin);//sin cos함수를 이용해 회전후 바뀌는 넓이와 높이를 구한다
//	int newH = (int)Math.floor(w*sin + h*cos);
//	
//	
//	GraphicsConfiguration gc = 
//	
//	BufferedImage result = gc.createCompatibleImage(newW, newH, Transparency.TRANSLUCENT);
//	Graphics2D g = result.createGraphics();
//	
//	g.translate((newW-w)/2, (newH-h)/2);
//	g.rotate(isangle,w/2,h/2);
//	g.drawRenderedImage(img , null);
//	g.dispose();
//}