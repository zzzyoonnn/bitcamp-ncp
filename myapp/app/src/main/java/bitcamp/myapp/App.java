package bitcamp.myapp;

public class App {

  public static void main(String[] args) {
    goMainMenu();
    System.out.println("안녕히 가세요!");

<<<<<<< HEAD
    // 프로그램이 사용한 자원 해제하기
    Prompt.close();
=======
    goMenu();

    Prompt.close();


>>>>>>> 6e666e9a723460470ddce1c3b064936ea6251b7b
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



  private static void goMainMenu() {
    while (true) {
      System.out.println("1. 일반학생관리");
      System.out.println("2. 국비지원학생관리");
      System.out.println("3. 위탁교육생관리");
      
      System.out.println("9. 종료");
      int menuNo = Prompt.inputInt("메뉴> ");

      if (menuNo == 1) {
    	  MemberHandler.title = "일반학생";
        MemberHandler.service();
      } else if (menuNo ==2) {
    	  Member2Handler.title = "국비지원학생";
    	  Member2Handler.service();
      } else if (menuNo ==3) {
    	  Member3Handler.title = "위탁교육생";
    	  Member3Handler.service();
      } else if (menuNo == 9) {
        break;
      } else {
        System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }

} // class App









