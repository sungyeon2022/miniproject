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
		sstest = new SpriteSheet("sword/sword_down.png", "test", SwordMotionSize.IMGWIDTH / 2,
				SwordSize.SWORDIMGHEIGHT - SwordMotionSize.IMGHEIGHT, SwordMotionSize.IMGWIDTH / 2,
				SwordMotionSize.IMGHEIGHT / 4);
	}

	private void setting() {
		setXtest(500);
		setYtest(250);
		sstest.drawObj(getXtest(), getYtest());

	}

	private void batch() {
		getApp().add(sstest, 0);

	}

	public void testMove() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (isButtonclick() == false) {
					setButtonclick(true);
					sstest.drawObj(getXtest(), getYtest());
				}

			}
		}).start();
	}

	public void testMotion() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				int imgxlocation = 1;
				int imgylocation = 0;
				if (isButtonclick() == false)
					setButtonclick(true);
				if (isButtonclick()) {
					while (true) {
						if (imgxlocation == 1 && imgylocation > 3) {
							imgylocation = 0;
							imgxlocation--;
						}
						if (imgxlocation == 0 && imgylocation > 2) {
							imgxlocation = 1;
							imgylocation = 0;
							break;
						}
						System.out.println("테스트");
						System.out.println(imgxlocation);
						System.out.println(imgylocation);
						System.out.println("테스트");
						sstest.setXPos(SwordMotionSize.WIDTH * imgxlocation);
						sstest.setYPos(SwordSize.SWORDIMGHEIGHT - (SwordMotionSize.IMGHEIGHT)
								+ (SwordMotionSize.HEIGHT * imgylocation));
						sstest.drawObj(getXtest(), getYtest());
						imgylocation++;
						try {
							Thread.sleep(20);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

		}).start();
	}
}
