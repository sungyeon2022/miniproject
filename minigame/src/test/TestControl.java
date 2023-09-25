package test;

import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import connect.ConnectControl;
import objectSetting.issacSize;

public class TestControl extends Test{
	private TestControl testcontorl = this;
	private ConnectControl connectControl;
	private SpriteSheet sstest;
	public TestControl(JFrame app, ConnectControl connectControl) {
		super(app);
		init(app, connectControl);
		setting();
		batch();
		imgrefreshThread();
	}
	public void init(JFrame app,ConnectControl connectControl) {
		this.connectControl = connectControl;
		sstest = new SpriteSheet("issac/issac.png","Test",0,0,issacSize.issacBODYWIDTH, issacSize.issacBODYHEIGHT);
	}
	public void setting() {
		sstest.drawObj(100, 100);
	}
	public void batch() {
		getApp().add(sstest,2);
	}
	public void imgrefreshThread() {
		new Thread(()->{
			while (true) {
				if (connectControl.isIsconnect()) {
					sstest.setIcon((ImageIcon)((HashMap<String, Object>)connectControl.getReceiveObject()).get("ssBody"));
					System.out.println(((HashMap<String, Object>)connectControl.getReceiveObject()).get("ssBody"));
					sstest.drawObj(100, 100);
				}else break;
			}
		}).start();
	}
}
