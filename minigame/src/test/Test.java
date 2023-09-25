package test;

import javax.swing.JFrame;

import lombok.Data;
import lombok.Lombok;

@Data

public class Test extends Lombok{
	private JFrame app;
	private int Xtest;
	private int Ytest;
	public Test(JFrame app){
		this.app = app;
	}
}
