package SpriteSheet;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data

public class SpriteSheet extends JLabel{
	Frame frame;
	private final static String TAG = "SpriteSheet:";
	private BufferedImage imgSprite;
	private String url;
	private String gubun;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private ImageIcon imgObj;
	
	public SpriteSheet() {}
	
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
			imgSprite = ImageIO.read(new File("images/"+url));
		}catch (Exception e) {
			System.out.println(TAG+"이미지 로드 실패");
		}
	}//url을 통해 이미지 파일을 가져옴 없을경우 SpriteSheet: 이미지 로드 실패 출력
	
	public void getrotateImage(double angle) {
		BufferedImage img = getObjimg();
		double isangle = Math.toRadians(angle);
		double sin = Math.abs(Math.sin(isangle));
		double cos = Math.abs(Math.cos(isangle));
		double w = img.getWidth();
		double h = img.getHeight();
		int newW = (int)Math.floor(w*cos + h*sin);//sin cos함수를 이용해 회전후 바뀌는 넓이와 높이를 구한다
		int newH = (int)Math.floor(w*sin + h*cos);
		
//		GraphicsConfiguration gc = getGraphicsConfiguration();
//		BufferedImage result = gc.createCompatibleImage(newW, newH, Transparency.TRANSLUCENT);
//		Graphics2D g = result.createGraphics();
//		
//		g.translate((newW-w)/2, (newH-h)/2);
//		g.rotate(isangle,w/2,h/2);
//		g.drawRenderedImage(img , null);
//		g.dispose();
	}
	
	public BufferedImage getObjimg() {
		return imgSprite.getSubimage(xPos, yPos, width, height);
	}//이미지 파일에서 가져온 이미지를 x,y좌표 기준 높이와 너비로 자른후 버퍼에 저장
	
	public void drawObj(int x, int y) {
		imgObj = new ImageIcon(getObjimg());
		setIcon(imgObj);
		setSize(width,height);
		setLocation(x,y);
		System.out.println(TAG + gubun + "그려짐");
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
