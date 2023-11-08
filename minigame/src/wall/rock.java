package wall;

import javax.swing.JFrame;

import lombok.Data;
import main.miniApp;

@Data
public class rock extends wall{
	public rock(miniApp app, int xwall, int ywall) {
		super(app, "structure/rock.png", "rock", xwall, ywall);
		drawwall();
	}
	@Override
	public void drawwall() {
		getSswall().drawObj(getXwall(), getYwall());
		getApp().add(getSswall());
	}
}
