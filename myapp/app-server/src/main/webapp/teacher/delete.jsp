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
      case 1: return "����";
      case 2: return "�����л�";
      case 3: return "�л�";
      case 4: return "����";
      case 5: return "�ڻ�";
      default: return "��Ÿ";
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
<title>��Ʈķ�� - NCP 1��</title>
</head>
<body>
<h1>����(JSP)</h1>

<% 
    txManager.startTransaction();
    try {
      if (teacherDao.delete(teacherNo) == 1 &&
          memberDao.delete(teacherNo) == 1) {
        txManager.commit();
%>
        
    <p>�����߽��ϴ�.</p>

<% 
      } else {
%>
    	  
    <p>�ش� ��ȣ�� ȸ���� �����ϴ�.</p>
    
<% 
      }
    } catch (Exception e) {
      txManager.rollback();
%>

  <p>���� �����Դϴ�.</p>
  
<% 
      e.printStackTrace();
    }
%>

</body>
</html>

<% 
    response.setHeader("Refresh", "1;url=list.jsp");
%>

