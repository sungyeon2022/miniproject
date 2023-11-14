package wall;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import lombok.Data;
import main.miniApp;

public class rock extends structure{
	public rock(miniApp app) {
		super(app);
		init();
		setting();
		batch();
	}

	private void init() {
		setSsStructure(new SpriteSheet(null, null, getXwall(), getXwall(), getYwall(), getXwall()));
		setGUBUN("rock");
	}

	private void setting() {
		// TODO Auto-generated method stub
		
	}

	private void batch() {
		// TODO Auto-generated method stub
		
	}
	
}
