package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JFrame;

import item.Createitem;
import item.Item;
import map.Background;
import map.Timer;
import player.issac;
import wall.Centerwall;
import wall.wall;


//JFrame 참조 
public class miniApp extends JFrame {
	private JFrame app;

	private Background bg;
	private Timer timer;
	private issac issac;
	private Vector<Item> items;
	private Vector<wall> walls;

	
	// miniApp에서 필요한 시스템 정보 가져옴
	public miniApp() {
		init();
		setting();
		batch();
		listener();
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
		//타이머
		timer = new Timer(app);
		items = new Vector<Item>();
		walls = new Vector<wall>();
<<<<<<< HEAD
		issac = new issac(app,walls,items);
		//아이템 생성 클래스 
		new Createitem(app,items,550,200);
		new Createitem(app,items,550,300);
		new Createitem(app,items,550,400);
		new Createitem(app,items,550,500);
		new Createitem(app,items,550,600);
		
		//중앙 벽
		new Centerwall(app,walls);
		
=======
		issac = new issac(app,monsters,walls,items);
		monsters.add(new Worm(app, issac, "monster/worm.png", WormSize.WIDTH, WormSize.HEIGHT));
		//아이템 시험 생성
		items.add(new Bomb(app, "item/bomb.png", "bomb", 140, 400, BombSize.PICKWIDTH, BombSize.PICKHEIGHT));
		items.add(new Heart(app, "item/recoveryLife.png", "heart", 240, 400, HeartSize.WIDTH,HeartSize.HEIGHT));
		//랜덤 아이템 생성
		int witem = (int)(Math.random()*4);
		switch(witem) {
		case 0:
			items.add(new Pill(app, "item/PowerUp.png", "Power", 440, 400, PillSize.WIDTH,PillSize.HEIGHT));
			break;
		case 1:
			items.add(new Pill(app, "item/ASUp.png", "AttackSpeed", 490, 400, PillSize.WIDTH,PillSize.HEIGHT));
			break;
		case 2:
			items.add(new Pill(app, "item/SpeedUp.png", "Speed", 540, 400, PillSize.WIDTH,PillSize.HEIGHT));
			break;
		case 3:
			items.add(new Pill(app, "item/FullHp.png", "FullHp", 440, 300, PillSize.WIDTH,PillSize.HEIGHT));
			break;		
		}
		
		//벽 시험 생성 및 10초후 제거
		new Thread(new Runnable() {
			@Override
			
			public void run() {
				walls.add(new rock(app,455,100));
				walls.add(new rock(app,455,150));
				walls.add(new rock(app,455,200));
				walls.add(new rock(app,455,250));
				walls.add(new rock(app,455,300));
				walls.add(new rock(app,455,350));
				//walls.add(new rock(app,455,400));
				//walls.add(new rock(app,455,450));
				repaint();
				for( int i = 0; i<30; i++) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				}
				for (int i = 0; i<walls.size(); i++) {
					walls.get(i).setBroken(true);
					remove(walls.get(i).getSswall());
				}
				repaint();
			}
			
			
		}).start();

>>>>>>> parent of 9760467 (ErrorFix)
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
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					issac.moveLeft();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					issac.moveDown();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					issac.moveUp();
				} else if (e.getKeyCode() == KeyEvent.VK_W) {

				} else if (e.getKeyCode() == KeyEvent.VK_D) {

				} else if (e.getKeyCode() == KeyEvent.VK_A) {
<<<<<<< HEAD

=======
					issac.attackMotion();
					for (int i = 0; i < monsters.size(); i++) {
						if (issac.getSwordControl().getSsSword().getBounds()
								.intersects(monsters.get(i).getSsMonster().getBounds())) {
							System.out.println("공격시도");
							monsters.get(i).setLife(monsters.get(i).getLife() - 1);
						}
					}
>>>>>>> parent of 9760467 (ErrorFix)
				} else if (e.getKeyCode() == KeyEvent.VK_S) {

				} else if (e.getKeyCode() == KeyEvent.VK_E) {

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					issac.setRight(false);
					issac.refreshDirect();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					issac.setLeft(false);
					issac.refreshDirect();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					issac.setDown(false);
					issac.refreshDirect();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					issac.setUp(false);
					issac.refreshDirect();
				}

			}
		});
	}
<<<<<<< HEAD

=======
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
>>>>>>> parent of 9760467 (ErrorFix)
}
