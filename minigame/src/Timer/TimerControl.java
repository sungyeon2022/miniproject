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
//		imsi();
	}

	public void init(ConnectControl connectControl) {
		timerLabel = new JLabel();
		this.connectControl = connectControl;
	}

	public void setting() {
		setStartTime((int)System.currentTimeMillis()/10);
		timerLabel.setSize(300, 30);
		timerLabel.setLocation(330, 0);
		timerLabel.setFont(new Font(MyFont.FONT2, Font.BOLD, 20));
		timerLabel.setForeground(Color.white);
		timerLabel.setBorder(new LineBorder(Color.black));
		timerLabel.setHorizontalAlignment(JLabel.CENTER);
	}

	public void batch() {
		getApp().add(timerLabel);
	}
	
	public void timerThread() {
		new Thread(() -> {
			while (true) {
				try {
					if (connectControl!=null&&connectControl.isIsconnect()&&connectControl.getReciveDataClass()!=null) {
						setStartTime(connectControl.getReciveDataClass().getStartTime());
					}else if(connectControl!=null && connectControl.isIsconnect()&& connectControl.getReciveDataClass()==null &&connectControl.isMulti()) {
						timerLabel.setText("Wait Enemy");
					}
					else {
						setMliSec((int)System.currentTimeMillis()/10-(getStartTime()));
						setSec(getMliSec()/100);
						setMin(getSec()/60);
						setHour(getMin()/60);
						timerLabel.setText(String.format("%02d:%02d:%02d:%02d", getHour()%60,getMin()%60,getSec()%60,getMliSec()%100));
					}
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
