package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Vector;


import javax.swing.JFrame;

import map.Background;

//JFrame 참조 
public class miniApp extends JFrame {
	private JFrame app;
	
	private Background bg;
	
	//miniApp에서 필요한 시스템 정보 가져옴
	public miniApp(){
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
	
	//앱에서 필요한 데이터정보 가져옴
	public void init() {
		app=this;
		bg = new Background(app);
		
	}
	
	//JFrame을 통한 창출력
	public void setting() {
		app.setTitle("miniApp");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(960,640);
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
		public void KeyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_W) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_D) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_A) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_E) {
				
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_W) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_D) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_A) {
				
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				
			}
		}
		});
	}

}

