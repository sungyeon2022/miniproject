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
	private ConnectControl connectControl;

	public TimerControl(JFrame app, ConnectControl connectControl) {
		super(app);
		init(connectControl);
		setting();
		batch();
		timerThread();
//		imsi();
	}

	public void init(ConnectControl connectControl) {
		setTimerLabel(new JLabel());
		this.connectControl = connectControl;
	}

	public void setting() {
		getTimerLabel().setText("Single");
		getTimerLabel().setSize(300, 30);
		getTimerLabel().setLocation(330, 0);
		getTimerLabel().setFont(new Font(MyFont.FONT2, Font.BOLD, 20));
		getTimerLabel().setForeground(Color.white);
		getTimerLabel().setBorder(new LineBorder(Color.black));
		getTimerLabel().setHorizontalAlignment(JLabel.CENTER);
		timerThread();
	}

	public void batch() {
		getApp().add(getTimerLabel());
	}

	public void timerThread() {
		new Thread(() -> {
			while (true) {
				try {
					if (connectControl != null && connectControl.isIsconnect()
							&& connectControl.getReciveDataClass() == null && connectControl.isMulti()
							&& !connectControl.getReciveDataClass().isStart()) {
						getTimerLabel().setText(Integer.toString(connectControl.getReciveDataClass().getStartTime()));
					}else if(connectControl != null && connectControl.isIsconnect()
							&& connectControl.getReciveDataClass() == null && connectControl.isMulti()
							&& connectControl.getReciveDataClass().isStart()) {
						setStartTime(connectControl.getReciveDataClass().getStartTime());
						setMliSec((int) System.currentTimeMillis() / 10 - (getStartTime()));
						setSec(getMliSec() / 100);
						setMin(getSec() / 60);
						setHour(getMin() / 60);
						getTimerLabel().setText(String.format("%02d:%02d:%02d:%02d", getHour() % 60, getMin() % 60,
								getSec() % 60, getMliSec() % 100));
					}
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
