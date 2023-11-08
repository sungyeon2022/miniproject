package startButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import SpriteSheet.SpriteSheet;
import connect.ConnectControl;
import imgSize.ButtonSize;
import main.miniApp;
import player.issac;

public class StartButtonControl extends StartButton {
	private ConnectControl connectControl;

	public StartButtonControl(miniApp app, ConnectControl connectControl) {
		super(app);
		init(connectControl);
		setting();
		batch();
	}

	public void init(ConnectControl connectControl) {
		this.connectControl = connectControl;
		setSsStartButton(new SpriteSheet("structure/button.png", "button", ButtonSize.XPos, ButtonSize.YPos,
				ButtonSize.Width, ButtonSize.Height));
	}

	public void setting() {
		setXButton(798);
		setYButton(110);
		getSsStartButton().drawObj(getXButton(), getYButton());
	}

	public void batch() {
		if (connectControl.isIsconnect()) {
			getApp().add(getSsStartButton());
		}
	}

}
