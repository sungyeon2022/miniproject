package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.xml.stream.events.StartDocument;
import org.w3c.dom.Text;

import imgSize.BodySize;
import imgSize.BombSize;
import imgSize.HeadSize;
import imgSize.HeartSize;
import imgSize.PillSize;
import imgSize.ViewDirect;
import imgSize.WormSize;
import map.Background;
import monster.Worm;
import monster.body;
import player.*;
import sword.Sword;
import monster.Head;
import monster.Monster;
import monster.Worm;
import player.issac;
import sword.SwordControl;
import sword.swordattackcontrol;
import testimg.testcontorl;
import item.Bomb;
import item.Heart;
import item.Item;
import item.Pill;
import map.Background;
import objectSetting.KeySize;
import player.issac;
import wall.rock;
import wall.wall;

//JFrame 참조 
public class miniApp extends JFrame {
	private JFrame app;

	private Background bg;
	private issac issac;
	private SwordControl swordControl;
	private testcontorl testcontorl;
	private Worm worm;
	private Vector<Monster> monsters;
	private swordattackcontrol swordattackcontrol;
	private Vector<Item> items;
	private Vector<wall> walls;

	// miniApp에서 필요한 시스템 정보 가져옴
	public miniApp() {
		init();
		setting();
		batch();
		listener();
		playerattack();
		new Thread(new Runnable() {
			@Override
			public void run() {

			}
		}).start();
		setVisible(true);
	}

	// 앱에서 필요한 데이터정보 가져옴
	public void init() {
		app = this;
		bg = new Background(app);
		monsters = new Vector<Monster>();
		items = new Vector<Item>();
		walls = new Vector<wall>();
		issac = new issac(app, monsters, walls, items);
		/*
		 * monsters.add(new Worm(app, issac, "monster/worm.png", WormSize.WIDTH,
		 * WormSize.HEIGHT)); monsters.add(new body(app, issac, "monster/body.png",
		 * BodySize.WIDTH, BodySize.HEIGHT)); monsters.add(new Head(app, issac,
		 * "monster/head.png", HeadSize.WIDTH, HeadSize.HEIGHT));
		 */
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
		app.setTitle("miniApp");
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
					issac.setIskeyPress(true);
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					issac.moveLeft();
					issac.setIskeyPress(true);
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					issac.moveDown();
					issac.setIskeyPress(true);
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					issac.moveUp();
					issac.setIskeyPress(true);
				} else if (e.getKeyCode() == KeyEvent.VK_W) {

				} else if (e.getKeyCode() == KeyEvent.VK_D) {

				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					issac.attackMotion();
					for (int i = 0; i < monsters.size(); i++) {
						if (issac.getSwordControl().getSsSword().getBounds()
								.intersects(monsters.get(i).getSsMonster().getBounds())) {
							System.out.println("공격시도");
							monsters.get(i).setLife(monsters.get(i).getLife() - issac.getAttackDamage());
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_S) {

				} else if (e.getKeyCode() == KeyEvent.VK_E) {

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					issac.setIskeyPress(false);
					issac.setRight(false);
					issac.refreshDirect();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					issac.setIskeyPress(false);
					issac.setLeft(false);
					issac.refreshDirect();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					issac.setIskeyPress(false);
					issac.setDown(false);
					issac.refreshDirect();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					issac.setIskeyPress(false);
					issac.setUp(false);
					issac.refreshDirect();
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					System.out.println("a키 떨어짐");
					issac.setPlayerAttack(false);
				}

			}
		});
	}

	public synchronized void playerattack() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (!issac.isDead()) {
					for (int i = 0; i < monsters.size(); i++) {
						if (!issac.isPlayerAttacking()) {
							if (issac.getSwordControl().getSsSword().getBounds()
									.intersects(monsters.get(i).getSsMonster().getBounds())) {
								System.out.println("판단 성공");
								monsters.get(i).setLife(monsters.get(i).getLife() - issac.getAttackDamage());
								try {
									Thread.sleep(800);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}).start();
	}
}
