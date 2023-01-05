package bitcamp.myapp;

public class App {

  public static void main(String[] args) {

    // 1단계
    System.out.println("1. 회원관리");
    System.out.println("9. 종료");




    // 2단계
    System.out.println("1. 등록");
    System.out.println("2. 목록");
    System.out.println("3. 조회");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 이전");






    MemberHandler.inputMembers();

    System.out.println();

    MemberHandler.printMembers();
  } // main()

} // class App









