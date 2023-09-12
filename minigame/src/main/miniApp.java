package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JFrame;

import item.Bomb;
import item.Heart;
import item.Item;
import item.Pill;
import map.Background;
import objectSetting.BombSize;
import objectSetting.HeartSize;
import objectSetting.KeySize;
import objectSetting.PillSize;
import player.issac;
import wall.rock;
import wall.wall;

//JFrame 참조 
public class miniApp extends JFrame {
	private JFrame app;

	private Background bg;
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
		
		items = new Vector<Item>();
		walls = new Vector<wall>();
		issac = new issac(app,walls);
		//아이템 시험 생성
		items.add(new Bomb(app, "item/bomb.png", "bomb", 140, 400, BombSize.PICKWIDTH, BombSize.PICKHEIGHT));
		items.add(new Heart(app, "item/recoveryLife.png", "heart", 240, 400, HeartSize.WIDTH,HeartSize.HEIGHT));
		items.add(new Pill(app, "item/pill1.png", "Power2", 440, 400, PillSize.WIDTH,PillSize.HEIGHT));
		items.add(new Pill(app, "item/pill2.png", "PS1", 490, 400, PillSize.WIDTH,PillSize.HEIGHT));
		items.add(new Pill(app, "item/pill3.png", "Speed2", 540, 400, PillSize.WIDTH,PillSize.HEIGHT));
		//벽 시험 생성 및 10초후 제거
		new Thread(new Runnable() {
			@Override
			
			public void run() {
				//walls.add(new rock(app,455,100));
				//walls.add(new rock(app,455,150));
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

}
