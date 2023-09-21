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
	}
	public void init() {
		timerLabel = new JLabel(Integer.toString(getSec())+":"+Integer.toString(getMliSec()));
	}
	public void setting() {
		System.out.println("시계");
		timerLabel.setSize(80,30);
		timerLabel.setLocation(450,0);
		timerLabel.setFont(new Font(MyFont.font1,Font.BOLD,30));
		timerLabel.setForeground(Color.white);
		timerLabel.setBorder(new LineBorder(Color.black));
	}
	public void batch() {
		getApp().add(timerLabel);
	}
}
