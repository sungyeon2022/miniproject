package test;

import javax.swing.JFrame;
import javax.swing.JLabel;

import SpriteSheet.SpriteSheet;
import lombok.Data;
import lombok.Lombok;
import main.miniApp;

@Data

public class Test{
	private miniApp app;
	private int Xtest;
	private int Ytest;
	private SpriteSheet sstest;
	public Test(miniApp app){
		this.app = app;
	}
}
