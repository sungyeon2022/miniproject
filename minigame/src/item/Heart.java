package item;

import javax.swing.JFrame;

public class Heart extends Item {
	private final static String TAG = "Key : ";
	private Heart heart = this;
   
	public Heart(JFrame app, String url, String gubun, int xItem, int yItem, int width, int height) {
		super(app, url, gubun, xItem, yItem, width, height);
		drawItem();
	}
	@Override
	public void drawItem() {
		getSsItem().drawObj(getXItem(), getYItem());
		getApp().add(getSsItem());
	}
}
