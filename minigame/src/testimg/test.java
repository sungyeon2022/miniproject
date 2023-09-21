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
	
	public test(JFrame app) {
		this.app = app;
	}
}
