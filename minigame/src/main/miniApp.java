package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JFrame;

import imgSize.BodySize;
import imgSize.WormSize;
import map.Background;
import monster.body;
import monster.Monster;
import monster.Worm;
import player.issac;
import sword.SwordControl;
import sword.swordattackcontrol;
import testimg.testcontorl;

//JFrame 참조 
public class miniApp extends JFrame {
	private JFrame app;

	private Background bg;
	private issac issac;
	private SwordControl swordControl;
	private testcontorl testcontorl;
	private Worm worm;
	private body body;
	private Vector<Monster> monsters;
	private swordattackcontrol swordattackcontrol;

	// miniApp에서 필요한 시스템 정보 가져옴
	public miniApp() {
		init();
		setting();
		batch();
		listener();
		playerattack();
		setVisible(true);
	}

	// 앱에서 필요한 데이터정보 가져옴
	public void init() {
		app = this;
		bg = new Background(app);
		monsters = new Vector<Monster>();
		issac = new issac(app, monsters);
		monsters.add(new Worm(app, issac, "monster/worm.png", WormSize.WIDTH, WormSize.HEIGHT));
		monsters.add(new body(app, issac, "monster/body.png", BodySize.WIDTH, BodySize.HEIGHT));
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
							monsters.get(i).setLife(monsters.get(i).getLife() - 1);
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
								monsters.get(i).setLife(monsters.get(i).getLife() - 1);
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
