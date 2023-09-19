package connect;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import lombok.Data;

@Data

public class ConnectControl extends Connect {

	public void connect() {
		try {	
			setSocket(new Socket("localhost", getSocketNum()));// 소켓 정보 초기화
			System.out.println("서버 연결 성공");// 확인용
			System.out.println("게임 시작");
			setIsconnect(true);

			setInputStream(getSocket().getInputStream());
			setOutputStream(getSocket().getOutputStream());
			
			setDataInputStream(new DataInputStream(getInputStream()));
			setDataOutputStream(new DataOutputStream(getOutputStream()));
			
			PrintStream printStream = new PrintStream(getOutputStream());
			printStream.println(getName() + "접속");
			printStream.flush();

		} catch (UnknownHostException e) { // 호스트 확인실패
			e.printStackTrace();
			System.out.println("서버 연결 실패");// 확인용
		} catch (IOException e) {// 정보 입출력 오류
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("데이터 전송 실패");// 확인용
		}
	}

	public void DataSend(int index) {
		try {
			getDataOutputStream().write(index);
			getDataOutputStream().flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int DataReceive() {
		int index = 0;
		try {
		 	index =  getDataInputStream().readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return index;
	}
}
