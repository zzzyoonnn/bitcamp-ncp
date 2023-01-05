package bitcamp.myapp;

public class App {

  public static void main(String[] args) {

    goMenu();

    Prompt.close();


  } // main()
  public static void goMenu() {
    while (true) {
      System.out.println("1.회원관리");
      System.out.println("9. 종료");

      int menuNo = Prompt.inputInt("메뉴>");

      if (menuNo == 1) {
        MemberHandler.service();
      } else if (menuNo == 9 ) {
        break;
      } else {
        System.out.println("잘못입력");
      }
    }

  }



} // class App









