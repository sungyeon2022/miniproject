package map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Background {
	private JLabel isBackground;
	
	public Background(JFrame app){
		app.setSize(960,640);
		int compxsize = app.getWidth();
		int compysize = app.getHeight();
		
		ImageIcon back = new ImageIcon("images/structure/map.png");
		Image temp = back.getImage().getScaledInstance(compxsize,compysize,back.getImage().SCALE_SMOOTH);
		
		ImageIcon toIcon = new ImageIcon(temp);
		isBackground = new JLabel(toIcon);
		app.setContentPane(isBackground);
	}
}
