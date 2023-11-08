package item;

import javax.swing.JFrame;

import main.miniApp;

public class Pill extends Item {
	private final static String TAG = "Key : ";
	private Pill pill = this;
   
	public Pill(miniApp app, String url, String gubun, int xItem, int yItem, int width, int height) {
		super(app, url, gubun, xItem, yItem, width, height);
		drawItem();
	}
	@Override
	public void drawItem() {
		getSsItem().drawObj(getXItem(), getYItem());
		getApp().add(getSsItem());
	}
}