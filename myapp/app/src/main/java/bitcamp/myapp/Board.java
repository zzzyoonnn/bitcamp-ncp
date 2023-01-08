package bitcamp.myapp;

public class Board {

  public static void main(String[] args) {
    goBoard();
    System.out.println("안녕히 가세요!");

    // 프로그램이 사용한 자원 해제하기
    Prompt.close();
  } // main()

  private static void goBoard() {
	  
	// 게시글 목록을 저장할 메모리를 준비한다.
	BoardHandler generalBoardHandler = new BoardHandler("게시글");
	
	  
    while (true) {
      System.out.println("1. 게시글 관리");
      System.out.println("9. 종료");
      int menuNo = Prompt.inputInt("메뉴> ");

      if (menuNo == 1) {
    	  generalBoardHandler.service();
        break;
      } else {
        System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }

} // class App









