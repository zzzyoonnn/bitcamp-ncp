package bitcamp.myapp.servlet.student;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.StudentDao;
import bitcamp.myapp.vo.Student;
import bitcamp.util.BitcampSqlSessionFactory;
import bitcamp.util.DaoGenerator;
import bitcamp.util.StreamTool;
import bitcamp.util.TransactionManager;

@WebServlet("/student")
public class StudentViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private TransactionManager txManager;
  private MemberDao memberDao;
  private StudentDao studentDao;

  public StudentViewServlet() {
	try {
	  InputStream mybatisConfigInputStream = Resources.getResourceAsStream(
		  "bitcamp/myapp/config/mybatis-config.xml");

	  SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

	  BitcampSqlSessionFactory sqlSessionFactory = new BitcampSqlSessionFactory(
	      builder.build(mybatisConfigInputStream));

	  txManager = new TransactionManager(sqlSessionFactory);
	  memberDao = new DaoGenerator(sqlSessionFactory).getObject(MemberDao.class);
	  studentDao = new DaoGenerator(sqlSessionFactory).getObject(StudentDao.class);
	  
	} catch (Exception e) {
	  e.printStackTrace();
	}
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
	String menu = request.getParameter("menu");
	if (menu == null) {
	  menu(request, response);
	}
	
	switch (menu) {
	  case "2":
		printMembers(request, response);
		break;
	}
  }

  private void inputMember(StreamTool streamTool) throws Exception {
    Student s = new Student();
    s.setName(streamTool.promptString("이름? "));
    s.setEmail(streamTool.promptString("이메일? "));
    s.setPassword(streamTool.promptString("암호? "));
    s.setTel(streamTool.promptString("전화? "));
    s.setPostNo(streamTool.promptString("우편번호? "));
    s.setBasicAddress(streamTool.promptString("주소1? "));
    s.setDetailAddress(streamTool.promptString("주소2? "));
    s.setWorking(streamTool.promptInt("0. 미취업\n1. 재직중\n재직자? ") == 1);
    s.setGender(streamTool.promptInt("0. 남자\n1. 여자\n성별? ") == 0 ? 'M' : 'W');
    s.setLevel((byte) streamTool.promptInt("0. 비전공자\n1. 준전공자\n2. 전공자\n전공? "));

    txManager.startTransaction();
    try {
      memberDao.insert(s);
      studentDao.insert(s);
      txManager.commit();
      streamTool.println("입력했습니다!").send();

    } catch (Exception e) {
      txManager.rollback();
      streamTool.println("입력 실패입니다!").send();
      e.printStackTrace();
    }
  }

  private void printMembers(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	out.println("<!DOCTYPE html>");
	out.println("<html>");
	out.println("<head>");
	out.println("<meta charset='UTF-8'>");
	out.println("<title>비트캠프 - NCP 1기</title>");
	out.println("</head>");
	out.println("<body>");
	out.println("<h1>학생 목록</h1>");
	out.println("<ul>");
	out.println("<table boarder='1'>");
	
	out.println("<div><a href='form'>학생 등록</a></div>");
	
	out.println("<tr>");
	out.println("  <th>번호</th> <th>이름</th> <th>전화</th> <th>재직</th> <th>전공</th>");
	out.println("</tr>");
	
	List<Student> members = this.studentDao.findAll();
    for (Student s : members) {
      out.println("<tr>");
	  out.printf("  <td>%d</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td>\n",
		  s.getNo(), s.getName(), s.getTel(),
		  s.isWorking() ? "예" : "아니오",
	      getLevelText(s.getLevel()));
	  out.println("</tr>");
    }
	out.println("</table>");
	
	out.println("</body>");
	out.println("</html>");
  }

  private void printMember(StreamTool streamTool) throws Exception {
    int memberNo = streamTool.promptInt("회원번호? ");

    Student m = this.studentDao.findByNo(memberNo);

    if (m == null) {
      streamTool.println("해당 번호의 학생이 없습니다.").send();
      return;
    }

    streamTool
    .printf("    이름: %s\n", m.getName())
    .printf("  이메일: %s\n", m.getEmail())
    .printf("    전화: %s\n", m.getTel())
    .printf("우편번호: %s\n", m.getPostNo())
    .printf("기본주소: %s\n", m.getBasicAddress())
    .printf("상세주소: %s\n", m.getDetailAddress())
    .printf("재직여부: %s\n", m.isWorking() ? "예" : "아니오")
    .printf("    성별: %s\n", m.getGender() == 'M' ? "남자" : "여자")
    .printf("    전공: %s\n", getLevelText(m.getLevel()))
    .printf("  등록일: %s\n", m.getCreatedDate())
    .send();

  }

  // 인스턴스 멤버(필드나 메서드)를 사용하지 않기 때문에
  // 그냥 스태틱 메서드로 두어라!
  private static String getLevelText(int level) {
    switch (level) {
      case 0: return "비전공자";
      case 1: return "준전공자";
      default: return "전공자";
    }
  }

  private void modifyMember(StreamTool streamTool) throws Exception {
    int memberNo = streamTool.promptInt("회원번호? ");

    Student old = this.studentDao.findByNo(memberNo);

    if (old == null) {
      streamTool.println("해당 번호의 회원이 없습니다.").send();
      return;
    }

    // 변경할 데이터를 저장할 인스턴스 준비
    Student m = new Student();
    m.setNo(old.getNo());
    m.setCreatedDate(old.getCreatedDate());
    m.setName(streamTool.promptString(String.format("이름(%s)? ", old.getName())));
    m.setEmail(streamTool.promptString(String.format("이메일(%s)? ", old.getEmail())));
    m.setPassword(streamTool.promptString("암호? "));
    m.setTel(streamTool.promptString(String.format("전화(%s)? ", old.getTel())));
    m.setPostNo(streamTool.promptString(String.format("우편번호(%s)? ", old.getPostNo())));
    m.setBasicAddress(streamTool.promptString(String.format("기본주소(%s)? ", old.getBasicAddress())));
    m.setDetailAddress(streamTool.promptString(String.format("상세주소(%s)? ", old.getDetailAddress())));
    m.setWorking(streamTool.promptInt(String.format(
        "0. 미취업\n1. 재직중\n재직여부(%s)? ",
        old.isWorking() ? "재직중" : "미취업")) == 1);
    m.setGender(streamTool.promptInt(String.format(
        "0. 남자\n1. 여자\n성별(%s)? ",
        old.getGender() == 'M' ? "남자" : "여자")) == 0 ? 'M' : 'W');
    m.setLevel((byte) streamTool.promptInt(String.format(
        "0. 비전공자\n1. 준전공자\n2. 전공자\n전공(%s)? ",
        getLevelText(old.getLevel()))));

    String str = streamTool.promptString("정말 변경하시겠습니까?(y/N) ");
    if (str.equalsIgnoreCase("Y")) {
      txManager.startTransaction();
      try {
        memberDao.update(m);
        studentDao.update(m);
        txManager.commit();
        streamTool.println("변경했습니다.");

      } catch (Exception e) {
        txManager.rollback();
        streamTool.println("변경 실패했습니다!");
        e.printStackTrace();
      }
    } else {
      streamTool.println("변경 취소했습니다.");
    }
    streamTool.send();
  }

  private void deleteMember(StreamTool streamTool) throws Exception {
    int memberNo = streamTool.promptInt("회원번호? ");

    Student m = this.studentDao.findByNo(memberNo);

    if (m == null) {
      streamTool.println("해당 번호의 회원이 없습니다.").send();
      return;
    }

    String str = streamTool.promptString("정말 삭제하시겠습니까?(y/N) ");
    if (!str.equalsIgnoreCase("Y")) {
      streamTool.println("삭제 취소했습니다.").send();
      return;
    }

    txManager.startTransaction();
    try {
      studentDao.delete(memberNo);
      memberDao.delete(memberNo);
      txManager.commit();
      streamTool.println("삭제했습니다.").send();

    } catch (Exception e) {
      txManager.rollback();
      streamTool.println("삭제 실패했습니다.").send();
    }
  }

  private void searchMember(StreamTool streamTool) throws Exception {
    String keyword = streamTool.promptString("검색어? ");

    List<Student> students = this.studentDao.findByKeyword(keyword);

    streamTool.println("번호\t이름\t전화\t재직\t전공");
    for (Student m : students) {
      streamTool.printf("%d\t%s\t%s\t%s\t%s\n",
          m.getNo(), m.getName(), m.getTel(),
          m.isWorking() ? "예" : "아니오",
              getLevelText(m.getLevel()));
    }
    streamTool.send();
  }

  

  void menu(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();  
	
	out.println("<!DOCTYPE html>");
	out.println("<html>");
	out.println("<head>");
	out.println("<meta charset='UTF-8'>");
	out.println("<title>비트캠프 - NCP 1기</title>");
	out.println("</head>");
	out.println("<body>");
	out.println("<h1>학생</h1>");
	out.println("<ul>");
	out.println("  <li><a href='student?menu=1'>등록</a></li>");
	out.println("  <li><a href='student?menu=2'>목록</a></li>");
	out.println("  <li><a href='student?menu=3'>조회</a></li>");
	out.println("  <li><a href='student?menu=4'>변경</a></li>");
	out.println("  <li><a href='student?menu=5'>삭제</a></li>");
	out.println("  <li><a href='student?menu=6'>검색</a></li>");
	out.println("</ul>");
	out.println("</body>");
	out.println("</html>");
  }
}











