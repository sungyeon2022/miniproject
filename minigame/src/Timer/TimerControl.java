package Timer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import connect.ConnectControl;
import objectSetting.MyFont;

public class TimerControl extends Timer {
	private ConnectControl connectControl;

	public TimerControl(JFrame app, ConnectControl connectControl) {
		super(app);
		init(connectControl);
		setting();
		batch();
		timerRefresh();
	}

	public void init(ConnectControl connectControl) {
		this.connectControl = connectControl;
		setTimerLabel(new JLabel("sin"));
	}

	public void setting() {
		System.out.println("시계");
		getTimerLabel().setSize(100, 30);
		getTimerLabel().setLocation(450, 0);
		getTimerLabel().setFont(new Font(MyFont.font1, Font.BOLD, 30));
		getTimerLabel().setForeground(Color.white);
		getTimerLabel().setBorder(new LineBorder(Color.black));
	}

	public void batch() {
		getApp().add(getTimerLabel());
	}

	@Override
	public void timerRefresh() {
		new Thread(() -> {
			while (connectControl.isIsconnect()) {
				if (connectControl.getReceiveObject() != null) {
					getTimerLabel().setText(
							(String) ((HashMap<String, Object>) connectControl.getReceiveObject()).get("Timer"));
				}else {
					getTimerLabel().setText("solo");
				}
			}
		}).start();

	}
}
