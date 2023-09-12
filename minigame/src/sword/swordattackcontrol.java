package sword;

import java.util.Vector;

import imgSize.SwordSize;
import lombok.Data;
import monster.Monster;
import player.issac;

@Data

public class swordattackcontrol implements Runnable {
	private Vector<Monster> monsters;
	private SwordControl swordControl;
	private Sword sword;
	private issac issac;
	
	public void run(Vector<Monster> monsters) {
		monsters = this.monsters;
		for (int i = 0; i < monsters.size(); i++) {
			double swordXcenter = swordControl.getSsSword().getX() - (swordControl.getSsSword().getWidth() / 2);
			double swordYcenter = swordControl.getSsSword().getY() - (swordControl.getSsSword().getHeight() / 2);
			double monsterXcenter = monsters.get(i).getSsMonster().getX()
					+ (monsters.get(i).getSsMonster().getWidth() / 2);
			double monsterYcenter = monsters.get(i).getSsMonster().getY()
					+ (monsters.get(i).getSsMonster().getHeight() / 2);
			double swordhalfdiagnoal = Math
					.sqrt(Math.pow(SwordSize.SWORDWIDTH / 2, 2) + Math.pow(SwordSize.SWORDYHEIGHT / 2, 2));
			double monsterhalfdiagnoal = Math.sqrt(Math.pow(monsters.get(i).getSsMonster().getWidth() / 2, 2)
					+ Math.pow(monsters.get(i).getSsMonster().getHeight() / 2, 2));
			double distance = Math
					.sqrt(Math.pow(swordXcenter - monsterYcenter, 2) + Math.pow(swordYcenter - monsterYcenter, 2));
			if (issac.isPlayerAttacking()) {

			} else {
				if (swordhalfdiagnoal + monsterhalfdiagnoal >= distance) {
					monsters.get(i).setLife(monsters.get(i).getLife() - 1);
					System.out.println(monsterhalfdiagnoal + " " + swordhalfdiagnoal + " " + distance + " "
							+ monsters.get(i).getSsMonster().getWidth());
				}
				try {
					Thread.sleep(30);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
