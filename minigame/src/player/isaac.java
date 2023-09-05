package player;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;

import player.Player;
import SpriteSheet.SpriteSheet;
import lombok.Data;
import objectSetting.*;

@Data

public class isaac extends Player{
	Player player;
	private final static String TAG = "isaac: ";
	private isaac isaac = this;
	
	private SpriteSheet ssHead,ssBody;
	private SpriteSheet ssTotal;
	
	private int xPlusBody = 7, yPlusBody = 30;
	private int yTotalSize;
	private int item1Count = 0;
	private int item2Count = 0;
	private int item3Count = 0;
	private int item4Count = 0;
	private int moveSpeed = 12;
	
	public isaac(JFrame app) {
		super(app);
		System.out.println(TAG + "make isaac");
		init();
		setting();
		batch();
		
	}
	public void init() {
		ssHead = new SpriteSheet("isaac/isaac.png","isaacssHead",0,0,isaacSize.ISAACHEADWIDTH,isaacSize.ISAACHEADHEIGHT);
		ssBody = new SpriteSheet("isaac/isaac.png", "isaacBody", 0, (isaacSize.ISAACHEADHEIGHT + Gap.ROWGAP), isaacSize.ISAACBODYWIDTH, isaacSize.ISAACBODYHEIGHT);
		ssTotal = new SpriteSheet("isaac/isaac.png", "isaacsBody", 0, yTotalSize, isaacSize.ISAACTOTALWIDTH, isaacSize.ISAACTOTALHEIGHT);
		
	}
	
	public void setting() {
		setViewDriect(ViewDirect.DOWN);
		setXPlayer(480);
		setYPlayer(430);
		setXPlayerCenter(getXPlayer()+isaacSize.ISAACHEADWIDTH/2);
		setYPlayerCenter(getXPlayer()+isaacSize.ISAACHEADHEIGHT);
		ssHead.drawObj(getXPlayer(), getYPlayer());
		ssBody.drawObj(getXPlayer()+xPlusBody, getYPlayer()+yPlusBody);
	}
	public void batch() {
		getApp().add(ssHead,0);
		getApp().add(ssBody,1);
	}
}
