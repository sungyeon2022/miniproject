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
	private SpriteSheet down_ssSword,up_ssSword,left_ssSword,right_ssSword;
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
		down_ssSword = new SpriteSheet("sword/sword_down.png","down_sword",SwordSize.SWORDIMGWIDTH-SwordSize.SWORDXGAP-SwordSize.SWORDWIDTH,SwordSize.SWORDYGAP,SwordSize.SWORDWIDTH,SwordSize.SWORDYHEIGHT);
		up_ssSword=new SpriteSheet("sword/sword_up.png","up_sword",SwordSize.SWORDXGAP,SwordSize.SWORDIMGHEIGHT-SwordSize.SWORDYGAP-SwordSize.SWORDYHEIGHT,SwordSize.SWORDWIDTH,SwordSize.SWORDYHEIGHT);
		left_ssSword = new SpriteSheet("sword/sword_left.png","left_sword",SwordSize.SWORDIMGHEIGHT-SwordSize.SWORDYGAP-SwordSize.SWORDYHEIGHT,SwordSize.SWORDIMGWIDTH-SwordSize.SWORDXGAP-SwordSize.SWORDWIDTH,SwordSize.SWORDYHEIGHT,SwordSize.SWORDWIDTH);
		right_ssSword = new SpriteSheet("sword/sword_right.png","left_sword",SwordSize.SWORDYGAP,SwordSize.SWORDXGAP,SwordSize.SWORDYHEIGHT,SwordSize.SWORDWIDTH);
	}
	public void setting() {
		setDirection(ViewDirect.DOWN);
		setXSword(489);
		setYSword(480);
		down_ssSword.drawObj(getXSword(), getYSword());
		
	}
	public void batch() {
		getApp().add(down_ssSword, 0);
		getApp().add(left_ssSword,0);
		getApp().add(up_ssSword,4);
		getApp().add(right_ssSword,0);
	}
}

