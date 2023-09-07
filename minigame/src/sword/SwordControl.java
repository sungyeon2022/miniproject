package sword;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.SwordSize;
import imgSize.ViewDirect;
import player.issac;

public class SwordControl extends Sword{
	private final static String TAG = "SwordControl : ";
	private SwordControl swordControl = this;
	
	private SpriteSheet ssSword;
	private Sword sword;
	private JFrame app;
	private issac issac;
	
	public SwordControl(JFrame app) {
		super(app);
		System.out.println(TAG+"makeSword");
		init();
		setting();
		batch();
	}

	private void init() {
		ssSword = new SpriteSheet("sword/sword.png","sword",SwordSize.SWORDXGAP,SwordSize.SWORDYGAP,SwordSize.SWORDWIDTH,SwordSize.SWORDYHEIGHT);
		
	}
	private void setting() {
		setDirection(ViewDirect.DOWN);
		setXSword(480);
		setYSword(460);
		}
	private void batch() {
		getApp().add(ssSword,0);
		
	}
	public void rotateImage(SpriteSheet img, double angle) {
		BufferedImage rotateimg = img.getImgSprite();
		double isangle = Math.toRadians(angle);
		double sin = Math.abs(Math.sin(isangle));
		double cos = Math.abs(Math.cos(isangle));
		double w = rotateimg.getWidth();
		double h = rotateimg.getHeight();
		int newW = (int)Math.floor(w*cos + h*sin);//sin cos함수를 이용해 회전후 바뀌는 넓이와 높이를 구한다
		int newH = (int)Math.floor(w*sin + h*cos);
		
		GraphicsConfiguration gc = img.getGraphicsConfiguration();
		
		BufferedImage result = gc.createCompatibleImage(newW, newH, Transparency.TRANSLUCENT);
		Graphics2D g = result.createGraphics();
		
		g.translate((newW-w)/2, (newH-h)/2);
		g.rotate(isangle,w/2,h/2);
		g.drawRenderedImage(rotateimg , null);
		g.dispose();
	}
}

