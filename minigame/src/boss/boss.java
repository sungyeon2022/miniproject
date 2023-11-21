package boss;

import SpriteSheet.SpriteSheet;
import lombok.Data;
import main.miniApp;

@Data
public class boss {
	private miniApp app;
	private String Gubun;
	private SpriteSheet ssboss;
	private int xBoss;
	private int yBoss;
	private int bossHp;
	private int bossDamage;
	private int bossSpeed;
	private boolean dead = false;
	public boss(miniApp app) {
		this.app = app;
		
	}
}
