package item;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.text.html.ImageView;

import main.miniApp;

public class bomb extends Item {
	int bombWidth = 30;
	int bombHeight = 31;
	Image imgtemp;
	Image resizeimg;
	boolean exploded = false;

	public bomb(miniApp app, int xbomb, int ybomb) {
		super(app);
		init();
		setting();
		batch(xbomb, ybomb);
		explosmotion();
	}

	public void init() {
		setItemLabel(new JLabel());
		try {
			imgtemp = ImageIO.read(new File("images/item/bomb.png"));
			resizeimg = imgtemp.getScaledInstance(bombWidth, bombHeight, Image.SCALE_SMOOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setting() {
		getItemLabel().setIcon(new ImageIcon(imgtemp.getScaledInstance(bombWidth, bombHeight, Image.SCALE_SMOOTH)));
		getItemLabel().setBounds(200, 200, bombWidth, bombHeight);
	}

	public void batch(int xbomb, int ybomb) {
		getApp().add(getItemLabel(), 0);
	}

	public void explosmotion() {
		new Thread(() -> {
			for (int i = 5; i > 0; i--) {
				getItemLabel().setIcon(new ImageIcon(imgtemp.getScaledInstance(bombWidth, bombHeight, Image.SCALE_SMOOTH)));
				getItemLabel().setBounds(200, 200, 30, 31);
				getItemLabel().repaint();
				try {
					Thread.sleep(i*50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getItemLabel().setIcon(new ImageIcon(imgtemp.getScaledInstance(bombWidth, 41, Image.SCALE_SMOOTH)));
				getItemLabel().setBounds(200, 190, 30, 41);
				getItemLabel().repaint();
				try {
					Thread.sleep(i*50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}
