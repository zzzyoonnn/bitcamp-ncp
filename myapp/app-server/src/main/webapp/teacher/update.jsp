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
    Teacher teacher = new Teacher();
    teacher.setNo(Integer.parseInt(request.getParameter("no")));
    teacher.setName(request.getParameter("name"));
    teacher.setEmail(request.getParameter("email"));
    teacher.setPassword(request.getParameter("password"));
    teacher.setTel(request.getParameter("tel"));
    teacher.setDegree(Integer.parseInt(request.getParameter("degree")));
    teacher.setSchool(request.getParameter("school"));
    teacher.setMajor(request.getParameter("major"));
    teacher.setWage(Integer.parseInt(request.getParameter("wage")));
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
      if (memberDao.update(teacher) == 1 &&
          teacherDao.update(teacher) == 1) {
        txManager.commit();
%>
 
    <p>�����߽��ϴ�.</p>

<% 
      } else {
%>

    <p>�ش� ��ȣ�� ���簡 �����ϴ�.</p>
    
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