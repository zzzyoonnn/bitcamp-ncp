package bitcamp.myapp;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {

    // 키보드에서 입력을 받는 도구 준비
    // Scanner 옆에 커서를 두고 ctrl + space 또는 ctrl + shift + O
    Scanner keyScanner = new java.util.Scanner(System.in);

    System.out.print("번호?");
    int no = Integer.parseInt(keyScanner.nextLine());

    System.out.print("이름?");
    String name = keyScanner.nextLine();

    System.out.print("전화?");
    String tel = keyScanner.nextLine();

    System.out.print("우편번호?");
    String postNo = keyScanner.nextLine();  // int일 경우 앞 0 출력안됨   // 자바는 낙타 표기법 사용

    System.out.print("주소1?");
    String basicAddress = keyScanner.nextLine();

    System.out.print("주소2?");
    String detailAddress = keyScanner.nextLine();

    System.out.print("재직자?(true/false)");
    boolean working = Boolean.parseBoolean(keyScanner.nextLine());

    System.out.print("성별?(남자: M, 여자: W");
    char gender = keyScanner.nextLine().charAt(0);   // M(남자), W(여자)

    System.out.println("전공?(0. 비전공자)");
    System.out.println("전공?(1. 준전공자)");
    System.out.println("전공?(2. 전공자)");
    System.out.print("전공?");
    byte level = Byte.parseByte(keyScanner.nextLine()); // 0(비전공자), 1(준전공자), 2(전공자)

    System.out.print("가입일?(예: 2022-12-29)");
    String createdDate = keyScanner.nextLine();

    System.out.printf("번호 : %d\n", no); // system이라는 도구함에 out이란 변수에는 콘솔에 대한 정보가 저장되어있다 // 콘솔 정보를 가지고 println 수행
    System.out.printf("이름 : %s\n", name);
    System.out.printf("전화번호 : %s\n", tel);
    System.out.printf("우편 번호 : %s\n", postNo);
    System.out.printf("주소1 : %s\n", basicAddress);
    System.out.printf("주소2 : %s\n", detailAddress);
    System.out.printf("재직자 : %s\n",working ? "예" : "아니오");
    System.out.printf("성별 : %s\n", gender=='M' ? "남자" : "여자");

    String levelTitle;
    switch (level) {
      case 0: levelTitle = "비전공자"; break;
      case 1: levelTitle = "준전공자"; break;
      default: levelTitle = "전공자";
    }
    System.out.printf("전공 : %s\n", levelTitle);
    System.out.printf("가입일 : %s\n", createdDate);
  }
}
