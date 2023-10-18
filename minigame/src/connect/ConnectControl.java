package connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

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
		new Thread(() -> {
			while (!Thread.interrupted()) {
				if (isIsconnect()) {
					try {
						if (isMulti()) {
							getMyObjectOutputStream().writeObject(getSendDataClass());
							getMyObjectOutputStream().reset();
						}
					} catch (IOException e) {
						System.out.println("서버 강제 종료");
						setIsconnect(false);
					}
				} else
					break;
			}
		}).start();

	}

	@Override
	public void ReceiveDataThread() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!Thread.interrupted()) {
					if (isIsconnect()) {
						try {
							if (isMulti()) {
								setReciveDataClass((DataClass) getMyObjectInputStream().readObject());
								setStart(getReciveDataClass().isStart());
								setReady(getReciveDataClass().isReady());
							}
						} catch (IOException | ClassNotFoundException e) {
							System.out.println("서버 닫힘");
							setIsconnect(false);
						}
					}
				}

			}
		}).start();

	}
}
