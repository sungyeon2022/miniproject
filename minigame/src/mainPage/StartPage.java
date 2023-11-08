package mainPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Timer.TimerControl;
import connect.ConnectControl;
import lombok.Data;
import main.miniApp;
import monster.Monster;
import objectSetting.MyFont;
import player.issac;
import startButton.StartButtonControl;
import sword.SwordControl;

@Data

public class StartPage{
	private miniApp app;
	private JPanel pagePanel;
	private JLabel pageLabel;
	private JLabel pJLabel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton startSingleButton;
	private JButton startMultiButton;
	private JButton endButton;
	private issac issac;
	private SwordControl swordControl;
	private Vector<Monster> monsters;
	private StartButtonControl startButtonControl;
	private ConnectControl connectControl;
	private TimerControl timerControl;
	private JLabel referenceLabel;
	private JLabel checkLabel;
	
	public StartPage(miniApp app) {
		this.app = app;
		init();
		setting();
		batch();
	}

	public void init() {
		pJLabel = new JLabel();
		pagePanel = new JPanel();
		pageLabel = new JLabel();
		startSingleButton = new JButton();
		startMultiButton = new JButton();
		endButton = new JButton();
		nameField = new JTextField();
		referenceLabel = new JLabel("This app is reference by Binding of Isaac");
		checkLabel = new JLabel("If you have Text error, run isaacGame.ttf in lib");
		ImageIcon pageIcon = new ImageIcon("images/structure/titlemenu.png");
		ImageIcon inputNameIcon = new ImageIcon("images/structure/namepaper.png");
		ImageIcon nameIcon = new ImageIcon("images/structure/main name.png");
		Image pageImg = pageIcon.getImage();
		Image reSizeImgImage = pageImg.getScaledInstance(944, 600, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(reSizeImgImage);
		pageLabel.setIcon(updateIcon);
		pJLabel.setIcon(nameIcon);
		
		startSingleButton.setText("Single Game");
		startSingleButton.setFont(new Font(MyFont.FONT3, Font.BOLD, 40));
		startSingleButton.setHorizontalAlignment(JButton.CENTER);
		startMultiButton.setText("Multi Game");
		startMultiButton.setFont(new Font(MyFont.FONT3, Font.BOLD, 40));
		startMultiButton.setHorizontalAlignment(JButton.CENTER);
		
		endButton.setText("EXIT");
		endButton.setFont(new Font(MyFont.FONT3, Font.BOLD, 40));
		endButton.setHorizontalAlignment(JButton.CENTER);
		
		nameField.setBorder(new LineBorder(Color.black));
		
		referenceLabel.setFont(new Font(MyFont.FONT3, 0, 15));
		
		checkLabel.setFont(new Font(MyFont.FONT3,0,15));
	}

	public void setting() {
		pagePanel.setBounds(0, 0, 960, 640);
		pagePanel.setLayout(null);
		
		pageLabel.setLayout(null);
		pageLabel.setBounds(0, 0, 944, 600);
		
		nameField.setBounds(200, 100, 100, 100);
		
		pJLabel.setBounds(230, 10, 500, 100);
		
		startSingleButton.setBounds(345, 120, 300, 50);
		startSingleButton.setBorderPainted(false);
		startSingleButton.setContentAreaFilled(false);
		startSingleButton.setFocusPainted(false);
		
		startMultiButton.setBounds(340, 170, 300, 50);
		startMultiButton.setBorderPainted(false);
		startMultiButton.setContentAreaFilled(false);
		startMultiButton.setFocusPainted(false);
		
		endButton.setBounds(390, 400, 150, 50);
		endButton.setBorderPainted(false);
		endButton.setContentAreaFilled(false);
		endButton.setFocusPainted(false);
		
		referenceLabel.setBounds(330,460,300,20);
		
		checkLabel.setBounds(0,5,400,18);
		
//		pageLabel.add(nameField);
		pageLabel.add(pJLabel);
		pageLabel.add(startSingleButton);
		pageLabel.add(startMultiButton);
		pageLabel.add(endButton);
		pageLabel.add(referenceLabel);
		pageLabel.add(checkLabel);
		pagePanel.add(pageLabel);
		pagePanel.setBackground(Color.GRAY);
	}

	public void batch() {
		app.add(pagePanel,0);
	}
}
