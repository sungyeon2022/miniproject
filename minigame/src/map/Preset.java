package map;

import java.util.Vector;

import main.miniApp;
import wall.*;

public class Preset {

	public Preset(miniApp app, Vector<structure> structures) {
		int r = 3;// (int) (Math.random() * 3) + 1;
		if (r == 1) {
			structures.add(new rock(app, 165, 160));
			structures.add(new rock(app, 215, 160));
			structures.add(new rock(app, 165, 210));
			structures.add(new spike(app, 215, 210));

			structures.add(new rock(app, 165, 345));
			structures.add(new rock(app, 165, 395));
			structures.add(new rock(app, 215, 395));
			structures.add(new spike(app, 215, 345));

			structures.add(new rock(app, 675, 160));
			structures.add(new rock(app, 725, 160));
			structures.add(new rock(app, 725, 210));
			structures.add(new spike(app, 675, 210));

			structures.add(new rock(app, 675, 395));
			structures.add(new rock(app, 725, 395));
			structures.add(new rock(app, 725, 345));
			structures.add(new spike(app, 675, 345));
		}

		else if (r == 2) {
			structures.add(new spike(app, 397, 225));
			structures.add(new spike(app, 447, 225));
			structures.add(new spike(app, 497, 225));
			structures.add(new spike(app, 397, 275));
			// 센터
			// structures.add(new spike(app, 447, 275));
			//
			structures.add(new spike(app, 497, 275));
			structures.add(new spike(app, 397, 325));
			structures.add(new spike(app, 447, 325));
			structures.add(new spike(app, 497, 325));

			structures.add(new rock(app, 347, 275));
			structures.add(new rock(app, 297, 275));
			structures.add(new rock(app, 247, 275));
			structures.add(new rock(app, 197, 275));

			structures.add(new rock(app, 547, 275));
			structures.add(new rock(app, 597, 275));
			structures.add(new rock(app, 647, 275));
			structures.add(new rock(app, 697, 275));

			structures.add(new rock(app, 397, 175));
			structures.add(new rock(app, 447, 175));
			structures.add(new rock(app, 497, 175));

			structures.add(new rock(app, 397, 375));
			structures.add(new rock(app, 447, 375));
			structures.add(new rock(app, 497, 375));

		}

		else if (r == 3) {
			structures.add(new spike(app, 447, 275));

			structures.add(new rock(app, 397, 225));
			structures.add(new rock(app, 347, 225));
			structures.add(new rock(app, 297, 225));
			structures.add(new rock(app, 247, 225));
			structures.add(new rock(app, 197, 225));

			structures.add(new rock(app, 697, 225));
			structures.add(new rock(app, 647, 225));
			structures.add(new rock(app, 597, 225));
			structures.add(new rock(app, 547, 225));
			structures.add(new rock(app, 497, 225));

			structures.add(new rock(app, 397, 325));
			structures.add(new rock(app, 347, 325));
			structures.add(new rock(app, 297, 325));
			structures.add(new rock(app, 247, 325));
			structures.add(new rock(app, 197, 325));

			structures.add(new rock(app, 697, 325));
			structures.add(new rock(app, 647, 325));
			structures.add(new rock(app, 597, 325));
			structures.add(new rock(app, 547, 325));
			structures.add(new rock(app, 497, 325));

			structures.add(new rock(app, 497, 375));
			structures.add(new rock(app, 397, 375));

			structures.add(new rock(app, 497, 175));
			structures.add(new rock(app, 397, 175));
		}

		else if (r == 4) {
			structures.add(new rock(app, 497, 175));
			structures.add(new rock(app, 547, 175));
			structures.add(new rock(app, 597, 175));
			structures.add(new rock(app, 647, 175));
			structures.add(new rock(app, 697, 175));

			structures.add(new rock(app, 397, 225));
			structures.add(new rock(app, 447, 225));
			structures.add(new rock(app, 497, 225));
			structures.add(new rock(app, 547, 225));
			structures.add(new rock(app, 597, 225));

			structures.add(new rock(app, 347, 275));
			structures.add(new rock(app, 397, 275));
			structures.add(new rock(app, 447, 275));
			structures.add(new rock(app, 497, 275));
			structures.add(new rock(app, 547, 275));

			structures.add(new rock(app, 297, 325));
			structures.add(new rock(app, 347, 325));
			structures.add(new rock(app, 397, 325));
			structures.add(new rock(app, 447, 325));
			structures.add(new rock(app, 497, 325));

			structures.add(new rock(app, 197, 375));
			structures.add(new rock(app, 247, 375));
			structures.add(new rock(app, 297, 375));
			structures.add(new rock(app, 347, 375));
			structures.add(new rock(app, 397, 375));

		}

		else if (r == 5) {
				
		}

		/*
		 * //왼쪽위 structures.add(new rock(app, 115, 110)); //오른쪽위 structures.add(new
		 * rock(app, 775, 110)); //왼쪽아래 structures.add(new rock(app,115,440)); //오른쪽아래
		 * structures.add(new rock(app,775,440));
		 */
	}

}
