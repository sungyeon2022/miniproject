package item;

import javax.swing.JLabel;
import lombok.Data;
import main.miniApp;


@Data


public class Item {
	private miniApp app;
	private String GUBUN;
	private JLabel itemLabel;
	private int xItem, yItem;
	
		public Item(miniApp app) {
			this.app = app;
		}
}
