package connect;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import lombok.Data;
import player.issac;

@Data

public class Connect {
	private Socket socket;
	private BufferedReader bufferedReader;
	private InputStream inputStream;
	private OutputStream outputStream;
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;
	private boolean isconnect;
	private String Name = "Player1";
	private int socketNum = 8050;
}
