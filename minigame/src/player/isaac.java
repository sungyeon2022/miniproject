package player;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Data;


public class isaac extends Player{
	private final static String TAG = "isaac: ";
	private isaac isaac = this;
	
	private int xPlusBody = 7, yPlusBody = 30;
	private int yTotalSize;
	private int item1Count = 0;
	private int item2Count = 0;
	private int item3Count = 0;
	private int item4Count = 0;
	public isaac(JFrame app) {
		super(app);
		
	}
}
