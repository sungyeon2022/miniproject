package main;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JFrame;

import SpriteSheet.SpriteSheet;
import Timer.TimerControl;
import connect.Connect;
import connect.ConnectControl;
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
import sword.EnemySwordControl;
import sword.SwordControl;
import test.TestControl;
import item.Bomb;
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

	// miniApp에서 필요한 시스템 정보 가져옴
	public miniApp() {
		init();
		setting();
		batch();
		listener();
		setVisible(true);
		checkUsedMomory();
	}

	// 앱에서 필요한 데이터정보 가져옴
	public void init() {
		connectControl = new ConnectControl();
		app = this;
		bg = new Background(app);
		timerControl = new TimerControl(app, connectControl);
		monsters = new Vector<Monster>();
		items = new Vector<Item>();
		walls = new Vector<wall>();
		issac = new issac(app, monsters, walls, items, connectControl);
		enemyIssac = new EnemyIssac(app, walls, items, issac);
//		testControl = new TestControl(app, connectControl);
		swordControl = new SwordControl(app, issac, monsters, enemyIssac);
		enemySwordControl = new EnemySwordControl(app, issac, enemyIssac);
		monsters.add(new Worm(app, issac, "monster/worm.png", WormSize.WIDTH, WormSize.HEIGHT));
		monsters.add(new body(app, issac, "monster/body.png", BodySize.WIDTH, BodySize.HEIGHT));
		monsters.add(new Head(app, issac, "monster/head.png", HeadSize.WIDTH, HeadSize.HEIGHT));

		items.add(new Bomb(app, "item/bomb.png", "bomb", 140, 400, BombSize.PICKWIDTH, BombSize.PICKHEIGHT));
		items.add(new Heart(app, "item/recoveryLife.png", "heart", 240, 400, HeartSize.WIDTH, HeartSize.HEIGHT));
		// 랜덤 아이템 생성
		int witem = (int) (Math.random() * 4);
		switch (witem) {
		case 0:
			items.add(new Pill(app, "item/PowerUp.png", "Power", 440, 400, PillSize.WIDTH, PillSize.HEIGHT));
			break;
		case 1:
			items.add(new Pill(app, "item/ASUp.png", "AttackSpeed", 490, 400, PillSize.WIDTH, PillSize.HEIGHT));
			break;
		case 2:
			items.add(new Pill(app, "item/SpeedUp.png", "Speed", 540, 400, PillSize.WIDTH, PillSize.HEIGHT));
			break;
		case 3:
			items.add(new Pill(app, "item/FullHp.png", "FullHp", 440, 300, PillSize.WIDTH, PillSize.HEIGHT));
			break;
		}

		// 벽 시험 생성 및 10초후 제거

//		walls.add(new rock(app, 455, 300));
		repaint();

	}

	// JFrame을 통한 창출력
	public void setting() {
		app.setTitle("miniapp");
		app.setSize(960, 640);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setLocationRelativeTo(null);
		app.setLayout(null);
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
				} else if (e.getKeyCode() == KeyEvent.VK_W) {
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					swordControl.swordAttackForm();
					swordControl.setAttackKeyPress(true);
					swordControl.setEnemyAttackPress(true);
					enemySwordControl.swordAttackForm();
					enemySwordControl.setAttackKeyPress(true);
					enemySwordControl.setEnemyAttackPress(true);
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
					issac.setPlayerAttack(false);
					swordControl.setAttackKeyPress(false);
					swordControl.setEnemyAttackPress(false);
					enemyIssac.setEnemyAttack(false);
					enemySwordControl.setAttackKeyPress(false);
					enemySwordControl.setEnemyAttackPress(false);
				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					System.out.println("s키 떨어짐");
				}

			}
		});
	}

	public void checkUsedMomory() {
		new Thread(() -> {
			while (true) {
				Runtime.getRuntime().gc();
				long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.out.println(usedMemory);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}).start();
	}
}
