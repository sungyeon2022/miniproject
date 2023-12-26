package connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

import data.DataClass;
import lombok.Data;
import main.miniApp;

import javax.swing.*;

public class ConnectControl extends Connect {
    public ConnectControl(miniApp app) {
        super(app);
        connect();
    }

    public void connect() {
        try {
            setSocket(new Socket("192.168.8.49", getSocketNum()));// 소켓 정보 초기화
            System.out.println("서버 연결 성공");// 확인용
            setIsconnect(true);
            System.out.println("게임 시작");
            setSendDataClass(getApp().getPrimaryDataClass());
            setMyInputStream(getSocket().getInputStream());
            setMyOutputStream(getSocket().getOutputStream());
            setMyObjectInputStream(new ObjectInputStream(getMyInputStream()));
            setMyObjectOutputStream(new ObjectOutputStream(getMyOutputStream()));
            getSendDataClass().setClientName(getName());
            getMyObjectOutputStream().writeObject(getSendDataClass());
            getMyObjectOutputStream().reset();
            setReciveDataClass((DataClass) getMyObjectInputStream().readObject());
            if(!getReciveDataClass().isLogin_success()){
                JOptionPane.showMessageDialog(null,"로그인에 실패하였습니다.");
                System.exit(1);
            }
            System.out.println(getReciveDataClass());
            if (isIsconnect()) {
                ReceiveDataThread();
            }
        } catch (UnknownHostException e) { // 호스트 확인실패
            setIsconnect(false);
            System.out.println("서버 확인 실패");// 확인용
            JOptionPane.showMessageDialog(null,"서버가 불안정합니다.");
        } catch (IOException e) {// 정보 입출력 오류
            setIsconnect(false);
            System.out.println("서버 연결 실패");// 확인용
            JOptionPane.showMessageDialog(null,"서버가 불안정합니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("데이터 에러");
        }
    }

    @Override
    public synchronized void SendData() {
        try {
            getMyObjectOutputStream().writeObject(getSendDataClass());
            getMyObjectOutputStream().reset();
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
                try {
                    setReciveDataClass((DataClass) getMyObjectInputStream().readObject());
                    System.out.println(getReciveDataClass().toString());
                    setReciveMulti(getReciveDataClass().isMulti());
                    setStart(getReciveDataClass().isStart());
                } catch (Exception e) {
                    System.out.println("데이터 입력 오류");
                    setIsconnect(false);
                }

            }
        }).start();

    }
}
