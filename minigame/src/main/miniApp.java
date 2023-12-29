package main;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

import Timer.TimerControl;
import collections.DarkWings;
import collections.GreenMus;
import collections.Mantle;
import collections.RedMus;
import collections.Collections;
import collections.Crown;
import collections.Wings;
import connect.ConnectControl;
import data.DataClass;
import enemy.EnemyIssac;
import imgSize.BodySize;
import imgSize.HeadSize;
import imgSize.WormSize;
import item.*;
import lombok.*;
import mainPage.*;
import map.Background;
import monster.*;
import objectSetting.*;
import player.issac;
import startButton.StartButtonControl;
import sword.*;
import test.TestControl;
import wall.structure;

@Getter
@Setter
//JFrame 참조 
public class miniApp extends JFrame {
	private DataClass PrimaryDataClass;
	private miniApp app;
	private StartPage startPage;
	private Background bg;
	private issac issac;
	private SwordControl swordControl;
	private EnemyIssac enemyIssac;
	private TestControl testControl;
	private Vector<Monster> monsters;
	private Vector<Item> items;
	private Vector<structure> structures;
	private ConnectControl connectControl;
	private TimerControl timerControl;
	private EnemySwordControl enemySwordControl;
	private StartButtonControl startButtonControl;
	private EndPage endPage;
	private Collections collections;

	// miniApp에서 필요한 시스템 정보 가져옴
	public miniApp() {
		init();
		setting();
		batch();
		listener();
		keyboardEvent();
//		checkUsedMomory();
	}

	// 앱에서 필요한 데이터정보 가져옴
	public void init() {
		PrimaryDataClass = new DataClass();
		String ID = JOptionPane.showInputDialog("ID를 입력해주세요");
		if(ID==null || ID.trim().isEmpty() || ID.contains(" ")){
			JOptionPane.showMessageDialog(null,"ID형식이 잘못되었습니다.");
			System.exit(0);
		}
		String PW = JOptionPane.showInputDialog("PW를 입력해주세요");
		if(PW==null || PW.trim().isEmpty() || PW.contains(" ")) {
			JOptionPane.showMessageDialog(null, "PW형식이 잘못되었습니다.");
			System.exit(0);
		}
		PrimaryDataClass.setMem_Id(ID);
		PrimaryDataClass.setMem_pw(PW);
		app = this;
		connectControl = new ConnectControl(app);
		bg = new Background(app);
		startPage = new StartPage(app);
//		testControl = new TestControl(app);
//		connectControl = new ConnectControl();
//		startButtonControl = new StartButtonControl(app, connectControl);
//		timerControl = new TimerControl(app, connectControl);
//		monsters = new Vector<Monster>();
//		items = new Vector<Item>();
//		walls = new Vector<wall>();
//		issac = new issac(app, monsters, walls, startButtonControl, connectControl, timerControl);
//		swordControl = new SwordControl(app, issac, monsters, connectControl);
//		enemyIssac = new EnemyIssac(app, walls, items, issac, connectControl);
//		enemySwordControl = new EnemySwordControl(app, issac, enemyIssac, connectControl);
		
//		monsters.add(new Worm(app, issac, "monster/worm.png", WormSize.WIDTH, WormSize.HEIGHT));
//		monsters.add(new body(app, issac, "monster/body.png", BodySize.WIDTH, BodySize.HEIGHT));
//		monsters.add(new Head(app, issac, "monster/head.png", HeadSize.WIDTH, HeadSize.HEIGHT));

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
		MouseListener();
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
					issac.issacInfoRefresh();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					issac.setRight(false);
					issac.refreshDirect();
					issac.getViewDirectInfo()[ViewDirect.LEFT] = false;
					issac.issacInfoRefresh();
					issac.sliding();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					issac.setLeft(false);
					issac.refreshDirect();
					issac.getViewDirectInfo()[ViewDirect.RIGHT] = false;
					issac.issacInfoRefresh();
					issac.sliding();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					issac.setDown(false);
					issac.refreshDirect();
					issac.getViewDirectInfo()[ViewDirect.UP] = false;
					issac.issacInfoRefresh();
					issac.sliding();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					issac.setUp(false);
					issac.refreshDirect();
					issac.getViewDirectInfo()[ViewDirect.DOWN] = false;
					issac.issacInfoRefresh();
					issac.sliding();
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					issac.setKeyPress(false);
					issac.issacInfoRefresh();
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

	public void gameStartCheck() {
		for (int i = 0; i < monsters.size(); i++) {
			monsters.get(i).getSsMonster().setVisible(false);
			app.remove(monsters.get(i).getSsMonster());
			System.out.println(monsters.size());
		}
		monsters.removeAllElements();
		connectCheck();
	}

	public void connectCheck() {
		new Thread(() -> {
			boolean wait = false;
			while (!Thread.interrupted()) {
				if (connectControl.isReciveMulti() && connectControl.isStart()) {
					issac.setXPlayer(issac.getDefaultX());
					issac.setYPlayer(issac.getDefaultY());
					issac.getSsBody().drawObj(issac.getXPlayer() + issac.getXPlusBody(),
							issac.getYPlayer() + issac.getYPlusBody());
					issac.getSsHead().drawObj(issac.getXPlayer(), issac.getYPlayer());
					issac.setViewDirect(ViewDirect.UP);
					issac.setUp(true);
					issac.issacInfoRefresh();
					enemyIssac.batch();
					enemySwordControl.batch();
					break;
				} else if (connectControl.isReciveMulti() && !connectControl.isStart()) {
					if (!wait) {
						waitting();
						wait = true;
					}
					issac.setXPlayer(issac.getDefaultX());
					issac.setYPlayer(issac.getDefaultY());
					issac.getSsBody().drawObj(issac.getXPlayer() + issac.getXPlusBody(),
							issac.getYPlayer() + issac.getYPlusBody());
					issac.getSsHead().drawObj(issac.getXPlayer(), issac.getYPlayer());
				} else {
					issac.setXPlayer(issac.getDefaultX());
					issac.setYPlayer(issac.getDefaultY());
					issac.getSsBody().drawObj(issac.getXPlayer() + issac.getXPlusBody(),
							issac.getYPlayer() + issac.getYPlusBody());
					issac.getSsHead().drawObj(issac.getXPlayer(), issac.getYPlayer());
				}
			}
		}).start();
	}

	public void waitting() {
		new Thread(() -> {
			for (int i = 3; i > 0; i--) {
				timerControl.getTimerLabel().setText(Integer.toString(i));
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
			}
			connectControl.getSendDataClass().setStart(true);
			int time = (int) System.currentTimeMillis() / 10;
			connectControl.getSendDataClass().setStartTime(time);
			timerControl.setStartTime(time);
			connectControl.SendData();
			connectControl.setStart(true);
		}).start();
	}

	private void MouseListener() {
		startPage.getStartSingleButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startPage.getStartSingleButton().setFont(new Font(MyFont.FONT3, Font.BOLD, 42));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startPage.getStartSingleButton().setFont(new Font(MyFont.FONT3, Font.BOLD, 40));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (startPage != null) {
					connectControl.getSendDataClass().setSingle(true);
					startPage.getPagePanel().removeAll();
					app.remove(startPage.getPagePanel());
					app.repaint();
					monsters = new Vector<Monster>();
					structures = new Vector<structure>();
					items = new Vector<Item>();
					issac = new issac(app, monsters, structures, items, startButtonControl, connectControl);
					monsters.add(new Worm(app, issac, "monster/worm.png", WormSize.WIDTH, WormSize.HEIGHT));
					monsters.add(new body(app, issac, "monster/body.png", BodySize.WIDTH, BodySize.HEIGHT));
					monsters.add(new Head(app, issac, "monster/head.png", HeadSize.WIDTH, HeadSize.HEIGHT));
					monsters.add(new fly(app, issac, "monster/fly.png",0,0));
				}
				startPage = null;
				app.setFocusable(true);
				app.requestFocus();
			}
		});
		startPage.getStartMultiButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startPage.getStartMultiButton().setFont(new Font(MyFont.FONT3, Font.BOLD, 42));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startPage.getStartMultiButton().setFont(new Font(MyFont.FONT3, Font.BOLD, 40));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				app.remove(startPage.getPagePanel());
				app.repaint();
				boolean check = true;
				if (check) {
					check = false;
					startButtonControl = new StartButtonControl(app, connectControl);
					issac = new issac(app, monsters, structures, items, startButtonControl, connectControl);
					app.setFocusable(true);
					app.requestFocus();
				}
			}
		});
		startPage.getEndButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startPage.getEndButton().setFont(new Font(MyFont.FONT3, Font.BOLD, 42));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startPage.getEndButton().setFont(new Font(MyFont.FONT3, Font.BOLD, 40));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}
}
