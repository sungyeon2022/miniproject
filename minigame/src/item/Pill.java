package item;

import javax.swing.JFrame;

public class Pill extends Item {
	private final static String TAG = "Key : ";
	private Pill pill = this;
   
	public Pill(JFrame app, String url, String gubun, int xItem, int yItem, int width, int height) {
		super(app, url, gubun, xItem, yItem, width, height);
		drawItem();
	}
	/*
	 * @Override public void drawItem() { getSsItem().drawObj(getXItem(),
	 * getYItem()); getApp().add(getSsItem()); }
	 */
}