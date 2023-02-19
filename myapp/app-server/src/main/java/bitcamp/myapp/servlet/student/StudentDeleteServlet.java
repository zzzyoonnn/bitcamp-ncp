package bitcamp.myapp.servlet.student;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Stream;

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
import bitcamp.util.StreamTool;

@WebServlet("/student/delete")
public class StudentDeleteServlet extends HttpServlet {

  private TransactionManager txManager;
  private MemberDao memberDao;
  private StudentDao studentDao;

  public StudentDeleteServlet() {
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
	
	int memberNo = Integer.parseInt(request.getParameter("no"));
	String password = request.getParameter("password");
	
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
		
	Student m = this.studentDao.findByNo(memberNo);

    if (m == null) {
      out.println("<p>해당 번호의 회원이 없습니다.</p>");
    }
/*
    String str = out.println("정말 삭제하시겠습니까?(y/N) ");
    if (!str.equalsIgnoreCase("Y")) {
      out.println("삭제 취소했습니다.");
      return;
    }
*/
    txManager.startTransaction();
    try {
      studentDao.delete(memberNo);
      memberDao.delete(memberNo);
      txManager.commit();
      out.println("삭제했습니다.");

    } catch (Exception e) {
      txManager.rollback();
      out.println("삭제 실패했습니다.");
    }
    
    
  }

 
}











