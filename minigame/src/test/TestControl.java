package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import SpriteSheet.SpriteSheet;
import connect.ConnectControl;
import org.jocl.*;

public class TestControl extends Test {
	private TestControl testcontorl = this;
	private ConnectControl connectControl;
	private SpriteSheet sstest;
	private JLabel testLabel;
	private cl_image_format format;
	private cl_image_desc outputDesc;
	private cl_image_desc inputDesc;
	private BufferedImage testImage;

	public TestControl(JFrame app, ConnectControl connectControl) {
		super(app);
		init(app);
		setting();
		batch();
	}

	public void init(JFrame app) {
		testLabel = new JLabel();
		format = new cl_image_format();
		outputDesc = new cl_image_desc();
		inputDesc = new cl_image_desc();
		try {
			testImage = ImageIO.read(new File("images/structure/namepaper.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setting() {
		}

	public void batch() {
		getApp().add(sstest, 2);
	}
	
}
