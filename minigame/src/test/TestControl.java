package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import SpriteSheet.SpriteSheet;
import connect.ConnectControl;

public class TestControl extends Test {
	private TestControl testcontorl = this;
	private ConnectControl connectControl;
	private SpriteSheet sstest;
	private JLabel testLabel;


	public TestControl(JFrame app, ConnectControl connectControl) {
		super(app);
		init(app);
		setting();
		batch();
	}

	public void init(JFrame app) {
		testLabel = new JLabel();
	}

	public void setting() {
		}

	public void batch() {
		getApp().add(sstest, 2);
	}
	
}
