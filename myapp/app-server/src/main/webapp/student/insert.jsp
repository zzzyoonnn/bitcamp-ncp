<%@page import="bitcamp.myapp.dao.MemberDao"%>
<%@page import="bitcamp.util.TransactionManager"%>
<%@page import="bitcamp.myapp.vo.Student"%>
<%@page import="java.util.List"%>
<%@page import="bitcamp.myapp.dao.StudentDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">

<%!
  private TransactionManager txManager;
  private MemberDao memberDao;
  private StudentDao studentDao;

  @Override
  public void init() {
    ServletContext ctx = getServletContext();
    txManager = (TransactionManager) ctx.getAttribute("txManager");
    memberDao = (MemberDao) ctx.getAttribute("memberDao");
    studentDao = (StudentDao) ctx.getAttribute("studentDao");
  }
  
  private static String getLevelText(int level) {
    switch (level) {
      case 0: return "비전공자";
      case 1: return "준전공자";
      default: return "전공자";
    }
  }
%>

<% 
    Student student = new Student();
    student.setName(request.getParameter("name"));
    student.setEmail(request.getParameter("email"));
    student.setPassword(request.getParameter("password"));
    student.setTel(request.getParameter("tel"));
    student.setPostNo(request.getParameter("postNo"));
    student.setBasicAddress(request.getParameter("basicAddress"));
    student.setDetailAddress(request.getParameter("detailAddress"));
    student.setWorking(request.getParameter("working") != null);
    student.setGender(request.getParameter("gender").charAt(0));
    student.setLevel(Byte.parseByte(request.getParameter("level")));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<meta http-equiv='Refresh' content='1;url=list'>
<title>비트캠프 - NCP 1기</title>
</head>
<body>
<h1>학생(JSP)</h1>

<% 
    txManager.startTransaction();
    try {
      memberDao.insert(student);
      studentDao.insert(student);
      txManager.commit();
%>
      
  <p>입력 했습니다.</p>

<% 
    } catch (Exception e) {
      txManager.rollback();
%>
      
  <p>입력 실패입니다.</p>
  
<% 
      e.printStackTrace();
    }
%>

</body>
</html>

