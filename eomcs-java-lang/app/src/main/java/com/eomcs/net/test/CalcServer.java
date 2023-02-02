package com.eomcs.net.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServer {
  public static void main(String[] args) {

    // 서버소켓 생성
    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 실행 중...");

      // 클라이언트 접속 대기
	  try (Socket socket = serverSocket.accept();
	    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    PrintStream out = new PrintStream(socket.getOutputStream());) {

	    IntroMessage(out);

        // 클라이언트가 보낸 요청에 대한 응답
        // 클라이언트가 보낸 문자열 한 줄 일을 때까지 리턴 X
	    while (true) {
	      String request = in.readLine();
	      /////////////////////////////////////////////////
	      // 클라이언트로부터 quit 객체를 전달 받으면
	      // equal메서드를 통해 break되어 서버종료
	      if(request.equals("quit")) {
	        System.out.println("종료");
	        break;
	        }
	      /////////////////////////////////////////////////
	      String message = compute(request);
	      sendResult(out, message);	// 클라이언트에게 응답
	    }
	  }

	} catch (Exception e) {
	  e.printStackTrace();
	}
  }
	
  // 클라이언트로부터 입력받은 계산식 계산 수행
  static String compute(String request) {
    String[] values = request.split(" ");

    int a = Integer.parseInt(values[0]);
	String symbol = values[1];
	int b = Integer.parseInt(values[2]);
	int result = 0;

    switch (symbol) {
      case "+": result = a + b; break;
      case "-": result = a - b; break;
      case "*": result = a * b; break;
      case "/": result = a / b; break;
        default:
          return String.format("올바르지 않은 연산자입니다.");
    }
	return String.format("         = %d", result);
  }
	
  // 클라이언트로부터 입력받은 계산식 결과 전송
  static void sendResult(PrintStream out, String message) {
    out.println(message);
    out.println();
    out.flush();
  }
	
  // 시작 메시지 전송 코드 메서드 분리
  static void IntroMessage(PrintStream out) throws Exception {
    out.println("사칙연산 계산기(Client / Server)");
    out.println("            4팀 : 한대호, 신지윤");
    out.println("예)");
    out.println("   계산식 : 10 + 5");
    out.println("            = 15");
    out.println("----------------------------------");
    out.println(); // 응답의 끝을 표시하는 빈 줄 전송
    out.flush();
  }
}
