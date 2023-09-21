package connect;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import lombok.Data;

@Data

public class ConnectControl extends Connect {
	public ConnectControl() {
		connect();
		

	}
	
	public void connect() {
		try {
			setSocket(new Socket("localhost", getSocketNum()));// 소켓 정보 초기화
			System.out.println("서버 연결 성공");// 확인용
			setIsconnect(true);
			System.out.println("게임 시작");
			

			setMyInputStream(getSocket().getInputStream());
			setMyOutputStream(getSocket().getOutputStream());
			
			setMyObjectInputStream(new ObjectInputStream(getMyInputStream()));
			setMyObjectOutputStream(new ObjectOutputStream(getMyOutputStream()));
			
			SendString(getName());
		} catch (UnknownHostException e) { // 호스트 확인실패
			System.out.println("서버 연결 실패");// 확인용
			e.printStackTrace();
		} catch (IOException e) {// 정보 입출력 오류
			System.out.println("데이터 전송 실패");// 확인용
			e.printStackTrace();
		}
	}

	public void SendString(Object string) {
		if (isIsconnect()) {
			try {
				getMyObjectOutputStream().writeObject(string);
				getMyObjectOutputStream().flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void SendData(Object doubleArr) {
		if (isIsconnect()) {
			try {
				getMyObjectOutputStream().writeObject(doubleArr);
				getMyObjectOutputStream().flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void SendKey(Object key) {
		if (isIsconnect()) {
			try {
				getMyObjectOutputStream().writeObject(key);
				getMyObjectOutputStream().flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Object StringReceive() {
		Object index = null;
		if (isIsconnect()) {
			try {
				index = getMyObjectInputStream().readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return index;
	}
	public Object DataReceive() {
		Object index = null;
		if (isIsconnect()) {
			try {
				index = getMyObjectInputStream().readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return index;
	}
	public Object KeyReceive() {
		Object index = null;
		if (isIsconnect()) {
			try {
				index = getMyObjectInputStream().readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return index;
	}
}
