package com.eomcs.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
// 서버 프로그램 기본
public class ServerApp2 {

	public static void main(String[] args) throws Exception{
		// 서버 소켓 생성
		ServerSocket serverSocket = new ServerSocket(8888);	// 포트 번호
		
		// 클라이언트 접속 대기
		Socket socket = serverSocket.accept();
		System.out.println("클라이언트와 연결됨!");
		
		// 데이터 송수신을 위한 I/O 스트림 생성
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		
		// 클라이언트가 보낸 파일의 이름을 읽는다.
		String filename = in.readUTF();
		
		// 클라이언트가 보낸 파일의 크기를 먼저 읽는다.
		long length = in.readLong();
		
		// Output 스트림을 통한 데이터 송신
		// 클라이언트가 보낸 바이트를 사진 바이트를 파일로 출력한다.
		FileOutputStream fileOut = new FileOutputStream(filename);
	
		for (long i = 0; i < length; i++) {
			fileOut.write(in.read());
		}
		fileOut.close();
		
		// 클라이언트에게 응답한다.
		out.writeUTF("완료!");
		
		in.close();
		out.close();
		
		// 통신 종료
		socket.close();
		serverSocket.close();
		
		System.out.println("서버 종료!");
	}

}
