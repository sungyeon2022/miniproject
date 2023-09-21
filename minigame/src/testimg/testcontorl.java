package testimg;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.SwordMotionSize;
import imgSize.SwordSize;
import player.issac;

public class testcontorl extends test {
	private final static String TAG = "testcontrol ";
	private test test = this;

	private SpriteSheet sstest;

	public testcontorl(JFrame app) {
		super(app);
		System.out.println(TAG + "테스트 실행중");
		init();
		setting();
		batch();
	}

	public void init() {
//		sstest = new SpriteSheet();
	}

	private void setting() {
		setXtest(500);
		setYtest(250);
		sstest.drawObj(getXtest(), getYtest());

	}

	private void batch() {
		getApp().add(sstest, 0);

	}

}
