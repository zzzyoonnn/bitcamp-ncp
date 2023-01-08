package bitcamp.myapp;

import java.sql.Date;

public class BoardHandler {

  // 모든 인스턴스가 공유하는 데이터를 스태틱 필드로 만든다.
  // 특히 데이터를 조회하는 용으로 사용하는 final 변수는 스태틱 필드로 만들어야 한다.
  static final int SIZE = 100;
  int count;	// 스태틱 필드나 인스턴스 필드는 0으로 초기화됨
  static Bulletin[] Bulletins = new Bulletin[SIZE];
  String title;
  
  // 인스턴스를 만들 때 프롬포트 제목을 반드시 입력하도록 강제한다.
  BoardHandler(String title) {	// 생성자 생성
	  this.title = title;
  }

  void inputBulletin() {
    Bulletin b = new Bulletin();
    b.no = Prompt.inputInt("번호: ");
    b.title = Prompt.inputString("제목: ");
    b.content = Prompt.inputString("내용: ");
    b.hits = Prompt.inputString("조회수: ");
    b.createdDate = new Date(System.currentTimeMillis()).toString();	// 조회수

    this.Bulletins[count++] = b;
  }
  
  

  void printBulletins() {
    System.out.println("번호\t제목\t내용\t조회수\t작성일");

    for (int i = 0; i < this.count; i++) {
      Bulletin b = this.Bulletins[i];
      System.out.printf("%d\t%s\t%s\t%s\t%s\n",
        b.no, b.title, b.content, b.hits, b.createdDate);
    }
  }

  void printBulletin() {
    int BulletinNo = Prompt.inputInt("게시글 번호: ");

    Bulletin b = this.findByNo(BulletinNo);

    if (b == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    System.out.printf("  제목: %s\n", b.title);
    System.out.printf("  내용: %s\n", b.content);
    System.out.printf("조회수: %s\n", b.hits);
    System.out.printf("작성일: %s\n", b.createdDate);
  }

  // 인스턴스 멤버(필든 메서드)를 사용하지 않기 때문에
  // 그냥 스태틱 메서드로 두어라!

  void modifyBulletin() {
    int BulletinNo = Prompt.inputInt("게시글 번호: ");

    Bulletin old = this.findByNo(BulletinNo);

    if (old == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    // 변경할 데이터를 저장할 인스턴스 준비
    Bulletin b = new Bulletin();
    b.no = old.no;
    b.createdDate = old.createdDate;
    b.title = Prompt.inputString(String.format("제목(%s): ", old.title));
    b.content = Prompt.inputString(String.format("내용(%s): ", old.content));
    b.hits = Prompt.inputString(String.format("조회수(%s): ", old.hits));

    String str = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (str.equalsIgnoreCase("Y")) {
      this.Bulletins[this.indexOf(old)] = b;
      System.out.println("변경했습니다.");
    } else {
      System.out.println("변경 취소했습니다.");
    }

  }

  void deleteBulletin() {
    int BulletinNo = Prompt.inputInt("게시글 번호: ");

    Bulletin b = this.findByNo(BulletinNo);

    if (b == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String str = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!str.equalsIgnoreCase("Y")) {
      System.out.println("삭제 취소했습니다.");
      return;
    }

    for (int i = this.indexOf(b) + 1; i < this.count; i++) {
      this.Bulletins[i - 1] = this.Bulletins[i];
    }
    this.Bulletins[--this.count] = null; // 레퍼런스 카운트를 줄인다.

    System.out.println("삭제했습니다.");

  }

  Bulletin findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.Bulletins[i].no == no) {
        return this.Bulletins[i];
      }
    }
    return null;
  }

  int indexOf(Bulletin b) {
    for (int i = 0; i < this.count; i++) {
      if (this.Bulletins[i].no == b.no) {
        return i;
      }
    }
    return -1;
  }
  
  void searchBulletin() {
	String title = Prompt.inputString("이름? ");
	
	System.out.println("번호\t제목\t내용\t조회수\t작성일");

	for (int i = 0; i < this.count; i++) {
	  Bulletin b = this.Bulletins[i];
	  if (b.title.equalsIgnoreCase(title)) {
	      System.out.printf("%d\t%s\t%s\t%s\t%s\n",
	        b.no, b.title, b.content, b.hits, b.createdDate);
	    }
	  }
  }

  void service() {
    while (true) {
      System.out.printf("[%s]\n", this.title);
      System.out.println("1. 입력");
      System.out.println("2. 목록");
      System.out.println("3. 조회");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전");
      int menuNo = Prompt.inputInt(String.format("%s> ", this.title));

      switch (menuNo) {
        case 0: return;
        case 1: this.inputBulletin(); break;
        case 2: this.printBulletins(); break;
        case 3: this.printBulletin(); break;
        case 4: this.modifyBulletin(); break;
        case 5: this.deleteBulletin(); break;
        default:
          System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }
}
