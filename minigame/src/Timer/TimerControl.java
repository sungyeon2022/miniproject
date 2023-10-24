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
		timerLabel = new JLabel("Singel");
		this.connectControl = connectControl;
	}

	public void setting() {
		System.out.println("시계");
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
					if (connectControl.isIsconnect()&&connectControl.getReciveDataClass()!=null) {
						
					}else timerLabel.setText("Single");
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
