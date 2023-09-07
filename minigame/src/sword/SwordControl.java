package sword;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.imageio.ImageTranscoder;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.SwordSize;
import imgSize.ViewDirect;
import player.issac;

public class SwordControl extends Sword{
	private final static String TAG = "SwordControl : ";
	private SwordControl swordControl = this;
	BufferedImage bufferedImage;
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
		ssSword.drawObj(getXSword(), getYSword());
	}
	private void batch() {
		getApp().add(ssSword,0);
		
	}
	
}

