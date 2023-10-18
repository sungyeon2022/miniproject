package startButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import SpriteSheet.SpriteSheet;
import connect.ConnectControl;
import imgSize.ButtonSize;
import player.issac;

public class StartButtonControl extends StartButton {
	private issac issac;
	private ConnectControl connectControl;
	public StartButtonControl(JFrame app, issac issac, ConnectControl connectControl) {
		super(app);
		init(issac, connectControl);
		setting();
		batch();
		startCheck();
	}
	public void init(issac issac, ConnectControl connectControl) {
		this.issac = issac;
		this.connectControl = connectControl;
		setSsStartButton(new SpriteSheet("structure/button.png", "button", ButtonSize.XPos, ButtonSize.YPos, ButtonSize.Width, ButtonSize.Height));
	}
	public void setting() {
		setXButton(798);
		setYButton(110);
		getSsStartButton().drawObj(getXButton(), getYButton());
	}
	public void batch() {
		if(connectControl.isIsconnect()) getApp().add(getSsStartButton());
	}
	
	@Override
	public void startCheck() {
		new Thread(()->{
			while (!isMulti()&&!Thread.interrupted()) {
				if(getSsStartButton().getBounds().intersects(issac.getSsBody().getBounds())&&connectControl.isIsconnect()) {
					getSsStartButton().setXPos(ButtonSize.XPos+ButtonSize.Width+ButtonSize.Gap);
					getSsStartButton().drawObj(getXButton(), getYButton());
					connectControl.setMulti(true);
				}
			}
		}).start();
	}
}
