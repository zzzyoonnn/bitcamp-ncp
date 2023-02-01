package com.eomcs.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class ClientApp2 {

	public static void main(String[] args) throws Exception {
		
		// 클라이언트 소켓 생성을 통한 서버 접속
		Socket socket = new Socket("192.168.0.254", 8888);	// ip address, port
		System.out.println("서버에 연결되었음!");
		
		// 데이터 송수신을 위한 I/O 스트림 생성
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		DataInputStream in = new DataInputStream(socket.getInputStream());
		
		File file = new File("photo123.jpg");
		
		// 데이터 송신 (클라이언트 -> 서버)
		// 전송할 파일의 이름을 보낸다.
		out.writeUTF(file.getName());
		
		// 전송할 파일의 크기를 먼저 보낸다.
		out.writeLong(file.length());
		
		// 파일을 1바이트씩 읽어 보낸다.
		FileInputStream fileIn = new FileInputStream(file);
		int b;
		while ((b = fileIn.read()) != -1) {
			out.write(b);
		}
		fileIn.close();
		
		// 데이터 수신 (서버 -> 클라이언트)
		// 서버의 응답을 읽는다.
		System.out.println(in.readUTF());
		
		out.close();
		in.close();
		
		// 통신 종료
		socket.close();
		
		System.out.println("클라이언트 종료!");
	}
}
