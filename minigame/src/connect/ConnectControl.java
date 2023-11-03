package connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

import data.DataClass;
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
			setSendDataClass(new DataClass());
			setMyInputStream(getSocket().getInputStream());
			setMyOutputStream(getSocket().getOutputStream());
			setMyObjectInputStream(new ObjectInputStream(getMyInputStream()));
			setMyObjectOutputStream(new ObjectOutputStream(getMyOutputStream()));
			getSendDataClass().setClientName(getName());
			getMyObjectOutputStream().writeObject(getSendDataClass());
			getMyObjectOutputStream().reset();
			if (isIsconnect()) {
				ReceiveDataThread();
			}
		} catch (UnknownHostException e) { // 호스트 확인실패
			setIsconnect(false);
			System.out.println("서버 확인 실패");// 확인용
		} catch (IOException e) {// 정보 입출력 오류
			setIsconnect(false);
			System.out.println("서버 연결 실패");// 확인용
		}
	}

	@Override
	public synchronized void SendData() {
		try {
			if (isIsconnect()&&isMulti()) {
				getMyObjectOutputStream().writeObject(getSendDataClass());
				getMyObjectOutputStream().reset();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("데이터 출력 오류");
			setIsconnect(false);
		}
	}

	@Override
	public void ReceiveDataThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isIsconnect()) {
					try {
						setReciveDataClass((DataClass) getMyObjectInputStream().readObject());
						setReciveMulti(getReciveDataClass().isMulti()); 
						setStart(getReciveDataClass().isStart());
					} catch (Exception e) {
						System.out.println("데이터 입력 오류");
						setIsconnect(false);
					}
				}
			}
		}).start();

	}
}
