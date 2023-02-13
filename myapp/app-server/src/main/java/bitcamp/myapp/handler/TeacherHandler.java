package bitcamp.myapp.handler;

import java.sql.Connection;
import java.util.List;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.TeacherDao;
import bitcamp.myapp.vo.Teacher;
import bitcamp.util.StreamTool;

public class TeacherHandler {

  private Connection con;
  private MemberDao memberDao;
  private TeacherDao teacherDao;
  private String title;

  public TeacherHandler(String title, Connection con, MemberDao memberDao, TeacherDao teacherDao) {
    this.title = title;
    this.con = con;
    this.memberDao = memberDao;
    this.teacherDao = teacherDao;
  }

  private void inputTeacher(StreamTool streamTool) throws Exception {
    Teacher t = new Teacher();
    t.setName(streamTool.promptString("이름? "));
    t.setTel(streamTool.promptString("전화? "));
    t.setEmail(streamTool.promptString("이메일? "));
    t.setDegree(streamTool.promptInt("1. 고졸\n2. 전문학사\n3. 학사\n4. 석사\n5. 박사\n0. 기타\n학위? "));
    t.setSchool(streamTool.promptString("학교? "));
    t.setMajor(streamTool.promptString("전공? "));
    t.setWage(streamTool.promptInt("강의료(시급)? "));

    con.setAutoCommit(false);
    try {
      memberDao.insert(t);
      teacherDao.insert(t);
      con.commit();
      streamTool.println("입력했습니다!").send();
    } catch (Exception e) {
      con.rollback();
      streamTool.println("입력 실패입니다!").send();
      e.printStackTrace();
    } finally {
      con.setAutoCommit(true);
    }
  }

  private void printTeachers(StreamTool streamTool) throws Exception {
    List<Teacher> members = this.teacherDao.findAll();
    streamTool.println("번호\t이름\t전화\t학위\t전공\t시강료");
    for (Teacher t : members) {
      streamTool.printf("%d\t%s\t%s\t%s\t%s\t%d\n",
          t.getNo(), t.getName(), t.getTel(),
          getDegreeText(t.getDegree()), t.getMajor(), t.getWage());
    }
    streamTool.send();
  }

  private void printTeacher(StreamTool streamTool) throws Exception {
    int memberNo = streamTool.promptInt("강사번호? ");

    Teacher t = this.teacherDao.findByNo(memberNo);

    if (t == null) {
      streamTool.println("해당 번호의 강사가 없습니다.").send();
      return;
    }

    streamTool.printf("    이름: %s\n", t.getName())
    .printf("    전화: %s\n", t.getTel())
    .printf("  이메일: %s\n", t.getEmail())
    .printf("    학위: %s\n", getDegreeText(t.getDegree()))
    .printf("    학교: %s\n", t.getSchool())
    .printf("    전공: %s\n", t.getMajor())
    .printf("  강의료: %s\n", t.getWage())
    .printf("  등록일: %s\n", t.getCreatedDate())
    .send();
  }

  private static String getDegreeText(int degree) {
    switch (degree) {
      case 1: return "고졸";
      case 2: return "전문학사";
      case 3: return "학사";
      case 4: return "석사";
      case 5: return "박사";
      default: return "기타";
    }
  }

  private void modifyTeacher(StreamTool streamTool) throws Exception {
    int memberNo = streamTool.promptInt("강사번호? ");

    Teacher old = this.teacherDao.findByNo(memberNo);

    if (old == null) {
      streamTool.println("해당 번호의 강사가 없습니다.");
      return;
    }

    // 변경할 데이터를 저장할 인스턴스 준비
    Teacher t = new Teacher();
    t.setNo(old.getNo());
    t.setCreatedDate(old.getCreatedDate());
    t.setName(streamTool.promptString(String.format("이름(%s)? ", old.getName())));
    t.setTel(streamTool.promptString(String.format("전화(%s)? ", old.getTel())));
    t.setEmail(streamTool.promptString(String.format("이메일(%s)? ", old.getEmail())));
    t.setDegree(streamTool.promptInt(String.format(
        "1. 고졸\n2. 전문학사\n3. 학사\n4. 석사\n5. 박사\n0. 기타\n학위(%s)? ",
        getDegreeText(old.getDegree()))));
    t.setSchool(streamTool.promptString(String.format("학교(%s)? ", old.getSchool())));
    t.setMajor(streamTool.promptString(String.format("전공(%s)? ", old.getMajor())));
    t.setWage(streamTool.promptInt(String.format("강의료(시급)(%s)? ", old.getWage())));

    String str = streamTool.promptString("정말 변경하시겠습니까?(y/N) ");
    if (str.equalsIgnoreCase("Y")) {
      con.setAutoCommit(false);
      try {
    	memberDao.update(t);
    	teacherDao.update(t);
    	con.commit();
    	streamTool.println("변경했습니다.");
      } catch (Exception e) {
    	con.rollback();
    	streamTool.println("변경 실패했습니다!");
    	e.printStackTrace();
      } finally {
    	con.setAutoCommit(true);
      }
    } else {
      streamTool.println("변경 취소했습니다.");
    }
    streamTool.send();
  }

  private void deleteTeacher(StreamTool streamTool) throws Exception {
    int memberNo = streamTool.promptInt("강사번호? ");

    Teacher t = this.teacherDao.findByNo(memberNo);

    if (t == null) {
      streamTool.println("해당 번호의 강사가 없습니다.").send();
      return;
    }

    String str = streamTool.promptString("정말 삭제하시겠습니까?(y/N) ");
    if (!str.equalsIgnoreCase("Y")) {
      streamTool.println("삭제 취소했습니다.").send();
      return;
    }
    
    con.setAutoCommit(false);
    try {
      teacherDao.delete(memberNo);
      memberDao.delete(memberNo);
      con.commit();
      streamTool.println("삭제했습니다.").send();
    } catch (Exception e) {
      con.rollback();
      streamTool.println("삭제 실패했습니다.").send();
    } finally {
      con.setAutoCommit(true);
    }
  }
  
  private void searchMember(StreamTool streamTool) throws Exception {
    String keyword = streamTool.promptString("검색어? ");
    
    List<Teacher> teachers = this.teacherDao.findByKeyword(keyword);
    
    streamTool.println("번호\t이름\t전화\t학위\t전공\t시강료");
    for (Teacher t : teachers) {
      streamTool.printf("%d\t%s\t%s\t%s\t%s\t%d\n",
        t.getNo(), t.getName(), t.getTel(),
        getDegreeText(t.getDegree()), t.getMajor(), t.getWage());
    }
    streamTool.send();
  }

  public void service(StreamTool streamTool) throws Exception {
    menu(streamTool);

    while (true) {

      String command = streamTool.readString();
      if (command.equals("menu")) {
        menu(streamTool);
        continue;
      }

      int menuNo;
      try {
        menuNo = Integer.parseInt(command);
      } catch (Exception e) {
        streamTool.println("메뉴 번호가 옳지 않습니다!").println().send();
        continue;
      }

      try {
        switch (menuNo) {
          case 0:
            streamTool.println("메인화면으로 이동!").send();
            return;
          case 1: this.inputTeacher(streamTool); break;
          case 2: this.printTeachers(streamTool); break;
          case 3: this.printTeacher(streamTool); break;
          case 4: this.modifyTeacher(streamTool); break;
          case 5: this.deleteTeacher(streamTool); break;
          case 6: this.searchMember(streamTool); break;
          default:
            streamTool.println("잘못된 메뉴 번호 입니다.").send();
        }
      } catch (Exception e) {
        streamTool.printf("명령 실행 중 오류 발생! - %s : %s\n",
            e.getMessage(),
            e.getClass().getSimpleName()).send();
      }
    }
  }

  void menu(StreamTool streamTool) throws Exception {
    streamTool.printf("[%s]\n", this.title)
    .println("1. 등록")
    .println("2. 목록")
    .println("3. 조회")
    .println("4. 변경")
    .println("5. 삭제")
    .println("6. 검색")
    .println("0. 이전")
    .send();
  }
}
