package item;

import javax.swing.JFrame;

import lombok.Data;


@Data


public class Item {
	private JFrame app;
	private final static String GUBUN = "ITEM :";
	private SpriteSheet ssItem;
	private int xItem, yItem;
	private boolean isDrop = true;
	
		public Item(JFrame app, String url, String gubun, int xItem, int yItem, int width, int height) {
			
		}
}
