package bitcamp.myapp;

import java.util.Scanner;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);

  static String promptString(String title) {
    System.out.println(title);
    return scanner.nextLine();
  }

  static int promptInt(String title) {
    return Integer.parseInt(promptString(title));
  }

  static void close() {
    scanner.close();
  }
}
