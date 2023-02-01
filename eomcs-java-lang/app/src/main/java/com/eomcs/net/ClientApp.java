package com.eomcs.net;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {

	public static void main(String[] args) throws Exception {
		Scanner keyScan = new Scanner(System.in);
		
		System.out.println("클라이언트 실행 중...");
		
		// 클라이언트 소켓 생성을 통한 서버접속
		Socket socket = new Socket("127.0.0.1", 8888);	// IP address, port
		System.out.println("서버에 연결되었음!");
		
		// 데이터 송수신을 위한 I/O 스트림 생성
		PrintStream out = new PrintStream(socket.getOutputStream());
		Scanner in = new Scanner(socket.getInputStream());
		
		while (true) {
			System.out.printf("입력> ");
			String message = keyScan.nextLine();
			out.println(message);
			
			
			if (message.equals("quit")) {
				break;
			}
			
			// System.out.println("서버에 메시지를 보냈음!");
			
			// 서버에서 응답이 올 때까지 리턴하지 않는다.
			String response = in.nextLine();
			System.out.printf("응답: %s\n", response);
		}
		
		// 통신 종료	
		out.close();
		in.close();
		socket.close();
		
		System.out.println("클라이언트 종료!");
		keyScan.close();
	}
}
