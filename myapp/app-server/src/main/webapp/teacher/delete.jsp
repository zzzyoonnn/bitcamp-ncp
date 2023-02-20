<%@page import="bitcamp.myapp.dao.MemberDao"%>
<%@page import="bitcamp.util.TransactionManager"%>
<%@page import="bitcamp.myapp.vo.Teacher"%>
<%@page import="java.util.List"%>
<%@page import="bitcamp.myapp.dao.TeacherDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%! 
  private TransactionManager txManager;
  private MemberDao memberDao;
  private TeacherDao teacherDao;

  @Override
  public void init() {
    ServletContext ctx = getServletContext();
    txManager = (TransactionManager) ctx.getAttribute("txManager");
    memberDao = (MemberDao) ctx.getAttribute("memberDao");
    teacherDao = (TeacherDao) ctx.getAttribute("teacherDao");
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
%>

<% 
    int teacherNo = Integer.parseInt(request.getParameter("no"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>비트캠프 - NCP 1기</title>
</head>
<body>
<h1>강사(JSP)</h1>

<% 
    txManager.startTransaction();
    try {
      if (teacherDao.delete(teacherNo) == 1 &&
          memberDao.delete(teacherNo) == 1) {
        txManager.commit();
%>
        
    <p>삭제했습니다.</p>

<% 
      } else {
%>
    	  
    <p>해당 번호의 회원이 없습니다.</p>
    
<% 
      }
    } catch (Exception e) {
      txManager.rollback();
%>

  <p>삭제 실패입니다.</p>
  
<% 
      e.printStackTrace();
    }
%>

</body>
</html>

<% 
    response.setHeader("Refresh", "1;url=list.jsp");
%>

