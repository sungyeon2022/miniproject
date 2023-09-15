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

	@Override
	public void run() {
		System.out.println("작동중");
		
	}
}
