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
		issac = new issac(app,walls,items);
		//아이템 생성 클래스 
		new Createitem(app,items,550,200);
		new Createitem(app,items,550,300);
		new Createitem(app,items,550,400);
		new Createitem(app,items,550,500);
		new Createitem(app,items,550,600);
		
		//중앙 벽
		new Centerwall(app,walls);
		
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
