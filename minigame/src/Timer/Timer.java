package Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Data;

@Data

public class Timer {
	private JFrame app;
	private JLabel timerLabel;
	private int sec;
	private int mliSec;
	private boolean isStart;
	public Timer(JFrame app) {
		this.app = app;
		sec = 30;
		mliSec = 30;
	}
	public void timerRefresh() {}
}
