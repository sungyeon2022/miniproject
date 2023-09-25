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
import imgSize.BodySize;
import imgSize.HeadSize;
import imgSize.WormSize;
import map.Background;
import monster.Worm;
import monster.body;
import monster.Head;
import monster.Monster;
import player.issac;
import sword.SwordControl;
import testimg.testcontorl;
import item.Bomb;
import item.Heart;
import item.Item;
import item.Pill;
import objectSetting.BombSize;
import objectSetting.HeartSize;
import objectSetting.PillSize;
import wall.rock;
import wall.wall;

//JFrame 참조 
public class miniApp extends JFrame {
	private JFrame app;
	private Background bg;
	private issac issac;
	private EnemyIssac enemyIssac;
	private SwordControl swordControl;
	private testcontorl testcontorl;
	private Worm worm;
	private Vector<Monster> monsters;
	private Vector<Item> items;
	private Vector<wall> walls;
	private ConnectControl connectControl;
	private TimerControl timerControl;

	private Socket socket;
	// miniApp에서 필요한 시스템 정보 가져옴
	public miniApp() {
		init();
		setting();
		batch();
		listener();
		playerattack();
		setVisible(true);
		checkUsedMomory();
	}

	// 앱에서 필요한 데이터정보 가져옴
	public void init() {
		connectControl = new ConnectControl();
		app = this;
		bg = new Background(app);
		timerControl = new TimerControl(app);
		monsters = new Vector<Monster>();
		items = new Vector<Item>();
		walls = new Vector<wall>();
		issac = new issac(app, monsters, walls, items, connectControl);
		enemyIssac = new EnemyIssac(app, monsters, walls, items);

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

		walls.add(new rock(app, 455, 300));
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
		miniApp miniApp = new miniApp();
//		if (miniApp.connectControl.isIsconnect());
//		miniApp.printObject();
//			miniApp.EnemyControl();
	}

	public void keyboardEvent() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					issac.moveRight();
//					connectControl.DataSend(0);
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					issac.moveLeft();
//					connectControl.DataSend(1);
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					issac.moveDown();
//					connectControl.DataSend(2);
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					issac.moveUp();
//					connectControl.DataSend(3);
				} else if (e.getKeyCode() == KeyEvent.VK_W) {
//					connectControl.DataSend(4);
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
//					connectControl.DataSend(5);
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
//					connectControl.DataSend(6);
					issac.attackMotion();
					issac.setAttackkeypress(true);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//					connectControl.DataSend(7);
					issac.setKeyRelease(true);
					issac.setRight(false);
					issac.refreshDirect();

				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//					connectControl.DataSend(8);
					issac.setLeft(false);
					issac.refreshDirect();

				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//					connectControl.DataSend(9);
					issac.setDown(false);
					issac.refreshDirect();

				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
//					connectControl.DataSend(10);
					issac.setUp(false);
					issac.refreshDirect();

				} else if (e.getKeyCode() == KeyEvent.VK_A) {
//					connectControl.DataSend(12);
					issac.setPlayerAttack(false);
					issac.setAttackkeypress(false);
				} else if (e.getKeyCode() == KeyEvent.VK_S) {

					System.out.println("s키 떨어짐");
					enemyIssac.setEnemyAttack(false);
				}

			}
		});
	}

	public void playerattack() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (!issac.isDead()) {
					if (issac.isPlayerAttacking())
						break;
					for (int i = 0; i < monsters.size(); i++) {
						if (issac.getSwordControl().getSsSword().getBounds()
								.intersects(monsters.get(i).getSsMonster().getBounds())) {
							monsters.get(i).setLife(monsters.get(i).getLife() - issac.getAttackDamage());
							System.out.println("공격 적용");
							try {
								Thread.sleep(1000); // 여기서 800mili 휴식 -----
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

					}
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					playerattack();

				}
			}
		}).start();
	}
//	public void printObject() {
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				Object receive = connectControl.DataReceive();
//				while (true) {
//					System.out.println(receive);
//				}
//				
//			}
//		}).start();
//	}
//	public void EnemyControl() {
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				while (!enemyIssac.isDead()) {
//					int a = connectControl.DataReceive();
//					System.out.println(a);
//					if (a == 0) {
//						enemyIssac.moveLeft();
//					} else if (a == 1) {
//						enemyIssac.moveRight();
//					} else if (a == 2) {
//						enemyIssac.moveUp();
//					} else if (a == 3) {
//						enemyIssac.moveDown();
//					} else if (a == 6) {
//						enemyIssac.attackMotion();
//					} else if (a == 7) {
//						enemyIssac.setLeft(false);
//						enemyIssac.refreshDirect();
//					} else if (a == 8) {
//						enemyIssac.setRight(false);
//						enemyIssac.refreshDirect();
//					} else if (a == 9) {
//						enemyIssac.setUp(false);
//						enemyIssac.refreshDirect();
//					} else if (a == 10) {
//						enemyIssac.setDown(false);
//						enemyIssac.refreshDirect();
//					} else if (a == 12) {
//						enemyIssac.setEnemyAttack(false);
//					}
//				}
//			}
//		}).start();
//	}
	public void checkUsedMomory() {
		new Thread(()->{
			while(true) {
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
