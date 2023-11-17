package wall;



import SpriteSheet.SpriteSheet;
import lombok.Data;
import main.miniApp;

@Data
public class structure {
		private miniApp app;
		private String Gubun;
		private SpriteSheet ssStructure;
		private int xwall, ywall;
		private boolean isBroken = false;
		
		public structure(miniApp app) {
			this.app = app;
		}
}
