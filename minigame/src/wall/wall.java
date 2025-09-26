package wall;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import imgSize.RockSize;
import lombok.Data;

@Data
public class wall {
		private JFrame app;
		private SpriteSheet sswall;
		private int xwall, ywall;
		private boolean isBroken = false;
		
		public wall(JFrame app,String url, String gubun, int xwall, int ywall) {
			this.app = app;
			this.sswall = new SpriteSheet(url, gubun, 0, 0, RockSize.WIDTH, RockSize.HEIGHT);
			this.xwall = xwall;
			this.ywall = ywall;
		}
		public void drawwall() {}
}
