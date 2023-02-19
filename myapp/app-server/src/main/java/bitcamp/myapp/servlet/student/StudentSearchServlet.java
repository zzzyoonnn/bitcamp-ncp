package bitcamp.myapp.servlet.student;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.StudentDao;
import bitcamp.myapp.vo.Student;
import bitcamp.util.BitcampSqlSessionFactory;
import bitcamp.util.DaoGenerator;
import bitcamp.util.TransactionManager;

@WebServlet("/student/search")
public class StudentSearchServlet {

  private TransactionManager txManager;
  private MemberDao memberDao;
  private StudentDao studentDao;

  public StudentSearchServlet() {
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


  protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
		
	out.println("<div><a href='form'>학생 등록</a></div>");
		
	out.println("<table border='1'>");
	out.println("<tr>");
	out.println("  <th>번호</th> <th>이름</th> <th>전화</th> <th>재직</th> <th>전공</th>");
	out.println("</tr>");
		
    List<Student> members = this.studentDao.findAll();
    for (Student m : members) {
      out.println("  <tr>");
      out.printf("  <td>%d</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td>\n",
       		m.getNo(), m.getName(), m.getTel(),
               m.isWorking() ? "예" : "아니오",
                    getLevelText(m.getLevel()));
      out.println("</tr>");
    }
    out.println("</table>");
	    
    out.println("</body>");
    out.println("</html>");
  }
  
  private static String getLevelText(int level) {
    switch (level) {
      case 0: return "비전공자";
      case 1: return "준전공자";
      default: return "전공자";
    }
  }
}











