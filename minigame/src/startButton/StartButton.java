package startButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import SpriteSheet.SpriteSheet;
import lombok.Data;

@Data

public class StartButton {
	private int xButton;
	private int yButton;
	private SpriteSheet ssStartButton;
	private JFrame app;
	private boolean isMulti;
	public StartButton(JFrame app){
		this.app = app;
	}
	
	public void startCheck() {}
}
