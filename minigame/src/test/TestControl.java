package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import SpriteSheet.SpriteSheet;
import connect.ConnectControl;
import objectSetting.*;
import imgSize.*;

public class TestControl extends Test {
	private TestControl testcontorl = this;
	private ConnectControl connectControl;
	private SpriteSheet sstest;

	public TestControl(JFrame app, ConnectControl connectControl) {
		super(app);
		init(app);
		setting();
		batch();
	}

	public void init(JFrame app) {
		sstest = new SpriteSheet("sword/sword.png", "Test", 7, 11, 20, 42);
	}

	public void setting() {
//		sstest.rotateDraw(200, 200, 270);
	}

	public void batch() {
		getApp().add(sstest, 2);
	}
	
}
