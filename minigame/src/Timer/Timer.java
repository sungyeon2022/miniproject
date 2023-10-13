package Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Data;

@Data

public class Timer {
	private JFrame app;
	private JLabel timerLabel;
	private String sec;
	private int mliSec;
	private String min;
	private String hour;
	private int startTime;
	private boolean isStart;
	public Timer(JFrame app) {
		this.app = app;
		startTime = (int)System.currentTimeMillis()/10; 
		
	}
}