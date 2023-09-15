package wall;

import javax.swing.JFrame;

import lombok.Data;

@Data
public class rock extends wall{
	public rock(JFrame app, int xwall, int ywall) {
		super(app, "structure/rock.png", "rock", xwall, ywall);
		drawwall();
	}
	@Override
	public void drawwall() {
		getSswall().drawObj(getXwall(), getYwall());
		getApp().add(getSswall());
	}
}
