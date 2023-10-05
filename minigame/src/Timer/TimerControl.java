package Timer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import objectSetting.MyFont;

public class TimerControl extends Timer {
	private JLabel timerLabel;
	private TimerControl timerControl = this;
	public TimerControl(JFrame app) {
		super(app);
		init();
		setting();
		batch();
		timerThread();
	}
	public void init() {
		timerLabel = new JLabel(Integer.toString(getSec())+":"+"00");
	}
	public void setting() {
		System.out.println("시계");
		timerLabel.setSize(90,30);
		timerLabel.setLocation(430,0);
		timerLabel.setFont(new Font(MyFont.FONT2,Font.BOLD,30));
		timerLabel.setForeground(Color.white);
		timerLabel.setBorder(new LineBorder(Color.black));
	}
	public void batch() {
		getApp().add(timerLabel);
	}
	public void timerThread() {
		new Thread(()->{
			while (true) {
				setMliSec(((int)System.currentTimeMillis()/10)-getStartTime());
				timerLabel.setText(Integer.toString(getMliSec()/100%60)+":"+Integer.toString(getMliSec()%100));
//				try {
//					Tre
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
			}
		}).start();
	}
}
