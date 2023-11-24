package wall;

import SpriteSheet.SpriteSheet;
import imgSize.RockSize;
import main.miniApp;

public class rock extends structure {
	
	
	public rock(miniApp app, int x, int y) {
		
		super(app);
		super.setXwall(x);
		super.setYwall(y);
		init();
		setting();
		batch(x, y);
	}

	private void init() {
		
		setSsStructure(new SpriteSheet("structure/rock.png", "rock", 0, 0, RockSize.WIDTH, RockSize.HEIGHT));
	}

	private void setting() {

	}

	private void batch(int x, int y) {
		getSsStructure().drawObj(x, y);
		getApp().add(getSsStructure(), 0);

	}
}
