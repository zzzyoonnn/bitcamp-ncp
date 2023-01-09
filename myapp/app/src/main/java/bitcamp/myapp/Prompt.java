package bitcamp.myapp;

import java.util.Scanner;

public class Prompt {
  public Scanner scanner = new Scanner(System.in);

  public String inputString(String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  public int inputInt(String title) {
    return Integer.parseInt(inputString(title));
  }

  // Prompt 클래스를 다 사용한 후에 자원을 해제시킬 수 있는 메서드를 추가한다.
  private void close() {
    scanner.close();
  }
}
