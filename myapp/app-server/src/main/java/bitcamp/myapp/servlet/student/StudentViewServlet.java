package bitcamp.myapp.servlet.student;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

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
import bitcamp.util.TransactionManager;


@WebServlet("student/view")
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

	int studentNo = Integer.parseInt(request.getParameter("no"));
	  
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
	
	Student m = this.studentDao.findByNo(studentNo);
	
	if (m == null) {
	  out.println("<p>해당 번호의 학생이 없습니다.</p>");
	} else {
	  out.println("<form id='student-form' action='update' method='post'");
	  out.println("<table border='1'>");
	  
	  out.println("<tr>");
	  out.println("  <th>이름</th>");
	  out.printf("  <td><input type='text' name='name' value='%s'></td>\n", m.getName());
	  out.println("</tr>");
	
	  out.println("<tr>");
	  out.println("  <th>이메일</th>");
	  out.printf("  <td><input type='email' name='email'></td>", m.getEmail());
	  out.println("</tr>");
	
	  out.println("<tr>");
	  out.println("  <th>전화</th>");
	  out.printf("  <td><input type='tel' name='tel'></td>", m.getTel());
	  out.println("</tr>");
	
	  out.println("<tr>");
	  out.println("  <th>우편번호</th>");
	  out.printf("  <td><input type='text' name='postNo'></td>", m.getPostNo());
	  out.println("</tr>");
	
	  out.println("<tr>");
	  out.println("  <th>기본주소</th>");
	  out.printf("  <td><input type='text' name='basicAddress'></td>", m.getBasicAddress());
	  out.println("</tr>");
	
	  out.println("<tr>");
	  out.println("  <th>상세주소</th>");
	  out.printf("  <td><input type='text' name='detailAddress'></td>", m.getDetailAddress());
	  out.println("</tr>");
	
	  out.println("<tr>");
	  out.println("  <th>재직여부</th>");
	  out.printf("  <td><input type='text' name='working'></td>", m.isWorking() ? "예" : "아니오");
	  out.println("</tr>");
	
	  out.println("<tr>");
	  out.println("  <th>성별</th>");
	  out.printf("  <td><input type='text' name='gender'></td>", m.getGender() == 'M' ? "남자" : "여자");
	  out.println("</tr>");
	
	  out.println("<tr>");
	  out.println("  <th>전공</th>");
	  out.printf("  <td><input type='text' name='level'></td>", getLevelText(m.getLevel()));
	  out.println("</tr>");
	
	  out.println("<tr>");
	  out.println("  <th>등록일</th>");
	  out.printf("  <td><input type='text' name='createdDate'></td>", m.getCreatedDate());
	  out.println("</tr>");
	
      out.println("</table>");
      out.println("<div>");
      out.println("<button>등록</button>");
      out.println("<button id='btn-cancel' type='button'>취소</button>");
      out.println("</div>");
      out.println("</form>");
    
      out.println("<script>");
      out.println("document.querySelector('#btn-list').onclick = function() {");
      out.println("  location.href = 'list';");
      out.println("}");
      out.println("document.querySelector('#btn-delete').onclick = function() {");
      out.println("  var form = document.querySelector('#student-form');");
      out.println("  form.action = 'delete';");
      out.println("  form.submit();");
      out.println("}");
      out.println("</script>");
	   
      out.println("</body>");
      out.println("</html>");
    }
  }
	
  private static String getLevelText(int level) {
    switch (level) {
      case 0: return "비전공자";
      case 1: return "준전공자";
      default: return "전공자";
    }
  }
}











