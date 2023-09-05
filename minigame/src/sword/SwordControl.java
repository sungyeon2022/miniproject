package sword;

import javax.swing.JFrame;

import objectSetting.ViewDirect;
import player.issac;

public class SwordControl {
	private final static String TAG = "SwordControl : ";
	private Sword sword;
	private JFrame app;
	
	private issac issac;
	
	public SwordControl(JFrame app,issac issac) {
		this.app = app;
		this.issac = issac;
	}
	public void drawSword() {
		if(sword.getDirection()==ViewDirect.DOWN) {
			sword.setSsSword();
		}else if(sword.getDirection()==ViewDirect.LEFT) {
			
		}else if(sword.getDirection()==ViewDirect.UP) {
			
		}else if(sword.getDirection()==ViewDirect.RIGHT) {
			
		}
	}
}
