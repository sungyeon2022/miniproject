package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import SpriteSheet.SpriteSheet;
import connect.ConnectControl;
import main.miniApp;

public class TestControl extends Test {
	private miniApp app;
	private TestControl testcontorl = this;
	private ConnectControl connectControl;
	private JLabel testLabel;


	public TestControl(miniApp app) {
		super(app);
		init();
		setting();
		batch();
		motion();
	}

	public void init() {
		setSstest(new SpriteSheet("item/bomb.png", "bomb", 0, 0, 30, 36));
	}

	public void setting() {
		setXtest(100);
		setYtest(100);
		getSstest().drawObj(getXtest(), getYtest());
	}

	public void batch() {
		getApp().add(getSstest(),0);
	}
	public void motion() {
		new Thread(()->{
			while (true) {
				getSstest().setXPos(0);
				getSstest().setYPos(5);
				setYtest(getYtest()-5);
				getSstest().setWidth(30);
				getSstest().setHeight(31);
				getSstest().drawObj(getXtest(), getYtest());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getSstest().setXPos(30);
				getSstest().setYPos(11);
				setYtest(getYtest());
				getSstest().setHeight(25);
				getSstest().drawObj(getXtest(), getYtest());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getSstest().setXPos(60);
				getSstest().setYPos(5);
				setYtest(getYtest()+5);
				getSstest().setHeight(31);
				getSstest().drawObj(getXtest(), getYtest());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getSstest().setXPos(89);
				getSstest().setYPos(0);
				setYtest(getYtest());
				getSstest().setWidth(27);
				getSstest().setHeight(36);
				getSstest().drawObj(getXtest(), getYtest());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
	}
}
