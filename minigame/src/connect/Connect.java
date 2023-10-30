package connect;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

import data.DataClass;
import lombok.Data;
import lombok.Lombok;
import player.issac;

@Data

public class Connect extends Lombok {
	private Socket socket;
	private InputStream myInputStream;
	private OutputStream myOutputStream;
//	private DataOutputStream dataOutputStream;
//	private DataInputStream dataInputStream;
	ObjectOutputStream myObjectOutputStream;
	ObjectInputStream myObjectInputStream;
	private boolean isconnect;
	private String Name;
	private Object receiveObject;
	private int socketNum;
	private DataClass sendDataClass;
	private DataClass reciveDataClass;
	private boolean isMulti;
	public Connect() {
		this.socket = null;
		this.myInputStream = null;
		this.myOutputStream = null;
		this.myObjectInputStream = null;
		this.myObjectOutputStream = null;
		this.Name = "Beta1";
		this.socketNum = 8050;
	}
	public void SendData() {}
	public void ReceiveDataThread() {}
}
