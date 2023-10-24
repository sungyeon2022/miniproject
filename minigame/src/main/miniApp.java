package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import Timer.TimerControl;
import connect.Connect;
import connect.ConnectControl;
import data.DataClass;
import enemy.EnemyIssac;
//import enemy.EnemyIssac;
import imgSize.BodySize;
import imgSize.BombSize;
import imgSize.HeadSize;
import imgSize.HeartSize;
import imgSize.PillSize;
import imgSize.WormSize;
import map.Background;
import monster.Worm;
import monster.body;
import objectSetting.ViewDirect;
import monster.Head;
import monster.Monster;
import player.issac;
import startButton.StartButtonControl;
import sword.EnemySwordControl;
import sword.SwordControl;
import test.TestControl;
import item.Heart;
import item.Item;
import item.Pill;
import wall.rock;
import wall.wall;

//JFrame 참조 
public class miniApp extends JFrame {
	private JFrame app;
	private Background bg;
	private issac issac;
	private SwordControl swordControl;
	private EnemyIssac enemyIssac;
	private TestControl testControl;
	private Vector<Monster> monsters;
	private Vector<Item> items;
	private Vector<wall> walls;
	private ConnectControl connectControl;
	private TimerControl timerControl;
	private EnemySwordControl enemySwordControl;
	private StartButtonControl startButtonControl;
	private DataClass dataClass;

	// miniApp에서 필요한 시스템 정보 가져옴
	public miniApp() {
		init();
		setting();
		batch();
		listener();
//		checkUsedMomory();
		monsterDeadCheck();
		gameStartCheck();
	}

	// 앱에서 필요한 데이터정보 가져옴
	public void init() {
		app = this;
		connectControl = new ConnectControl();
		bg = new Background(app);
		startButtonControl = new StartButtonControl(app, connectControl);
//		page = new Page(app);
		monsters = new Vector<Monster>();
//		items = new Vector<Item>();
		walls = new Vector<wall>();
		issac = new issac(app, monsters, walls, items, startButtonControl, connectControl);
		swordControl = new SwordControl(app, issac, monsters, connectControl);
		enemyIssac = new EnemyIssac(app, walls, items, issac, connectControl);
		enemySwordControl = new EnemySwordControl(app, issac, enemyIssac, connectControl);
//		testControl = new TestControl(app, connectControl);
		monsters.add(new Worm(app, issac, "monster/worm.png", WormSize.WIDTH, WormSize.HEIGHT));
		monsters.add(new body(app, issac, "monster/body.png", BodySize.WIDTH, BodySize.HEIGHT));
		monsters.add(new Head(app, issac, "monster/head.png", HeadSize.WIDTH, HeadSize.HEIGHT));
		timerControl = new TimerControl(app, connectControl);

	}

	// JFrame을 통한 창출력
	public void setting() {
		app.setTitle("Player1");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setResizable(false);
		app.setLocationRelativeTo(null);
		app.setLayout(null);
		setVisible(true);
	}

	public void batch() {
	}

	public void listener() {
		keyboardEvent();
	}

	public static void main(String[] args) {
		new miniApp();

	}

	public void keyboardEvent() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					issac.moveRight();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					issac.moveLeft();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					issac.moveDown();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					issac.moveUp();
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					issac.setKeyPress(true);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					issac.setRight(false);
					issac.refreshDirect();
					issac.getViewDirectInfo()[ViewDirect.LEFT] = false;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					issac.setLeft(false);
					issac.refreshDirect();
					issac.getViewDirectInfo()[ViewDirect.RIGHT] = false;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					issac.setDown(false);
					issac.refreshDirect();
					issac.getViewDirectInfo()[ViewDirect.UP] = false;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					issac.setUp(false);
					issac.refreshDirect();
					issac.getViewDirectInfo()[ViewDirect.DOWN] = false;
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					issac.setKeyPress(false);
				}
			}
		});
	}

//	public void checkUsedMomory() {
//		new Thread(() -> {
//			while (true) {
//				Runtime.getRuntime().gc();
//				long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//				System.out.println(usedMemory);
//				try {
//					Thread.sleep(1000);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		}).start();
//	}

	public void monsterDeadCheck() {
		new Thread(() -> {
			while (!connectControl.isMulti() && !monsters.isEmpty()) {
				for (int i = 0; i < monsters.size(); i++) {
					if (monsters.get(i).isDead()) {
						String monName = monsters.get(i).getGUBUN();
						monsters.remove(i);
						if (monName.equals("Worm")) {
							try {
								Thread.sleep(3000);
							} catch (Exception e) {
								e.printStackTrace();
							}
							monsters.add(new Worm(app, issac, "monster/worm.png", WormSize.WIDTH, WormSize.HEIGHT));
						}
						if (monName.equals("Head")) {
							try {
								Thread.sleep(3000);
							} catch (Exception e) {
								e.printStackTrace();
							}
							monsters.add(new Head(app, issac, "monster/head.png", HeadSize.WIDTH, HeadSize.HEIGHT));
						}
						if (monName.equals("Body")) {
							try {
								Thread.sleep(3000);
							} catch (Exception e) {
								e.printStackTrace();
							}
							monsters.add(new body(app, issac, "monster/body.png", BodySize.WIDTH, BodySize.HEIGHT));
						}
					}
				}
				gameStartCheck();
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void gameStartCheck() {
		if (connectControl.isMulti()) {
			for (int i = 0; i < monsters.size(); i++) {
				monsters.get(i).getSsMonster().setVisible(false);
				app.remove(monsters.get(i).getSsMonster());
				System.out.println(monsters.size());
			}
			monsters.removeAllElements();
			enemyIssac.batch();
			enemySwordControl.batch();
			issac.setXPlayer(issac.getDefaultX());
			issac.setYPlayer(issac.getDefaultY());
			issac.getSsBody().drawObj(issac.getXPlayer() + issac.getXPlusBody(),
					issac.getYPlayer() + issac.getYPlusBody());
			issac.getSsHead().drawObj(issac.getXPlayer(), issac.getYPlayer());
		}
	}
}
