package com.eomcs.net.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class CalcClient {
  public static void main(String[] args) {

    try (Scanner keyboardScanner = new Scanner(System.in);
      // 클라이언트 소켓 생성을 통한 서버접속
      Socket socket = new Socket("127.0.0.1", 8888);

      // 데이터 송수신을 위한 I/O 스트림 생성
      PrintStream out = new PrintStream(socket.getOutputStream());
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

      // 계산식 입력받을 때 출력할 메세지
      ableInput(in);
			
      // 계산식 입력 받아서 서버에 전달
      while (true) {
        String input = prompt(keyboardScanner);
        /////////////////////////////////////////////////
        if (input == null) {
          continue;
          // 만약 사용자가 입력한 값이 quit라면 else if문이 실행되고
          // 서버로 quit값을 전달하고 클라이언트 서버종료
        } else if(input.equals("quit")) {
          sendCalculation(out, input); // 서버에 요청을 보내기
          System.out.println("종료");
          break;
        }
        /////////////////////////////////////////////////
        sendCalculation(out, input);	// 서버에 계산식 전송
        ableInput(in);	// 서버로부터 계산식 받고 다시 계산식 전달
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static String prompt(Scanner keyboardScanner) {
    System.out.print("계산식 : ");
    String input = keyboardScanner.nextLine();

    /////////////////////////////////////////////////
    if(input.equals("quit")) {
      return "quit";
      // 입력 형식에 맞게 입력했는지 확인
      } else  if (input.split(" ").length != 3) {
        System.out.println("입력 형식이 올바르지 않습니다.");
    /////////////////////////////////////////////////
        return null;
      }
    return input;
  }
	
  static void sendCalculation(PrintStream out, String message) throws Exception {
    out.println(message);
    out.flush();
  }

  // 서버로부터 응답 받을 메세지 (계산식을 입력받아야 할 때 출력됨)
  // -> 입력이 가능한 상태
  static void ableInput(BufferedReader in) throws Exception {
    while (true) {
      String input = in.readLine();
      if (input.length() == 0) {
        break;
      }
      System.out.println(input);
    }
  }
}
