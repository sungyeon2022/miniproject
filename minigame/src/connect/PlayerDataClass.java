package connect;

import java.io.Serializable;

import lombok.Data;

@Data

class PlayerDataClass implements Serializable {
	private String name;
	private int xPlayer;
	private int yPlayer;
	private int viewDirect;
	private boolean isRight;
	private boolean isDown;
	private boolean isUp;
	private boolean isLeft;
	private int isKeyPress;
	private String timer = null;

	public PlayerDataClass(String name, int xPlayer, int yPlayer, int viewDirect, boolean isRight, boolean isDown, boolean isUp, 
			boolean isLeft, int isKeyPress) {
		this.name = name;
		this.xPlayer = xPlayer;
		this.yPlayer = yPlayer;
		this.viewDirect = viewDirect;
		this.isRight = isRight;
		this.isLeft = isLeft;
		this.isDown = isDown;
		this.isUp = isUp;
		this.isKeyPress = isKeyPress;
	}
	
	@Override
	public String toString() {
		String view = "isRight";
		if (isRight()) view = "isRight";
		else if (isLeft()) view = "isLeft";
		else if (isUp()) view = "isUp";
		else if (isDown()) view = "isDown";
		return "Name : " + getName() + " xPlayer : " + getXPlayer() + " yPlayer : " + getYPlayer() + "videDirect : "
				+ getViewDirect() + " isView : " + view+" isKeyPress : " + getIsKeyPress()
				+ " Timer : " +  getTimer();
	}
}
