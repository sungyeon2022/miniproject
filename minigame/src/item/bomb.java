package item;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.text.html.ImageView;

import SpriteSheet.SpriteSheet;
import imgSize.explosionsize;
import lombok.Data;
import main.miniApp;
import objectSetting.ViewDirect;
import player.issac;

@Data

public class bomb extends Item {
	private int bombWidth = 30;
	private int bombHeight = 31;
	private Image imgtemp;
	private Image resizeimg;
	private issac issac;
	private SpriteSheet ssExplosion;
	boolean exploded = false;
	boolean moving = false;

	public bomb(miniApp app, int xbomb, int ybomb) {
		super(app);
		setXItem(xbomb);
		setYItem(ybomb);
		init();
		setting();
		batch();
		explosmotion();
	}

	public void init() {
		ssExplosion = new SpriteSheet("item/explosion.png", "explosion", 0, 0, explosionsize.WIDTH,
				explosionsize.HEIGHT);
		setItemLabel(new JLabel());
		try {
			imgtemp = ImageIO.read(getClass().getClassLoader().getResource("images/item/bomb.png"));
			resizeimg = imgtemp.getScaledInstance(bombWidth, bombHeight, Image.SCALE_SMOOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setting() {
		getItemLabel().setIcon(new ImageIcon(imgtemp.getScaledInstance(bombWidth, bombHeight, Image.SCALE_SMOOTH)));
		getItemLabel().setBounds(getXItem(), getYItem(), bombWidth, bombHeight);
		setGUBUN("bomb");
	}

	public void batch() {
		getApp().add(getItemLabel(), 0);
	}

	public void explosmotion() {
		new Thread(() -> {
			for (int i = 12; i > 0; i--) {
				getItemLabel()
						.setIcon(new ImageIcon(imgtemp.getScaledInstance(bombWidth, bombHeight, Image.SCALE_SMOOTH)));
				getItemLabel().setBounds(getXItem(), getYItem(), 30, 31);
				getItemLabel().repaint();
				try {
					Thread.sleep(i * 10);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				getItemLabel().setIcon(new ImageIcon(imgtemp.getScaledInstance(bombWidth, 36, Image.SCALE_SMOOTH)));
				getItemLabel().setBounds(getXItem(), getYItem() - 10, 30, 36);
				getItemLabel().repaint();
				try {
					Thread.sleep(i * 10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getItemLabel().setIcon(new ImageIcon(imgtemp.getScaledInstance(bombWidth, 41, Image.SCALE_SMOOTH)));
				getItemLabel().setBounds(getXItem(), getYItem() - 10, 30, 41);
				getItemLabel().repaint();
				try {
					Thread.sleep(i * 10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			explosion();
		}).start();
	}

	public void explosion() {
		getItemLabel().removeAll();
		getApp().remove(getItemLabel());
		getApp().repaint();
		ssExplosion.drawObj(getItemLabel().getX() - 30, getItemLabel().getY() - 45);
		getApp().add(ssExplosion);
		getApp().repaint();
		int x = 0;
		int y = 0;
		while (true) {
			if (x <= 3 && y <= 3) {
				x++;
			}
			if (x > 3 && y <= 3) {
				x = 0;
				y++;
			}
			if (y > 2) {
				break;
			}
			ssExplosion.setXPos(explosionsize.WIDTH * x);
			ssExplosion.setYPos(explosionsize.HEIGHT * y);
			ssExplosion.drawObj(getItemLabel().getX() - 30, getItemLabel().getY() - 45);
			try {
				Thread.sleep(55);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		getApp().remove(ssExplosion);
		getApp().repaint();
	}
}
