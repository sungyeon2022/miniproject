package wall;

import java.util.Vector;

import javax.swing.JFrame;

public class Centerwall {
	public Centerwall(JFrame app, Vector<wall> walls) {
		new Thread(new Runnable() {
			@Override
			
			public void run() {
				walls.add(new rock(app,455,100));
				walls.add(new rock(app,455,150));
				walls.add(new rock(app,455,200));
				walls.add(new rock(app,455,250));
				walls.add(new rock(app,455,300));
				walls.add(new rock(app,455,350));
				walls.add(new rock(app,455,400));
				walls.add(new rock(app,455,450));
				app.repaint();
				
					try {
						Thread.sleep(30000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				for (int i = 0; i<walls.size(); i++) {
					walls.get(i).setBroken(true);
					app.remove(walls.get(i).getSswall());
				}
				app.repaint();
			}
		}).start();
		
	}
}
