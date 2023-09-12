package sword;

import java.util.Vector;

import lombok.Data;
import monster.Monster;

@Data

public class swordattackcontrol {
	private Vector<Monster> monsters;
	private SwordControl swordControl;
	private Sword sword;
	
}
