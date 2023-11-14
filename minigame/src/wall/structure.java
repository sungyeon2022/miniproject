package wall;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.RockSize;
import lombok.Data;
import main.miniApp;

@Data
public class structure {
		private miniApp app;
		private String GUBUN;
		private SpriteSheet ssStructure;
		private int xwall, ywall;
		private boolean isBroken = false;
		
		public structure(miniApp app) {
			this.app = app;
		}
}
