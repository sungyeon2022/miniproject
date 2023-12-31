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
import imgSize.Gap;
import imgSize.SwordSize;
import imgSize.ViewDirect;
import imgSize.issacSize;
import lombok.Data;
import player.issac;

@Data
public class SwordControl extends Sword{
	private final static String TAG = "SwordControl : ";
	private SwordControl swordControl = this;
	private SpriteSheet ssSword;
	private Sword sword;
	private issac issac;
	
	public SwordControl(JFrame app) {
		super(app);
		System.out.println(TAG+"makeSword");
		init();
		setting();
		batch();
		
		
	}

	public void init() {
		ssSword = new SpriteSheet("sword/sword_down.png","sword",SwordSize.SWORDIMGWIDTH-SwordSize.SWORDXGAP-SwordSize.SWORDWIDTH,SwordSize.SWORDYGAP,SwordSize.SWORDWIDTH,SwordSize.SWORDYHEIGHT-2);
		
	}
	public void setting() {
		setDirection(ViewDirect.DOWN);
		setXSword(492);
		setYSword(480);
		ssSword.drawObj(getXSword(), getYSword());
		
	}
	public void batch() {
		getApp().add(ssSword, 2);
		
	}
}

