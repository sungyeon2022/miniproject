package item;

import java.util.Vector;

import javax.swing.JFrame;

import objectSetting.BombSize;
import objectSetting.HeartSize;
import objectSetting.PillSize;

public class Createitem {
	public Createitem(JFrame app, Vector<Item> items, int x, int y) {
		int witem = (int)(Math.random()*6);
		switch(witem) {
		case 0:
			items.add(new Pill(app, "item/PowerUp.png", "Power", x, y, PillSize.WIDTH,PillSize.HEIGHT));
			break;
		case 1:
			items.add(new Pill(app, "item/ASUp.png", "AttackSpeed", x, y, PillSize.WIDTH,PillSize.HEIGHT));
			break;
		case 2:
			items.add(new Pill(app, "item/SpeedUp.png", "Speed", x, y, PillSize.WIDTH,PillSize.HEIGHT));
			break;
		case 3:
			items.add(new Pill(app, "item/FullHp.png", "FullHp", x, y, PillSize.WIDTH,PillSize.HEIGHT));
			break;	
		case 4:
			items.add(new Bomb(app, "item/bomb.png", "bomb", x, y, BombSize.PICKWIDTH, BombSize.PICKHEIGHT));
			break;
		case 5:
			items.add(new Heart(app, "item/recoveryLife.png", "heart", x, y, HeartSize.WIDTH,HeartSize.HEIGHT));
			break;
		}
	
	}
}
