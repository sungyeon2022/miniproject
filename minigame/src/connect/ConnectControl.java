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
			SendData(getSendMap());
		} catch (UnknownHostException e) { // 호스트 확인실패
			setIsconnect(false);
			System.out.println("서버 확인 실패");// 확인용
		} catch (IOException e) {// 정보 입출력 오류
			setIsconnect(false);
			System.out.println("서버 연결 실패");// 확인용
		}
	}
	@Override
	public void SendData(Map<String, Object> sendMap) {
		if (isIsconnect()) {
			try {
				getMyObjectOutputStream().writeObject(sendMap);
				getMyObjectOutputStream().flush();
				System.out.println(getMyObjectOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
				setIsconnect(false);
			}
		}

	}
	@Override
	public void ReceiveData() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (isIsconnect()) {
						try {
							setReceiveObject(getMyObjectInputStream().readObject());
						} catch (IOException | ClassNotFoundException e) {
							System.out.println("서버 닫힘");
							setIsconnect(false);
						}
					} else
						break;
				}

			}
		}).start();

	}
}
