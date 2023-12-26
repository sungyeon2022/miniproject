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
import lombok.Data;
import main.miniApp;
import objectSetting.MyFont;

@Data

public class Background {
	private JLabel isBackground;

	public Background(miniApp app) {
		app.setSize(960, 640);

		ImageIcon back = new ImageIcon(getClass().getClassLoader().getResource("images/structure/map.png"));
		isBackground = new JLabel(back);
		app.setContentPane(isBackground);
	}
}
