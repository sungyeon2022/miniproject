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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import SpriteSheet.SpriteSheet;
import lombok.Data;
import player.issac;

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
			getSendMap().put("Client name", getName());
			SendDataThread();
			ReceiveDataThread();
		} catch (UnknownHostException e) { // 호스트 확인실패
			setIsconnect(false);
			System.out.println("서버 확인 실패");// 확인용
		} catch (IOException e) {// 정보 입출력 오류
			setIsconnect(false);
			System.out.println("서버 연결 실패");// 확인용
		}
	}
	@Override
	public void SendDataThread() {
		new Thread(()->	{
			while (true) {
				if (isIsconnect()) {
					try {
						getMyObjectOutputStream().writeObject(getSendMap());
						getMyObjectOutputStream().reset();
					} catch (IOException e) {
						System.out.println("서버 강제 종료");
						setIsconnect(false);
					}
				}else break;
			}
			
		}).start();

	}
	@Override
	public void ReceiveDataThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (isIsconnect()) {
						try {
							System.out.println("작동중");
							setReceiveObject(getMyObjectInputStream().readObject());
							System.out.println(((HashMap<String, Object>)getReceiveObject()).get("PlayerX"));
							System.out.println(((HashMap<String, Object>)getReceiveObject()).get("PlayerY"));
						} catch (IOException | ClassNotFoundException e) {
							System.out.println("서버 닫힘");
							setIsconnect(false);
						}
					} else {
						System.out.println("작동정지");
						break;
					}
				}

			}
		}).start();

	}
}
