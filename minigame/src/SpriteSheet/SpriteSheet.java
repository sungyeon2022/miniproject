package SpriteSheet;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsConfigTemplate;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import lombok.Data;
import main.miniApp;

@Data

public class SpriteSheet extends JLabel {
	private final static String TAG = "SpriteSheet:";
	private miniApp app;	
	private BufferedImage imgSprite;
	private BufferedImage originImg;
	private Graphics2D graphics;
	private BufferedImage newImg;
	private BufferedImage oldImg;
	
	private String url;
	private String gubun;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private ImageIcon imgObj;

	public SpriteSheet(String url, String gubun, int xPos, int yPos, int width, int height) {
		this.url = url;
		this.gubun = gubun;
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		loadSpriteimage(url);
	}
	
	public void loadSpriteimage(String url) {
		try {
			imgSprite = ImageIO.read(new File("images/" + url));
		} catch (IOException e) {
			System.out.println("이미지 로드 실패");
		}
	}// url을 통해 이미지 파일을 가져옴 없을경우 SpriteSheet: 이미지 로드 실패 출력

	public synchronized BufferedImage getObjimg() {
		loadSpriteimage(url);
		return imgSprite.getSubimage(xPos, yPos, width, height);
	}// 이미지 파일에서 가져온 이미지를 x,y좌표 기준 높이와 너비로 자른후 버퍼에 저장

	public synchronized void drawObj(int x, int y) {
		imgObj = new ImageIcon(getObjimg());
		setIcon(imgObj);
		setBounds(x, y, width, height);
		setBorder(new LineBorder(Color.black));
//		System.out.println(TAG + gubun + "그려짐");
	}
	
	//이미지 회전용(실패)
//	public void rotateDraw(int x, int y, double degree) {
//		oldImg = getObjimg();
//		if (degree == 180||degree == 0||degree==360) {
//			newImg = new BufferedImage(oldImg.getWidth(), oldImg.getHeight(), oldImg.getType());
//		} else {
//			newImg = new BufferedImage(oldImg.getHeight(), oldImg.getWidth(), oldImg.getType());
//		}
//		graphics = (Graphics2D)newImg.getGraphics();
//		graphics.rotate(Math.toRadians(degree), newImg.getWidth() / 2-1, newImg.getHeight() / 2);
//		graphics.translate((newImg.getWidth() - oldImg.getWidth()) / 2, (newImg.getHeight() - oldImg.getHeight()) / 2);
//		graphics.drawImage(oldImg, 0, 0, null);
//		
//		setLocation(x, y);
//		setWidth(newImg.getWidth());
//		setHeight(newImg.getHeight());
//		setIcon(new ImageIcon(newImg));
//		setBorder(new LineBorder(Color.black));
//		
//	}
}
