package Timer;

import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Data;

@Data

public class Timer {
	private JFrame app;
	private JLabel timerLabel;
	private int sec;
	private int mliSec;
	private int min;
	private int hour;
	private int startTime;
	public Timer(JFrame app) {
		this.app = app;
		
	}
}
