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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import lombok.Data;

@Data

public class SpriteSheet extends JLabel{
	private final static String TAG = "SpriteSheet:";
	JFrame frame;
	private BufferedImage imgSprite;
	private BufferedImage newimg;
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
	}
	public synchronized void loadSpriteimage(String url) {
		try {
			imgSprite = ImageIO.read(new File("images/"+url));
		}catch (Exception e) {
			System.out.println(TAG+"이미지 로드 실패");
		}
	}//url을 통해 이미지 파일을 가져옴 없을경우 SpriteSheet: 이미지 로드 실패 출력
	
	
	public synchronized BufferedImage getObjimg() {
		loadSpriteimage(url);
		return imgSprite.getSubimage(xPos, yPos, width, height);
	}//이미지 파일에서 가져온 이미지를 x,y좌표 기준 높이와 너비로 자른후 버퍼에 저장
	
	public synchronized void drawObj(int x, int y) {
		imgObj = new ImageIcon(getObjimg());
		setIcon(imgObj);
		setSize(width,height);
		setLocation(x,y);
		setBorder(new LineBorder(Color.black));
//		System.out.println(TAG + gubun + "그려짐");
	}
	
	public void erase() {
		setIcon(null);
	}
	
	public void imgverlap(SpriteSheet spriteSheet,SpriteSheet spriteSheet2) {
		SpriteSheet.getDefaultLocale();
	}
	
	/*
	 * public static BufferedImage resize(InputStream image, int width, int height)
	 * throws IOException { BufferedImage inputImage = ImageIO.read(image);
	 * BufferedImage outputimage = new BufferedImage(width, height,
	 * inputImage.getType());
	 * 
	 * Graphics2D changeSize = outputimage.createGraphics();
	 * changeSize.drawImage(inputImage, 0, 0, width, height, null);
	 * changeSize.dispose();
	 * 
	 * return outputimage; }
	 */
}
