package sword;

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
		ssSword.getrotateImage(90);
	}
	private void batch() {
		getApp().add(ssSword,0);
		
	}
}
