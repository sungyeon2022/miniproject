package testimg;

import javax.swing.JFrame;

import lombok.Data;
import lombok.Lombok;

@Data
public class test extends Lombok{
	private JFrame app;
	private final static String GUBUN_STRING = "Test : ";
	private int Xtest;
	private int Ytest;
	private boolean buttonclick;
	
	public test(JFrame app) {
		this.app = app;
		this.buttonclick = false;
	}
}
