package SpriteSheet;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data

public class SpriteSheet extends JLabel{
	private final static String TAG = "SpriteSheet:";
	private BufferedImage imgSprite;
	private String url;
	private String seper;
	private int xPos,yPos;
	
}
