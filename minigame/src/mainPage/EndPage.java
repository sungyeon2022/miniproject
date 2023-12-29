package mainPage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import lombok.Data;
import main.miniApp;
import objectSetting.MyFont;
import player.issac;

@Data

public class EndPage {
	private miniApp app;
	private JPanel endAlphaPanel;
	private JLabel endLabel;
	private JLabel end2Label;
	private JLabel checkLabel;
	private ImageIcon endIcon;
	private ImageIcon endpageIcon;
	private Image resizeimg;
	private JButton exitButton;
	private JButton resetButton;
	private JLabel deadissac;

	public EndPage(miniApp app) {
		this.app = app;
		init();
		setting();
		batch();
		mouseListener();
	}

	public void init() {
		endLabel = new JLabel();
		end2Label = new JLabel();
		endAlphaPanel = new JPanel();
		checkLabel = new JLabel();

		endpageIcon = new ImageIcon(getClass().getClassLoader().getResource("images/structure/paper.png"));

		endIcon = new ImageIcon(getClass().getClassLoader().getResource("images/structure/endpage.png"));
		resizeimg = endIcon.getImage();
		endIcon = new ImageIcon(resizeimg.getScaledInstance(400, 500, Image.SCALE_SMOOTH));

		exitButton = new JButton();
		resetButton = new JButton();
	}

	private void setting() {
		endLabel.setBounds(0, 0, 960, 640);
		endLabel.setLayout(null);

		endAlphaPanel.setBounds(0, 0, 960, 640);
		endAlphaPanel.setLayout(null);
		endAlphaPanel.setBackground(new Color(0, 0, 0, 127));

		end2Label.setIcon(endIcon);
		end2Label.setBounds(250, 30, 400, 500);

		checkLabel.setIcon(endpageIcon);
		checkLabel.setBounds(650, 150, 320, 190);
		checkLabel.setLayout(null);

		resetButton.setText("New Game");
		resetButton.setFont(new Font(MyFont.FONT3, Font.BOLD, 40));
		resetButton.setBounds(5, 30, 230, 50);
		resetButton.setHorizontalAlignment(JButton.CENTER);
		resetButton.setBorderPainted(false);
		resetButton.setContentAreaFilled(false);
		resetButton.setFocusable(false);

		exitButton.setText("EXIT");
		exitButton.setFont(new Font(MyFont.FONT3, Font.BOLD, 40));
		exitButton.setBounds(10, 85, 140, 50);
		exitButton.setHorizontalAlignment(JButton.CENTER);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
	}

	private void batch() {
		checkLabel.add(exitButton);
		checkLabel.add(resetButton);

		endAlphaPanel.add(end2Label, 0);
		endAlphaPanel.add(checkLabel, 1);

		endLabel.add(endAlphaPanel);

		app.add(endLabel, 0);
		app.repaint();
	}

	public void mouseListener() {
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setFont(new Font(MyFont.FONT3, Font.BOLD, 42));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setFont(new Font(MyFont.FONT3, Font.BOLD, 40));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		resetButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				resetButton.setFont(new Font(MyFont.FONT3, Font.BOLD, 42));
			}

			public void mouseExited(MouseEvent e) {
				resetButton.setFont(new Font(MyFont.FONT3, Font.BOLD, 40));
			}

			public void mouseClicked(MouseEvent e) {
				if (getApp().getEndPage() != null) {
					app.getContentPane().removeAll();
					app.setStartPage(new StartPage(app));
					app.getContentPane().repaint();
					app.getConnectControl().getSendDataClass().setSingle(false);
					app.getConnectControl().getSendDataClass().setEnd(false);
					app.listener();
				}
			}
		});
	}
}
