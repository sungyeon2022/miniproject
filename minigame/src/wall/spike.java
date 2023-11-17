package wall;

import SpriteSheet.SpriteSheet;
import imgSize.RockSize;
import imgSize.SpikeSize;
import main.miniApp;

public class spike extends structure {

	public spike(miniApp app, int x ,int y) {
		super(app);
		init();
		setting();
		batch(x, y);
		
	}
	private void init() {
		setSsStructure(new SpriteSheet("structure/spike.png", "spike", 0, 0, SpikeSize.WIDTH, SpikeSize.HEIGHT));
	}
	private void setting() {
		
	}
	private void batch(int x, int y) {
		getSsStructure().drawObj(x, y);
		getApp().add(getSsStructure(),0);
		
	}

}
