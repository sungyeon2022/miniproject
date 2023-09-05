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

public class issac extends Player{
	Player player;
	private final static String TAG = "issac: ";
	private issac issac = this;
	
	private SpriteSheet ssHead,ssBody;
	private SpriteSheet ssTotal;
	
	private int xPlusBody = 7, yPlusBody = 30;
	private int yTotalSize;
	private int item1Count = 0;
	private int item2Count = 0;
	private int item3Count = 0;
	private int item4Count = 0;
	private int moveSpeed = 12;
	
	public issac(JFrame app) {
		super(app);
		System.out.println(TAG + "make issac");
		init();
		setting();
		batch();
		
	}
	public void init() {
		ssHead = new SpriteSheet("issac/issac.png","issacssHead",0,0,issacSize.issacHEADWIDTH,issacSize.issacHEADHEIGHT);
		ssBody = new SpriteSheet("issac/issac.png", "issacBody", 0, (issacSize.issacHEADHEIGHT + Gap.ROWGAP), issacSize.issacBODYWIDTH, issacSize.issacBODYHEIGHT);
		ssTotal = new SpriteSheet("issac/issac.png", "issacsBody", 0, yTotalSize, issacSize.issacTOTALWIDTH, issacSize.issacTOTALHEIGHT);
		
	}
	
	public void setting() {
		setViewDriect(ViewDirect.DOWN);
		setXPlayer(480);
		setYPlayer(430);
		setXPlayerCenter(getXPlayer()+issacSize.issacHEADWIDTH/2);
		setYPlayerCenter(getXPlayer()+issacSize.issacHEADHEIGHT);
		ssHead.drawObj(getXPlayer(), getYPlayer());
		ssBody.drawObj(getXPlayer()+xPlusBody, getYPlayer()+yPlusBody);
	}
	public void batch() {
		getApp().add(ssHead,0);
		getApp().add(ssBody,1);
	}
}
