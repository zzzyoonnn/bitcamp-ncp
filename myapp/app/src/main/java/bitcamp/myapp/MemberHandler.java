package bitcamp.myapp;

public class MemberHandler {

  static final int SIZE = 100;
  static int count = 0;

  // 레퍼런스 배열 준비
  static Member[] members = new Member[SIZE];

  static void inputMember() {
    Member m = new Member();
    m.no = Prompt.inputInt("번호? ");
    m.name = Prompt.inputString("이름? ");
    m.tel = Prompt.inputString("전화? ");
    m.working = Prompt.inputInt("0. 미취업\n1. 재직중\n재직자? ") == 1;
    m.level = (byte) Prompt.inputInt("0. 비전공자\n1. 준전공자\n2. 전공자\n전공? ");
    members[count++] = m;


  }
  public static void service() {

    while (true) {
      System.out.println("1. 등록");
      System.out.println("2. 목록");
      System.out.println("3. 조회");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("6. 등록");
      int menuNo = Prompt.inputInt("회원관리");

      if (menuNo == 0) {
        break;
      } else if (menuNo == 1) {
        inputMember();
      } else if (menuNo == 2) {
        printMembers();
      } else {
        System.out.println("잘못 입력");
      }
    }

  }
  static void printMembers() {

    System.out.println("번호\t이름\t전화\t재직\t전공");

    for (int i = 0; i < count; i++) {
      Member m = members[i];

      String levelTitle;
      switch (m.level) {
        case 0: levelTitle = "비전공자"; break;
        case 1: levelTitle = "준전공자"; break;
        default: levelTitle = "전공자";
      }

      System.out.printf("%d\t%s\t%s\t%s\t%s\n",
          m.no,m.name,m.tel,
          m.working ? "예" : "아니오",levelTitle);
    }
  }
}
