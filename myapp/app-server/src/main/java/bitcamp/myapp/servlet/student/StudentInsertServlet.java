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

@WebServlet("/student/insert")
public class StudentInsertServlet extends HttpServlet {

  private TransactionManager txManager;
  private MemberDao memberDao;
  private StudentDao studentDao;

  public StudentInsertServlet() {
    try {
	  InputStream mybatisConfigInputStream = Resources.getResourceAsStream(
	      "bitcamp/myapp/config/mybatis-config.xml");
	  SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	  BitcampSqlSessionFactory sqlSessionFactory = new BitcampSqlSessionFactory(
	      builder.build(mybatisConfigInputStream));
	  memberDao = new DaoGenerator(sqlSessionFactory).getObject(MemberDao.class);
	  studentDao = new DaoGenerator(sqlSessionFactory).getObject(StudentDao.class);
	  txManager = new TransactionManager(sqlSessionFactory);
	} catch (Exception e) {
	  e.printStackTrace();
	}
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	
	Student student = new Student();
	student.setEmail("name");
	student.setPassword("name");
	student.setTel("name");
	student.setPostNo("name");
	student.setBasicAddress("name");
	student.setDetailAddress("name");
	  
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
	
	out.println("<div><a href='form'>학생 등록</a></div>");
	
	out.println("<table border='1'>");
	out.println("<tr>");
	out.println("  <th>번호</th> <th>이름</th> <th>전화</th> <th>재직</th> <th>전공</th>");
	out.println("</tr>");
	
    List<Student> members = this.studentDao.findAll();
    for (Student s : members) {
      out.println("  <tr>");
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

  // 인스턴스 멤버(필드나 메서드)를 사용하지 않기 때문에
  // 그냥 스태틱 메서드로 두어라!
  private static String getLevelText(int level) {
    switch (level) {
      case 0: return "비전공자";
      case 1: return "준전공자";
      default: return "전공자";
    }
  }
}











