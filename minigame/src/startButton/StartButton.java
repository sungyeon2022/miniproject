package startButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import SpriteSheet.SpriteSheet;
import lombok.Data;
import main.miniApp;

@Data

public class StartButton {
	private int xButton;
	private int yButton;
	private SpriteSheet ssStartButton;
	private miniApp app;
	private boolean isMulti;
	public StartButton(miniApp app){
		this.app = app;
	}
	
	public void startCheck() {}
}
