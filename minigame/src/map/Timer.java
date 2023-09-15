package map;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Timer {
	int min = 0;
	int sec = 0;

	public Timer(JFrame app) {

		JLabel latimer = new JLabel(String.format("%02d:%02d", min, sec));
		latimer.setSize(100, 30);
		latimer.setLocation(450, 20);
		latimer.setFont(new Font("바탕", Font.ITALIC, 15));
		latimer.setForeground(Color.WHITE);
		app.add(latimer);
		new Thread(new Runnable() {

			@Override

			public void run() {

				try {
					for (int i = 0; i < 9999; i++) {

						Thread.sleep(1000);
						sec++;
						if (sec == 60) {
							min++;
							sec = 0;
						}
						latimer.setText(String.format("%02d:%02d", min, sec));
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start();

	}
}
