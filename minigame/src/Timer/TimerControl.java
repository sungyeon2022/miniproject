package Timer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import connect.ConnectControl;
import objectSetting.MyFont;

public class TimerControl extends Timer {
	private JLabel timerLabel;
	private ConnectControl connectControl;

	public TimerControl(JFrame app, ConnectControl connectControl) {
		super(app);
		init(connectControl);
		setting();
		batch();
		timerThread();
	}

	public void init(ConnectControl connectControl) {
		timerLabel = new JLabel("SOL");
		this.connectControl = connectControl;
	}

	public void setting() {
		System.out.println("시계");
		timerLabel.setSize(120, 30);
		timerLabel.setLocation(420, 0);
		timerLabel.setFont(new Font(MyFont.FONT2, Font.BOLD, 20));
		timerLabel.setForeground(Color.white);
		timerLabel.setBorder(new LineBorder(Color.black));
	}

	public void batch() {
		getApp().add(timerLabel);
	}

	public void timerThread() {
		new Thread(() -> {
			while (true) {
				timerLabel.setText((String) connectControl.getReciveMap().get("Timer"));
//				setMliSec(((int)System.currentTimeMillis()/10)-getStartTime());
//				setSec(String.format("%02d", getMliSec()/100%60));
//				setMin(String.format("%02d", getMliSec()/6000%60));
//				setHour(String.format("%02d", getMliSec()/360000%60));
//				timerLabel.setText(getHour()+":"+getMin()+":"+getSec()+":"+getMliSec()%100);
			}
		}).start();
	}
}
