package test;

import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Data;
import lombok.Lombok;
import main.miniApp;

@Data

public class Test extends JLabel{
	private miniApp app;
	private int Xtest;
	private int Ytest;
	public Test(miniApp app){
		this.app = app;
	}
}
