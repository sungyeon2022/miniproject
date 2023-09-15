package map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import imgSize.BombSize;
import item.Bomb;
import item.Item;

public class Background {
	private JLabel isBackground;
	private Vector<Item> itemcount;

	public Background(JFrame app) {
		app.setSize(960, 640);
		int compxsize = app.getWidth();
		int compysize = app.getHeight();

		ImageIcon back = new ImageIcon("images/structure/map.png");
		Image temp = back.getImage().getScaledInstance(compxsize, compysize, back.getImage().SCALE_SMOOTH);

		ImageIcon toIcon = new ImageIcon(temp);
		isBackground = new JLabel(toIcon);
		app.setContentPane(isBackground);
		// 폭탄 개수 표시용 이미지 삽입 + X 글자 삽입
		itemcount = new Vector<Item>();
		itemcount.add(new Bomb(app, "item/bomb.png", "Bomb", 10, 40, BombSize.PICKWIDTH, BombSize.PICKHEIGHT));
		itemcount.add(new Bomb(app, "item/Power.png", "Power", 15, 80, 21, 35));
		itemcount.add(new Bomb(app, "item/Speed.png", "Speed", 10, 115, 33, 33));
		itemcount.add(new Bomb(app, "item/AttackSpeed.png", "AttackSpeed", 10, 150, 33,33));
		
		JLabel label = new JLabel("X");
		label.setSize(20, 30); // 라벨에 대한 사이즈
		label.setLocation(50, 50);
		label.setFont(new Font("바탕", Font.ITALIC, 15));
		label.setForeground(Color.WHITE);
		JLabel label2 = new JLabel("=");
		label2.setSize(20, 30); // 라벨에 대한 사이즈
		label2.setLocation(50, 85);
		label2.setFont(new Font("바탕", Font.ITALIC, 15));
		label2.setForeground(Color.WHITE);
		JLabel label3 = new JLabel("=");
		label3.setSize(20, 30); // 라벨에 대한 사이즈
		label3.setLocation(50, 120);
		label3.setFont(new Font("바탕", Font.ITALIC, 15));
		label3.setForeground(Color.WHITE);
		JLabel label4 = new JLabel("=");
		label4.setSize(20, 30); // 라벨에 대한 사이즈
		label4.setLocation(50, 155);
		label4.setFont(new Font("바탕", Font.ITALIC, 15));
		label4.setForeground(Color.WHITE);
		
		app.add(label);
		app.add(label2);
		app.add(label3);
		app.add(label4);

	}
}
