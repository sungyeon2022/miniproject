package startPage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import SpriteSheet.SpriteSheet;
import map.Background;

public class Page {
	private JFrame app;
	private JPanel pagePanel;
	private JLabel pageLabel;
	private JLabel inputNameLable;
	private boolean isSingle;
	public Page(JFrame app) {
		this.app = app;
		init();
		setting();
		batch();
//		inputName.setBounds(91, 63, 300, 300);
		
		
	}
	public void init() {
		app.setSize(960, 640);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setLocationRelativeTo(null);
		pagePanel = new JPanel();
		pageLabel = new JLabel();
		inputNameLable = new JLabel();
		ImageIcon pageIcon = new ImageIcon("images/structure/paper_plus.png");
		ImageIcon inputNameIcon = new ImageIcon("images/structure/namepaper.png");
		Image pageImg = pageIcon.getImage();
		Image reSizeImgImage = pageImg.getScaledInstance(700, 500, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(reSizeImgImage);
		pageLabel.setIcon(updateIcon);
		inputNameLable.setIcon(inputNameIcon);
		inputNameLable.setBorder(new LineBorder(Color.black));
		pageLabel.setBorder(new LineBorder(Color.red));
		
	}
	public void setting() {
		pagePanel.setBounds(0,0,960,640);
		pagePanel.setLayout(null);
		pageLabel.setBounds(0,0,700, 500);
		inputNameLable.setBounds(200, 100, 100, 100);
		pageLabel.add(inputNameLable);
		pagePanel.add(pageLabel);
		pagePanel.setBackground(Color.BLACK);
		pagePanel.setBorder(new LineBorder(Color.GREEN));
	}
	public void batch() {
		app.add(pagePanel);
	}
}
