package item;

import javax.swing.JFrame;
import SpriteSheet.SpriteSheet;
import lombok.Data;
import objectSetting.*;


@Data


public class Item {
	private JFrame app;
	private final static String GUBUN = "ITEM :";
	private SpriteSheet ssItem;
	private int xItem, yItem;
	private boolean isDrop = true;
	
		public Item(JFrame app, String url, String gubun, int xItem, int yItem, int width, int height) {
			this.app = app;
			this.ssItem = new SpriteSheet(url, gubun, 0, 0, width, height);
			this.xItem = xItem;
			this.yItem = yItem;
			drawItem();
		}
		public void drawItem() {
			getSsItem().drawObj(getXItem(), getYItem());
			getApp().add(getSsItem());
		}
}
