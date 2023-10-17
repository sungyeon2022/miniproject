package map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Timer.TimerControl;
import imgSize.BombSize;
import item.Item;
import objectSetting.MyFont;

public class Background {
	private JLabel isBackground;

	public Background(JFrame app) {
		app.setSize(960, 640);

		ImageIcon back = new ImageIcon("images/structure/map.png");
		isBackground = new JLabel(back);
		app.setContentPane(isBackground);
	}
}
